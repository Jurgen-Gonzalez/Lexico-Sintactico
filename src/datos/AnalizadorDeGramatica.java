package datos;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AnalizadorDeGramatica {
    private String[] producciones;
    private String[] derivaciones;
    private String[] terminales;
    private String[] noTerminales;
    
    
    public void analizar(){
        try{
            BufferedReader bf = new BufferedReader(new FileReader("src/datos/Gramatica.txt"));
            String line;
            int length = 0;
            while(( bf.readLine()) != null){
                length++;
            }

            bf = new BufferedReader(new FileReader("src/datos/Gramatica.txt"));
        
        
            producciones = new String[length];
            derivaciones = new String[length];//parte derecha
            String [] derivantes = new String[length]; //parte izquierda
            for(int i = 0;(line = bf.readLine()) != null; i++){
                producciones[i] = line;
                derivantes[i] = line.split("->")[0];
                if(line.split("->").length > 1)
                    derivaciones[i] = line.split("->")[1];
                else derivaciones[i] = "";
            }
            Set<String> noTerminalesSet = new HashSet<String>();
            for (int i = 0; i < derivantes.length; i++) {
                noTerminalesSet.add(derivantes[i]);
            }
            noTerminales = noTerminalesSet.toArray(new String[noTerminalesSet.size()]);

            Set<String> terminalesSet = new HashSet<String>();
            String [] aux;
            for (int i = 0; i < derivaciones.length; i++) {
                if(derivaciones[i].contains(" ") && derivaciones[i].split(" ").length > 1)
                    aux = derivaciones[i].split(" ");
                else aux = new String[]{derivaciones[i]};
                for (int j = 0; j < aux.length; j++) {
                    if(!isInArray(aux[j], noTerminales) && aux[j] != "")
                        terminalesSet.add(aux[j]);
                }

            }
            terminales = terminalesSet.toArray(new String[terminalesSet.size()]);
            
        }catch(IOException e){}
        
    }
    
    private static boolean isInArray(String word, String [] array){
        for (int i = 0; i < array.length; i++) {
            if(word.equals(array[i]))
                return true;
        }
        return false;
    }
    
    public String[] getProducciones(){
        return terminales;
    }
    
    public String[] getDerivaciones(){
        return terminales;
    }
    
    public String[] getTerminales(){
        return terminales;
    }
    
    public String[] getNoTerminales(){
        return terminales;
    }
    
}
