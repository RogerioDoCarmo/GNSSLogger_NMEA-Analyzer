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

    int NUM_INSTANCIAS;
    int NUM_ATRIBUTOS;
    ArrayList<String> medicoesNMEA;
    ArrayList<String> medicoesGPGGA;

    public Analise (){
        
    }
    
    public Analise(String caminhoArquivo) {
        this.medicoesNMEA = new ArrayList<>();
        // tratando a extensão do arquivo:
        String extensao = caminhoArquivo.substring(caminhoArquivo.length() - 4);
        lerTXT(caminhoArquivo);

//        extensao = caminhoArquivo.substring(caminhoArquivo.length() - 5);
//
//        System.out.println(extensao);
//
//        if (extensao.equals(".nmea")) {
//            lerTXT(caminhoArquivo);
//        }
        //
    }
    
    public ArrayList<String> extrairMedicoesGPGGA(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$GPGGA"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }
    
    public ArrayList<String> extrairMedicoesPGLOR(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$PGLOR"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }
    
    public ArrayList<String> extrairMedicoesGPGSV(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$GPGSV"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }
    
    public ArrayList<String> extrairMedicoesGLGSV(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$GLGSV"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }
    
    public ArrayList<String> extrairMedicoesBDGSV(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$BDGSV"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }
    
    public ArrayList<String> extrairMedicoesGPGSA(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$GPGSA"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }

    public ArrayList<String> extrairMedicoesGNGSA(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$GNGSA"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }

    public ArrayList<String> extrairMedicoesQZGSA(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$QZGSA"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }
    
    public ArrayList<String> extrairMedicoesIMGSA(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$IMGSA"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }
    
    public ArrayList<String> extrairMedicoesBDGSA(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$BDGSA"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }
    
    public ArrayList<String> extrairMedicoesGAGSA(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$BDGSA"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
    }
    
    public ArrayList<String> extrairMedicoesGPRMC(){
        this.medicoesGPGGA = new ArrayList<>();
        
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$GPRMC"))
                medicoesGPGGA.add(medicoesTemp);
        }
        return medicoesGPGGA;
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
                    medicoesNMEA.add(line);
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

    public float[] getColuna(int indiceColuna) {
        float[] dados = new float[NUM_INSTANCIAS];

        if (indiceColuna < 0 || indiceColuna > NUM_ATRIBUTOS - 1) {
            try {
                throw new Exception("Índice inválido!");
            } catch (Exception ex) {
                Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (!isFloat(medicoesNMEA.get(0).toString())) {
            try {
                throw new Exception("A coluna escolhida não é do tipo numérico!");
            } catch (Exception ex) {
                Logger.getLogger(Analise.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        for (int i = 0; i < NUM_INSTANCIAS; i++) {
            float valorAtual = Float.valueOf(medicoesNMEA.get(i).toString());
            dados[i] = valorAtual;
        }

        System.out.println("Dados:");
        for (int i = 0; i < dados.length; i++) {
            System.out.println(dados[i]);
        }

        return dados;
    }

}
