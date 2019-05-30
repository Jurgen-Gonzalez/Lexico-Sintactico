
package lexicosintactico;

/**
 *
 * @author Isass
 */
public class Pila {

    Nodo inicio;
    Nodo fin;

    public Pila() {
        inicio = fin = null;
    }

    public void push(String s) {
        Nodo nuevo = new Nodo();
        nuevo.dato = s;

        nuevo.siguiente = inicio;
        inicio = nuevo;

    }

    public void showStack() {
        Nodo actual;
        actual = inicio;
        if (inicio != null) {
            System.out.print("[");
            while (actual != null) {
                System.out.print(actual.dato);
                actual = actual.siguiente;
                if(actual != null) System.out.print(", ");
            }
            System.out.println("]");
        } else {
            System.out.print("La pila se encuentra vacía");
        }

    }

    public void searchItem(String s) {

        Nodo actual;
        actual = inicio;
        String datoBuscado;
        boolean encontrado = false;

        datoBuscado = s;
        if (inicio != null) {

            while (actual != null && encontrado == false) {
                if (actual.dato.equals(datoBuscado)) {
                    encontrado = true;
                }
                actual = actual.siguiente;

            }
            if (encontrado == false) {
                System.out.println("\nNo hay algún nodo con ese dato\n");
            }
        } else {
            System.out.print("\nLa pila se encuentra vacía\n");
        }

    }

    public void Eliminar(String s) {
        Nodo actual;
        actual = inicio;
        Nodo anterior = new Nodo();
        String datoBuscado;
        boolean encontrado = false;

        datoBuscado = s;
        if (inicio != null) {

            while (actual != null && encontrado == false) {
                if (actual.dato.equals(datoBuscado)) {

                    if (actual == inicio) {
                        inicio = inicio.siguiente;

                    } else if (actual == fin) {
                        anterior.siguiente = null;
                        fin = anterior;
                    } else {
                        anterior.siguiente = actual.siguiente;
                    }
                    encontrado = true;

                }
                anterior = actual;
                actual = actual.siguiente;

            }
            if (encontrado == false) {
                System.out.println("\nNo hay algún nodo con ese dato\n");
            }
        } else {
            System.out.print("\nLa pila se encuentra vacía\n");
        }

    }
    
    public boolean empty(){
        if(inicio == null)
            return true;
        else return false;
    }

    public String peek() {

        Nodo actual;
        actual = inicio;

        if (inicio != null) {
            return actual.dato;

        } else {
            System.out.print("\nLa pila se encuentra vacía\n");
        }
        return actual.dato;
    }
    
    public void pop(){
        
        Nodo actual;
        actual = inicio;
        
        inicio = actual.siguiente;
        actual.siguiente = null;
        
    }

}
