/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compute;

/**
 *
 * @author aipova
 */
public interface AgentRegister {
    // возвращает сгенерированный командным центром id агента
    int register(Compute stub, String ip);
    
    void unRegister(int id);
}
