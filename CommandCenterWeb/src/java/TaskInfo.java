
import java.util.List;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author aipova
 */
public class TaskInfo {
    String type;

    String function;
    double a, b;
    int dots;
    Double result;
    List<AgentInfo> agents;
    
    String hash;
    String alphabet;

    public String getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(String alphabet) {
        this.alphabet = alphabet;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    
    int agentsCount;
    public int getAgentsCount() {
        return agents.size();
    }

    public Double getResult() {
        return result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setResult(Double result) {
        this.result = result;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public List<AgentInfo> getAgents() {
        return agents;
    }

    public void setAgents(List<AgentInfo> agents) {
        this.agents = agents;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public int getDots() {
        return dots;
    }

    public void setDots(int dots) {
        this.dots = dots;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public TaskInfo(MonteCarlo task, List<AgentInfo> agents, Double result) {
        this.type = "mc";
        this.function = task.function;
        this.a = task.a;
        this.b = task.b;
        this.dots = task.dots * agents.size();
        this.agents = agents;
        this.result = result;
    }
    
    public TaskInfo(PasswordHash task, List<AgentInfo> agents) {
        this.type = "hash";
        this.alphabet = task.alphabet;
        this.hash = task.hash;
        this.agents = agents;
    }
}
