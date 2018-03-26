package Controlador;

import Modelo.Analise;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Rogerio
 */
public class ControladorTeste {
    
    Analise teste;
    private static ControladorTeste uniqueInstance;
    
    private ControladorTeste(){
        
    }
    
    public static synchronized ControladorTeste getInstance() {
		if (uniqueInstance == null)
			uniqueInstance = new ControladorTeste();
		return uniqueInstance;
    }
    
    public boolean abrirLog(String caminhoArquivo){
        teste = new Analise(caminhoArquivo);
        return true;
    }
    
    public boolean abrirMedicao(String caminhoArquivo){
        
        return true;
    }
    
    public Resultado getGPGGA(){
        ArrayList<String> dados = teste.extrairMedicoesGPGGA();        
        Resultado result = new Resultado("Medições $GPGGA brutas", dados);        
        return result;
    }
    
    public Resultado getPGLOR(){
        ArrayList<String> dados = teste.extrairMedicoesPGLOR();        
        Resultado result = new Resultado("Medições $PGLOR brutas", dados);        
        return result;
    }
    
    public Resultado getGPGSV(){
        ArrayList<String> dados = teste.extrairMedicoesGPGSV();        
        Resultado result = new Resultado("Medições $GPGSV brutas", dados);        
        return result;
    }
    
    public Resultado getGLGSV(){
        ArrayList<String> dados = teste.extrairMedicoesGLGSV();        
        Resultado result = new Resultado("Medições $GLGSV brutas", dados);        
        return result;
    }
    
    public Resultado getBDGSV(){
        ArrayList<String> dados = teste.extrairMedicoesBDGSV();        
        Resultado result = new Resultado("Medições $BDGSV brutas", dados);        
        return result;
    }
    
    public Resultado getGPGSA(){
        ArrayList<String> dados = teste.extrairMedicoesGPGSA();        
        Resultado result = new Resultado("Medições $GPGSA brutas", dados);        
        return result;
    }
    
    public Resultado getGNGSA(){
        ArrayList<String> dados = teste.extrairMedicoesGNGSA();        
        Resultado result = new Resultado("Medições $GNGSA brutas", dados);        
        return result;
    }
    
    public Resultado getQZGSA(){
        ArrayList<String> dados = teste.extrairMedicoesQZGSA();        
        Resultado result = new Resultado("Medições $QZGSA brutas", dados);        
        return result;
    }
    
    public Resultado getIMGSA(){
        ArrayList<String> dados = teste.extrairMedicoesIMGSA();        
        Resultado result = new Resultado("Medições $IMGSA brutas", dados);        
        return result;
    }
    
    public Resultado getBDGSA(){
        ArrayList<String> dados = teste.extrairMedicoesBDGSA();        
        Resultado result = new Resultado("Medições $BDGSA brutas", dados);        
        return result;
    }
    
    public Resultado getGAGSA(){
        ArrayList<String> dados = teste.extrairMedicoesGAGSA();        
        Resultado result = new Resultado("Medições $GAGSA brutas", dados);        
        return result;
    }
    
    public Resultado getGPRMC(){
        ArrayList<String> dados = teste.extrairMedicoesGPRMC();        
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
