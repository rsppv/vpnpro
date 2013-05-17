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

    public static void startRmi(Class<?> classToAddInCodebase) throws UnknownHostException {

        /* Use follow string for jdk 1.7-update 21 and newer
         *  
         * System.setProperty("java.rmi.server.useCodebaseOnly", "false");
         */

        if (classToAddInCodebase != null) {
            System.setProperty("java.rmi.server.codebase", classToAddInCodebase
                    .getProtectionDomain().getCodeSource().getLocation().toString());

        }
        System.setProperty("java.security.policy", PolicyFileLocator.getLocationOfPolicyFile());

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
    }
}
