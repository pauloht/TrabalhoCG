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
import Pipeline.Projecao.ProjecaoEnum;
import ViewComponents.MyJPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class JanelaPrincipal extends javax.swing.JFrame {
    
    /**
     * Janela onde será topo esquerdo
     */
    MyJPanel pTopL;
    
    /**
     * Janela onde sera topo direito
     */
    MyJPanel pTopR;
    
    /**
     * Janela onde sera embaixo esquerdo
     */
    MyJPanel pBottomL;
    
    /**
     *  Janela onde sera embaixo direito
     */
    MyJPanel pBottomR;
    
    /**
     * Indica painel que esta sendo manipulado
     */
    JPanel painelSelecionado;
    
    /**
     *  Indica MyJpanel selecionado
     */
    MyJPanel mypainelSelecionado;
    
    
    /**
     * Indica painel selecionado
     */
    int contadorPainelSelecionado = 0;
    
    /**
     * Contem parametros como vpr,p,view up
     */
    CameraClass camera;
    
    /**
     * Conjunto de poligonos em coordenada de mundo
     */
    List< Polygon > scene;
    
    PlanoPanel planoPanel;
    
    //adicionar variavel que controle parametros de mapeamento
    //Mapping map
    
    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal(List<Polygon> polyLista) {
        initComponents();
        scene = polyLista;
        pTopL = new MyJPanel();
        pTopL.setBackground(Color.GRAY);
        pTopL.tipoProjecao = ProjecaoEnum.FRONTAL;
        
        pFront.setLayout(null);
        pFront.add(pTopL);
        pTopL.setSize(pFront.getSize());
        
        pBottomL = new MyJPanel();
        pBottomL.setBackground(Color.GRAY);
        pBottomL.tipoProjecao = ProjecaoEnum.TOP;
        
        pTop.setLayout(null);
        pTop.add(pBottomL);
        pBottomL.setSize(pTop.getSize());
        
        pTopR = new MyJPanel();
        pTopR.setBackground(Color.GRAY);
        pTopR.tipoProjecao = ProjecaoEnum.SIDE;
        
        pSide.setLayout(null);
        pSide.add(pTopR);
        pTopR.setSize(pSide.getSize());
        
        pBottomR = new MyJPanel();
        pBottomR.setBackground(Color.GRAY);
        pBottomR.tipoProjecao = ProjecaoEnum.PERSPERCTIVE;
        
        planoPanel = new PlanoPanel();
        
        pParaPlanos.add(planoPanel);
        //pParaPlanos.add(planoPanel);
        //planoPanel.setPreferredSize(pParaPlanos.getPreferredSize());
        
        contadorPainelSelecionado = 0;
        painelSelecionado = pMTopL;
        
        pack();
        ViewGlobal.centralizarJanela(this);
        
        camera = CameraClass.getInstance();
        camera.setP(new Vertex(0.00,0.00,4.00));
        camera.setVrp(new Vertex(0.00,0.00,5.00));
        camera.setView_up(new Vertex(0.00,1.00,0.00));
        
        updateVisao();
        update();
        this.setVisible(true);
    }
    
    /**
     * Update de visao de acordo com parametros(camera,projecao,mapeamento,etc)
     */
    private void update()
    {
        if (painelSelecionado != null)
        {
            painelSelecionado.setBackground(Color.RED);
        }
        
        lbBottomL.setText(pBottomL.tipoProjecao.toString());
        lbBottomR.setText(pBottomR.tipoProjecao.toString());
        lbTopL.setText(pTopL.tipoProjecao.toString());
        lbTopR.setText(pTopR.tipoProjecao.toString());
        
        switch (contadorPainelSelecionado)
        {
            case 0 :
                painelSelecionado = pMTopL;
                mypainelSelecionado = pTopL;
                btEsquerdaPlano.setEnabled(false);
                break;
            case 1 :
                painelSelecionado = pMTopR;
                mypainelSelecionado = pTopR;
                btEsquerdaPlano.setEnabled(true);
                break;
            case 2 :
                painelSelecionado = pMBottomL;
                mypainelSelecionado = pBottomL;
                btDireitaPlano.setEnabled(true);
                break;
            case 3 :
                painelSelecionado = pMBottomR;
                mypainelSelecionado = pBottomR;
                btDireitaPlano.setEnabled(false);
                break;
            default :
                throw new IllegalArgumentException("DEFAULT EM SWITCH CASE EM UPDATE()");
        }
        planoPanel.panel = mypainelSelecionado;
        painelSelecionado.setBackground(Color.BLACK);
        planoPanel.update();
        
    }
    
    /**
     * Realiza o desenho em si
     */
    private void updateVisao()
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
        
        
        
        /*
        Matrix depoisProjecaoFrontal = frontProjecao.multiplicacaoMatrix(poly.get3DVertexMatrix());
        frontPolygon.set3DVertexMatrix(depoisProjecaoFrontal);
        System.out.println("frontMatrix = " + frontPolygon.get3DVertexMatrix());
        
        Matrix depoisProjecaoTopo = topProjecao.multiplicacaoMatrix(poly.get3DVertexMatrix());
        topPolygon.set3DVertexMatrix(depoisProjecaoTopo);
        System.out.println("topMatrix = " + topPolygon.get3DVertexMatrix());
        
        Matrix depoisProjecaoLado = sideProjecao.multiplicacaoMatrix(poly.get3DVertexMatrix());
        sidePolygon.set3DVertexMatrix(depoisProjecaoLado);
        System.out.println("ladoMatrix = " + sidePolygon.get3DVertexMatrix());
        */
        
        /*
        //front
        Matrix frontMatrix = Pipeline.Matrix3Dto2D.Transform.retirarZ( frontPolygon.get3DVertexMatrix() );
        frontPolygon.set2DVertexMatrix( frontMatrix );
        
        //top
        Matrix topMatrix = Pipeline.Matrix3Dto2D.Transform.retirarZ( topPolygon.get3DVertexMatrix() );
        topPolygon.set2DVertexMatrix( topMatrix );
        
        //lado
        Matrix sideMatrix = Pipeline.Matrix3Dto2D.Transform.retirarZ( sidePolygon.get3DVertexMatrix() );
        sidePolygon.set2DVertexMatrix( sideMatrix );
        */
        
        pTopL.addPolygon(frontPolygon);
        
        pTopR.addPolygon(sidePolygon);
        
        pBottomL.addPolygon(topPolygon);
        planoPanel.setSize(pParaPlanos.getSize());
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
        pMTopL = new javax.swing.JPanel();
        lbTopL = new javax.swing.JLabel();
        pFront = new javax.swing.JPanel();
        pMTopR = new javax.swing.JPanel();
        lbTopR = new javax.swing.JLabel();
        pSide = new javax.swing.JPanel();
        pMBottomL = new javax.swing.JPanel();
        lbBottomL = new javax.swing.JLabel();
        pTop = new javax.swing.JPanel();
        pMBottomR = new javax.swing.JPanel();
        lbBottomR = new javax.swing.JLabel();
        pPerspectiva = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        pParaPlanos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btDireitaPlano = new javax.swing.JButton();
        btEsquerdaPlano = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pViews.setBackground(new java.awt.Color(0, 0, 0));
        pViews.setPreferredSize(new java.awt.Dimension(600, 600));

        pMTopL.setBackground(new java.awt.Color(255, 0, 51));

        lbTopL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTopL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTopL.setText("TopLeftText");

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

        javax.swing.GroupLayout pMTopLLayout = new javax.swing.GroupLayout(pMTopL);
        pMTopL.setLayout(pMTopLLayout);
        pMTopLLayout.setHorizontalGroup(
            pMTopLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMTopLLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(lbTopL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pMTopLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pFront, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
        );
        pMTopLLayout.setVerticalGroup(
            pMTopLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMTopLLayout.createSequentialGroup()
                .addComponent(lbTopL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pFront, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pMTopR.setBackground(new java.awt.Color(255, 0, 0));

        lbTopR.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTopR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTopR.setText("TopRightText");

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

        javax.swing.GroupLayout pMTopRLayout = new javax.swing.GroupLayout(pMTopR);
        pMTopR.setLayout(pMTopRLayout);
        pMTopRLayout.setHorizontalGroup(
            pMTopRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMTopRLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(lbTopR, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(pMTopRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pSide, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        pMTopRLayout.setVerticalGroup(
            pMTopRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMTopRLayout.createSequentialGroup()
                .addComponent(lbTopR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pSide, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pMBottomL.setBackground(new java.awt.Color(255, 0, 0));

        lbBottomL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbBottomL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBottomL.setText("BottomLeftText");

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

        javax.swing.GroupLayout pMBottomLLayout = new javax.swing.GroupLayout(pMBottomL);
        pMBottomL.setLayout(pMBottomLLayout);
        pMBottomLLayout.setHorizontalGroup(
            pMBottomLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMBottomLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTop, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pMBottomLLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(lbBottomL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        pMBottomLLayout.setVerticalGroup(
            pMBottomLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMBottomLLayout.createSequentialGroup()
                .addComponent(lbBottomL)
                .addGap(18, 18, 18)
                .addComponent(pTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pMBottomR.setBackground(new java.awt.Color(255, 0, 0));

        lbBottomR.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbBottomR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBottomR.setText("Perspectiva");

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

        javax.swing.GroupLayout pMBottomRLayout = new javax.swing.GroupLayout(pMBottomR);
        pMBottomR.setLayout(pMBottomRLayout);
        pMBottomRLayout.setHorizontalGroup(
            pMBottomRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMBottomRLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(lbBottomR, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pMBottomRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pPerspectiva, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addContainerGap())
        );
        pMBottomRLayout.setVerticalGroup(
            pMBottomRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMBottomRLayout.createSequentialGroup()
                .addComponent(lbBottomR)
                .addGap(18, 18, 18)
                .addComponent(pPerspectiva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 255, 204));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout pParaPlanosLayout = new javax.swing.GroupLayout(pParaPlanos);
        pParaPlanos.setLayout(pParaPlanosLayout);
        pParaPlanosLayout.setHorizontalGroup(
            pParaPlanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pParaPlanosLayout.setVerticalGroup(
            pParaPlanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("NomeDoPlano");

        btDireitaPlano.setText("Direita");
        btDireitaPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDireitaPlanoActionPerformed(evt);
            }
        });

        btEsquerdaPlano.setText("Esquerda");
        btEsquerdaPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEsquerdaPlanoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pParaPlanos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(btEsquerdaPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDireitaPlano, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDireitaPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEsquerdaPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(pParaPlanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Planos", jPanel2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Camera", jPanel7);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jTabbedPane3)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Config", jPanel6);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 287, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 613, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Camera", jPanel1);

        javax.swing.GroupLayout pViewsLayout = new javax.swing.GroupLayout(pViews);
        pViews.setLayout(pViewsLayout);
        pViewsLayout.setHorizontalGroup(
            pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pViewsLayout.createSequentialGroup()
                .addGroup(pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pMTopL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pMBottomL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pMTopR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pMBottomR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1))
        );
        pViewsLayout.setVerticalGroup(
            pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pViewsLayout.createSequentialGroup()
                .addGroup(pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pMTopL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pMTopR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(pViewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pMBottomL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pMBottomR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
            .addComponent(pViews, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEsquerdaPlanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEsquerdaPlanoActionPerformed
        // TODO add your handling code here:
        contadorPainelSelecionado--;
        update();
    }//GEN-LAST:event_btEsquerdaPlanoActionPerformed

    private void btDireitaPlanoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDireitaPlanoActionPerformed
        // TODO add your handling code here:
        contadorPainelSelecionado++;
        update();
    }//GEN-LAST:event_btDireitaPlanoActionPerformed

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
        return(pTopL);
    }
    
    public MyJPanel getSidePanel()
    {
        return(pTopR);
    }
    
    public MyJPanel getTopPanel()
    {
        return(pBottomL);
    }
    
    public void setScene(List<Polygon> scene) {
        this.scene = scene;
    }
    
    //</editor-fold>

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDireitaPlano;
    private javax.swing.JButton btEsquerdaPlano;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbBottomL;
    private javax.swing.JLabel lbBottomR;
    private javax.swing.JLabel lbTopL;
    private javax.swing.JLabel lbTopR;
    private javax.swing.JPanel pFront;
    private javax.swing.JPanel pMBottomL;
    private javax.swing.JPanel pMBottomR;
    private javax.swing.JPanel pMTopL;
    private javax.swing.JPanel pMTopR;
    private javax.swing.JPanel pParaPlanos;
    private javax.swing.JPanel pPerspectiva;
    private javax.swing.JPanel pSide;
    private javax.swing.JPanel pTop;
    private javax.swing.JPanel pViews;
    // End of variables declaration//GEN-END:variables
}
