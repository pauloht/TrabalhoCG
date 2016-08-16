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
import Generator.PolygonGenerator;
import Pipeline.CameraPacote.CameraClass;
import Pipeline.Projecao.ProjecaoEnum;
import ViewComponents.MyJPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
        camera.setP(new Vertex(0.00,0.00,4.00));
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
        update();
        this.setVisible(true);
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
        painelSelecionado.setBackground(Color.BLACK);
        carregarCamera();
        planoPanel.update();
        
        
        
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
                .addComponent(pMMTopL, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
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
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(lbTopR, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
            .addGroup(pMTopRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pMMTopR, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
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
                .addComponent(pMMBottomL, javax.swing.GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
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
                .addComponent(pMMBottomR, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
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
    
    public void setScene(List<Polygon> polyLista) {
        scene.clearPolygonos();
        scene.addPolygon(polyLista);
    }
    
    //</editor-fold>

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btDireitaPlano;
    private javax.swing.JButton btEsquerdaPlano;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
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
    private javax.swing.JTextField tfPX;
    private javax.swing.JTextField tfPY;
    private javax.swing.JTextField tfPZ;
    private javax.swing.JTextField tfVRPX;
    private javax.swing.JTextField tfVRPY;
    private javax.swing.JTextField tfVRPZ;
    private javax.swing.JTextField tfViewUX;
    private javax.swing.JTextField tfViewUY;
    private javax.swing.JTextField tfViewUZ;
    // End of variables declaration//GEN-END:variables
}
