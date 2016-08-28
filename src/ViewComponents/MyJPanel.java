/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewComponents;

import java.awt.Graphics;
import javax.swing.JPanel;
import Data.Base_Data.*;
import Data.Composta_Data.*;
import Pipeline.CameraPacote.CameraClass;
import Pipeline.Mapeamento.Map;
import Pipeline.Projecao.ProjecaoEnum;
import View.JanelaPrincipal;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author FREE
 */
public class MyJPanel extends JPanel{
    //private final List<Polygon>poly = new ArrayList<>();
    public Polygon cena;
    public String nome = "NomeDefault";
    public Map map = new Map();
    
    public boolean mapeamentoAutomatico = true;
    //private fin
    
    public ProjecaoEnum tipoProjecao = ProjecaoEnum.FRONTAL;
    
    public MyJPanel()
    {
        super();
    }
    
    public MyJPanel(Polygon algumPolygono)
    {
        super();
        cena = algumPolygono;
    }
    
    @Override
    synchronized public void paint(Graphics g)
    {
        super.paint(g);
        if (cena != null)
        {
            Polygon copia = new Polygon(cena);
            //System.out.println("REPAINT");
            //System.out.println("REDESENHANDO");
            //System.out.println("Dimension = " + this.getSize());
            //System.out.println("super chamado!");
            //Polygon copia = new Polygon(polygon);
            
            //System.out.println("Polygon = " + polygon.get3DVertexMatrix());
            List< Matrix > operacoesMatriciais = new ArrayList<>();
            //System.out.println("Antes da camera = " + copia.get3DVertexMatrix());

            //Matrix operacoesDeTransformacaoGeometrica = copia.getOperacoes();
            //Matrix depoisTransformacoes = operacoesDeTransformacaoGeometrica.multiplicacaoMatrix(copia.get3DVertexMatrix());
            //copia.set3DVertexMatrix(depoisTransformacoes);


            Matrix operacao_de_camera = CameraClass.getInstance().getTransform_matrix();
            operacoesMatriciais.add(operacao_de_camera);
            //System.out.println("matrix de camera = " + operacao_de_camera);
            Matrix depois_da_camera = operacao_de_camera.multiplicacaoMatrix(copia.get3DVertexMatrixDummy());
            copia.set3DVertexMatrixDummy(depois_da_camera);

            //System.out.println("antes projecao = " + copia.get3DVertexMatrix());
            //System.out.println("Matrix de projecao = " + tipoProjecao.getMatrix());
            Matrix depoisProjecao = tipoProjecao.getMatrix().multiplicacaoMatrix(copia.get3DVertexMatrixDummy());
            copia.set3DVertexMatrixDummy(depoisProjecao);
            //operacoesMatriciais.add(tipoProjecao.getMatrix());
            //System.out.println("depois projecao = " + copia.get3DVertexMatrix());

            if (mapeamentoAutomatico)
            {
                Double[] medidas;
                medidas = map.setNiceParametros(copia);

                Double comprimento = medidas[0];
                Double altura = medidas[1];

                Double comprimentoNoPanel;
                Double alturaNoPanel;

                Double comprimentoMaximo = this.getSize().width + 0.00;
                Double alturaMaximo = this.getSize().height + 0.00;

                Double sobraNoComprimento;
                Double sobraNaAltura;

                if (Constantes.aproximador(comprimento) == 0.00)
                {
                    comprimento = 1.00;
                }

                if (Constantes.aproximador(altura) == 0.00)
                {
                    altura = 1.00;
                }

                Double razaoComprimento = comprimentoMaximo/comprimento;
                Double razaoAltura = alturaMaximo/altura;
                Double razaoUsada;

                if (razaoComprimento < razaoAltura)
                {
                    razaoUsada = razaoComprimento;
                }
                else
                {
                    razaoUsada = razaoAltura;
                }

                comprimentoNoPanel = comprimento*razaoUsada;
                alturaNoPanel = altura*razaoUsada;

                sobraNoComprimento = comprimentoMaximo - comprimentoNoPanel;
                sobraNaAltura = alturaMaximo - alturaNoPanel;

                //System.err.println("Altura = " + altura + "\nComprimento = " + comprimento + "\nrazaoComprimento = " + razaoComprimento + "\nRazaoAltura = " + razaoAltura + "\nRazaoUsada = " + razaoUsada);

                /*
                System.out.println("comprimento = " + comprimento + ",altura = " + altura + "\n" +
                                   "No panel comprimentoMaximo = " + comprimentoMaximo + ",Altura maxima = " + alturaMaximo + "\n" +
                                    "No panel comprimento = " + comprimentoNoPanel + ",altura = " + alturaNoPanel + "\n" +
                                     "Sobra comprimento = " + sobraNoComprimento + ",sobra altura = " + sobraNaAltura);
                */

                map.setUMax( new Double(comprimentoMaximo - Math.floor(sobraNoComprimento/2.00)) );
                map.setUMin( new Double(Math.floor(sobraNoComprimento/2.00)) );
                if (Constantes.aproximador( map.getUMin() ) < 0.00)
                {
                    //System.err.println("comprimentoMaximo = " + comprimentoMaximo);
                    //System.err.println("comprimentoNoPanel = " + comprimentoNoPanel);
                    //System.err.println("sobraNoComprimento = " + sobraNoComprimento);
                }
                map.setVMax( new Double(this.getSize().height - Math.floor(sobraNaAltura/2.00)) );
                map.setVMin( new Double(Math.floor(sobraNaAltura/2.00)) );
            }
            Matrix operacaoMapeamento = map.getMappingMatrix();
            //System.out.println("matrix de mapeamento = " + operacaoMapeamento);
            operacoesMatriciais.add(operacaoMapeamento);
            Matrix depois_mapeamento = operacaoMapeamento.multiplicacaoMatrix( copia.get3DVertexMatrixDummy() );
            //System.out.println("No panel " + nome + " depois do mapeamento = " + depois_mapeamento);

            Matrix concatenada = Matrix.concatenacao(operacoesMatriciais);

            copia.set3DVertexMatrixDummy(depois_mapeamento);
            //copia.set3DVertexMatrix(concatenada);
            
            //System.out.println("Copia = " + copia.get3DVertexMatrix());
            int x1,y1,x2,y2;
            if (copia.edge_list != null)
            {
                for (Edge edge : copia.edge_list)
                {
                    x1 = edge.getStart_vertex().getPosXDummy().intValue();

                    if (x1 == 0)
                    {
                        x1 = 1;
                    }
                    if (x1 == this.getSize().width)
                    {
                        x1 = this.getSize().width-1;
                    }

                    y1 = edge.getStart_vertex().getPosYDummy().intValue();
                    if (y1 == 0)
                    {
                        y1 = 1;
                    }
                    if (y1 == this.getSize().height)
                    {
                        y1 = this.getSize().height-1;
                    }

                    x2 = edge.getEnd_vertex().getPosXDummy().intValue();
                    if (x2 == 0)
                    {
                        x2 = 1;
                    }
                    if (x2 == this.getSize().width)
                    {
                        x2 = this.getSize().width-1;
                    }

                    y2 = edge.getEnd_vertex().getPosYDummy().intValue();

                    if (y2 == 0)
                    {
                        y2 = 1;
                    }
                    if (y2 == this.getSize().height)
                    {
                        y2 = this.getSize().height-1;
                    }

                    //System.out.println("x1 = " + x1 + ",y1 = " + y1 + ",x2 = " + x2 + ",y2 = " + y2);
                    g.drawLine(x1, y1, x2, y2);

                }
            }
            JanelaPrincipal.foiFeitoRepaint(this);
        }
    }
    
    @Override
    public void repaint()
    {
        super.repaint();
        //System.out.println("repaint chamado");
    }
}
