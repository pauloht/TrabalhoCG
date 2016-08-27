/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Data.Composta_Data.Polygon;
import SalvarCarregar.ArquivoCorrompidoException;
import SalvarCarregar.Carregar;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author FREE
 */
public class CarregarView extends javax.swing.JFrame {
    private boolean modoInvalido = false;
    private String decodedPath;
    /**
     * Creates new form CarregarView
     */
    public CarregarView(){
        initComponents();
        ViewGlobal.centralizarJanela(this);
        cbArquivo.removeAllItems();
        String path = CarregarView.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        try {
            decodedPath = URLDecoder.decode(path, "UTF-8");
            //System.out.println("decoded path = " + decodedPath);
            File folder = new File(decodedPath);
            List< String > arquivos = listFilesForFolder(folder);
            for (String string : arquivos)
            {
                cbArquivo.addItem(string);
            }
        }
        catch (UnsupportedEncodingException ex) {
            modoInvalido = true;
            taAvisos.setText("Erro em encoding(UTF-8)...nao pode carregar!");
            Logger.getLogger(CarregarView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List< String > listFilesForFolder(final File folder) {
        List< String > retorno = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                //System.out.println("Diretorio nao faz nada *smileface*");
            } else {
                String fileName = fileEntry.getName();
                int tamanho = fileName.length();
                if (tamanho >=5)
                {
                    /*
                    System.out.println("Analisando ... " + fileName);
                    System.out.println("-1 = " + fileName.charAt(tamanho-1));
                    System.out.println("-2 = " + fileName.charAt(tamanho-2));
                    System.out.println("-3 = " + fileName.charAt(tamanho-3));
                    System.out.println("-4 = " + fileName.charAt(tamanho-4));
                    */
                    if (fileName.endsWith(".txt"))
                    {
                        retorno.add(fileName);
                    }
                    else
                    {
                        //System.out.println("fileName = " + fileName + " recusado!");
                    }
                }
            }
        }
        return(retorno);
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cbArquivo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btCarregar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAvisos = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 5), "CarregarCena"));

        cbArquivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Arquivo :");

        btCarregar.setText("Carregar");
        btCarregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCarregarActionPerformed(evt);
            }
        });

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        taAvisos.setColumns(20);
        taAvisos.setRows(5);
        jScrollPane1.setViewportView(taAvisos);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbArquivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btCarregar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btCarregar, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btCarregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarregarActionPerformed
        // TODO add your handling code here:
        JanelaPrincipal janela = JanelaPrincipal.janela;
        if (!modoInvalido&&janela!=null)
        {
            List< Polygon > scenaCarregada;
            File arquivoCarregado = new File(decodedPath + cbArquivo.getSelectedItem());
            try{
                scenaCarregada = Carregar.lerArquivo(arquivoCarregado);
                
                int resposta;
                resposta = JOptionPane.showConfirmDialog(this, "Isso sobreescrevera a cena corrente!, deseja continuar?", "Aviso",JOptionPane.YES_NO_OPTION);
                if (resposta==0)
                {
                    //continuar
                    janela.scene.clearPolygonos();
                    janela.scene.addPolygon(scenaCarregada);
                    janela.updateExternoPoligono();
                    taAvisos.setText("Nova cena carregada com sucesso!");
                }
                else
                {
                    //cancelar
                    taAvisos.setText("Carregamento cancelado");
                }
            }
            catch(ArquivoCorrompidoException e)
            {
                taAvisos.setText("Arquivo corrompido/invalido !");
            } catch (FileNotFoundException ex) {
                taAvisos.setText("Arquivo nao encontrado erro!");
                Logger.getLogger(CarregarView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                taAvisos.setText("IOExcetion erro!");
                Logger.getLogger(CarregarView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btCarregarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        aoFechar();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if (JanelaPrincipal.janela!=null)
        {
            JanelaPrincipal.janela.setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(CarregarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CarregarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CarregarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CarregarView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CarregarView().setVisible(true);
                System.out.println("DEU MERDA :d");
            }
        });
    }
    
    private void aoFechar()
    {
        if (JanelaPrincipal.janela!=null)
        {
            JanelaPrincipal.janela.setVisible(true);
        }
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCarregar;
    private javax.swing.JComboBox<String> cbArquivo;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taAvisos;
    // End of variables declaration//GEN-END:variables
}
