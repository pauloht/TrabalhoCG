/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Pipeline.Projecao.ProjecaoEnum;
import ViewComponents.MyJPanel;
import java.awt.Color;
import java.util.Locale;
import javax.swing.JComboBox;

/**
 *
 * @author FREE
 */
public class PlanoPanel extends javax.swing.JPanel {
    public MyJPanel panel;
    /**
     * Creates new form PlanoPanel
     */
    public PlanoPanel() {
        initComponents();
        bgProjecao.add(bFrontal);
        bgProjecao.add(bLateral);
        bgProjecao.add(bTopo);
        bgProjecao.add(bPerspectiva);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        bgProjecao = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        bFrontal = new javax.swing.JRadioButton();
        bLateral = new javax.swing.JRadioButton();
        bTopo = new javax.swing.JRadioButton();
        bPerspectiva = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfUMin = new javax.swing.JTextField();
        tfUMax = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfVMin = new javax.swing.JTextField();
        tfVMax = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        tfXMax = new javax.swing.JTextField();
        tfYMax = new javax.swing.JTextField();
        tfXMin = new javax.swing.JTextField();
        tfYMin = new javax.swing.JTextField();
        bMapAutomatico = new javax.swing.JToggleButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 10));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                formComponentResized(evt);
            }
        });

        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 0, 0), 5, true));

        bFrontal.setText("Frontal");
        bFrontal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFrontalActionPerformed(evt);
            }
        });

        bLateral.setText("Lateral");
        bLateral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLateralActionPerformed(evt);
            }
        });

        bTopo.setText("Topo");
        bTopo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTopoActionPerformed(evt);
            }
        });

        bPerspectiva.setText("Perspectiva");
        bPerspectiva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPerspectivaActionPerformed(evt);
            }
        });

        jLabel1.setText("DP");

        jTextField1.setText("10");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bFrontal)
                    .addComponent(bLateral)
                    .addComponent(bTopo)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(bPerspectiva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(bFrontal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bLateral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bTopo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPerspectiva)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 135, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Projecao", jPanel1);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51), 5));

        jLabel2.setText("Janela");

        jLabel4.setText("UMax :");

        jLabel5.setText("UMin :");

        tfUMin.setText("jTextField2");
        tfUMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUMinActionPerformed(evt);
            }
        });

        tfUMax.setText("jTextField2");
        tfUMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfUMaxActionPerformed(evt);
            }
        });

        jLabel6.setText("VMax :");

        jLabel7.setText("VMin :");

        tfVMin.setText("jTextField2");
        tfVMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfVMinActionPerformed(evt);
            }
        });

        tfVMax.setText("jTextField2");
        tfVMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfVMaxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tfUMin, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfUMax, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfVMax))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(tfVMin, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tfUMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tfVMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfUMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(tfVMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 51), 5));

        jLabel3.setText("Mundo");

        jLabel8.setText("XMax :");

        jLabel9.setText("XMin  :");

        jLabel10.setText("YMax :");

        jLabel11.setText("YMin :");

        tfXMax.setText("jTextField2");
        tfXMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfXMaxActionPerformed(evt);
            }
        });

        tfYMax.setText("jTextField2");
        tfYMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfYMaxActionPerformed(evt);
            }
        });

        tfXMin.setText("jTextField2");
        tfXMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfXMinActionPerformed(evt);
            }
        });

        tfYMin.setText("jTextField2");
        tfYMin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfYMinActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfXMin, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfYMin)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfXMax, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfYMax)))
                .addGap(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(tfXMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfYMax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11)
                    .addComponent(tfXMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfYMin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 26, Short.MAX_VALUE))
        );

        bMapAutomatico.setText("MapeamentoAutomatico");
        bMapAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMapAutomaticoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bMapAutomatico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(bMapAutomatico)
                .addGap(1, 1, 1)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jTabbedPane2.addTab("Mapeamento", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bFrontalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFrontalActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_bFrontalActionPerformed

    private void bLateralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLateralActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_bLateralActionPerformed

    private void bTopoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTopoActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_bTopoActionPerformed

    private void bPerspectivaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPerspectivaActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_bPerspectivaActionPerformed

    private void tfXMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfXMaxActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_tfXMaxActionPerformed

    private void bMapAutomaticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMapAutomaticoActionPerformed
        // TODO add your handling code here:
        mudarEstadoTF();
        panel.mapeamentoAutomatico = bMapAutomatico.isSelected();
        if (bMapAutomatico.isSelected())
        {
            carregarValoresTF();
        }
    }//GEN-LAST:event_bMapAutomaticoActionPerformed

    private void formComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentResized
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_formComponentResized

    private void tfUMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUMaxActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_tfUMaxActionPerformed

    private void tfUMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfUMinActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_tfUMinActionPerformed

    private void tfVMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfVMaxActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_tfVMaxActionPerformed

    private void tfVMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfVMinActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_tfVMinActionPerformed

    private void tfYMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfYMaxActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_tfYMaxActionPerformed

    private void tfXMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfXMinActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_tfXMinActionPerformed

    private void tfYMinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfYMinActionPerformed
        // TODO add your handling code here:
        aoMudar();
    }//GEN-LAST:event_tfYMinActionPerformed

    public void update()
    {
        if (panel != null)
        {
            switch (panel.tipoProjecao)
            {
                case FRONTAL :
                    bFrontal.setSelected(true);
                    break;
                case PERSPERCTIVE :
                    bPerspectiva.setSelected(true);
                    break;
                case SIDE :
                    bLateral.setSelected(true);
                    break;
                case TOP :
                    bTopo.setSelected(true);
                    break;
            }
            if (panel.mapeamentoAutomatico)
            {
                bMapAutomatico.setSelected(true);
            }
            else
            {
                bMapAutomatico.setSelected(false);
            }
            mudarEstadoTF();
            carregarValoresTF();
        }
        //System.out.println("UPDATE PLANOPANELFEITO");
    }
    
    /**
     * Sobreescreve valores TF de acordo com parametros de mapeamento
     */
    private void carregarValoresTF()
    {
        tfUMax.setText(String.format(Locale.US,"%.4f", panel.map.UMax));
        tfUMin.setText(String.format(Locale.US,"%.4f", panel.map.UMin));
        tfVMax.setText(String.format(Locale.US,"%.4f", panel.map.VMax));
        tfVMin.setText(String.format(Locale.US,"%.4f", panel.map.VMin));
        tfXMax.setText(String.format(Locale.US,"%.4f", panel.map.XMax));
        tfXMin.setText(String.format(Locale.US,"%.4f", panel.map.XMin));
        tfYMax.setText(String.format(Locale.US,"%.4f", panel.map.YMax));
        tfYMin.setText(String.format(Locale.US,"%.4f", panel.map.YMin));
    }
    
    /**
     * Muda estado dos textfields dependendo do estado do ToggleeButton
     */
    private void mudarEstadoTF()
    {
        if (bMapAutomatico.isSelected())
        {
            tfUMax.setEnabled(false);
            tfUMin.setEnabled(false);
            tfVMax.setEnabled(false);
            tfVMin.setEnabled(false);
            tfXMax.setEnabled(false);
            tfXMin.setEnabled(false);
            tfYMax.setEnabled(false);
            tfYMin.setEnabled(false);
        }
        else
        {
            tfUMax.setEnabled(true);
            tfUMin.setEnabled(true);
            tfVMax.setEnabled(true);
            tfVMin.setEnabled(true);
            tfXMax.setEnabled(true);
            tfXMin.setEnabled(true);
            tfYMax.setEnabled(true);
            tfYMin.setEnabled(true);
        }
    }
    
    private void aoMudar()
    {
        System.out.println("AO MUDAR CHAMADO");
        if (bFrontal.isSelected())
        {
            panel.tipoProjecao = ProjecaoEnum.FRONTAL;
        }
        else if (bLateral.isSelected())
        {
            panel.tipoProjecao = ProjecaoEnum.SIDE;
        }
        else if (bPerspectiva.isSelected())
        {
            panel.tipoProjecao = ProjecaoEnum.PERSPERCTIVE;
        }
        else if (bTopo.isSelected())
        {
            panel.tipoProjecao = ProjecaoEnum.TOP;
        }
        try {
            panel.map.UMax = Double.parseDouble(tfUMax.getText());
            panel.map.UMin = Double.parseDouble(tfUMin.getText());
            panel.map.VMax = Double.parseDouble(tfVMax.getText());
            panel.map.VMin = Double.parseDouble(tfVMin.getText());
            
            panel.map.XMax = Double.parseDouble(tfXMax.getText());
            panel.map.XMin = Double.parseDouble(tfXMin.getText());
            panel.map.YMax = Double.parseDouble(tfYMax.getText());
            panel.map.YMin = Double.parseDouble(tfYMin.getText());
        }
        catch(ArithmeticException e)
        {
            carregarValoresTF();
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bFrontal;
    private javax.swing.JRadioButton bLateral;
    private javax.swing.JToggleButton bMapAutomatico;
    private javax.swing.JRadioButton bPerspectiva;
    private javax.swing.JRadioButton bTopo;
    private javax.swing.ButtonGroup bgProjecao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField tfUMax;
    private javax.swing.JTextField tfUMin;
    private javax.swing.JTextField tfVMax;
    private javax.swing.JTextField tfVMin;
    private javax.swing.JTextField tfXMax;
    private javax.swing.JTextField tfXMin;
    private javax.swing.JTextField tfYMax;
    private javax.swing.JTextField tfYMin;
    // End of variables declaration//GEN-END:variables
}
