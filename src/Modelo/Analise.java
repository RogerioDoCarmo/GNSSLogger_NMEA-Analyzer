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

    private int NUM_MEDICOES;
    private boolean leituraMedicoes;
    private String caminhoArquivoLOG;
    private ArrayList<String> medicoesNMEA;
    private ArrayList<String> medicoesGPGGAbrutas;
     private ArrayList<String> medicoesGPGGAprocessadas;
    private ArrayList<String> medicoesProcessadas;

    public Analise (){
        this.medicoesNMEA = new ArrayList<>();
        this.medicoesGPGGAbrutas = new ArrayList<>();
        this.medicoesGPGGAprocessadas = new ArrayList<>();
        this.medicoesProcessadas = new ArrayList<>();
        this.NUM_MEDICOES = 0;
        this.leituraMedicoes = false;
        caminhoArquivoLOG = null;
    }
    
    public Analise(String caminhoArquivoLog) {
        this.medicoesNMEA = new ArrayList<>();
        this.medicoesGPGGAbrutas = new ArrayList<>();
        this.medicoesGPGGAprocessadas = new ArrayList<>();
        this.medicoesProcessadas = new ArrayList<>();
        this.NUM_MEDICOES = 0;
        this.leituraMedicoes = false;
        this.caminhoArquivoLOG = caminhoArquivoLog;   
    }
    
    public ArrayList<String> extrairMedicoesGPGGA_brutas(){
        if (!leituraMedicoes)
            lerLogFile(caminhoArquivoLOG);        
        extrairGPGGA_brutas();
        return (this.medicoesGPGGAbrutas);  
    }
    
    // TODO: Arrumar a verificação inicial do método!
    
    public ArrayList<String> extrairMedicoesGPGGA_processadas(){
        if (!leituraMedicoes) // FIXME Arrumar aqui!
            lerLogFile(caminhoArquivoLOG);        
        extrairGPGGA_processadas();
        return (this.medicoesGPGGAprocessadas);  
    }
    
    private void extrairGPGGA_brutas(){       
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$GPGGA"))
                medicoesGPGGAbrutas.add(medicoesTemp);
        }
    }
    
    private void extrairGPGGA_processadas(){       
        for (String medicoesTemp : medicoesProcessadas) {
            if (medicoesTemp.contains("$GPGGA"))
                medicoesGPGGAprocessadas.add(medicoesTemp);
        }
    }
    
    public ArrayList<String> extrairMedicaoBruta(String tipoNMEA){
       if (!leituraMedicoes){
            lerLogFile(caminhoArquivoLOG);            
        }
        
        ArrayList<String> medidas = new ArrayList<>();
       
       for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains(tipoNMEA))
                medidas.add(medicoesTemp);
        }   
       
       return medidas;
    }
    
    public ArrayList<String> abrirArquivoProcessado(String nomeArquivo){
      lerNMEAprocessada(nomeArquivo);
      // TODO arrumar extrairGPGGA_processadas();  
      return (this.medicoesProcessadas);
    }
    
    public ArrayList<String> extrairMedicoesBrutas(){
        if (!this.medicoesNMEA.isEmpty())
                return this.medicoesNMEA;
        return null;
    }
    
    public ArrayList<String> extrairMedicoesProcessadas(){
               if (!this.medicoesProcessadas.isEmpty())
                return this.medicoesProcessadas;
        return null;
    }
        
    public void compararValores(){
        ArrayList<Comparacao> comparacoes = new ArrayList<>();
             
        
        int i = 0;
        while (i < medicoesGPGGAbrutas.size()) {
            
            try {
                float diferenca = 0f;
                
                diferenca = 1800f * Float.valueOf(medicoesGPGGAbrutas.get(i).split(",")[3])  -
                            1800f * Float.valueOf(dadosProcessados.get(i)[3].toString());
                //System.out.println("Diferença: " + diferenca);
                
                Comparacao novaComparacao = new Comparacao(medicoesGPGGAbrutas.get(i).split(",")[2], "asa", "dasd");
                comparacoes.add(novaComparacao);

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
    
    private class Comparacao{
        private String UTC;
        private String diffLatitude;
        private String diffLongitude;
        
        public Comparacao(String UTC, String diffLatitude, String diffLongitude){
            this.UTC = UTC;
            this.diffLatitude = diffLatitude;
            this.diffLongitude = diffLongitude;
        }
        
        public String getUTC(){
            return this.UTC;
        }        
        public String getDiffLatitude(){
            return this.diffLatitude;
        }
        public String getDiffLongitude(){
            return this.diffLongitude;
        }
    }
    
    public void lerLogFile(String caminhoArquivo) {       
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            while ((line = br.readLine()) != null) {
                if (line.contains("NMEA,")) {
                    this.medicoesNMEA.add(line);
                    this.NUM_MEDICOES++;
                }
            }
            this.leituraMedicoes = true;
            this.caminhoArquivoLOG = caminhoArquivo;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void lerNMEAprocessada(String arquivoTeste) {
        String csvFile = arquivoTeste;
        String line = "";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                if (line.contains("$")) {
                    this.medicoesProcessadas.add(line);
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

}
