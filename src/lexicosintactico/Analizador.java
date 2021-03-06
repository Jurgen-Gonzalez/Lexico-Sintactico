package lexicosintactico;

import datos.AnalizadorDeGramatica;

public class Analizador {
    private final int tabla[][] = {
            {  0,  1,  1,  1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
            {  0,  2,  2,  2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
            {  0,  3,  3,  3,  3,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
            {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  4,  4,  4,  4,  4,  4},
            {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  5,  5,  5,  5,  5,  5,  6,  6,  6,  6,  6,  6},
            {  0,  7,  8,  9, 10,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
            {  0,  0,  0,  0,  0, 11, 12, 13, 14, 15, 16, 17,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
            {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 18, 19, 20, 21, 22, 23,  0,  0,  0,  0,  0,  0},
            {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 24, 25, 26, 27, 28, 29}
        };
    private int i;
    private String zote;
    private AnalizadorDeGramatica ag; 
    
    public Analizador(String zote){
        this.zote = zote;
        i = 0;
        ag = new AnalizadorDeGramatica();
    }
    
    public void comenzar(){
        ag.analizar();
        LLDriver();
    }
    
    private String scanner() {
        int estado = 0;
        String lexema = "";

        while (i < zote.length()) {
            switch (estado){
                case 0:
                    if(zote.charAt(i) == ' '){
                        lexema = "";
                        i++;
                    }
                    else {
                        estado = 1;
                    }
                    break;
                case 1:
                    if(esAlfa(zote.charAt(i))){
                        lexema += zote.charAt(i);
                        i++;
                    }
                    else{
                        estado = 2;
                    }
                    break;
                case 2: 
                    if(isInArray(lexema, ag.getNoTerminales())){
                        return lexema;
                    }else if(isInArray(lexema, ag.getTerminales())){
                        return lexema;
                    }
                    else{
                        System.out.println("ERROR!!! (lexico) en el lexema = " +lexema);
                        System.exit(0);
                    }
                    break;

            }
        }
        return lexema;
    }
    
    private void LLDriver(){
        Pila pila = new Pila();
        
        pila.push(ag.getDerivaciones()[0]);
        String x = pila.peek();
        String a = scanner();
        
        while(!pila.empty()){
            System.out.println("x = "+x+ ", a = "+a);
            pila.showStack();
            if(isInArray(x, ag.getNoTerminales())){
                System.out.println(getRow(x)+", "+getColumn(a));
                if(tabla[getRow(x)][getColumn(a)] != 0){
                    pila.pop();
                    
                    String[] aux = ag.getDerivaciones()[tabla[getRow(x)][getColumn(a)]-1].split(" ");
                    for (int i = aux.length - 1; i >= 0; i--) {
                        if(aux[i] != "")
                            pila.push(aux[i]);
                    }
                }
                else {
                    System.out.println("ERROR!!!!! (de sintaxis) en x = "+x+" y a = "+a);
                    System.exit(0);
                }
            }
            else {
                if(x.equals(a)){
                    pila.pop();
                    a = scanner();
                }
                else {
                    System.out.println("ERROR!!!!! (de sintaxis) en x = "+x+" y a = "+a);
                    System.exit(0);
                }
            }
            x = pila.peek();
        }
        
        System.out.println("------------------------------------------");
        System.out.println("CADENA CORRECTA");
    }
    
    private int getRow(String x){
        String [] noTerminales = ag.getNoTerminales();
        for (int j = 0; j < noTerminales.length; j++) {
            if(noTerminales[j].equals(x))
                return j;
        }
        return 0; //imposible llegar aqui
    }
    
    private int getColumn(String a){
        String [] terminales = ag.getTerminales();
        for (int j = 0; j < terminales.length; j++) {
            if(terminales[j].equals(a))
                return j;
        }
        return 0;
    }
    
    private boolean esAlfa(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }
    
    private boolean isInArray(String word, String [] array){
        for (int i = 0; i < array.length; i++) {
            if(word.equals(array[i]))
                return true;
        }
        return false;
    }
}
