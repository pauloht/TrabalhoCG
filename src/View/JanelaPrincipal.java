/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Data.Base_Data.Matrix;
import Data.Base_Data.Vertex;
import Data.Composta_Data.Polygon;
import Data.Composta_Data.SuperPolygon;
import Generator.Extrusao;
import Generator.PolygonGenerator;
import Modificadores.Bevel;
import Pipeline.CameraPacote.CameraClass;
import Pipeline.Projecao.ProjecaoEnum;
import ViewComponents.MyJPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
    public MyJPanel mypainelSelecionado;
    
    
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
    SuperPolygon scene = null;
    
    public PlanoPanel planoPanel;
    
    public static JanelaPrincipal janela = null;
    
    
    //adicionar variavel que controle parametros de mapeamento
    //Mapping map
    
    /**
     * Creates new form JanelaPrincipal
     */
    public JanelaPrincipal(List<Polygon> polyLista) {
        initComponents();
        janela = this;
        camera = CameraClass.getInstance();
        camera.setP(new Vertex(0.00,0.00,0.00));
        camera.setVrp(new Vertex(0.00,0.00,5.00));
        camera.setView_up(new Vertex(0.00,-1.00,0.00));
        
        scene = new SuperPolygon( polyLista );
        pTopL = new MyJPanel(scene.getSuperPolygon());
        pTopL.map.setNiceParametros(pTopL.polygon);
        pTopL.setBackground(Color.GRAY);
        pTopL.tipoProjecao = ProjecaoEnum.FRONTAL;
        
        pMMTopL.setLayout(null);
        pMMTopL.add(pTopL);
        pTopL.setSize(pMMTopL.getSize());
        
        pBottomL = new MyJPanel(scene.getSuperPolygon());
        pBottomL.setBackground(Color.GRAY);
        pBottomL.tipoProjecao = ProjecaoEnum.TOP;
        
        pMMBottomL.setLayout(null);
        pMMBottomL.add(pBottomL);
        pBottomL.setSize(pMMBottomL.getSize());
        
        pTopR = new MyJPanel(scene.getSuperPolygon());
        pTopR.setBackground(Color.GRAY);
        pTopR.tipoProjecao = ProjecaoEnum.SIDE;
        
        pMMTopR.setLayout(null);
        pMMTopR.add(pTopR);
        pTopR.setSize(pMMTopR.getSize());
        
        pBottomR = new MyJPanel(scene.getSuperPolygon());
        pBottomR.setBackground(Color.GRAY);
        pBottomR.tipoProjecao = ProjecaoEnum.PERSPERCTIVE;
        
        pMMBottomR.setLayout(null);
        pMMBottomR.add(pBottomR);
        pBottomR.setSize(pMMBottomR.getSize());
        
        
        
        planoPanel = new PlanoPanel();
        
        pParaPlanos.add(planoPanel);
        //pParaPlanos.add(planoPanel);
        //planoPanel.setPreferredSize(pParaPlanos.getPreferredSize());
        
        contadorPainelSelecionado = 0;
        painelSelecionado = pMTopL;
        mypainelSelecionado = pTopL;
        
        
        pack();
        ViewGlobal.centralizarJanela(this);
        
        planoPanel.setSize(pParaPlanos.getSize());
        seletorObjetos.addPolygon(polyLista);
        resetarJanelaAdicionarObjetoTF();
        update();
        this.setVisible(true);
        this.revalidate();
    }
    
    public static void foiFeitoRepaint(MyJPanel onde)
    {
        if (janela != null)
        {
            if (janela.mypainelSelecionado == onde)
            {
                if (janela.planoPanel != null)
                {
                    janela.planoPanel.update();
                }
            }
        }
    }
    
    private void activatePanelSelecionado()
    {
        if (painelSelecionado != null)
        {
            painelSelecionado.setBackground(Color.BLACK);
        }
    }
    
    private void resetPanelSelecionado()
    {
        if (painelSelecionado != null)
        {
            painelSelecionado.setBackground(Color.RED);
        }
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
        
        pTopR.polygon = scene.getSuperPolygon();
        pTopL.polygon = scene.getSuperPolygon();
        pBottomR.polygon = scene.getSuperPolygon();
        pBottomL.polygon = scene.getSuperPolygon();
        
        lbBottomL.setText(pBottomL.tipoProjecao.toString());
        lbBottomR.setText(pBottomR.tipoProjecao.toString());
        lbTopL.setText(pTopL.tipoProjecao.toString());
        lbTopR.setText(pTopR.tipoProjecao.toString());
        
        switch (contadorPainelSelecionado)
        {
            case 0 :
                lbNomeDoPlano.setText("Topo Esquerdo");
                painelSelecionado = pMTopL;
                mypainelSelecionado = pTopL;
                btEsquerdaPlano.setEnabled(false);
                break;
            case 1 :
                lbNomeDoPlano.setText("Topo Direito");
                painelSelecionado = pMTopR;
                mypainelSelecionado = pTopR;
                btEsquerdaPlano.setEnabled(true);
                break;
            case 2 :
                lbNomeDoPlano.setText("Embaixo Esquerdo");
                painelSelecionado = pMBottomL;
                mypainelSelecionado = pBottomL;
                btDireitaPlano.setEnabled(true);
                break;
            case 3 :
                lbNomeDoPlano.setText("Embaixo Direito");
                painelSelecionado = pMBottomR;
                mypainelSelecionado = pBottomR;
                btDireitaPlano.setEnabled(false);
                break;
            default :
                throw new IllegalArgumentException("DEFAULT EM SWITCH CASE EM UPDATE()");
        }
        planoPanel.panel = mypainelSelecionado;
        if (jPanel2.isShowing())
        {
            painelSelecionado.setBackground(Color.BLACK);
        }
        carregarCamera();
        planoPanel.update();
        
        pTopR.repaint();
        pTopL.repaint();
        pBottomR.repaint();
        pBottomL.repaint();
        
    }
    
    public void updateExterno()
    {
        update();
    }
    
    private void carregarCamera()
    {
        CameraClass camera = CameraClass.getInstance();
        Vertex vrp = camera.getVrp();
        Vertex p = camera.getP();
        Vertex viewUp = camera.getView_up();
        
        tfVRPX.setText(String.format(Locale.US, "%.2f", vrp.getPos_x()));
        tfVRPY.setText(String.format(Locale.US, "%.2f", vrp.getPos_z()));
        tfVRPZ.setText(String.format(Locale.US, "%.2f", vrp.getPos_y()));
        
        tfPX.setText(String.format(Locale.US, "%.2f", p.getPos_x()));
        tfPY.setText(String.format(Locale.US, "%.2f", p.getPos_y()));
        tfPZ.setText(String.format(Locale.US, "%.2f", p.getPos_z()));
        
        tfViewUX.setText(String.format(Locale.US, "%.2f", viewUp.getPos_x()));
        tfViewUY.setText(String.format(Locale.US, "%.2f", viewUp.getPos_y()));
        tfViewUZ.setText(String.format(Locale.US, "%.2f", viewUp.getPos_z()));
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
        pMMTopL = new javax.swing.JPanel();
        pMTopR = new javax.swing.JPanel();
        lbTopR = new javax.swing.JLabel();
        pMMTopR = new javax.swing.JPanel();
        pMBottomL = new javax.swing.JPanel();
        lbBottomL = new javax.swing.JLabel();
        pMMBottomL = new javax.swing.JPanel();
        pMBottomR = new javax.swing.JPanel();
        lbBottomR = new javax.swing.JLabel();
        pMMBottomR = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        pParaPlanos = new javax.swing.JPanel();
        lbNomeDoPlano = new javax.swing.JLabel();
        btDireitaPlano = new javax.swing.JButton();
        btEsquerdaPlano = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfVRPY = new javax.swing.JTextField();
        tfVRPX = new javax.swing.JTextField();
        tfVRPZ = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfPX = new javax.swing.JTextField();
        tfViewUY = new javax.swing.JTextField();
        tfPZ = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfViewUX = new javax.swing.JTextField();
        tfPY = new javax.swing.JTextField();
        tfViewUZ = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        seletorObjetos = new View.SeletorObjetos();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        rbBaseQuadrada = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfOrigemY = new javax.swing.JTextField();
        tfOrigemZ = new javax.swing.JTextField();
        tfOrigemX = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tfTamanhoLado = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        tfDistanciaX = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        tfDistanciaY = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tfDistanciaZ = new javax.swing.JTextField();
        tfNumeroSegmentos = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btCriar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfAvisosCriarObjeto = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pViews.setBackground(new java.awt.Color(0, 0, 0));
        pViews.setPreferredSize(new java.awt.Dimension(600, 600));

        pMTopL.setBackground(new java.awt.Color(255, 0, 51));

        lbTopL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTopL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTopL.setText("TopLeftText");

        pMMTopL.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout pMMTopLLayout = new javax.swing.GroupLayout(pMMTopL);
        pMMTopL.setLayout(pMMTopLLayout);
        pMMTopLLayout.setHorizontalGroup(
            pMMTopLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pMMTopLLayout.setVerticalGroup(
            pMMTopLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addComponent(pMMTopL, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
        );
        pMTopLLayout.setVerticalGroup(
            pMTopLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMTopLLayout.createSequentialGroup()
                .addComponent(lbTopL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pMMTopL, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pMTopR.setBackground(new java.awt.Color(255, 0, 0));

        lbTopR.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbTopR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTopR.setText("TopRightText");

        pMMTopR.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout pMMTopRLayout = new javax.swing.GroupLayout(pMMTopR);
        pMMTopR.setLayout(pMMTopRLayout);
        pMMTopRLayout.setHorizontalGroup(
            pMMTopRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pMMTopRLayout.setVerticalGroup(
            pMMTopRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pMTopRLayout = new javax.swing.GroupLayout(pMTopR);
        pMTopR.setLayout(pMTopRLayout);
        pMTopRLayout.setHorizontalGroup(
            pMTopRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pMTopRLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(lbTopR, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(pMTopRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pMMTopR, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        pMTopRLayout.setVerticalGroup(
            pMTopRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMTopRLayout.createSequentialGroup()
                .addComponent(lbTopR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pMMTopR, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pMBottomL.setBackground(new java.awt.Color(255, 0, 0));

        lbBottomL.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbBottomL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBottomL.setText("BottomLeftText");

        pMMBottomL.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout pMMBottomLLayout = new javax.swing.GroupLayout(pMMBottomL);
        pMMBottomL.setLayout(pMMBottomLLayout);
        pMMBottomLLayout.setHorizontalGroup(
            pMMBottomLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pMMBottomLLayout.setVerticalGroup(
            pMMBottomLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pMBottomLLayout = new javax.swing.GroupLayout(pMBottomL);
        pMBottomL.setLayout(pMBottomLLayout);
        pMBottomLLayout.setHorizontalGroup(
            pMBottomLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMBottomLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pMMBottomL, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pMBottomLLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(lbBottomL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );
        pMBottomLLayout.setVerticalGroup(
            pMBottomLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMBottomLLayout.createSequentialGroup()
                .addComponent(lbBottomL)
                .addGap(18, 18, 18)
                .addComponent(pMMBottomL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pMBottomR.setBackground(new java.awt.Color(255, 0, 0));

        lbBottomR.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbBottomR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbBottomR.setText("Perspectiva");

        pMMBottomR.setPreferredSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout pMMBottomRLayout = new javax.swing.GroupLayout(pMMBottomR);
        pMMBottomR.setLayout(pMMBottomRLayout);
        pMMBottomRLayout.setHorizontalGroup(
            pMMBottomRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pMMBottomRLayout.setVerticalGroup(
            pMMBottomRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addComponent(pMMBottomR, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        pMBottomRLayout.setVerticalGroup(
            pMBottomRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMBottomRLayout.createSequentialGroup()
                .addComponent(lbBottomR)
                .addGap(18, 18, 18)
                .addComponent(pMMBottomR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 255, 204));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));
        jPanel2.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                jPanel2AncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel2AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                jPanel2AncestorRemoved(evt);
            }
        });

        javax.swing.GroupLayout pParaPlanosLayout = new javax.swing.GroupLayout(pParaPlanos);
        pParaPlanos.setLayout(pParaPlanosLayout);
        pParaPlanosLayout.setHorizontalGroup(
            pParaPlanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pParaPlanosLayout.setVerticalGroup(
            pParaPlanosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 340, Short.MAX_VALUE)
        );

        lbNomeDoPlano.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbNomeDoPlano.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNomeDoPlano.setText("NomeDoPlano");

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(btEsquerdaPlano, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btDireitaPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lbNomeDoPlano, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(lbNomeDoPlano)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btDireitaPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEsquerdaPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(pParaPlanos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );

        jTabbedPane3.addTab("Planos", jPanel2);

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 5));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("VRP");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Y");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Z");

        tfVRPY.setText("jTextField1");

        tfVRPX.setText("jTextField1");

        tfVRPZ.setText("jTextField1");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("P");

        tfPX.setText("jTextField1");

        tfViewUY.setText("jTextField1");

        tfPZ.setText("jTextField1");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText(" UP");
        jLabel6.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("View");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        tfViewUX.setText("jTextField1");

        tfPY.setText("jTextField1");

        tfViewUZ.setText("jTextField1");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(6, 6, 6)
                .addComponent(tfVRPX, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(tfVRPY, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(tfVRPZ, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(31, 31, 31)
                .addComponent(tfPX, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(tfPY, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(tfPZ, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel7)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(tfViewUX, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(tfViewUY, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(tfViewUZ, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(6, 6, 6)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(tfVRPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfVRPY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfVRPZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfPX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfPZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(tfViewUX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfViewUY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfViewUZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(seletorObjetos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 140, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(seletorObjetos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 180, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Objetos", jPanel1);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5), "TipoDeBase"));

        rbBaseQuadrada.setText("Base Quadrada");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbBaseQuadrada)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rbBaseQuadrada)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5), "PropriedadesBase"));

        jLabel8.setText("Origem : ");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("X");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Y");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Z");

        tfOrigemY.setText("jTextField1");

        tfOrigemZ.setText("jTextField1");

        tfOrigemX.setText("jTextField1");

        jLabel11.setText("Tamanho Lado :");

        tfTamanhoLado.setText("jTextField1");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel10)
                .addGap(73, 73, 73)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(65, 65, 65))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfOrigemX, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(tfOrigemY, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfOrigemZ, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tfTamanhoLado)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfOrigemY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfOrigemZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfOrigemX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(tfTamanhoLado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5), "PropriedadesExtrusao"));

        jLabel9.setText("Distancia da Base : ");

        tfDistanciaX.setText("jTextField1");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel15.setText("X");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("Y");

        tfDistanciaY.setText("jTextField1");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Z");

        tfDistanciaZ.setText("jTextField1");

        tfNumeroSegmentos.setText("jTextField1");

        jLabel12.setText("Numero de Segmentos :");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                        .addComponent(jLabel16)
                        .addGap(54, 54, 54))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfDistanciaX, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(tfDistanciaY, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfDistanciaZ, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(36, 36, 36)))
                .addContainerGap())
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tfNumeroSegmentos, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfDistanciaX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDistanciaY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfDistanciaZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNumeroSegmentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        btCriar.setText("Criar");
        btCriar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCriarActionPerformed(evt);
            }
        });

        tfAvisosCriarObjeto.setColumns(20);
        tfAvisosCriarObjeto.setLineWrap(true);
        tfAvisosCriarObjeto.setRows(5);
        jScrollPane1.setViewportView(tfAvisosCriarObjeto);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btCriar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btCriar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("AdicionarObjeto", jPanel3);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            .addComponent(pViews, javax.swing.GroupLayout.DEFAULT_SIZE, 1003, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pViews, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
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

    private void btCriarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCriarActionPerformed
        // TODO add your handling code here:
        tentarAdicionarPoligono();
    }//GEN-LAST:event_btCriarActionPerformed

    private void jPanel2AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel2AncestorAdded
        // TODO add your handling code here:
        if (jPanel2.isShowing())
        {
            activatePanelSelecionado();
        }
    }//GEN-LAST:event_jPanel2AncestorAdded

    private void jPanel2AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel2AncestorMoved
        // TODO add your handling code here:
        //System.out.println("MOVED");
    }//GEN-LAST:event_jPanel2AncestorMoved

    private void jPanel2AncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel2AncestorRemoved
        // TODO add your handling code here:
        resetPanelSelecionado();
    }//GEN-LAST:event_jPanel2AncestorRemoved

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
                Polygon piramide = PolygonGenerator.generatePiramideQuadrada(5.00, 2.00, new Vertex(0.00,0.00,0.00));
                piramide.nome = "Piramide";
                Polygon cubo = PolygonGenerator.generateCube(2.00, new Vertex(0.00,0.00,0.00));
                cubo.nome = "Cubo";
                List< Polygon > polyLista = new ArrayList<>();
                //polyLista.add(piramide);
                //polyLista.add(cubo);
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
    
    public void setScene(List<Polygon> polyLista) {
        scene.clearPolygonos();
        scene.addPolygon(polyLista);
    }
    
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="relacionado a janela adicionar Objeto">
    
    private void resetarJanelaAdicionarObjetoTF()
    {
        tfOrigemX.setText("");
        tfOrigemY.setText("");
        tfOrigemZ.setText("");
        
        tfDistanciaX.setText("");
        tfDistanciaY.setText("");
        tfDistanciaZ.setText("");
        
        tfNumeroSegmentos.setText("");
        
        tfTamanhoLado.setText("1.00");
    }
    
    private void tentarAdicionarPoligono()
    {
        try{
            double origemX = Double.parseDouble( tfOrigemX.getText() );
            double origemY = Double.parseDouble( tfOrigemY.getText() );
            double origemZ = Double.parseDouble( tfOrigemZ.getText() );
            
            double translacaoX = Double.parseDouble( tfDistanciaX.getText() );
            double translacaoY = Double.parseDouble( tfDistanciaY.getText() );
            double translacaoZ = Double.parseDouble( tfDistanciaZ.getText() );
            
            double tamanhoLado = Double.parseDouble( tfTamanhoLado.getText() );
            int numeroSegmentos = Integer.parseInt( tfNumeroSegmentos.getText() );
            if (numeroSegmentos < 0 || tamanhoLado <= 0.00)
            {
                throw new IllegalArgumentException();
            }
            Vertex centroDaBase = new Vertex(origemX,origemY,origemZ);
            
            Polygon base = PolygonGenerator.generateBaseQuadrada(tamanhoLado, centroDaBase);
            
            Polygon real = Extrusao.gerarPolygonoExtrudido(base, numeroSegmentos ,centroDaBase, translacaoX, translacaoY, translacaoZ);
            
            //Bevel.bevelPolygon(real, 0.5);
            
            scene.addPolygon(real);
            tfAvisosCriarObjeto.setText("Adicionado!");
            update();
            seletorObjetos.update(scene.getPoligonos());
        }
        catch(Exception e)
        {
            resetarJanelaAdicionarObjetoTF();
            tfAvisosCriarObjeto.setText("Erro.... resetando valores!");
        }
    }
    
    //</editor-fold>

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCriar;
    private javax.swing.JButton btDireitaPlano;
    private javax.swing.JButton btEsquerdaPlano;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel lbBottomL;
    private javax.swing.JLabel lbBottomR;
    private javax.swing.JLabel lbNomeDoPlano;
    private javax.swing.JLabel lbTopL;
    private javax.swing.JLabel lbTopR;
    private javax.swing.JPanel pMBottomL;
    private javax.swing.JPanel pMBottomR;
    private javax.swing.JPanel pMMBottomL;
    private javax.swing.JPanel pMMBottomR;
    private javax.swing.JPanel pMMTopL;
    private javax.swing.JPanel pMMTopR;
    private javax.swing.JPanel pMTopL;
    private javax.swing.JPanel pMTopR;
    private javax.swing.JPanel pParaPlanos;
    private javax.swing.JPanel pViews;
    private javax.swing.JRadioButton rbBaseQuadrada;
    private View.SeletorObjetos seletorObjetos;
    private javax.swing.JTextArea tfAvisosCriarObjeto;
    private javax.swing.JTextField tfDistanciaX;
    private javax.swing.JTextField tfDistanciaY;
    private javax.swing.JTextField tfDistanciaZ;
    private javax.swing.JTextField tfNumeroSegmentos;
    private javax.swing.JTextField tfOrigemX;
    private javax.swing.JTextField tfOrigemY;
    private javax.swing.JTextField tfOrigemZ;
    private javax.swing.JTextField tfPX;
    private javax.swing.JTextField tfPY;
    private javax.swing.JTextField tfPZ;
    private javax.swing.JTextField tfTamanhoLado;
    private javax.swing.JTextField tfVRPX;
    private javax.swing.JTextField tfVRPY;
    private javax.swing.JTextField tfVRPZ;
    private javax.swing.JTextField tfViewUX;
    private javax.swing.JTextField tfViewUY;
    private javax.swing.JTextField tfViewUZ;
    // End of variables declaration//GEN-END:variables
}
