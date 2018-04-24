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
import javax.swing.JOptionPane;

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
        {
            lerLogFile(caminhoArquivoLOG);
        }
        extrairGPGGA_processadas();
        return (this.medicoesGPGGAprocessadas);
    }

    private boolean extrairGPGGA_brutas() {  //TODO CHAMAR SO UMA VEZ NO CONSTRUTOR OU ALGO ASSIM     
        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains("$GPGGA")) {
                medicoesGPGGAbrutas.add(medicoesTemp);
            }
        }
        return !medicoesGPGGAbrutas.isEmpty();
    }

    private void extrairGPGGA_processadas() {
        for (String medicoesTemp : medicoesProcessadas) {
            if (medicoesTemp.contains("$GPGGA")) {
                medicoesGPGGAprocessadas.add(medicoesTemp);
            }
        }
    }

    public ArrayList<String> extrairMedicaoBruta(String tipoNMEA) {
        if (!leituraMedicoes) {
            lerLogFile(caminhoArquivoLOG);
        }

        ArrayList<String> medidas = new ArrayList<>();

        for (String medicoesTemp : medicoesNMEA) {
            if (medicoesTemp.contains(tipoNMEA)) {
                medidas.add(medicoesTemp);
            }
        }

        return medidas;
    }

    public ArrayList<String> abrirArquivoProcessado(String nomeArquivo) {
        lerNMEAprocessada(nomeArquivo);
        // TODO arrumar extrairGPGGA_processadas();  
        return (this.medicoesProcessadas);
    }

    public ArrayList<String> extrairMedicoesBrutas() {
        if (!this.medicoesNMEA.isEmpty()) {
            return this.medicoesNMEA;
        }
        return null;
    }

    public ArrayList<String> extrairMedicoesProcessadas() {
        if (!this.medicoesProcessadas.isEmpty()) {
            return this.medicoesProcessadas;
        }
        return null;
    }

    public ResultadoComparacaoGPGGA compararMedicoesGPGGA() {

        compararMedicoesGPGGA_RMS(); // FIXME TIRAR ISSO URGENTE!
        
        ResultadoComparacaoGPGGA resultado = new ResultadoComparacaoGPGGA();

        extrairGPGGA_brutas();
        extrairGPGGA_processadas();

        int i = 0;
        while (i < medicoesGPGGAbrutas.size()) {
            try {
                float diferencaLatitude = 0f;
                float diferencaLongitude = 0f;

                diferencaLatitude = 
                        (1800f * Float.valueOf(medicoesGPGGAprocessadas.get(i).split(",")[2])) -
                        
                        (1800f * Float.valueOf(medicoesGPGGAbrutas.get(i).split(",")[3]));

                diferencaLongitude = (1800f * Float.valueOf(medicoesGPGGAprocessadas.get(i).split(",")[4]))
                        - (1800f * Float.valueOf(medicoesGPGGAbrutas.get(i).split(",")[5]));
                

                resultado.adicionarComparacao(medicoesGPGGAbrutas.get(i).split(",")[2],
                                                     String.valueOf(diferencaLatitude),
                                                     String.valueOf(diferencaLongitude));
            } catch (NumberFormatException ex) {
                System.out.println("Valor não presente na solução pvt!\n"); // FIXME
                // TODO USAR O MECANISMO DE LOG AQUI
            }
            i++;
        }
        
        resultado.calcMediaLatitude();
        resultado.calcMediaLongitude();
        return resultado;
    }

    public ResultadoComparacaoGPGGA compararMedicoesGPGGA_RMS() {

        ResultadoComparacaoGPGGA resultado = new ResultadoComparacaoGPGGA();

        extrairGPGGA_brutas();
        extrairGPGGA_processadas();

        // FIX ME POR EM OUTRO LUGAR
        
        float valoresLatitude = 0f;
        float valoresLongitude = 0f;
        
        int i = 0;
        while (i < medicoesGPGGAbrutas.size()) {
            try {
                float diferencaLatitude = 0f;
                float diferencaLongitude = 0f;

                diferencaLatitude = 
                        (1800f * Float.valueOf(medicoesGPGGAprocessadas.get(i).split(",")[2])) -
                        
                        (1800f * Float.valueOf(medicoesGPGGAbrutas.get(i).split(",")[3]));

                diferencaLongitude = (1800f * Float.valueOf(medicoesGPGGAprocessadas.get(i).split(",")[4]))
                        - (1800f * Float.valueOf(medicoesGPGGAbrutas.get(i).split(",")[5]));
                
                
                
                // 1 - Quadrado
                valoresLatitude += diferencaLatitude * diferencaLatitude;
                valoresLongitude += diferencaLongitude* diferencaLongitude;
                
                
                resultado.adicionarComparacao(medicoesGPGGAbrutas.get(i).split(",")[2],
                                                     String.valueOf(diferencaLatitude),
                                                     String.valueOf(diferencaLongitude));
            } catch (NumberFormatException ex) {
                System.out.println("Valor não presente na solução pvt!\n"); // FIXME
                // TODO USAR O MECANISMO DE LOG AQUI
            }
            i++;
        }
        
        // 2 - Média
        valoresLatitude = valoresLatitude/medicoesGPGGAbrutas.size();
        valoresLongitude = valoresLongitude/medicoesGPGGAbrutas.size();
        
        // 3 - Sqrt
        double RMS_Latitude = Math.sqrt((double)valoresLatitude);
        double RMS_Longitude = Math.sqrt((double)valoresLongitude);
        
        // TODO TIRAR ESSA GAMBIARRA
        JOptionPane.showMessageDialog(null, "RMS da Latitude: " + RMS_Latitude + "\n" +
                                            "RMS da Longitude: " + RMS_Longitude);
        
        resultado.calcMediaLatitude();
        resultado.calcMediaLongitude();
        return resultado;
    }    
    
    public class ResultadoComparacaoGPGGA {
        private ArrayList<ComparacaoGPGGA> comparacoes; // FIXME Arrumar os atributos de visibilidade
        float mediaDiffLatitude;
        float mediaDiffLongitude;
                
        public ResultadoComparacaoGPGGA(){
            this.comparacoes = new ArrayList<>();
            this.mediaDiffLatitude = 0f;
            this.mediaDiffLongitude = 0f;
        }

        public boolean adicionarComparacao(String UTC, String diffLatitude, String diffLongitude){
            if (this.getComparacoes() == null) return false;
            
            ComparacaoGPGGA novaComparacao = new ComparacaoGPGGA(UTC, diffLatitude, diffLongitude);            
            this.getComparacoes().add(novaComparacao);
            return true;
        }
        
        public float getMediaLatitude(){
            return this.mediaDiffLatitude;
        }
        
        public float getMediaLongitude(){
            return this.mediaDiffLongitude;
        }
        
        public float calcMediaLatitude(){
            if (getComparacoes() == null || getComparacoes().isEmpty()) return -1f;
            for (ComparacaoGPGGA comp : getComparacoes()) {
                this.mediaDiffLatitude += Float.parseFloat(comp.diffLatitude);
            }
            this.mediaDiffLatitude = this.mediaDiffLatitude / (float) getComparacoes().size();
            
            return this.mediaDiffLatitude;
        }
        
        public float calcMediaLongitude(){
            if (getComparacoes() == null || getComparacoes().isEmpty()) return -1f;
            for (ComparacaoGPGGA comp : getComparacoes()) {
                this.mediaDiffLongitude += Float.parseFloat(comp.diffLongitude);
            }
            this.mediaDiffLongitude = this.mediaDiffLongitude / (float) getComparacoes().size();
            
            return this.mediaDiffLongitude;
        }
        
        public class ComparacaoGPGGA {

            private String UTC;
            private String diffLatitude;
            private String diffLongitude;

            public ComparacaoGPGGA(String UTC, String diffLatitude, String diffLongitude) {
                this.UTC = UTC;
                this.diffLatitude = diffLatitude;
                this.diffLongitude = diffLongitude;
            }

            public String getUTC() {
                return this.UTC;
            }

            public String getDiffLatitude() {
                return this.diffLatitude;
            }

            public String getDiffLongitude() {
                return this.diffLongitude;
            }
        }

        /**
         * @return the comparacoes
         */
        public ArrayList<ComparacaoGPGGA> getComparacoes() { // TODO ARRUMAR ESSA GAMBIARRA
            return comparacoes;
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
