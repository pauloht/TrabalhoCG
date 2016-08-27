/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalvarCarregar;

import Data.Base_Data.Edge;
import Data.Base_Data.Vertex;
import Data.Composta_Data.Polygon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Paulo.Tenorio
 */
public class Salvar {
    String path = "C:\\";
    public final static int SALVADOCOMSUCESSO = 0;
    public final static int FALHAAOSALVAR = -1;
    public static int salvar(List< Polygon > scene,File nomeArquivo)
    {
        try{
            PrintWriter writer = new PrintWriter(nomeArquivo);
            StringBuilder test = new StringBuilder();
            
            /*
            
                Coisas que prescissam ser armazenadas para recuperar o poligono
                String nome - feito
                Polygon base - feito
                Vertex alvo - feito
                Numero de segmentos - feito
                Matrix de transformacao - feito
                Valores Bend,Twist e Bevel
            
            
            */
            
            int contadorDePoligonos = 0;
            test.append("<Poligonos>\n");
            
            for (Polygon poligono : scene)
            {
                
                
                test.append("   ").append("<Poligono>").append("\n");
                
                test.append("       ").append("<Nome>\n");
                test.append("           ").append(poligono.nome).append('\n');
                test.append("       ").append("</Nome>\n");
                
                test.append("       ").append("<Base>").append('\n');
                
                List< VertexInterno > listaVertex = new ArrayList<>();
                List< EdgeInterno > listaEdge = new ArrayList<>();
                
                
                //System.out.println("Vertex list size base= " + poligono.base.vertex_list.size());
                for (Vertex vertex : poligono.base.vertex_list)
                {
                    VertexInterno novo = new VertexInterno(vertex,"Vertex" + Integer.toString(listaVertex.size()+1) );
                    if (!listaVertex.contains(novo))
                    {
                        listaVertex.add(novo);
                        System.out.println("Adicionando vertex");
                    }
                    else
                    {
                        System.out.println("Vertex ja existente");
                    }
                }

                test.append("           ").append("<Vertexes>\n");
                for (VertexInterno vertex : listaVertex)
                {
                    test.append("               ").append(vertex.getNome()).append(vertex.getVertex().toString()).append('\n');
                }
                
                test.append("           ").append("</Vertex>\n");
                
                for (Edge edge : poligono.base.edge_list)
                {
                    Vertex inicio = edge.getStart_vertex();
                    Vertex fim = edge.getEnd_vertex();
                    
                    String nomeInicio = "NomeInvalidoInicio";
                    String nomeFim = "NomeInvalidoFim";
                    
                    boolean achouInicio = false;
                    boolean achouFim = false;
                    
                    for (VertexInterno vertex : listaVertex)
                    {
                        if (!achouInicio)
                        {
                            if (inicio.equals(vertex.getVertex()))
                            {
                                nomeInicio = vertex.getNome();
                                if (achouFim)
                                {
                                    break;
                                }
                                achouInicio = true;
                            }
                        }
                        
                        if (!achouFim)
                        {
                            if (fim.equals(vertex.getVertex()))
                            {
                                nomeFim = vertex.getNome();
                                if (achouInicio)
                                {
                                    break;
                                }
                                achouFim = true;
                            }
                        }
                    }
                    
                    EdgeInterno novoEdge = new EdgeInterno("Edge"+Integer.toString(listaEdge.size()+1),nomeInicio,nomeFim);
                    listaEdge.add(novoEdge);
                }
                
                test.append("           ").append("<Edges>\n");
                for (EdgeInterno edge : listaEdge)
                {
                    test.append("               ").append(edge.getNome()).append('(').append(edge.getVertexInicio()).append(',').append(edge.getVertexFim()).append(')').append('\n');
                }
                test.append("           ").append("</Edges>\n");

                test.append("       ").append("</Base>\n");
                
                test.append("       ").append("<Alvo>\n");
                
                test.append("           ").append(poligono.alvo).append('\n');
                
                test.append("       ").append("</Alvo>\n");
                
                test.append("       ").append("<Segmentos>\n");
                
                test.append("           ").append(Integer.toString(poligono.segmentos.size()-1)).append('\n');
                
                test.append("       ").append("</Segmentos>\n");
               
                test.append("       ").append("<Bevel>\n");
                
                test.append("           ").append(Double.toString(poligono.fatorBevel)).append('\n');
                
                test.append("       ").append("</Bevel>\n");
                
                test.append("       ").append("<Twist>\n");
                
                test.append("           ").append(Double.toString(poligono.fatorTwist)).append('\n');
                
                test.append("       ").append("</Twist>\n");
                
                test.append("       ").append("<Bend>\n");
                
                test.append("           ").append(Double.toString(poligono.fatorBend)).append('\n');
                
                test.append("           ").append(Integer.toString(poligono.constanteBend.getValor())).append('\n');
                
                test.append("       ").append("</Bend>\n");
                
                Double[][] operacoesGeometricas = poligono.getOperacoes().toRawMatrix();
                
                test.append("       ").append("<Matrix>\n");
                
                test.append("           ").append(Double.toString( operacoesGeometricas[0][0] )).append(',').append(Double.toString( operacoesGeometricas[0][1] )).append(',').append(Double.toString( operacoesGeometricas[0][2] )).append(',').append(Double.toString( operacoesGeometricas[0][3] )).append('\n');
                test.append("           ").append(Double.toString( operacoesGeometricas[1][0] )).append(',').append(Double.toString( operacoesGeometricas[1][1] )).append(',').append(Double.toString( operacoesGeometricas[1][2] )).append(',').append(Double.toString( operacoesGeometricas[1][3] )).append('\n');
                test.append("           ").append(Double.toString( operacoesGeometricas[2][0] )).append(',').append(Double.toString( operacoesGeometricas[2][1] )).append(',').append(Double.toString( operacoesGeometricas[2][2] )).append(',').append(Double.toString( operacoesGeometricas[2][3] )).append('\n');
                test.append("           ").append(Double.toString( operacoesGeometricas[3][0] )).append(',').append(Double.toString( operacoesGeometricas[3][1] )).append(',').append(Double.toString( operacoesGeometricas[3][2] )).append(',').append(Double.toString( operacoesGeometricas[3][3] )).append('\n');
                
                test.append("       ").append("</Matrix>\n");
                test.append("   ").append("</Poligono>\n");
                contadorDePoligonos++;
                
            }
            
            test.append("</Poligonos>\n");
            writer.write(test.toString());
            writer.close();
            return(SALVADOCOMSUCESSO);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return(FALHAAOSALVAR);
        }
    }
  
    public static void main(String args[])
    {
        Vertex centro = new Vertex(0.00,0.00,0.00);
        Polygon poly = Generator.PolygonGenerator.generateGenericPolygon(1, centro, 4);
        Polygon poly2 = Generator.PolygonGenerator.generateGenericPolygon(1, centro, 3);
        Polygon extrudido = Generator.Extrusao.gerarPolygonoExtrudido(poly, 2, centro, 0.00, 10.00, 0.00);
        Polygon extrudido2 = Generator.Extrusao.gerarPolygonoExtrudido(poly2, 2, centro, 0.00, 10.00, 0.00);
        List< Polygon > lista = new ArrayList<>();
        //lista.add(poly);
        lista.add(extrudido);
        lista.add(extrudido2);
        if (salvar(lista,new File("TESTCG")) == SALVADOCOMSUCESSO)
        {
            System.out.println("Sucesso!");
        }
        else
        {
            System.out.println("Erro!");
        }
    }
}