/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import compute.AgentRegister;
import compute.Compute;
import compute.RmiStarter;
import compute.Task;
import java.net.InetAddress;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author aipova
 */
public class Agent implements Compute{
  
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "agent.policy");
        RmiStarter.startRmi(Compute.class);
        
        try {
            // TODO найти регистратор командного центра (ввод с консоли ip адреса)
            Scanner scan = new Scanner(System.in);
            System.out.println("Please, type the IP address of Command Center:\n");
            String centerIP = scan.next();
            System.out.println("Please, wait. Connecting...\n");
            InetAddress inetAddress = InetAddress.getLocalHost();
            String agentIP = inetAddress.getHostAddress();
            //System.out.println("IP:"+inetAddress.getHostAddress());
            //Runtime.getRuntime().exec("javaw rmiregistry -J-Djava.rmi.useLocalHostName=true -J-Djava.rmi.server.hostname=127.0.0.1 ");
            String name = "Compute";
            Agent engine = new Agent();
            Registry registry = LocateRegistry.getRegistry(centerIP, 1099);
            AgentRegister commandCenter = (AgentRegister) registry.lookup("AgentRegister");             
            Compute stub =
                (Compute) UnicastRemoteObject.exportObject(engine, 0);
            commandCenter.register(stub, agentIP); 
            //  вместо следующего создать себя и зарегать в командном центре                        
            //Registry registry = LocateRegistry.createRegistry(1099);
            //registry.bind(name, stub);
            System.out.println("Agent ready");
        } catch (Exception e) {
            System.err.println("Agent exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Double executeTask(Task t) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
