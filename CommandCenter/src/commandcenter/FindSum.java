/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package commandcenter;

import compute.Task;
import java.io.Serializable;

/**
 *
 * @author aipova
 */
public class FindSum implements Task, Serializable {
    private static final long serialVersionUID = 227L; 
    
    double a, b;
     public FindSum(double a, double b) {
         this.a = a;
         this.b = b;
     }
     
     public FindSum() {
     }
     
    @Override
    public Double execute() {
        System.out.println("Получил задание...");
        Double c = a+b;
        System.out.println("Выполнил блабла!");
        return c;
    }
    
}
