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
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author aipova
 */
public class Agent {
  
    private static AgentRegister commandCenter;
    private static Integer id;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        System.setProperty("java.security.policy", "agent.policy");
        
        
        try {
            // TODO найти регистратор командного центра (ввод с консоли ip адреса)
            Scanner scan = new Scanner(System.in);
            System.out.println("Please, type the IP address of Command Center:\n");
            String centerIP = scan.next();
            RmiStarter.startRmi(centerIP);
            System.out.println("Please, wait. Connecting...\n");
            InetAddress inetAddress = InetAddress.getLocalHost();
            String agentIP = inetAddress.getHostAddress();
            //System.out.println("IP:"+inetAddress.getHostAddress());
            Runtime.getRuntime().exec("javaw rmiregistry -J-Djava.rmi.useLocalHostName=true -J-Djava.rmi.server.hostname=127.0.0.1 ");
            String name = "Compute";
            ComputeEngine engine = new ComputeEngine();
            Registry registry = LocateRegistry.getRegistry(centerIP, 1099);
            commandCenter = (AgentRegister) registry.lookup("AgentRegister");             
            Compute stub =
                (Compute) UnicastRemoteObject.exportObject(engine, 0);
            id = commandCenter.register(stub, agentIP); 
            System.out.println("Agent ready");
            
            while (true){
                if (scan.next().equals("close")) {
                    commandCenter.unRegister(id);
                    System.exit(0);
                }
            }
        } catch (Exception e) {
            System.err.println("Agent exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    



}
