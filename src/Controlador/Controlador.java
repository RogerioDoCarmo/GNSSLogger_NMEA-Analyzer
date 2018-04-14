package Controlador;

import Modelo.Analise;
import Modelo.Analise.ResultadoComparacaoGPGGA;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rogerio
 */
public class Controlador {
    
    static Analise analise;
    private static Controlador uniqueInstance;
    private static String fileNameLOG;
    private static String fileNameNMEA;
    
    private Controlador(){
        
    }
    
    public static synchronized Controlador getInstance() {
		if (uniqueInstance == null){
                    Controlador.analise = new Analise();
                    Controlador.fileNameLOG = null;
                    Controlador.fileNameNMEA = null;
                    uniqueInstance = new Controlador();
                }			
		return uniqueInstance;
    }
    
    public boolean abrirLog(String caminhoArquivo){
        analise.lerLogFile(caminhoArquivo);
        this.fileNameLOG = caminhoArquivo;
        return true;
    }
    
    public boolean abrirMedicoesProcessadas(String caminhoArquivo){
        analise.abrirArquivoProcessado(caminhoArquivo);
        this.fileNameNMEA = caminhoArquivo;
//        analise.extrairGPGGA_processadas(); FIXME
        return true;
    }
    
    public boolean possuiLogAberto(){
        return (Controlador.fileNameLOG != null);
    }
    
    public boolean possuiNMEAAberto(){
        return (Controlador.fileNameNMEA != null);
    }
    
    public Resultado getMedicoesBrutas(){
        ArrayList<String> dados = analise.extrairMedicoesBrutas();        
        Resultado result = new Resultado("Medições NMEA capturadas pelo software GNSS Logger", dados);        
        return result;
    }
    
    public Resultado getMedicoesProcessadas(){
        ArrayList<String> dados = analise.extrairMedicoesProcessadas();        
        Resultado result = new Resultado("Medições NMEA processadas pelo software GNSS Analysis app", dados);        
        return result;
    }
    
    public ArrayList<ResultadoComparacaoGPGGA> getComparacaoGPGGA(){
        return analise.compararMedicoesGPGGA();
    }
    
    public Resultado getGPGGAbrutas(){
        ArrayList<String> dados = analise.extrairMedicoesGPGGA_brutas();        
        Resultado result = new Resultado("Medições $GPGGA brutas", dados);        
        return result;
    }
    
    public Resultado getGPGGAprocessadas(){
        ArrayList<String> dados = analise.extrairMedicoesGPGGA_processadas();        
        Resultado result = new Resultado("Medições $GPGGA processadas", dados);        
        return result;
    }
    
    public Resultado getPGLOR(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$PGLOR");        
        Resultado result = new Resultado("Medições $PGLOR brutas", dados);        
        return result;
    }
    
    public Resultado getGPGSV(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$GPGSV");        
        Resultado result = new Resultado("Medições $GPGSV brutas", dados);        
        return result;
    }
    
    public Resultado getGLGSV(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$GLGSV");         
        Resultado result = new Resultado("Medições $GLGSV brutas", dados);        
        return result;
    }
    
    public Resultado getBDGSV(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$BDGSV");        
        Resultado result = new Resultado("Medições $BDGSV brutas", dados);        
        return result;
    }
    
    public Resultado getGPGSA(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$GPGSA");        
        Resultado result = new Resultado("Medições $GPGSA brutas", dados);        
        return result;
    }
    
    public Resultado getGNGSA(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$GNGSA");          
        Resultado result = new Resultado("Medições $GNGSA brutas", dados);        
        return result;
    }
    
    public Resultado getQZGSA(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$QZGSA");        
        Resultado result = new Resultado("Medições $QZGSA brutas", dados);        
        return result;
    }
    
    public Resultado getIMGSA(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$IMGSA");        
        Resultado result = new Resultado("Medições $IMGSA brutas", dados);        
        return result;
    }
    
    public Resultado getBDGSA(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$BDGSA");        
        Resultado result = new Resultado("Medições $BDGSA brutas", dados);        
        return result;
    }
    
    public Resultado getGAGSA(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$GAGSA");        
        Resultado result = new Resultado("Medições $GAGSA brutas", dados);        
        return result;
    }
    
    public Resultado getGPRMC(){
        ArrayList<String> dados = analise.extrairMedicaoBruta("$GPRMC");       
        Resultado result = new Resultado("Medições $GPRMC brutas", dados);        
        return result;
    }
    
    public class Resultado{
        private String label;
        private ArrayList<String> resultado;
        private Date data;

        public Resultado(String label, ArrayList<String> dados){
            this.label = label;
            this.resultado = dados;
            this.data = new Date();
        }
        
        /**
         * @return the label
         */
        public String getLabel() {
            return label;
        }

        /**
         * @param label the label to set
         */
        public void setLabel(String label) {
            this.label = label;
        }

        /**
         * @return the resultado
         */
        public ArrayList<String> getDados() {
            return resultado;
        }

        /**
         * @return the data
         */
        public Date getData() {
            return data;
        }
        
    }

}
