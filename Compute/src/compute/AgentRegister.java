/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author aipova
 */
public interface AgentRegister extends Remote {
    // возвращает сгенерированный командным центром id агента
    Integer register(Compute stub, String ip) throws RemoteException;
    
    void unRegister(Integer id) throws RemoteException;
}
