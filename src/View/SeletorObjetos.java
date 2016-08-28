/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Data.Base_Data.Edge;
import Data.Base_Data.Matrix;
import Data.Composta_Data.Polygon;
import Generator.Extrusao;
import Modificadores.Bend;
import Modificadores.BendConstraints;
import Modificadores.Bevel;
import Modificadores.Blend;
import Modificadores.Twist;
import Transform_package.TransformTipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo.Tenorio
 */
public class SeletorObjetos extends javax.swing.JPanel {
    Polygon poligonoSelecionado = null;
    private List< Polygon > listaPoligonos = new ArrayList<>();
    
    private int contador = 0;
    
    /**
     * Creates new form SeletorObjetos
     */
    public SeletorObjetos() {
        initComponents();
        revalidarComponente();
        resetTF();
    }
    
    public void update(List< Polygon > poly)
    {
        listaPoligonos.clear();
        listaPoligonos.addAll(poly);
        revalidarComponente();
        resetTF();
    }
    
    public void addPolygon(Polygon poly)
    {
        listaPoligonos.add(poly);
        revalidarComponente();
    }
    
    public void addPolygon(List< Polygon > polyLista)
    {
        listaPoligonos.addAll(polyLista);
        revalidarComponente();
    }
    
    private void revalidarComponente()
    {
        if (listaPoligonos.size() == 0)
        {
            btDireita.setEnabled(false);
            btEsquerda.setEnabled(false);
            tfNumeroSegmentos.setEnabled(false);
            tfBevelNumer.setEnabled(false);
            tfTwistValorPorSegmento.setEnabled(false);
            tfBendValor.setEnabled(false);
            rbXMinus.setEnabled(false);
            rbXPlus.setEnabled(false);
            rbZMinus.setEnabled(false);
            rbZPlus.setEnabled(false);
            tfNomePoligono.setText("Sem poligono!");
            btDeletar.setEnabled(false);
            btResetarOG.setEnabled(false);
        }
        else
        {
            tfNumeroSegmentos.setEnabled(true);
            tfBevelNumer.setEnabled(true);
            tfTwistValorPorSegmento.setEnabled(true);
            tfBendValor.setEnabled(true);
            rbXMinus.setEnabled(true);
            rbXPlus.setEnabled(true);
            rbZMinus.setEnabled(true);
            rbZPlus.setEnabled(true);
            btDeletar.setEnabled(true);
            btResetarOG.setEnabled(true);
            
            poligonoSelecionado = listaPoligonos.get(contador);
            if (contador == 0)
            {
                btEsquerda.setEnabled(false);
            }
            else
            {
                btEsquerda.setEnabled(true);
            }
            if (contador == listaPoligonos.size()-1)
            {
                btDireita.setEnabled(false);
            }
            else
            {
                btDireita.setEnabled(true);
            }
            tfNomePoligono.setText(poligonoSelecionado.nome);
            tfNumeroSegmentos.setText( Integer.toString( poligonoSelecionado.segmentos.size() - 1) );
        }
    }
    
    private void resetTF()
    {
        if (poligonoSelecionado != null)
        {
            tfBevelNumer.setText( Double.toString(poligonoSelecionado.fatorBevel) );
            tfTwistValorPorSegmento.setText( Double.toString(poligonoSelecionado.fatorTwist ) );
            tfBendValor.setText( Double.toString( poligonoSelecionado.fatorBend ) );
            switch (poligonoSelecionado.constanteBend)
            {
                case XMinus :
                    rbXMinus.setSelected(true);
                    break;
                case XPlus :
                    rbXPlus.setSelected(true);
                    break;
                case ZMinus :
                    rbZMinus.setSelected(true);
                    break;
                case ZPlus :
                    rbZPlus.setSelected(true);
                    break;
            }
        }
        tfEscalaX.setText("1.00");
        tfEscalaY.setText("1.00");
        tfEscalaZ.setText("1.00");
        tfTranslacaoX.setText("0.00");
        tfTranslacaoY.setText("0.00");
        tfTranslacaoZ.setText("0.00");
        tfValorRotacao.setText("0.00");
        rbXRotacao.setSelected(true);
        if (poligonoSelecionado != null)
        {
            tfNumeroSegmentos.setText( Integer.toString( poligonoSelecionado.segmentos.size() - 1) );
        }
        else
        {
            tfNumeroSegmentos.setText("");
        }
    }
    
    
    private void tentarAplicarTranslacao()
    {
        Double[] valores = tentarAplicarOperacao(TransformTipo.TRANSLACAO);
        if (valores != null)
        {
            Double valorX = valores[0];
            Double valorY = valores[1];
            Double valorZ = valores[2];
            
            Matrix translacaoMatrix = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(valorX, valorY, valorZ) );
            poligonoSelecionado.adicionarOperacoesGeometricas(translacaoMatrix);
            JanelaPrincipal.janela.updateExterno();
            taAvisos.setText("TranslacaoFeita!");
        }
        else
        {
            taAvisos.setText(taAvisos.getText() + "Erro na translacao!");
        }
    }
    
    private void tentarAplicarEscala()
    {
        Double[] valores = tentarAplicarOperacao(TransformTipo.ESCALA);
        if (valores != null)
        {
            Double valorX = valores[0];
            Double valorY = valores[1];
            Double valorZ = valores[2];
            
            Matrix escalaMatrix = new Matrix( Transform_package.TransformationPrimitives.get3Dscale(valorX, valorY, valorZ) );
            poligonoSelecionado.adicionarOperacoesGeometricas(escalaMatrix);
            JanelaPrincipal.janela.updateExterno();
            taAvisos.setText("EscalaFeita!");
        }
        else
        {
            taAvisos.setText(taAvisos.getText() + "Erro na escala!");
        }
    }
    
    private void tentarAplicarRotacao()
    {
        Double[] valores = tentarAplicarOperacao(TransformTipo.ROTACAO);
        if (valores != null)
        {
            Double valor = valores[0];
            
            Matrix rotacao;
            if (rbXRotacao.isSelected())
            {
                rotacao = new Matrix( Transform_package.TransformationPrimitives.get3DrotateX(valor) );
            }
            else if (rbYRotacao.isSelected())
            {
                rotacao = new Matrix( Transform_package.TransformationPrimitives.get3DrotateY(valor) );
            }
            else if (rbZRotacao.isSelected())
            {
                rotacao = new Matrix( Transform_package.TransformationPrimitives.get3DrotateZ(valor) );
            }
            else
            {
                throw new IllegalArgumentException("Nenhum dos botoes X/Y/Z precionado!");
            }
            
            poligonoSelecionado.adicionarOperacoesGeometricas(rotacao);
            JanelaPrincipal.janela.updateExterno();
            taAvisos.setText("RotacaoFeita!");
        }
        else
        {
            taAvisos.setText(taAvisos.getText() + "Erro na rotacao!");
        }
    }
    
    private Double[] tentarAplicarOperacao(TransformTipo tipo)
    {
        if (poligonoSelecionado != null)
        {
            try{
                Double valorX,valorY,valorZ;
                switch (tipo)
                {
                    case TRANSLACAO :
                        valorX = Double.parseDouble( tfTranslacaoX.getText() );
                        valorY = Double.parseDouble( tfTranslacaoY.getText() );
                        valorZ = Double.parseDouble( tfTranslacaoZ.getText() );
                        break;
                    case ESCALA : 
                        valorX = Double.parseDouble( tfEscalaX.getText() );
                        valorY = Double.parseDouble( tfEscalaY.getText() );
                        valorZ = Double.parseDouble( tfEscalaZ.getText() ); 
                        break;
                    case ROTACAO :
                        valorX = Math.toRadians( Double.parseDouble( tfValorRotacao.getText() ) );
                        valorY = new Double(valorX);
                        valorZ = new Double(valorX);
                        break;
                    default :
                        throw new IllegalArgumentException();
                }
                
                Double[] retorno = new Double[3];
                retorno[0] = valorX;
                retorno[1] = valorY;
                retorno[2] = valorZ;
                return(retorno);
                
            }catch(NumberFormatException e)
            {
                taAvisos.setText("Valores incorretos, cancelando operacao...");
                resetTF();
            }
        }
        else
        {
            taAvisos.setText("Poligono nulo!, cancelando operacao...");
            resetTF();
        }
        return(null);
    }
    
    private void tentarDeletar()
    {
        try {
            int indiceDeletado = contador;
            if (contador > 0)
            {
                contador--;
            }
            listaPoligonos.remove(indiceDeletado);
            poligonoSelecionado = null;
            revalidarComponente();
            JanelaPrincipal.janela.scene.deleteAt(indiceDeletado);
            JanelaPrincipal.janela.updateExterno();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            taAvisos.setText("Problema na delecao!...cancelando....");
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

        bgBendOpcoes = new javax.swing.ButtonGroup();
        bgOpcoesRotacao = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taAvisos = new javax.swing.JTextArea();
        btEsquerda = new javax.swing.JButton();
        btDireita = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tfTranslacaoX = new javax.swing.JTextField();
        tfTranslacaoY = new javax.swing.JTextField();
        tfTranslacaoZ = new javax.swing.JTextField();
        btTranslacao = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        tfValorRotacao = new javax.swing.JTextField();
        btRotacao = new javax.swing.JButton();
        rbXRotacao = new javax.swing.JRadioButton();
        rbYRotacao = new javax.swing.JRadioButton();
        rbZRotacao = new javax.swing.JRadioButton();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        tfEscalaX = new javax.swing.JTextField();
        tfEscalaY = new javax.swing.JTextField();
        tfEscalaZ = new javax.swing.JTextField();
        btEscala = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        tfBevelNumer = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tfTwistValorPorSegmento = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        tfBendValor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        rbXPlus = new javax.swing.JRadioButton();
        rbXMinus = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        rbZPlus = new javax.swing.JRadioButton();
        rbZMinus = new javax.swing.JRadioButton();
        tfNumeroSegmentos = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        tfNomePoligono = new javax.swing.JTextField();
        btDeletar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btResetarOG = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0), 3));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        taAvisos.setColumns(20);
        taAvisos.setLineWrap(true);
        taAvisos.setRows(5);
        jScrollPane1.setViewportView(taAvisos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btEsquerda.setText("Esquerda");
        btEsquerda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEsquerdaActionPerformed(evt);
            }
        });

        btDireita.setText("Direita");
        btDireita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDireitaActionPerformed(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setText("X :");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setText("Y :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setText("Z :");

        tfTranslacaoX.setText("jTextField1");

        tfTranslacaoY.setText("jTextField2");

        tfTranslacaoZ.setText("jTextField3");

        btTranslacao.setText("Aplicar");
        btTranslacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTranslacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btTranslacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTranslacaoX, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTranslacaoZ, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTranslacaoY, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tfTranslacaoX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tfTranslacaoY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(tfTranslacaoZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btTranslacao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Translacao", jPanel13);

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        tfValorRotacao.setText("jTextField1");

        btRotacao.setText("Aplicar");
        btRotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRotacaoActionPerformed(evt);
            }
        });

        bgOpcoesRotacao.add(rbXRotacao);
        rbXRotacao.setText("X");

        bgOpcoesRotacao.add(rbYRotacao);
        rbYRotacao.setText("Y");

        bgOpcoesRotacao.add(rbZRotacao);
        rbZRotacao.setText("Z");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(btRotacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(rbXRotacao)
                                .addGap(59, 59, 59)
                                .addComponent(rbYRotacao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(rbZRotacao))
                            .addComponent(tfValorRotacao, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE))
                        .addGap(20, 20, 20))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbXRotacao)
                    .addComponent(rbYRotacao)
                    .addComponent(rbZRotacao))
                .addGap(18, 18, 18)
                .addComponent(tfValorRotacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btRotacao)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Rotacao(graus)", jPanel15);

        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setText("X :");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setText("Y :");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Z :");

        tfEscalaX.setText("jTextField1");

        tfEscalaY.setText("jTextField2");

        tfEscalaZ.setText("jTextField3");

        btEscala.setText("Aplicar");
        btEscala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEscalaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btEscala, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEscalaX, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEscalaZ, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfEscalaY, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(tfEscalaX, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(tfEscalaY, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(tfEscalaZ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btEscala)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Escala", jPanel17);

        jTabbedPane1.addTab("OperacoesGeometricas", jTabbedPane2);

        jLabel2.setText("Numero De Segmentos :");

        jTabbedPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        tfBevelNumer.setText("AQUIVEMUMNUMERO");
        tfBevelNumer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBevelNumerActionPerformed(evt);
            }
        });

        jLabel6.setText("Escala em relacao a base");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                    .addComponent(tfBevelNumer))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(tfBevelNumer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Bevel", jPanel2);

        jLabel3.setText("Graus por segmento");

        tfTwistValorPorSegmento.setText("AQUIVEMUMNUMERO");
        tfTwistValorPorSegmento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTwistValorPorSegmentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 93, Short.MAX_VALUE))
                    .addComponent(tfTwistValorPorSegmento))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfTwistValorPorSegmento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Twist", jPanel3);

        tfBendValor.setText("jTextField4");
        tfBendValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfBendValorActionPerformed(evt);
            }
        });

        jLabel4.setText("Graus por segmento :");

        bgBendOpcoes.add(rbXPlus);
        rbXPlus.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        rbXPlus.setText("+X");
        rbXPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbXPlusActionPerformed(evt);
            }
        });

        bgBendOpcoes.add(rbXMinus);
        rbXMinus.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        rbXMinus.setText("-X");
        rbXMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbXMinusActionPerformed(evt);
            }
        });

        jLabel5.setText("Direcao de Bend");

        bgBendOpcoes.add(rbZPlus);
        rbZPlus.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        rbZPlus.setText("+Z");
        rbZPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbZPlusActionPerformed(evt);
            }
        });

        bgBendOpcoes.add(rbZMinus);
        rbZMinus.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        rbZMinus.setText("-Z");
        rbZMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbZMinusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rbXPlus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbZPlus)
                        .addGap(18, 18, 18)
                        .addComponent(tfBendValor, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(rbXMinus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbZMinus))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbXPlus)
                    .addComponent(rbZPlus)
                    .addComponent(tfBendValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbXMinus)
                    .addComponent(rbZMinus))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Bend", jPanel5);

        tfNumeroSegmentos.setText("AQUIVEMUMNUMERO");
        tfNumeroSegmentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNumeroSegmentosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNumeroSegmentos)
                .addContainerGap())
            .addComponent(jTabbedPane3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfNumeroSegmentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane3))
        );

        jTabbedPane1.addTab("Modificadores", jPanel1);

        jLabel1.setText("Nome :");

        tfNomePoligono.setText("Nome");
        tfNomePoligono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfNomePoligonoActionPerformed(evt);
            }
        });

        btDeletar.setText("Deletar");
        btDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeletarActionPerformed(evt);
            }
        });

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));

        btResetarOG.setText("Resetar Operacoes Geometricas");
        btResetarOG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResetarOGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btResetarOG, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btResetarOG, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btEsquerda)
                .addGap(18, 18, 18)
                .addComponent(btDeletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btDireita, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfNomePoligono))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEsquerda)
                    .addComponent(btDireita)
                    .addComponent(btDeletar))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfNomePoligono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btTranslacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTranslacaoActionPerformed
        // TODO add your handling code here:
        tentarAplicarTranslacao();
    }//GEN-LAST:event_btTranslacaoActionPerformed

    private void btDireitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDireitaActionPerformed
        // TODO add your handling code here:
        contador++;
        revalidarComponente();
        resetTF();
    }//GEN-LAST:event_btDireitaActionPerformed

    private void btEsquerdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEsquerdaActionPerformed
        // TODO add your handling code here:
        contador--;
        revalidarComponente();
        resetTF();
    }//GEN-LAST:event_btEsquerdaActionPerformed

    private void btEscalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEscalaActionPerformed
        // TODO add your handling code here:
        tentarAplicarEscala();
    }//GEN-LAST:event_btEscalaActionPerformed

    private void btRotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRotacaoActionPerformed
        // TODO add your handling code here:
        tentarAplicarRotacao();
    }//GEN-LAST:event_btRotacaoActionPerformed

    private void tfNumeroSegmentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNumeroSegmentosActionPerformed
        // TODO add your handling code here:
        try {
            int novoNumeroSegmentos = Integer.parseInt( tfNumeroSegmentos.getText() );
            if (novoNumeroSegmentos < 0)
            {
                throw new IllegalArgumentException();
            }
            Polygon poligonoResegmentado = Extrusao.reSegmentar(poligonoSelecionado, novoNumeroSegmentos);
            if (poligonoResegmentado == null)
            {
                throw new UnsupportedOperationException();
            }
            poligonoSelecionado.refresh(poligonoResegmentado);
            //aplicarOperacoesModificadoresGenerica();
            JanelaPrincipal.janela.updateExterno();
            taAvisos.setText("Poligono resegmentado!");
        }catch(Exception e)
        {
            taAvisos.setText("Numero de segmentos invalido!... operacao cancelada");
            resetTF();
            e.printStackTrace();
        }
    }//GEN-LAST:event_tfNumeroSegmentosActionPerformed

    private void aplicarOperacoesModificadoresGenerica()
    {
        try{
            JanelaPrincipal.janela.updateExterno();
            taAvisos.setText("Modificador aplicado!");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            taAvisos.setText("Erro modificadores!");
        }
    }
    
    private void tfBevelNumerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBevelNumerActionPerformed
        // TODO add your handling code here:
        try{
            double fatorBevel = Double.parseDouble(tfBevelNumer.getText());
            poligonoSelecionado.fatorBevel = fatorBevel;
            aplicarOperacoesModificadoresGenerica();
            //Polygon poligonoResegmentado = Extrusao.reSegmentar(poligonoSelecionado, poligonoSelecionado.segmentos.size()-1);
            //poligonoSelecionado.refresh(poligonoResegmentado);
            //Bevel.bevelPolygon(poligonoSelecionado, fatorBevel);
            //JanelaPrincipal.janela.updateExterno();
            //taAvisos.setText("Bevel executado!");
        }catch(Exception e)
        {
            //taAvisos.setText("Erro em bevel resetando.");
        }
    }//GEN-LAST:event_tfBevelNumerActionPerformed

    private void tfTwistValorPorSegmentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTwistValorPorSegmentoActionPerformed
        // TODO add your handling code here:
        try{
            double fatorTwist = Double.parseDouble(tfTwistValorPorSegmento.getText());
            poligonoSelecionado.fatorTwist = fatorTwist;
            aplicarOperacoesModificadoresGenerica();
            //Polygon poligonoResegmentado = Extrusao.reSegmentar(poligonoSelecionado, poligonoSelecionado.segmentos.size()-1);
            //poligonoSelecionado.refresh(poligonoResegmentado);
            //Twist.twistPolygon(poligonoSelecionado, fatorTwist);
            //JanelaPrincipal.janela.updateExterno();
            //taAvisos.setText("Bevel executado!");
        }catch(Exception e)
        {
            //taAvisos.setText("Erro em bevel resetando.");
        }
    }//GEN-LAST:event_tfTwistValorPorSegmentoActionPerformed

    private void tentarAplicarBend()
    {
        try{
            BendConstraints constante;
            double fatorBend = Double.parseDouble( tfBendValor.getText() );
            if (rbXPlus.isSelected())
            {
                constante = BendConstraints.XPlus;
            }
            else if (rbXMinus.isSelected())
            {
                constante = BendConstraints.XMinus;
            }
            else if (rbZPlus.isSelected())
            {
                constante = BendConstraints.ZPlus;
            }
            else if (rbZMinus.isSelected())
            {
                constante = BendConstraints.ZMinus;
            }
            else
            {
                throw new IllegalArgumentException();
            }
            poligonoSelecionado.constanteBend = constante;
            poligonoSelecionado.fatorBend = fatorBend;
            aplicarOperacoesModificadoresGenerica();
            //Polygon poligonoResegmentado = Extrusao.reSegmentar(poligonoSelecionado, poligonoSelecionado.segmentos.size()-1);
            //poligonoSelecionado.refresh(poligonoResegmentado);
            //Bend.bendPolygon(poligonoSelecionado, fatorBend, constante);
            //JanelaPrincipal.janela.updateExterno();
            //taAvisos.setText("Bend executado!");
        }
        catch(Exception e){
            //taAvisos.setText("Falha em bend!.. cancelando operacao");
            //e.printStackTrace();
        }
    }
    
    private void rbXPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbXPlusActionPerformed
        // TODO add your handling code here:
        tentarAplicarBend();
    }//GEN-LAST:event_rbXPlusActionPerformed

    private void rbXMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbXMinusActionPerformed
        // TODO add your handling code here:
        tentarAplicarBend();
    }//GEN-LAST:event_rbXMinusActionPerformed

    private void rbZPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbZPlusActionPerformed
        // TODO add your handling code here:
        tentarAplicarBend();
    }//GEN-LAST:event_rbZPlusActionPerformed

    private void rbZMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbZMinusActionPerformed
        // TODO add your handling code here:
        tentarAplicarBend();
    }//GEN-LAST:event_rbZMinusActionPerformed

    private void tfBendValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfBendValorActionPerformed
        // TODO add your handling code here:
        tentarAplicarBend();
    }//GEN-LAST:event_tfBendValorActionPerformed

    private void btDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeletarActionPerformed
        // TODO add your handling code here:
        tentarDeletar();
    }//GEN-LAST:event_btDeletarActionPerformed

    private void tfNomePoligonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfNomePoligonoActionPerformed
        // TODO add your handling code here:
        if (poligonoSelecionado != null)
        {
            poligonoSelecionado.nome = tfNomePoligono.getText();
            taAvisos.setText("Poligono renomeado com sucesso");
        }
        else
        {
            taAvisos.setText("Poligono nulo erro!");
        }
    }//GEN-LAST:event_tfNomePoligonoActionPerformed

    private void btResetarOGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResetarOGActionPerformed
        // TODO add your handling code here:
        if (poligonoSelecionado != null)
        {
            poligonoSelecionado.setOperacoes( new Matrix(Matrix.MATRIXBASICA) );
            taAvisos.setText("Matrix de operacoes resetada!");
            if (JanelaPrincipal.janela != null)
            {
                JanelaPrincipal.janela.updateExterno();
            }
        }
    }//GEN-LAST:event_btResetarOGActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgBendOpcoes;
    private javax.swing.ButtonGroup bgOpcoesRotacao;
    private javax.swing.JButton btDeletar;
    private javax.swing.JButton btDireita;
    private javax.swing.JButton btEscala;
    private javax.swing.JButton btEsquerda;
    private javax.swing.JButton btResetarOG;
    private javax.swing.JButton btRotacao;
    private javax.swing.JButton btTranslacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JRadioButton rbXMinus;
    private javax.swing.JRadioButton rbXPlus;
    private javax.swing.JRadioButton rbXRotacao;
    private javax.swing.JRadioButton rbYRotacao;
    private javax.swing.JRadioButton rbZMinus;
    private javax.swing.JRadioButton rbZPlus;
    private javax.swing.JRadioButton rbZRotacao;
    private javax.swing.JTextArea taAvisos;
    private javax.swing.JTextField tfBendValor;
    private javax.swing.JTextField tfBevelNumer;
    private javax.swing.JTextField tfEscalaX;
    private javax.swing.JTextField tfEscalaY;
    private javax.swing.JTextField tfEscalaZ;
    private javax.swing.JTextField tfNomePoligono;
    private javax.swing.JTextField tfNumeroSegmentos;
    private javax.swing.JTextField tfTranslacaoX;
    private javax.swing.JTextField tfTranslacaoY;
    private javax.swing.JTextField tfTranslacaoZ;
    private javax.swing.JTextField tfTwistValorPorSegmento;
    private javax.swing.JTextField tfValorRotacao;
    // End of variables declaration//GEN-END:variables
}
