/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commandcenter;

import compute.AgentRegister;
import compute.Compute;
import compute.RmiStarter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aipova
 */
public class CommandCenter implements AgentRegister {
    private List<AgentInfo> agents = new ArrayList<AgentInfo>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RmiStarter.startRmi(FindSum.class);
        try {
            // TODO создать регистратор агентов
            
//            String name = "Compute";
//            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
//            Compute comp = (Compute) registry.lookup(name);
//            FindSum task = new FindSum(4, 8);
//            Double result = comp.executeTask(task);
//            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Command Center exception:");
            e.printStackTrace();
        }
    }

    public int register(Compute stub, String ip) {
        // создать AgentInfo и добавить в agents
        return 0;
    }

    public void unRegister(int id) {
       // убрать из agents
    }
}
