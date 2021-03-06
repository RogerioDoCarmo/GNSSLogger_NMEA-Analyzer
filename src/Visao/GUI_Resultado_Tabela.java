/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controlador.Controlador;
import Modelo.Analise;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Rogerio
 */
public class GUI_Resultado_Tabela extends javax.swing.JDialog {

    private boolean flag_conteudo;
    private DefaultTableModel model;
    
    /**
     * Creates new form GUI_Resultado_Tabela
     */
    public GUI_Resultado_Tabela(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        
        // TODO Refatorar esse mecanismo de verificação!
        this.flag_conteudo = true;        
        verificarArquivosAbertos();
        
        if (!this.flag_conteudo){
            this.setVisible(false);
            this.dispose();
            return;
        }else{
            //this.setVisible(true);
        }
        
        this.flag_conteudo = true;
        carregarValores();
        
        if (!this.flag_conteudo){
            this.setVisible(false);
            this.dispose();
            return;
        }else{
            this.setVisible(true);
        }
    }
    
    private boolean verificarArquivosAbertos(){
        if (!Controlador.getInstance().possuiLogAberto()){
            JOptionPane optionPane = new JOptionPane("O arquivo de log não foi aberto!", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Erro");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            this.flag_conteudo = false;
            return false;
        }
        
        if (!Controlador.getInstance().possuiNMEAAberto()){
            JOptionPane optionPane = new JOptionPane("O arquivo NMEA processado não foi aberto!", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Erro");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            this.flag_conteudo = false;
            return false;
        }
        return true;
    }
    
    private void carregarValores() {
        Analise.ResultadoComparacaoGPGGA resultado = Controlador.getInstance().getComparacaoGPGGA();
        
        setarTabela();        
        
        if (resultado == null) { // TODO estava assim, || resultado.isEmpty()) ARRUMAR
            JOptionPane optionPane = new JOptionPane("O arquivo de não possui medições NMEA!", JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog("Erro");
            dialog.setAlwaysOnTop(true);
            dialog.setVisible(true);
            this.flag_conteudo = false;
            return;
        }
        
//        for (resultado.getComparacoes(); resultado) { // FIXME
//            Object[] novaLinha = new Object[]{resultadoTemp.getUTC(), resultadoTemp.getDiffLatitude(), resultadoTemp.getDiffLongitude()};
//            model.addRow(novaLinha);
//        }
        
        for (int i = 0; i < resultado.getComparacoes().size(); i++){
             Object[] novaLinha = new Object[]{resultado.getComparacoes().get(i).getUTC(),
                                               resultado.getComparacoes().get(i).getDiffLatitude(),
                                               resultado.getComparacoes().get(i).getDiffLongitude()};
             
            model.addRow(novaLinha);
//        TODO testar essa alternativa funcional...
//        resultado.stream().map((resultadoTemp) -> new Object[]{resultadoTemp.getUTC(), resultadoTemp.getDiffLatitude(), resultadoTemp.getDiffLongitude()}).forEachOrdered((novaLinha) -> {
//            model.addRow(novaLinha);
//        });
        }
        
        txtMediaLatitude.setText(String.valueOf(resultado.getMediaLatitude()));
        txtMediaLongitude.setText(String.valueOf(resultado.getMediaLongitude()));
        txtRMSlatitude.setText(String.valueOf(resultado.getRMSlatitude()));
        txtRMSlongitude.setText(String.valueOf(resultado.getRMSlongitude()));
    }
    
    private void setarTabela(){
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Microsoft JhengHei UI Light", Font.PLAIN, 12));
        model = (DefaultTableModel) tabela.getModel();
        tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(35);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(35);
        
        while (tabela.getModel().getRowCount() > 0) {
            model.removeRow(0);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtMediaLongitude = new javax.swing.JTextField();
        txtMediaLatitude = new javax.swing.JTextField();
        txtRMSlatitude = new javax.swing.JTextField();
        txtRMSlongitude = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Comparação #GPGGA");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Comparação de medições NMEA $GPGGA:");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "UTC", "Diferença na Latitude (m)", "Diferença na Longitude (m)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tabela);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Média na diferença de Longitude:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Média na diferença de Latitude:");

        txtMediaLongitude.setEditable(false);

        txtMediaLatitude.setEditable(false);

        txtRMSlatitude.setEditable(false);

        txtRMSlongitude.setEditable(false);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("RMS Latitude:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("RMS Longitude:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMediaLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMediaLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRMSlatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRMSlongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(81, 81, 81))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMediaLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMediaLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRMSlatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtRMSlongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Resultado_Tabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Resultado_Tabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Resultado_Tabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Resultado_Tabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI_Resultado_Tabela dialog = new GUI_Resultado_Tabela(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabela;
    private javax.swing.JTextField txtMediaLatitude;
    private javax.swing.JTextField txtMediaLongitude;
    private javax.swing.JTextField txtRMSlatitude;
    private javax.swing.JTextField txtRMSlongitude;
    // End of variables declaration//GEN-END:variables
}
