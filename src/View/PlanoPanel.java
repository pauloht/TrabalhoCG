/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Pipeline.Projecao.ProjecaoEnum;
import ViewComponents.MyJPanel;
import java.awt.Color;
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
        bFrontal = new javax.swing.JRadioButton();
        bLateral = new javax.swing.JRadioButton();
        bTopo = new javax.swing.JRadioButton();
        bPerspectiva = new javax.swing.JRadioButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

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

        jTextField1.setText("10");

        jLabel1.setText("DP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bPerspectiva)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(bLateral)
                        .addComponent(bFrontal)
                        .addComponent(bTopo, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(bFrontal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bLateral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bTopo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPerspectiva)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(117, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Projecao", jPanel1);

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
        }
    }
    
    private void aoMudar()
    {
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
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton bFrontal;
    private javax.swing.JRadioButton bLateral;
    private javax.swing.JRadioButton bPerspectiva;
    private javax.swing.JRadioButton bTopo;
    private javax.swing.ButtonGroup bgProjecao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
