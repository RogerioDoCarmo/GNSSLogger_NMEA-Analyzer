/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Controlador.Controlador;
import Controlador.Tipos_Operacoes;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Rogerio
 */
public class GUI_Principal extends javax.swing.JFrame {

    private String fileNameLOG;
    private String fileNameNMEA;
    
    private String abrirArquivo(String extensao, String titulo, String filtro){
        String fileName = null;
        String dir = System.getProperty("user.home");
        dir += "/Desktop";
      
        JFileChooser fileChooser = new JFileChooser(dir);
        fileChooser.setDialogTitle(titulo);
        fileChooser.setFileHidingEnabled(true);
        fileChooser.removeChoosableFileFilter(fileChooser.getChoosableFileFilters()[0]);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter(filtro, extensao));
        
        int userSelection = fileChooser.showOpenDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            fileName = fileToSave.getAbsolutePath();
        }else if (userSelection == JFileChooser.CANCEL_OPTION){          
            
        }       
        
        return fileName;
    }
    
    /**
     * Creates new form GUI_Principal
     */
    public GUI_Principal() {
        initComponents();
        this.fileNameLOG = null;
        this.fileNameNMEA = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbrirLog = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtLog = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtNMEA = new javax.swing.JTextField();
        btnAbrirLog1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuOpenLog = new javax.swing.JMenuItem();
        menuOpenNMEA = new javax.swing.JMenuItem();
        menuShowNMEAraw = new javax.swing.JMenuItem();
        menuShowNMEApvt = new javax.swing.JMenuItem();
        menuExtrairGPGGAbrutas = new javax.swing.JMenuItem();
        menuExtrairGPGGAprocessadas = new javax.swing.JMenuItem();
        menuCompararGPGGA = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        menuExtrairPGLOR = new javax.swing.JMenuItem();
        menuExtrairGPGSV = new javax.swing.JMenuItem();
        menuExtrairGLGSV = new javax.swing.JMenuItem();
        menuExtrairBDGSV = new javax.swing.JMenuItem();
        menuExtrairGPGSA = new javax.swing.JMenuItem();
        menuExtrairGNGSA = new javax.swing.JMenuItem();
        menuExtrairQZGSA = new javax.swing.JMenuItem();
        menuExtrairIMGSA = new javax.swing.JMenuItem();
        menuExtrairBDGSA = new javax.swing.JMenuItem();
        menuExtrairGAGSA = new javax.swing.JMenuItem();
        menuExtrairGPRMC = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GNSS Logger - NMEA Analyzer");
        setResizable(false);

        btnAbrirLog.setText("Abrir .txt");
        btnAbrirLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirLogActionPerformed(evt);
            }
        });

        jLabel1.setText("Arquivo do GNSS Logger:");

        txtLog.setEditable(false);
        txtLog.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Arquivo do GNSS Analysis App:");

        txtNMEA.setEditable(false);
        txtNMEA.setDisabledTextColor(new java.awt.Color(255, 255, 255));

        btnAbrirLog1.setText("Abrir .nmea");
        btnAbrirLog1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirLog1ActionPerformed(evt);
            }
        });

        jButton1.setText("Comparar Medições $GPGGA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("Arquivo");

        menuOpenLog.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuOpenLog.setText("Abrir log");
        menuOpenLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenLogActionPerformed(evt);
            }
        });
        jMenu1.add(menuOpenLog);

        menuOpenNMEA.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        menuOpenNMEA.setText("Abrir NMEA processado");
        menuOpenNMEA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenNMEAActionPerformed(evt);
            }
        });
        jMenu1.add(menuOpenNMEA);

        menuShowNMEAraw.setText("Mostrar NMEA brutas");
        menuShowNMEAraw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuShowNMEArawActionPerformed(evt);
            }
        });
        jMenu1.add(menuShowNMEAraw);

        menuShowNMEApvt.setText("Mostrar NMEA processadas");
        menuShowNMEApvt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuShowNMEApvtActionPerformed(evt);
            }
        });
        jMenu1.add(menuShowNMEApvt);

        menuExtrairGPGGAbrutas.setText("Mostrar $GPGGA brutas");
        menuExtrairGPGGAbrutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairGPGGAbrutasActionPerformed(evt);
            }
        });
        jMenu1.add(menuExtrairGPGGAbrutas);

        menuExtrairGPGGAprocessadas.setText("Mostrar $GPGGA processadas");
        menuExtrairGPGGAprocessadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairGPGGAprocessadasActionPerformed(evt);
            }
        });
        jMenu1.add(menuExtrairGPGGAprocessadas);

        menuCompararGPGGA.setText("Comparar Medições $GPGGA");
        menuCompararGPGGA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCompararGPGGAActionPerformed(evt);
            }
        });
        jMenu1.add(menuCompararGPGGA);

        jMenu3.setText("Extrair outras medições NMEA");

        menuExtrairPGLOR.setText("Extrair $PGLOR");
        menuExtrairPGLOR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairPGLORActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairPGLOR);

        menuExtrairGPGSV.setText("Extrair $GPGSV");
        menuExtrairGPGSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairGPGSVActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairGPGSV);

        menuExtrairGLGSV.setText("Extrair $GLGSV");
        menuExtrairGLGSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairGLGSVActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairGLGSV);

        menuExtrairBDGSV.setText("Extrair $BDGSV");
        menuExtrairBDGSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairBDGSVActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairBDGSV);

        menuExtrairGPGSA.setText("Extrair $GPGSA");
        menuExtrairGPGSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairGPGSAActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairGPGSA);

        menuExtrairGNGSA.setText("Extrair $GNGSA");
        menuExtrairGNGSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairGNGSAActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairGNGSA);

        menuExtrairQZGSA.setText("Extrair $QZGSA");
        menuExtrairQZGSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairQZGSAActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairQZGSA);

        menuExtrairIMGSA.setText("Extrair $IMGSA");
        menuExtrairIMGSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairIMGSAActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairIMGSA);

        menuExtrairBDGSA.setText("Extrair $BDGSA");
        menuExtrairBDGSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairBDGSAActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairBDGSA);

        menuExtrairGAGSA.setText("Extrair $GAGSA");
        menuExtrairGAGSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairGAGSAActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairGAGSA);

        menuExtrairGPRMC.setText("Extrair $GPRMC");
        menuExtrairGPRMC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExtrairGPRMCActionPerformed(evt);
            }
        });
        jMenu3.add(menuExtrairGPRMC);

        jMenu1.add(jMenu3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtNMEA, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
                                .addComponent(txtLog)))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAbrirLog, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAbrirLog1))))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbrirLog))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNMEA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAbrirLog1))
                .addGap(77, 77, 77)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuOpenLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenLogActionPerformed
        String titulo = "Abra o arquivo .txt gerado pelo GNSS Logger";
        String extensao = "txt";
        String filtro = "txt (*.txt)";
        
        fileNameLOG = abrirArquivo(extensao,titulo,filtro);
        
        if (fileNameLOG != null){
            Controlador.getInstance().abrirLog(fileNameLOG);
            txtLog.setText(fileNameLOG);
        }            
    }//GEN-LAST:event_menuOpenLogActionPerformed

    private void menuExtrairGPGGAbrutasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairGPGGAbrutasActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$GPGGA_Brutas);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairGPGGAbrutasActionPerformed

    private void menuOpenNMEAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenNMEAActionPerformed
        String titulo = "Abra o arquivo gerado pelo GNSS Analysis App";
        String extensao = "nmea";
        String filtro = "nmea (*.nmea)";
        
        fileNameNMEA = abrirArquivo(extensao,titulo,filtro);
        
        if (fileNameNMEA != null){
            Controlador.getInstance().abrirMedicoesProcessadas(fileNameNMEA);
            txtNMEA.setText(fileNameLOG);
        }        
    }//GEN-LAST:event_menuOpenNMEAActionPerformed

    private void menuExtrairPGLORActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairPGLORActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$PGLOR);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairPGLORActionPerformed

    private void menuExtrairGPGSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairGPGSVActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$GPGSV);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairGPGSVActionPerformed

    private void menuExtrairGLGSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairGLGSVActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$GLGSV);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairGLGSVActionPerformed

    private void menuExtrairBDGSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairBDGSVActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$BDGSV);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairBDGSVActionPerformed

    private void menuExtrairGPGSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairGPGSAActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$GPGSA);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairGPGSAActionPerformed

    private void menuExtrairGNGSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairGNGSAActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$GNGSA);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairGNGSAActionPerformed

    private void menuExtrairQZGSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairQZGSAActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$QZGSA);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairQZGSAActionPerformed

    private void menuExtrairIMGSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairIMGSAActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$IMGSA);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairIMGSAActionPerformed

    private void menuExtrairBDGSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairBDGSAActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$BDGSA);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairBDGSAActionPerformed

    private void menuExtrairGAGSAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairGAGSAActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$GAGSA);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairGAGSAActionPerformed

    private void menuExtrairGPRMCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairGPRMCActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$GPRMC);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairGPRMCActionPerformed

  private void menuShowNMEApvtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuShowNMEApvtActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.MEDICOES_PROCESSADAS);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuShowNMEApvtActionPerformed

    private void menuShowNMEArawActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuShowNMEArawActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.MEDICOES_BRUTAS);
    }//GEN-LAST:event_menuShowNMEArawActionPerformed

    private void menuExtrairGPGGAprocessadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExtrairGPGGAprocessadasActionPerformed
       GUI_Resultado novaTela = new GUI_Resultado(this,true,Tipos_Operacoes.$GPGGA_Processadas);
       novaTela.setVisible(true);
    }//GEN-LAST:event_menuExtrairGPGGAprocessadasActionPerformed

    private void btnAbrirLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirLogActionPerformed
        menuOpenLogActionPerformed(null);
        txtLog.setText(fileNameLOG);
    }//GEN-LAST:event_btnAbrirLogActionPerformed

    private void menuCompararGPGGAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCompararGPGGAActionPerformed
        GUI_Resultado_Tabela novaTela = new GUI_Resultado_Tabela(this, true);
    }//GEN-LAST:event_menuCompararGPGGAActionPerformed

    private void btnAbrirLog1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirLog1ActionPerformed
        menuOpenNMEAActionPerformed(null);
        txtNMEA.setText(fileNameNMEA);
    }//GEN-LAST:event_btnAbrirLog1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        menuCompararGPGGAActionPerformed(null);
    }//GEN-LAST:event_jButton1ActionPerformed


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
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirLog;
    private javax.swing.JButton btnAbrirLog1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem menuCompararGPGGA;
    private javax.swing.JMenuItem menuExtrairBDGSA;
    private javax.swing.JMenuItem menuExtrairBDGSV;
    private javax.swing.JMenuItem menuExtrairGAGSA;
    private javax.swing.JMenuItem menuExtrairGLGSV;
    private javax.swing.JMenuItem menuExtrairGNGSA;
    private javax.swing.JMenuItem menuExtrairGPGGAbrutas;
    private javax.swing.JMenuItem menuExtrairGPGGAprocessadas;
    private javax.swing.JMenuItem menuExtrairGPGSA;
    private javax.swing.JMenuItem menuExtrairGPGSV;
    private javax.swing.JMenuItem menuExtrairGPRMC;
    private javax.swing.JMenuItem menuExtrairIMGSA;
    private javax.swing.JMenuItem menuExtrairPGLOR;
    private javax.swing.JMenuItem menuExtrairQZGSA;
    private javax.swing.JMenuItem menuOpenLog;
    private javax.swing.JMenuItem menuOpenNMEA;
    private javax.swing.JMenuItem menuShowNMEApvt;
    private javax.swing.JMenuItem menuShowNMEAraw;
    private javax.swing.JTextField txtLog;
    private javax.swing.JTextField txtNMEA;
    // End of variables declaration//GEN-END:variables
}
