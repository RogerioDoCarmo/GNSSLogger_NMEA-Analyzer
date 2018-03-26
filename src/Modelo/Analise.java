package Modelo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rogerio
 */
public class Analise {

    int NUM_MEDICOES;
    ArrayList<String> medicoesNMEA;
    ArrayList<String> medicoesGPGGA;

    public Analise (){
        
    }
    
    public Analise(String caminhoArquivo) {
        this.medicoesNMEA = new ArrayList<>();
        this.medicoesGPGGA = new ArrayList<>();
        this.NUM_MEDICOES = 0;
        lerTXT(caminhoArquivo);
        extrairGPGGA();        
    }
    
    public void extrairGPGGA(){
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$GPGGA"))
                medicoesGPGGA.add(medicoesTemp);
        }
    }
    
    public ArrayList<String> extrairMedicoesGPGGA(){        
        return (this.medicoesGPGGA);
    }
    
    public ArrayList<String> extrairMedicoes(String tipoNMEA){
       ArrayList<String> medidas = new ArrayList<>();
       
       for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains(tipoNMEA))
                medidas.add(medicoesTemp);
        }   
       
       return medidas;
    }
        
    public void compararValores(String arquivoOriginal, String arquivoProcessado){
        String cvsSplitBy = ",";
        String line = "";
        
        ArrayList<Object[]> dadosOriginais = new ArrayList<>();
        ArrayList<Object[]> dadosProcessados = new ArrayList<>();
        ArrayList<String> comparacoes = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoOriginal))) {
            while ((line = br.readLine()) != null) {
                if (line.contains("$GPGGA,")) {
                    Object[] novaLinha = line.split(cvsSplitBy);
                    dadosOriginais.add(novaLinha);
                    //System.out.println(novaLinha);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try (BufferedReader br = new BufferedReader(new FileReader(arquivoProcessado))) {
            while ((line = br.readLine()) != null) {
                if (line.contains("$GPGGA,")) {
                    Object[] novaLinha = line.split(cvsSplitBy);
                    dadosProcessados.add(novaLinha);
                   // System.out.println(novaLinha);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //Comparação
        int i = 0;
        while(i < dadosOriginais.size()){
            //System.out.println(dadosOriginais.get(i)[2] + " --- " + dadosProcessados.get(i)[1]);
            i++;
        }
        System.out.println("\nForam comparadas " + i + " medições NMEA $GPGGA\n");
        
        //Comparando Latitudes
        String novaLinha = "";
        i = 0;
        while (i < dadosOriginais.size()) {
            
            try {
                float diferenca = 0f;
                novaLinha = "UTC: " + dadosOriginais.get(i)[2].toString();
                diferenca = 1800f * Float.valueOf(dadosOriginais.get(i)[3].toString())  -
                            1800f * Float.valueOf(dadosProcessados.get(i)[2].toString());
                //System.out.println("Diferença: " + diferenca);

                novaLinha += " Diferenca na latitude : " + diferenca + "\n";
            }catch (NumberFormatException ex){
                novaLinha += " Latitude não presente na solução pvt!\n";
            }

            comparacoes.add(novaLinha);
            i++;
        }
        novaLinha += "\n-----------------------------------------\n\n";
        comparacoes.add(novaLinha);
        //Comparando Longitudes
        i = 0;
        while (i < dadosOriginais.size()) {
             
            try {
                float diferenca = 0f;
                novaLinha = "UTC: " + dadosOriginais.get(i)[2].toString();
                diferenca = Float.valueOf(dadosOriginais.get(i)[5].toString())  -
                        Float.valueOf(dadosProcessados.get(i)[4].toString());
                //System.out.println("Diferença: " + diferenca);

                novaLinha += " Diferenca na longitude : " + diferenca + "\n";
            }catch (NumberFormatException ex){
                novaLinha += " Longitude não presente na solução pvt!\n";
            }

            comparacoes.add(novaLinha);
            i++;
        }
        
        
        System.out.println(comparacoes);

    }
    
    private void lerTXT(String arquivoTeste) {
        String csvFile = arquivoTeste;
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (line.contains("NMEA,")) {
                    this.medicoesNMEA.add(line);
                    this.NUM_MEDICOES++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean salvarTXT(String caminhoArquivo) {
        try {
            Charset utf8 = StandardCharsets.UTF_8;
            Path path = Paths.get(caminhoArquivo);
            BufferedWriter output = Files.newBufferedWriter(path, utf8);

            for (int i = 0; i < medicoesNMEA.size(); i++) {
                String linha = "";
                linha = (String) medicoesNMEA.get(i);
                output.write(linha);
                output.newLine();
            }
            output.flush();
            output.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isFloat(String input) {
        try {
            Float.parseFloat(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
