/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Data.Base_Data.Matrix;
import Data.Base_Data.Vertex;
import Data.Composta_Data.Polygon;
import Generator.PolygonGenerator;
import Pipeline.CameraPacote.CameraClass;
import ViewComponents.MyJPanel;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FREE
 */
public class JanelaPrincipal extends javax.swing.JFrame {
    
    /**
     * Janela onde será desenhado projecao frontal 
     */
    MyJPanel frontPanel;
    
    /**
     * Janela onde sera desenhado projecao lateral
     */
    MyJPanel sidePanel;
    
    /**
     * Janela onde sera desenhado projecao topo
     */
    MyJPanel topPanel;
    
    /**
     * Contem parametros como vpr,p,view up
     */
    CameraClass camera;
    
    /**
     * Conjunto de poligonos em coordenada de mundo
     */
    List< Polygon > scene;
    
    //adicionar variavel que controle parametros de mapeamento
    //Mapping map
    
    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal(List<Polygon> polyLista) {
        initComponents();
        scene = polyLista;
        frontPanel = new MyJPanel();
        frontPanel.nome = "FrontPanel";
        frontPanel.setBackground(Color.GRAY);
        
        pFront.setLayout(null);
        pFront.add(frontPanel);
        frontPanel.setSize(pFront.getSize());
        
        topPanel = new MyJPanel();
        topPanel.nome = "TopPanel";
        topPanel.setBackground(Color.GRAY);
        
        pTop.setLayout(null);
        pTop.add(topPanel);
        topPanel.setSize(pTop.getSize());
        
        sidePanel = new MyJPanel();
        sidePanel.nome = "SidePanel";
        sidePanel.setBackground(Color.GRAY);
        
        pSide.setLayout(null);
        pSide.add(sidePanel);
        sidePanel.setSize(pSide.getSize());
        
        pack();
        ViewGlobal.centralizarJanela(this);
        
        camera = new CameraClass();
        camera.setP(new Vertex(0.00,0.00,4.00));
        camera.setVrp(new Vertex(0.00,0.00,5.00));
        camera.setView_up(new Vertex(0.00,1.00,0.00));
        
        update();
    }
    
    private void update()
    {
        Polygon frontPolygon,sidePolygon,topPolygon;
        
        Polygon poly = scene.get(0);
        
        System.out.println("Pontos inicias = " + poly.get3DVertexMatrix());
        
        Matrix operacao_de_camera = camera.getTransform_matrix();
        Matrix depois_da_camera = operacao_de_camera.multiplicacaoMatrix(poly.get3DVertexMatrix());
        poly.set3DVertexMatrix(depois_da_camera);
        
        System.out.println("Pontos depois da camera = " + poly.get3DVertexMatrix());
        
        frontPolygon = new Polygon(poly);
        sidePolygon = new Polygon(poly);
        topPolygon = new Polygon(poly);
        
        Matrix frontProjecao = Pipeline.Projecao.Project.getFrontMatrix();
        System.out.println("matrix projFrontal = " + frontProjecao);
        
        Matrix sideProjecao = Pipeline.Projecao.Project.getSideMatrix();
        System.out.println("matrix projLateral = " + sideProjecao);
        
        Matrix topProjecao = Pipeline.Projecao.Project.getTopMatrix();
        System.out.println("matrix projTopo = " + topProjecao);
        
        Matrix depoisProjecaoFrontal = frontProjecao.multiplicacaoMatrix(poly.get3DVertexMatrix());
        frontPolygon.set3DVertexMatrix(depoisProjecaoFrontal);
        System.out.println("frontMatrix = " + frontPolygon.get3DVertexMatrix());
        
        Matrix depoisProjecaoTopo = topProjecao.multiplicacaoMatrix(poly.get3DVertexMatrix());
        topPolygon.set3DVertexMatrix(depoisProjecaoTopo);
        System.out.println("topMatrix = " + topPolygon.get3DVertexMatrix());
        
        Matrix depoisProjecaoLado = sideProjecao.multiplicacaoMatrix(poly.get3DVertexMatrix());
        sidePolygon.set3DVertexMatrix(depoisProjecaoLado);
        System.out.println("ladoMatrix = " + sidePolygon.get3DVertexMatrix());

        //front
        Matrix frontMatrix = Pipeline.Matrix3Dto2D.Transform.retirarZ( frontPolygon.get3DVertexMatrix() );
        frontPolygon.set2DVertexMatrix( frontMatrix );
        
        //top
        Matrix topMatrix = Pipeline.Matrix3Dto2D.Transform.retirarZ( topPolygon.get3DVertexMatrix() );
        topPolygon.set2DVertexMatrix( topMatrix );
        
        //lado
        Matrix sideMatrix = Pipeline.Matrix3Dto2D.Transform.retirarZ( sidePolygon.get3DVertexMatrix() );
        sidePolygon.set2DVertexMatrix( sideMatrix );
        
        frontPanel.addPolygon(frontPolygon);
        
        sidePanel.addPolygon(sidePolygon);
        
        topPanel.addPolygon(topPolygon);
        
        this.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pViews = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pFront = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pSide = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        pTop = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        pPerspectiva = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pViews.setBackground(new java.awt.Color(0, 0, 0));
        pViews.setPreferredSize(new java.awt.Dimension(600, 600));

        jPanel1.setBackground(new java.awt.Color(255, 0, 51));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Front");

        pFront.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout pFrontLayout = new javax.swing.GroupLayout(pFront);
        pFront.setLayout(pFrontLayout);
        pFrontLayout.setHorizontalGroup(
            pFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pFrontLayout.setVerticalGroup(
            pFrontLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pFront, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pFront, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 204, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Side");

        pSide.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout pSideLayout = new javax.swing.GroupLayout(pSide);
        pSide.setLayout(pSideLayout);
        pSideLayout.setHorizontalGroup(
            pSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pSideLayout.setVerticalGroup(
            pSideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pSide, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pSide, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 102, 0));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Top");

        pTop.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout pTopLayout = new javax.swing.GroupLayout(pTop);
        pTop.setLayout(pTopLayout);
        pTopLayout.setHorizontalGroup(
            pTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pTopLayout.setVerticalGroup(
            pTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTop, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(pTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Perspectiva");

        pPerspectiva.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout pPerspectivaLayout = new javax.swing.GroupLayout(pPerspectiva);
        pPerspectiva.setLayout(pPerspectivaLayout);
        pPerspectivaLayout.setHorizontalGroup(
            pPerspectivaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pPerspectivaLayout.setVerticalGroup(
            pPerspectivaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pPerspectiva, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(pPerspectiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 255, 204));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 604, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("tab1", jPanel6);

        javax.swing.GroupLayout pViewsLayout = new javax.swing.GroupLayout(pViews);
        pViews.setLayout(pViewsLayout);
        pViewsLayout.setHorizontalGroup(
            pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pViewsLayout.createSequentialGroup()
                .addGroup(pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );
        pViewsLayout.setVerticalGroup(
            pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pViewsLayout.createSequentialGroup()
                .addGroup(pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pViews, javax.swing.GroupLayout.PREFERRED_SIZE, 893, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pViews, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Polygon poly = PolygonGenerator.generatePiramideQuadrada(5.00, 2.00, new Vertex(0.00,0.00,0.00));
                List< Polygon > polyLista = new ArrayList<>();
                polyLista.add(poly);
                JanelaPrincipal janela = new JanelaPrincipal(polyLista);
            }
        });
    }
    
    ////<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    
    public MyJPanel getFrontPanel()
    {
        return(frontPanel);
    }
    
    public MyJPanel getSidePanel()
    {
        return(sidePanel);
    }
    
    public MyJPanel getTopPanel()
    {
        return(topPanel);
    }
    
    public void setScene(List<Polygon> scene) {
        this.scene = scene;
    }
    
    //</editor-fold>

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel pFront;
    private javax.swing.JPanel pPerspectiva;
    private javax.swing.JPanel pSide;
    private javax.swing.JPanel pTop;
    private javax.swing.JPanel pViews;
    // End of variables declaration//GEN-END:variables
}
