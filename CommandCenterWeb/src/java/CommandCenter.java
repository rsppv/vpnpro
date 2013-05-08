/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import compute.AgentRegister;
import compute.Compute;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aipova
 */
public class CommandCenter implements AgentRegister {

    private Map<Integer, AgentInfo> agents = new HashMap<Integer, AgentInfo>();
    Integer forAgentsId = 1;
    
    public int getAgentsCount() {
        return agents.size();
    }
    
    public Map getAgents() {
        return agents;
    }
    
    @Override
    public Integer register(Compute stub, String ip) {
        AgentInfo newAgent = new AgentInfo(forAgentsId, stub, ip);
        forAgentsId++;
        System.out.println("Новый агент " + ip);
        agents.put(newAgent.getId(), newAgent);
        return newAgent.getId();
    }

    @Override
    public void unRegister(Integer id) {
        System.out.println("Свалил агент " + agents.get(id).getIp());
        agents.remove(id);
    }
}
