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
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author FREE
 */
public class MyJPanel extends JPanel{
    private final List<Polygon>poly = new ArrayList<>();
    public String nome = "NomeDefault";
    
    public MyJPanel()
    {
        super();
        Font fonte = new Font(Font.DIALOG,Font.BOLD,12);
        this.setFont(fonte);
        this.setLayout(null);
    }
    
    @Override
    synchronized public void paint(Graphics g)
    {
        super.paint(g);
        //System.out.println("Dimension = " + this.getSize());
        //System.out.println("super chamado!");
        if (poly != null&&poly.size()>0)
        {
            for (Polygon local_poly : poly)
            {
                
                //if (poly.get(i)!=null)
                //{
                    //Polygon local_poly = poly.get(i);
                    int x1,y1,x2,y2;
                    if (local_poly.edge_list != null)
                    {
                        for (Edge edge : local_poly.edge_list)
                        {
                            x1 = edge.getStart_vertex().getPosArray()[0].intValue();
                            if (x1 == 0)
                            {
                                x1 = 1;
                            }
                            if (x1 >= this.getSize().width-1)
                            {
                                x1 = this.getSize().width-2;
                            }
                            
                            y1 = edge.getStart_vertex().getPosArray()[1].intValue();
                            if (y1 == 0)
                            {
                                y1 = 1;
                            }
                            if (y1 >= this.getSize().height-1)
                            {
                                y1 = this.getSize().height-2;
                            }
                            
                            x2 = edge.getEnd_vertex().getPosArray()[0].intValue();
                            if (x2 == 0)
                            {
                                x2 = 1;
                            }
                            if (x2 >= this.getSize().width-1)
                            {
                                x2 = this.getSize().width-2;
                            }
                            
                            y2 = edge.getEnd_vertex().getPosArray()[1].intValue();
                            if (y2 == 0)
                            {
                                y2 = 1;
                            }
                            if (y2 >= this.getSize().height-1)
                            {
                                y2 = this.getSize().height-2;
                            }

                            //System.out.println("x1 = " + x1 + ",y1 = " + y1 + ",x2 = " + x2 + ",y2 = " + y2);
                            g.drawLine(x1, y1, x2, y2);

                        }
                    }
                //}
            }
        }
        //g.drawString("HELLO FKING MUNDO", 0, this.getFont().getSize()+500);
    }
    
    @Override
    public void repaint()
    {
        super.repaint();
        //System.out.println("repaint chamado");
    }
    
    public void addPolygon(Polygon otherpoly)
    {
        List< Polygon > lista = new ArrayList<>();
        lista.add(otherpoly);
        Double[] medidas;
        medidas = Pipeline.Mapeamento.Map.setNiceParametros(lista);
        
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
        
        
        
        System.out.println("comprimento = " + comprimento + ",altura = " + altura + "\n" +
                           "No panel comprimentoMaximo = " + comprimentoMaximo + ",Altura maxima = " + alturaMaximo + "\n" +
                            "No panel comprimento = " + comprimentoNoPanel + ",altura = " + alturaNoPanel + "\n" +
                             "Sobra comprimento = " + sobraNoComprimento + ",sobra altura = " + sobraNaAltura);
        
        Matrix operacao_mapeamento = Pipeline.Mapeamento.Map.getMappingMatrix( comprimentoMaximo - Math.floor(sobraNoComprimento/2.00), Math.floor(sobraNoComprimento/2.00), this.getSize().height - Math.floor(sobraNaAltura/2.00), Math.floor(sobraNaAltura/2.00) );
        Matrix depois_mapeamento = operacao_mapeamento.multiplicacaoMatrix( otherpoly.get2DVertexMatrix() );
        System.out.println("No panel " + nome + " depois do mapeamento = " + depois_mapeamento);
        otherpoly.set2DVertexMatrix(depois_mapeamento);
        
        //System.out.println("MyJPanel, Pontos depois de mapeamento = " + otherpoly.get2DVertexMatrix());
        this.poly.add(otherpoly);
    }
    
    public void addPolygon(List< Polygon > otherpoly)
    {
        for (Polygon local_poly : otherpoly)
        {
            addPolygon(local_poly);
        }
    }
    
    public void clearPoly()
    {
        poly.clear();
    }
}
