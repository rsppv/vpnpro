/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import compute.Compute;
import compute.RmiStarter;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author aipova
 */
public class Agent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "agent.policy");
        RmiStarter.startRmi(Compute.class);
        try {
            //Runtime.getRuntime().exec("javaw rmiregistry -J-Djava.rmi.useLocalHostName=true -J-Djava.rmi.server.hostname=127.0.0.1 ");
            String name = "Compute";
            Compute engine = new ComputeEngine();
            Compute stub =
                (Compute) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind(name, stub);
            System.out.println("Agent ready");
        } catch (Exception e) {
            System.err.println("Agent exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
