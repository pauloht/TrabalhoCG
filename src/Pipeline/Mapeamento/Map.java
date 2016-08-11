/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pipeline.Mapeamento;
import Data.Base_Data.*;
import Data.Composta_Data.*;
import java.util.List;

/**
 *
 * @author Paulo.Tenorio
 */
public class Map {
    /**
     * Determina que apartir de certo valores pequenos deve tratar como zero
     */
    private static final double PRESCISSAO = 0.00001;
    
    private static Matrix getMappingMatrix(Double XMax,Double XMin,Double YMax,Double YMin,Double UMax,Double UMin,Double VMax,Double VMin)
    {
        Double razao_larguras;
        if (Math.abs(XMax - XMin) > PRESCISSAO)
        {
            razao_larguras = (UMax - UMin)/(XMax - XMin);
        }
        else
        {
            razao_larguras = 1.00;
        }
        Double razao_alturas;
        
        if (Math.abs(YMax - YMin) > PRESCISSAO)
        {
            razao_alturas = (VMax - VMin)/(YMax - YMin);
        }
        else
        {
            razao_alturas = 1.00;
        }
        
        Double movimento_em_x = (-XMin*(razao_larguras)) + UMin;
        
        Double movimento_em_y = (-YMin*(razao_alturas)) + VMin;
        
        Double[][] valores = {  { razao_larguras , 0.00            , movimento_em_x } ,
                                { 0.00           , razao_alturas   , movimento_em_y } ,
                                { 0.00           , 0.00            , 1.00           } };
        
        Matrix returnmatrix = new Matrix(valores);
        /*
        System.out.println(" APAGAR DEPOIS EM MAP.java getMappingMatrix" + "\n" + 
                "Matrix de retorno = " + returnmatrix);
        */
        return(returnmatrix);
    }
    
    /**
     * Tenta achar valores "interessantes" para plano de mapeamento da cena
     * Tentar achar um valor bom para variavel valorAjustavel(dependendo do contesto um valor bom mudaria, achar essa regra)
     * @param cena conjunto de poligonos
     * @param UMax maior Hozintal da tela
     * @param UMin menor Horizontal da tela
     * @param VMax maior Vertical da tela
     * @param VMin menor Vertical da tela
     * @return 
     */
    public static Matrix getIdealMapping(List< Polygon > cena,Double UMax, Double UMin,Double VMax, Double VMin)
    {
        Double XMax = null;
        Double XMin = null;
        Double YMax = null;
        Double YMin = null;
        
        boolean primeiraVez = true;
        
        for (Polygon poly : cena)
        {
            //System.out.println("Poligono Matrix = " + poly.get3DVertexMatrix());
            for (Edge edge : poly.edge_list)
            {
                Vertex vertex_inicio = edge.getStart_vertex();
                Vertex vertex_fim = edge.getEnd_vertex();
                
                Double x_vertex_inicio = vertex_inicio.getPos_x();
                Double y_vertex_inicio = vertex_inicio.getPos_y();
                
                Double x_vertex_fim = vertex_fim.getPos_x();
                Double y_vertex_fim = vertex_fim.getPos_y();
                
                if (primeiraVez)
                {
                    if (x_vertex_inicio > x_vertex_fim)
                    {
                        XMax = x_vertex_inicio;
                        XMin = x_vertex_fim;
                    }
                    else
                    {
                        XMax = x_vertex_fim;
                        XMin = x_vertex_inicio;
                    }
                    
                    if (y_vertex_inicio > y_vertex_fim)
                    {
                        YMax = y_vertex_inicio;
                        YMin = y_vertex_fim;
                    }
                    else
                    {
                        YMax = y_vertex_fim;
                        YMin = y_vertex_inicio;
                    }
                    primeiraVez = false;
                }
                else
                {
                    if (x_vertex_inicio > XMax)
                    {
                        XMax = x_vertex_inicio;
                    }

                    if (x_vertex_inicio < XMin)
                    {
                        XMin = x_vertex_inicio;
                    }

                    if (y_vertex_inicio > YMax)
                    {
                        YMax = y_vertex_inicio;
                    }

                    if (y_vertex_inicio < YMin)
                    {
                        YMin = y_vertex_inicio;
                    }

                    if (x_vertex_fim > XMax)
                    {
                        XMax = x_vertex_fim;
                    }

                    if (x_vertex_fim < XMin)
                    {
                        XMin = x_vertex_fim;
                    }

                    if (y_vertex_fim > YMax)
                    {
                        YMax = y_vertex_fim;
                    }

                    if (y_vertex_fim < YMin)
                    {
                        YMin = y_vertex_fim;
                    }
                }
            }
        }
        /*
        System.out.println("APAGAR DEPOIS EM MAP getIdealMapping\n "
                + "XMax = " + XMax + ", XMin = " + XMin + ",YMax = " + YMax + ",YMin = " + YMin + "\n"
                + "UMax = " + UMax + ", UMin = " + UMin + ",VMax = " + VMax + ",VMin = " + VMin  + "\n");
        */
        
        Double valorAjustavel = 0.00;
        
        XMax = XMax + valorAjustavel;
        XMin = XMin - valorAjustavel;
        YMax = YMax + valorAjustavel;
        YMin = YMin - valorAjustavel;
        
        
        System.out.println("APAGAR DEPOIS EM MAP AJUSTADO\n "
        + "XMax = " + XMax + ", XMin = " + XMin + ",YMax = " + YMax + ",YMin = " + YMin + "\n"
        + "UMax = " + UMax + ", UMin = " + UMin + ",VMax = " + VMax + ",VMin = " + VMin  + "\n");
        
        
        System.out.println("XMax-Xmin = " + (XMax-XMin) + "YMax-YMin = " + (YMax-YMin));
        
        return( getMappingMatrix(XMax, XMin, YMax, YMin, UMax, UMin, VMax, VMin) );
    
    }
}
