/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compute;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author aipova
 */
public class RmiStarter {
    
    public static void startRmi(String serverIP) throws UnknownHostException {
        
        /* Use follow string for jdk 1.7-update 21 and newer
         *  
         * System.setProperty("java.rmi.server.useCodebaseOnly", "false");
         */
        
        System.setProperty("java.rmi.server.codebase", "file://" + serverIP + "/classes/");
        System.out.println(System.getProperty("java.rmi.server.codebase"));
        System.setProperty("java.security.policy", PolicyFileLocator.getLocationOfPolicyFile());

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }
    
}
