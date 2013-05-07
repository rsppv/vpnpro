/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import compute.Compute;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aipova
 */
public class SendTaskServlet extends HttpServlet {

    private Double fullResult = 0.0;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private synchronized void addToFullResult(Double a) {
        fullResult += a;
    }

    protected void processRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {

        
        try {

            final String func = request.getParameter("function");
            final Double ainterval = Double.parseDouble(request.getParameter(
                "ainterval"));
            final Double binterval = Double.parseDouble(request.getParameter(
                "binterval"));
            final Integer dots = Integer.parseInt(request.getParameter("dots"));
            // TODO проверка функции на interpreter
            System.out.println("Resieved params:");
            System.out.println(func);
            System.out.println(ainterval);
            System.out.println(binterval);
            String[] checkedAgentsId = request.getParameterValues("agentsCheckBox");
            System.out.println("agents selected:");
            // создаем список потоков чтобы потом ждать пока все закончатся
            List<Thread> threads = new ArrayList<Thread>();
            // берем объект коммандного центра
            CommandCenter commandCenter = (CommandCenter) this.getServletConfig().getServletContext().getAttribute(
                "commandCenter");
            // Создаем задание
            final MonteCarlo task = new MonteCarlo(func, ainterval, binterval, dots);
            // для каждогог агента
            for (String s : checkedAgentsId) {
                // находим его по id
                final AgentInfo agent = (AgentInfo) commandCenter.getAgents().get(Integer.parseInt(
                    s));
                agent.setFree(false);
                System.out.println("Agent" + agent);
                // создаем поток
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            // в потоке выполняем задание
                            Compute comp = agent.getStub();
                            Double result = comp.executeTask(task);
                            // добавляем полученный результат к общему
                            addToFullResult(result);
                        } catch (RemoteException e) {
                            System.out.println(
                                "Client disconnected or unknown error occured");
                        }
                    }
                });
                threads.add(thread);
                thread.start();
            }
            // Ждем пока выполнятся все потоки
            for (Thread t : threads) {
                t.join();
            }
            // создадим список агентов и пометим их опять свободными
            List<AgentInfo> checkedAgents = new ArrayList<AgentInfo>();
            for (String s : checkedAgentsId) {
                AgentInfo agent = (AgentInfo) commandCenter.getAgents().get(Integer.parseInt(
                    s));
                checkedAgents.add(agent);
                agent.setFree(true);

            }
            fullResult /= checkedAgentsId.length;
            // поместим в ответ информацию о выполненном задании
            request.getSession().setAttribute("task", new TaskInfo(task,
                checkedAgents, fullResult));
            System.out.println("RESULT = " + fullResult);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
