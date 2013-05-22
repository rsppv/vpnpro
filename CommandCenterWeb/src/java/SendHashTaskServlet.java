/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import bsh.EvalError;
import bsh.Interpreter;
import compute.Compute;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aipova
 */
public class SendHashTaskServlet extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request,
        HttpServletResponse response)
        throws ServletException, IOException {
        String sendTaskResp = "Задание выполнено!";
        int symbolCount=3;
        boolean error = false;
        try {
            String alphabet = "";
            final String hash = request.getParameter("hash");
            String[] checkedAlphabet = request.getParameterValues(
                "alphabetCheckBox");
            if (checkedAlphabet.length == 0) {
                alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
            } else {
                for (String s : checkedAlphabet) {
                    if (s.equals("abc"))
                        alphabet += "abcdefghijklmnopqrstuvwxyz";
                    if (s.equals("ABC"))
                        alphabet += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    if (s.equals("123"))
                        alphabet += "1234567890";
                }
            }
            String[] checkedSymCountt = request.getParameterValues(
                "symCountRadio");
            if (checkedAlphabet.length != 0) {

                for (String s : checkedSymCountt) {
                    if (s.equals("3"))
                        symbolCount = 3;
                    if (s.equals("4"))
                        symbolCount = 4;
                    if (s.equals("5"))
                        symbolCount = 5;
                }
            }
            String[] checkedAgentsId = request.getParameterValues(
                "agentsCheckBox");
            if (checkedAgentsId.length == 0)
                throw new Exception("Не выбран ни один агент!");
            System.out.println("agents selected:");

            // создаем список потоков чтобы потом ждать пока все закончатся
            List<Thread> threads = new ArrayList<Thread>();
            // берем объект коммандного центра
            CommandCenter commandCenter = (CommandCenter) this.getServletConfig().getServletContext().getAttribute(
                "commandCenter");
            // Создаем задание

            // делим на интервалы
            int start = 1;
            int stringsCount = (int) Math.pow(alphabet.length(), symbolCount);
            int interval = (int) (stringsCount / checkedAgentsId.length) + 1;
            // для каждого агента
            for (String s : checkedAgentsId) {
                // находим его по id
                final AgentInfo agent = (AgentInfo) commandCenter.getAgents().get(Integer.parseInt(
                    s));
                agent.setFree(false, "hash");
                final PasswordHash task = new PasswordHash(hash, alphabet, start,
                    start + interval, symbolCount);
                System.out.println(
                    "Start: " + task.getStartId() + " end " + task.getMaxId());
                System.out.println("Agent" + agent);
                // создаем поток
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            // в потоке выполняем задание
                            Compute comp = agent.getStub();
                            String result = comp.executeTask(task);
                            System.out.println("Result^ " + result);
                            if (result.isEmpty()) {
                                agent.setResult("Не найден");
                            } else {
                                agent.setResult(result);
                            }

                        } catch (RemoteException e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        }
                    }
                });
                threads.add(thread);
                thread.start();
                start += interval + 1;
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
            System.out.println("Agents count: " + checkedAgentsId.length);
            // поместим в ответ информацию о выполненном задании
            TaskInfo madeTask = new TaskInfo(new PasswordHash(hash, alphabet),
                checkedAgents);
            request.getSession().setAttribute("task", madeTask);
            // и поместим задание в общай список выполненных заданий
            List<TaskInfo> tasks = (List<TaskInfo>) request.getSession().getAttribute(
                "tasks");
            tasks.add(madeTask);
            // может и не надо опять устанавливать
            request.getSession().setAttribute("tasks", tasks);
        } catch (NumberFormatException ex) {
            sendTaskResp = "Неправильно введены данные в форму!";
            error = true;
            // ошибка в преобразовании данных из формы!
        } catch (NullPointerException ex) {
            sendTaskResp = "Нет агентов!";
            error = true;
        } catch (Exception ex) {
            // какято еще ошибка
            error = true;
            sendTaskResp = ex.getMessage();
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        request.getSession().setAttribute("sendTaskResp", sendTaskResp);
        request.getSession().setAttribute("error", error);
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
