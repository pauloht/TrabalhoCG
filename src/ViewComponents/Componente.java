/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewComponents;

import java.awt.Graphics;
import Data.Composta_Data.Polygon;
import Data.Base_Data.*;
import javax.swing.JPanel;

/**
 *
 * @author paulo.tenorio
 */
public class Componente extends JPanel{
    Polygon poly;
    public Componente()
    {
        super();
        poly = null;
    }
    
    public Componente(Polygon poly)
    {
        super();
        this.poly = poly;
    }
    @Override
    protected void paintComponent(Graphics g)
    {
        if (poly!=null)
        {
            poly.edge_list.stream().forEach((local_edge) -> {
                Double[] aux = new Double[3];
                int x1,y1,z1;
                int x2,y2,z2;
                aux = local_edge.getStart_vertex().getPosArray();
                x1 = (int) Math.floor(aux[0]);
                y1 = (int) Math.floor(aux[1]);
                z1 = (int) Math.floor(aux[2]);
                aux = local_edge.getEnd_vertex().getPosArray();
                x2 = (int) Math.floor(aux[0]);
                y2 = (int) Math.floor(aux[1]);
                z2 = (int) Math.floor(aux[2]);
                g.drawLine(x1, y1, x2, y2);
            });
        }
    }
    public void setPoly(Polygon pol)
    {
        poly = pol;
    }
}
