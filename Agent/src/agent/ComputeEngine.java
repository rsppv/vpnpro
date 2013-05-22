/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import compute.Compute;
import compute.Task;

/**
 *
 * @author aipova
 */
public class ComputeEngine implements Compute {

    public ComputeEngine() {
        super();
    }

    @Override
    public <T> T executeTask(Task<T> t) {
        return t.execute();
    }
}
