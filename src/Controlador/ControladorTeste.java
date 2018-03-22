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
