/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commandcenter;

import compute.Compute;
import compute.RmiStarter;
import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author aipova
 */
public class CommandCenter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RmiStarter.startRmi(FindSum.class);
        try {
            String name = "Compute";
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");
            Compute comp = (Compute) registry.lookup(name);
            FindSum task = new FindSum(4, 8);
            Double result = comp.executeTask(task);
            System.out.println(result);
        } catch (Exception e) {
            System.err.println("Command Center exception:");
            e.printStackTrace();
        }
    }
}
