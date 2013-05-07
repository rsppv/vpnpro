

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import compute.AgentRegister;
import compute.RmiStarter;
import java.io.IOException;
import java.net.InetAddress;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
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

        try {
            // если это не первое обновление страницы то возникнет ошибка и не продолжать
            AgentRegister stub = (AgentRegister) UnicastRemoteObject.exportObject(
                center, 0);
            RmiStarter.startRmi(MonteCarlo.class);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("AgentRegister", stub);
            this.getServletConfig().getServletContext().setAttribute(
                "commandCenter", center);

        } catch (ExportException e) {
            System.err.println("ObjectExportet - page recall");
        } catch (Exception e) {
            System.err.println("Command Center exception:");
            e.printStackTrace();
        }
        
        String serverIP = InetAddress.getLocalHost().getHostAddress();
        request.getSession().setAttribute("serverIP", serverIP);
        request.getSession().setAttribute("agents", center.getAgents().values());
        request.getRequestDispatcher("startPage.jsp").forward(request,
            response);


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
