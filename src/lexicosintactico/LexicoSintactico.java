package lexicosintactico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LexicoSintactico {

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/datos/Cadena.txt"));
        String line;
        String zote = "";
        while((line = br.readLine()) != null){
            zote += line;
        }
        
        Analizador a = new Analizador(zote);
        a.comenzar();
        
    }
    
}
