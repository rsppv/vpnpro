/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commandcenter;

import bsh.EvalError;
import bsh.Interpreter;
import compute.Task;
import java.io.Serializable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aipova
 */
public class MonteCarlo implements Task, Serializable {

    private static final long serialVersionUID = 227L;
    String function;
    double a, b;
    int dots;

    public MonteCarlo(String function, double a, double b, int dots) {
        this.a = a;
        this.b = b;
        this.function = function;
        this.dots = dots;
    }

    public MonteCarlo(String function, double a, double b) {
        this.a = a;
        this.b = b;
        this.dots = 10000000;
    }

    public MonteCarlo() {
    }

    @Override
    public Double execute() {
        System.out.println("Task recieved!");
        System.out.println("Executing task...");

        Interpreter interpreter = new Interpreter();
        Random rand = new Random();
        Double result = 0.0;
        for (int i = 0; i < dots; i++) {
            double x = rand.nextDouble() * (b - a) + a;
            try {
                interpreter.set("x", x);
                interpreter.eval("result=" + function + ";");
                result += (Double) interpreter.get("result");
            } catch (EvalError ex) {
                System.out.println(ex.getMessage());
            }

        }
        result /= dots;
        result *= (b - a);
        System.out.println("Task complete!");
        return result;

    }
}
