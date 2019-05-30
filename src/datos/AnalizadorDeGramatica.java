package datos;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
            int length = 0, derivacionesLength = 0;
            while(( line = bf.readLine()) != null){
                length++;
//                if(line.split("->").length > 1)
//                    derivacionesLength++;
            }
            
            bf = new BufferedReader(new FileReader("src/datos/Gramatica.txt"));
        
        
            producciones = new String[length];
            derivaciones = new String[length];//parte derecha
            String [] derivantes = new String[length]; //parte izquierda
            for(int i = 0;(line = bf.readLine()) != null; i++){
                producciones[i] = line;
                derivantes[i] = line.split("->")[0];
                if(line.split("->").length > 1){
                    derivaciones[i] = line.split("->")[1];
                }else derivaciones[i] = "";
            }
            
            Set<String> noTerminalesSet = new LinkedHashSet<String>();
            for (int i = 0; i < derivantes.length; i++) {
                noTerminalesSet.add(derivantes[i]);
            }
            
            int z =0;
            noTerminales = new String[noTerminalesSet.size()];
            for (String s : noTerminalesSet) {
                noTerminales[z] = s;
                z++;
            }
            
            Set<String> terminalesSet = new LinkedHashSet<String>();
            String [] aux;
            for (int i = 0; i < derivaciones.length; i++) {
                aux = derivaciones[i].split(" ");
                for (int j = 0; j < aux.length; j++) {
                    if(!isInArray(aux[j], noTerminales)){
                        terminalesSet.add(aux[j]);
                    }
                }

            }
            
            z =0;
            terminales = new String[terminalesSet.size()];
            for (String s : terminalesSet) {
                terminales[z] = s;
                z++;
            }
            
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
        return producciones;
    }
    
    public String[] getDerivaciones(){
        return derivaciones;
    }
    
    public String[] getTerminales(){
        return terminales;
    }
    
    public String[] getNoTerminales(){
        return noTerminales;
    }
    
}
