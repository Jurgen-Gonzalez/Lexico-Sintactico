
package lexicosintactico;

/**
 *
 * @author Isass
 */

public class test_Pila {

    
    public static void main(String[] args) {
        
        Pila pila = new Pila();
        pila.push("1");
        pila.push("2");
        pila.push("3");
        pila.push("4");
        System.out.println(pila.peek());
        pila.pop();
        System.out.println(pila.peek());
        
        
    }
    
}
