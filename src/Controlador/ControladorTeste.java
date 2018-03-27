package Controlador;

import Modelo.Analise;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rogerio
 */
public class ControladorTeste {
    
    static Analise analise;
    private static ControladorTeste uniqueInstance;
    
    private ControladorTeste(){
        
    }
    
    public static synchronized ControladorTeste getInstance() {
		if (uniqueInstance == null){
                    ControladorTeste.analise = new Analise();
                    uniqueInstance = new ControladorTeste();
                }
			
		return uniqueInstance;
    }
    
    public boolean abrirLog(String caminhoArquivo){
        analise.lerLogFile(caminhoArquivo);
        return true;
    }
    
    public boolean abrirMedicoesProcessadas(String caminhoArquivo){
        analise.abrirArquivoProcessado(caminhoArquivo);                
        return true;
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
    
    public Resultado getGPGGA(){
        ArrayList<String> dados = analise.extrairMedicoesGPGGA();        
        Resultado result = new Resultado("Medições $GPGGA brutas", dados);        
        return result;
    }
    
    public Resultado getPGLOR(){
        ArrayList<String> dados = analise.extrairMedicoes("$PGLOR");        
        Resultado result = new Resultado("Medições $PGLOR brutas", dados);        
        return result;
    }
    
    public Resultado getGPGSV(){
        ArrayList<String> dados = analise.extrairMedicoes("$GPGSV");        
        Resultado result = new Resultado("Medições $GPGSV brutas", dados);        
        return result;
    }
    
    public Resultado getGLGSV(){
        ArrayList<String> dados = analise.extrairMedicoes("$GLGSV");         
        Resultado result = new Resultado("Medições $GLGSV brutas", dados);        
        return result;
    }
    
    public Resultado getBDGSV(){
        ArrayList<String> dados = analise.extrairMedicoes("$BDGSV");        
        Resultado result = new Resultado("Medições $BDGSV brutas", dados);        
        return result;
    }
    
    public Resultado getGPGSA(){
        ArrayList<String> dados = analise.extrairMedicoes("$GPGSA");        
        Resultado result = new Resultado("Medições $GPGSA brutas", dados);        
        return result;
    }
    
    public Resultado getGNGSA(){
        ArrayList<String> dados = analise.extrairMedicoes("$GNGSA");          
        Resultado result = new Resultado("Medições $GNGSA brutas", dados);        
        return result;
    }
    
    public Resultado getQZGSA(){
        ArrayList<String> dados = analise.extrairMedicoes("$QZGSA");        
        Resultado result = new Resultado("Medições $QZGSA brutas", dados);        
        return result;
    }
    
    public Resultado getIMGSA(){
        ArrayList<String> dados = analise.extrairMedicoes("$IMGSA");        
        Resultado result = new Resultado("Medições $IMGSA brutas", dados);        
        return result;
    }
    
    public Resultado getBDGSA(){
        ArrayList<String> dados = analise.extrairMedicoes("$BDGSA");        
        Resultado result = new Resultado("Medições $BDGSA brutas", dados);        
        return result;
    }
    
    public Resultado getGAGSA(){
        ArrayList<String> dados = analise.extrairMedicoes("$GAGSA");        
        Resultado result = new Resultado("Medições $GAGSA brutas", dados);        
        return result;
    }
    
    public Resultado getGPRMC(){
        ArrayList<String> dados = analise.extrairMedicoes("$GPRMC");       
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
