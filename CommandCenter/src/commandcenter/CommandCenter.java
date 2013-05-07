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
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aipova
 */
public class CommandCenter implements AgentRegister {

    private Map<Integer, AgentInfo> agents = new HashMap<Integer, AgentInfo>();
    Integer forAgentsId = 0;
    public static void main(String[] args) {
        CommandCenter center = new CommandCenter();
        RmiStarter.startRmi(MonteCarlo.class);
        try {
            AgentRegister stub = (AgentRegister) UnicastRemoteObject.exportObject(
                center, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("AgentRegister", stub);
            System.out.println("Command Center ready. Waiting for agents...");

        } catch (Exception e) {
            System.err.println("Command Center exception:");
            e.printStackTrace();
        }
    }

    public Integer register(Compute stub, String ip) {
        // создать AgentInfo и добавить в agents
        // и както отправить на страницу обновленное количество клиентов ?????
        AgentInfo newAgent = new AgentInfo(forAgentsId, stub, ip);
        forAgentsId++;
        System.out.println("Новый агент " + ip);
        agents.put(newAgent.getId(), newAgent);
        return newAgent.getId();
    }

    public void unRegister(Integer id) {
        System.out.println("Свалил агент " + agents.get(id).getIp());
        agents.remove(id);
    }
    
     public int getAgentsCount() {
        return agents.size();
    }
    
}
