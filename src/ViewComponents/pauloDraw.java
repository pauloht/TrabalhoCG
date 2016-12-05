/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ViewComponents;

import Data.Base_Data.Edge;
import Data.Base_Data.Face;
import Data.Base_Data.Vertex;
import Data.Composta_Data.Polygon;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Paulo.Tenorio
 */
public class pauloDraw {
    private MyJPanel panel;
    private int xlimitemenor = 0;
    private int ylimitemenor = 0;

    private int xlimitemaior = 0;
    private int ylimitemaior = 0;
    
    private List< Integer > valoresX; 
    private List< Integer > valoresY;
    
    private Color[][] zbufferCor;
    
    private Double[][] zbufferDistancia;
    
    private Integer[][] pinturaMatrix;
    
    public pauloDraw(MyJPanel panel)
    {
        setPanel(panel);
    }
    
    private void setPanel(MyJPanel panel)
    {
        this.panel = panel;
        xlimitemaior = panel.getSize().width - 1;
        ylimitemaior = panel.getSize().height - 1;
        valoresX = new ArrayList<>();
        valoresY = new ArrayList<>();
        
        System.out.println("limite x [" + xlimitemenor + "," + xlimitemaior + "]");
        System.out.println("limite y [" + ylimitemenor + "," + ylimitemaior + "]");
        
        //System.out.println("panel.getSize = " + panel.getSize().height);
        pinturaMatrix = new Integer[panel.getSize().height][2];
        for (int i=0;i<pinturaMatrix.length;i++)
        {
            pinturaMatrix[i][0] = -1;
            pinturaMatrix[i][1] = -1;
        }
        
        zbufferCor = new Color[xlimitemaior+1][ylimitemaior+1];
        
        for (int i=0;i<xlimitemaior+1;i++)
        {
            for (int j=0;j<ylimitemaior+1;j++)
            {
                zbufferCor[i][j] = Color.BLACK;
            }
        }
        
        panel.setzBufferColor(zbufferCor);
        //System.out.println("length = " + pinturaMatrix.length + ", length2 = " + pinturaMatrix[0].length);
    }
    
    public void reloadPanel()
    {
        setPanel(this.panel);
    }
    
    private void addXYValue(int xvalue, int yvalue)
    {         
        //System.out.println("adicinando par (" + xvalue + "," + yvalue + ")");
        int xbuffer,ybuffer;
        if (yvalue < ylimitemenor)
        {
            valoresY.add(ylimitemenor);
        }
        else if (yvalue > ylimitemaior)
        {
            valoresY.add(ylimitemaior);
        }
        else
        {
            valoresY.add(yvalue);
        }

        if (xvalue < xlimitemenor)
        {
            valoresX.add(xlimitemenor);
        }
        else if (xvalue > xlimitemaior)
        {
            valoresX.add(xlimitemaior);
        }
        else
        {
            valoresX.add(xvalue);
        }
        
        xbuffer = valoresX.get(valoresX.size()-1);
        ybuffer = valoresY.get(valoresY.size()-1);
        
        if (ybuffer > pinturaMatrix.length)
        {
            throw new IllegalArgumentException("Argumento instanciado errado ou argumento errado!");
        }
        else
        {
            //System.out.println("ybuffer = " + ybuffer);
            int valorMenorAnterior = pinturaMatrix[ybuffer][0];
            int valorMaiorAnterior = pinturaMatrix[ybuffer][1];
            
            if (valorMenorAnterior == -1)
            {
                pinturaMatrix[ybuffer][0] = xbuffer;
                pinturaMatrix[ybuffer][1] = xbuffer;
            }
            else
            {
                if (xbuffer < valorMenorAnterior)
                {
                    pinturaMatrix[ybuffer][0] = xbuffer;
                }
                
                if (xbuffer > valorMaiorAnterior)
                {
                    pinturaMatrix[ybuffer][1] = xbuffer;
                }
            }
        }
    }
    
    public void drawPoly(Polygon poly)
    {
        //System.out.println("numero de faces = " + poly.face_list.size());
        int contadorFace = 0;
        for (Face face : poly.face_list)
        {
            //System.out.println("A Face " + contadorFace + " tem " + face.getEdgeList().size() + " edges");
            contadorFace++;
            for (Edge edge : face.getEdgeList())
            {
                //edge.printMe();
                //System.out.println("");
                Vertex vertexInicio = edge.getStart_vertex();
                Vertex vertexFim = edge.getEnd_vertex();
                
                double xinicio = vertexInicio.getPosXDummy();
                double yinicio = vertexInicio.getPosYDummy();

                double xfim = vertexFim.getPosXDummy();
                double yfim = vertexFim.getPosYDummy();
                
                
                drawLine(xinicio,yinicio,xfim,yfim);
            }
        }
        
        fillPoly(Color.BLUE);
    }
    
    public void drawLine(Double xinicio,Double yinicio,Double xfim,Double yfim)
    {
        //draw.drawLine(test, 50.0, 50.0, 100.0, 50.0);
        //System.out.println("draw.drawLine(" + xinicio + "," + yinicio + "," + xfim + "," + yfim + ");");
        
        
        int intxinicio = new Double(Math.round(xinicio)).intValue();
        int intxfim = new Double(Math.round(xfim)).intValue();
        int intxfimbuffer = intxfim;
        
        int intyinicio = new Double(Math.round(yinicio)).intValue();
        int intyfim = new Double(Math.round(yfim)).intValue();
        int intyfimbuffer = intyfim;
        
        Double xinicioprox = Math.round( xinicio ) + 0.5;
        Double xfimprox = Math.round( xfim ) + 0.5;
        
        Double yinicioprox = Math.round( yinicio ) + 0.5;
        Double yfimprox = Math.round( yfim ) + 0.5;
        
        addXYValue(intxinicio , intyinicio);
        boolean foraDoPanel = false;
        
        if (!xinicioprox.equals( xfimprox ) && !yinicioprox.equals( yfimprox ))
        {
            double m = (yfimprox - yinicioprox)/(xfimprox - xinicioprox);
        
            System.out.println("m = " + m);
            
            if ( Math.abs(m) > 1)
            {
                int loop = Math.abs(intyinicio - intyfim);
                int valorY = intyinicio;
                int incremento = 0;
                if (intyinicio > intyfim)
                {
                    incremento = -1;
                }
                else
                {
                    incremento = 1;
                }
                for (int i=0 ; i<loop-1 ; i++)
                {
                    Double valorX = xinicioprox + (i+0.5)/m*incremento;
                    valorY = valorY + incremento;
                    addXYValue(valorX.intValue(),valorY);
                }
            }
            else
            {
                int loop = Math.abs(intxinicio - intxfim);
                int valorX = intxinicio;
                int incremento = 0;
                if (intxinicio > intxfim)
                {
                    incremento = -1;
                }
                else
                {
                    incremento = 1;
                }
                for (int i=0; i<loop-1 ; i++)
                {
                    Double valorY = yinicioprox + m*(i+0.5)*incremento;
                    valorX = valorX + incremento;
                    addXYValue(valorX,valorY.intValue());
                }
            }
        }
        else if (yinicioprox.equals(yfimprox)) //y1 == y2 -> m = infinito
        {
            //System.err.println("TRUE1");
            if (intxinicio > intxfim)
            {
                int aux = intxinicio;
                intxinicio = intxfim;
                intxfim = aux;
            }
            for (int i=intxinicio+1 ; i<intxfim ; i++)
            {
                Double valorY = yinicioprox;
                addXYValue(i,valorY.intValue());
            }
        }
        else if (xinicioprox.equals(xfimprox)) //x1 == x2 -> m = 0
        {
            //System.err.println("TRUE2");
            if (intyinicio > intyfim)
            {
                int aux = intyinicio;
                intyinicio = intyfim;
                intyfim = aux;
            }
            for (int i=intyinicio+1 ; i<intyfim ; i++)
            {
                Double valorX = xinicioprox;
                addXYValue(valorX.intValue(),i);
            }
        }
        
        if (!xinicio.equals(xfim) || !yinicio.equals(yfim))
        {
            //System.out.println("TRUE");
            addXYValue(intxfim, intyfim);
            
        }
        
        //System.out.println("valores x : " + valoresX);
        //System.out.println("valores y : " + valoresY);
        
        //this.panel.repaint();
    }
    
    private void fillPoly(Color fillColor)
    {
        for (int i=0;i<pinturaMatrix.length;i++)
        {
            int menorValor = pinturaMatrix[i][0];
            int maiorValor = pinturaMatrix[i][1];
            if (menorValor != -1)
            {
                for (int j=menorValor;j<=maiorValor;j++)
                {
                    zbufferCor[j][i] = fillColor;
                    //System.out.println(j + ", " + i + " = vermelho");
                }
            }
        }
    }
}
