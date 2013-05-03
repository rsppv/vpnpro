

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import compute.AgentRegister;
import compute.Compute;
import compute.RmiStarter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author aipova
 */
public class StartCommandCenterServlet extends HttpServlet {
    CommandCenter center = new CommandCenter();
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            
            RmiStarter.startRmi(FindSum.class);
            try {
                AgentRegister stub = (AgentRegister) UnicastRemoteObject.exportObject(
                    center, 0);
                Registry registry = LocateRegistry.createRegistry(1099);
                registry.bind("AgentRegister", stub);

            } catch (Exception e) {
                System.err.println("Command Center exception:");
                e.printStackTrace();
            }
            
            this.vaitForAgents();

//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Compute</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println(
//                "<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        } finally {
            out.close();
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

    private void vaitForAgents() {
        int clientCount = center.getAgentsCount();
        while (true) {
            try {
               
 
                new Thread(new Runnable () {
                   public void run () {
                       
                   }
                }).start();
 
            } catch (Exception e) {
 
            }
        }
    }
}
