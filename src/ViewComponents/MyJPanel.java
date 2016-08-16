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


/**
 *
 * @author FREE
 */
public class MyJPanel extends JPanel{
    //private final List<Polygon>poly = new ArrayList<>();
    public Polygon polygon;
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
        polygon = algumPolygono;
    }
    
    @Override
    synchronized public void paint(Graphics g)
    {
        super.paint(g);
        System.out.println("REPAINT");
        System.out.println("REDESENHANDO");
        //System.out.println("Dimension = " + this.getSize());
        //System.out.println("super chamado!");
        Polygon copia = new Polygon(polygon);

        //System.out.println("Antes da camera = " + copia.get3DVertexMatrix());

        Matrix operacao_de_camera = CameraClass.getInstance().getTransform_matrix();
        //System.out.println("matrix de camera = " + operacao_de_camera);
        Matrix depois_da_camera = operacao_de_camera.multiplicacaoMatrix(copia.get3DVertexMatrix());
        copia.set3DVertexMatrix(depois_da_camera);

        //System.out.println("antes projecao = " + copia.get3DVertexMatrix());
        //System.out.println("Matrix de projecao = " + tipoProjecao.getMatrix());
        Matrix depoisProjecao = tipoProjecao.getMatrix().multiplicacaoMatrix(copia.get3DVertexMatrix());
        copia.set3DVertexMatrix(depoisProjecao);
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

            Double razaoNoMundo = comprimento/altura;
            Double razaoNoPanel = comprimentoMaximo/alturaMaximo;

            if (comprimentoMaximo > alturaMaximo)
            {
                alturaNoPanel = alturaMaximo;
                comprimentoNoPanel = comprimento*alturaMaximo/altura;
            }
            else
            {
                comprimentoNoPanel = comprimentoMaximo;
                alturaNoPanel = altura*comprimentoMaximo/comprimento;
            }

            sobraNoComprimento = comprimentoMaximo - comprimentoNoPanel;
            sobraNaAltura = alturaMaximo - alturaNoPanel;


            /*
            System.out.println("comprimento = " + comprimento + ",altura = " + altura + "\n" +
                               "No panel comprimentoMaximo = " + comprimentoMaximo + ",Altura maxima = " + alturaMaximo + "\n" +
                                "No panel comprimento = " + comprimentoNoPanel + ",altura = " + alturaNoPanel + "\n" +
                                 "Sobra comprimento = " + sobraNoComprimento + ",sobra altura = " + sobraNaAltura);
            */

            map.UMax = new Double(comprimentoMaximo - Math.floor(sobraNoComprimento/2.00));
            map.UMin = new Double(Math.floor(sobraNoComprimento/2.00));
            map.VMax = new Double(this.getSize().height - Math.floor(sobraNaAltura/2.00));
            map.VMin = new Double(Math.floor(sobraNaAltura/2.00));
        }
        Matrix operacao_mapeamento = map.getMappingMatrix();
        Matrix depois_mapeamento = operacao_mapeamento.multiplicacaoMatrix( copia.get2DVertexMatrix() );
        //System.out.println("No panel " + nome + " depois do mapeamento = " + depois_mapeamento);
        copia.set2DVertexMatrix(depois_mapeamento);


        int x1,y1,x2,y2;
        if (copia.edge_list != null)
        {
            for (Edge edge : copia.edge_list)
            {
                x1 = edge.getStart_vertex().getPosArray()[0].intValue();
                
                if (x1 == 0)
                {
                    x1 = 1;
                }
                if (x1 == this.getSize().width)
                {
                    x1 = this.getSize().width-1;
                }

                y1 = edge.getStart_vertex().getPosArray()[1].intValue();
                if (y1 == 0)
                {
                    y1 = 1;
                }
                if (y1 == this.getSize().height)
                {
                    y1 = this.getSize().height-1;
                }

                x2 = edge.getEnd_vertex().getPosArray()[0].intValue();
                if (x2 == 0)
                {
                    x2 = 1;
                }
                if (x2 == this.getSize().width)
                {
                    x2 = this.getSize().width-1;
                }

                y2 = edge.getEnd_vertex().getPosArray()[1].intValue();
                
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
    
    @Override
    public void repaint()
    {
        super.repaint();
        //System.out.println("repaint chamado");
    }
}
