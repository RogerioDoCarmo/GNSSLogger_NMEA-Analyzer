package Controlador;

import Modelo.Analise;
import java.util.ArrayList;

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
    
    public ArrayList<String> getGPGGA(){
        return teste.extrairMedicoesGPGGA();
    }
}
