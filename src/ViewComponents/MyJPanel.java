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
    private Polygon cena;
    public String nome = "NomeDefault";
    public Map map = new Map();
    public Matrix bufferMatrix;
    
    private double XCentro = 0.00;
    private double YCentro = 0.00;
    private double maiorX = 0.00;
    private double menorX = 0.00;
    private double maiorY = 0.00;
    private double menorY = 0.00;
    
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
        bufferMatrix = null;
        cena = algumPolygono;
    }
    
    public void etapa1()
    {
        int tamanhoX = 0;
        int tamanhoY = 0;
        double distanciaX = 0.00;
        double distanciaY = 0.00;
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
            bufferMatrix = copia.get3DVertexMatrixDummy();
            //System.out.println("Eu sou o " + this.nome);
            //System.out.println("pontos antes de mapeamento -> " + bufferMatrix);
            calcularExtremosMapeamentoECentro();
            //System.out.println("CentroX = " + XCentro + ",YCentro = " + YCentro);
            tamanhoX = this.getSize().width;
            tamanhoY = this.getSize().height;
            distanciaX = maiorX-menorX;
            distanciaY = maiorY-menorY;
        }
        Map.informarMapeamentoIdeal(tamanhoX, tamanhoY, distanciaX, distanciaY);
    }
    
    private void calcularExtremosMapeamentoECentro()
    {
        Double[][] matrix = bufferMatrix.toRawMatrix();
        
        maiorX = matrix[0][0];
        menorX = matrix[0][0];
        maiorY = matrix[1][0];
        menorY = matrix[1][0];
        double xLocal;
        double yLocal;
        for (int j=0;j<matrix[0].length;j++)
        {
            xLocal = matrix[0][j];
            yLocal = matrix[1][j];
            
            if (xLocal > maiorX)
            {
                maiorX = xLocal;
            }
            if (xLocal < menorX)
            {
                menorX = xLocal;
            }
            if (yLocal > maiorY)
            {
                maiorY = yLocal;
            }
            if (yLocal < menorY)
            {
                menorY = yLocal;
            }
        }
        XCentro = (maiorX+menorX)/2.00;
        YCentro = (maiorY+menorY)/2.00;
        //System.out.println("maiorX = " +maiorX + ",menorX = " + menorX + ",maiorY=" + maiorY + ",menorY = " + menorY);
    }
    
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        if (cena != null)
        {
            if (mapeamentoAutomatico)
            {
                map.pegarBonsValores(this.getSize().width, this.getSize().height, XCentro, YCentro);
                
                
                /*
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

                System.out.println("comprimento = " + comprimento + ",altura = " + altura + "\n" +
                                   "No panel comprimentoMaximo = " + comprimentoMaximo + ",Altura maxima = " + alturaMaximo + "\n" +
                                    "No panel comprimento = " + comprimentoNoPanel + ",altura = " + alturaNoPanel + "\n" +
                                     "Sobra comprimento = " + sobraNoComprimento + ",sobra altura = " + sobraNaAltura);

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
                */
                
                
            }
            Matrix operacaoMapeamento = map.getMappingMatrix();
            //System.out.println("matrix de mapeamento = " + operacaoMapeamento);
            Matrix depois_mapeamento = operacaoMapeamento.multiplicacaoMatrix( bufferMatrix );
            //System.out.println("No panel " + nome + " depois do mapeamento = " + depois_mapeamento);

            Polygon copia = new Polygon(cena);
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
    
    public void setPolygon(Polygon poly)
    {
        bufferMatrix = null;
        this.cena = poly;
    }
    
    @Override
    public void repaint()
    {
        super.repaint();
        //System.out.println("repaint chamado");
    }
}
