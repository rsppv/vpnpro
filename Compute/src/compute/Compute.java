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
public interface Compute extends Remote {
    Double executeTask(Task t) throws RemoteException;;
}
