/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Composta_Data;

import Data.Base_Data.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo.Tenorio
 */
public class Polygon {
    public List<Vertex> vertex_list;
    public List<Edge> edge_list;
    public List<Face> face_list;
    
    public Polygon()
    {
        vertex_list = new ArrayList<>();
        edge_list = new ArrayList<>();
        face_list = new ArrayList<>();
    }
    
    public Polygon( List< Vertex > vertex_list , List< Edge > edge_list , List< Face > face_list )
    {
        this.vertex_list = vertex_list;
        this.edge_list = edge_list;
        this.face_list = face_list;
    }
    
    /**
     * Construtor de copia, por enquanto nao copia FACES deixa fazio lista de faces
     * nao faz ligacao vertex.edge apenas edge.vertexinicio e edge.vertexfim
     * ACONTECE PROBLEMAS SE EXISTIR DOIS VERTEX EM MESMAS COORDENADAS!(lembrar de manter original)
     * @param outro 
     * @return Polygono copia
     */
    public Polygon( Polygon outro )
    {
        List< Vertex > novosVertex = new ArrayList<>();
        
        List< Edge > novasEdges = new ArrayList<>();
        
        List< Face > novasFaces = new ArrayList<>();
        
        for ( Vertex vertice : outro.vertex_list )
        {
            Vertex novo = new Vertex(vertice);
            novosVertex.add(novo);
        }
        
        for ( Edge edge : outro.edge_list)
        {
            Edge nova;
            
            Vertex vertexInicio = null;
            Vertex vertexFim = null;
            
            for (Vertex vertex : novosVertex)
            {
                if (vertex.equals(edge.getStart_vertex()))
                {
                    vertexInicio = vertex;
                    break;
                }
            }
            
            for (Vertex vertex : novosVertex)
            {
                if (vertex.equals(edge.getEnd_vertex()))
                {
                    vertexFim = vertex;
                    break;
                }
            }
            nova = new Edge(vertexInicio,vertexFim);
            novasEdges.add(nova);
        }
        
        this.vertex_list = novosVertex;
        this.edge_list = novasEdges;
        this.face_list = novasFaces;
    }
    
    //metodos relacionados a vertex
    public void addVertex(Double pos_x,Double pos_y,Double pos_z)
    {
        Vertex new_vertex = new Vertex(pos_x,pos_y,pos_z);
        vertex_list.add(new_vertex);
    }
    
    public void addVertex(Vertex new_vertex)
    {
        if (new_vertex==null)
        {
            System.out.println("null pointer?");
        }
        vertex_list.add(new_vertex);
    }
    //fim metodos relacionados a vertex
    
    //metodos relacionados a edges
    public void addEdge(Vertex start_vertex,Vertex end_vertex)
    {
        Edge new_edge = new Edge(start_vertex,end_vertex);
        edge_list.add(new_edge);
    }
    //fim metodos relacionados a edges
    
    //metodos relacionados a faces
    public void addFace(Edge edge)
    {
        Face new_face = new Face(edge);
        face_list.add(new_face);
    }
    //fim metodos relacionados a faces
    
    public void printMe()
    {
        int i;
        System.out.print("Vertexes:");
        for (i=0;i<vertex_list.size();i++)
        {
            vertex_list.get(i).printMe();
            System.out.print(" ");
        }
        System.out.println("");
        System.out.println("Edges:");
        for (i=0;i<edge_list.size();i++)
        {
            edge_list.get(i).printMe();
            System.out.print(" ");   
        }
        System.out.println("");
        System.out.println("Faces:");
        for (i=0;i<face_list.size();i++)
        {
            face_list.get(i).printMe();
        }
        System.out.println("\n");
    }
    
    public void set3DVertexMatrix(Matrix pontos)
    {
        Double[][] valores = pontos.toRawMatrix();
        
        int n_colunas = valores[0].length;
        
        for (int j=0;j<n_colunas;j++)
        {

            Vertex local = vertex_list.get(j);
            //System.out.println("vertex antes " + "j = " + local);
            Double pos_x = valores[0][j];
            Double pos_y = valores[1][j];
            Double pos_z = valores[2][j];

            local.setPos_x(pos_x);
            local.setPos_y(pos_y);
            local.setPos_z(pos_z);
            
            
            //System.out.println("vertex depois " + "j = " + local);

        }
    }
    
    public void set2DVertexMatrix(Matrix pontos)
    {
        Double[][] valores = pontos.toRawMatrix();
        
        int n_colunas = valores[0].length;
        
        for (int j=0;j<n_colunas;j++)
        {

            Vertex local = vertex_list.get(j);
            Double pos_x = valores[0][j];
            Double pos_y = valores[1][j];

            local.setPos_x(pos_x);
            local.setPos_y(pos_y);

        }
    }
    
    public Matrix get3DVertexMatrix()
    {
        int numero_colunas = vertex_list.size();
        Double[][] retorno = new Double[4][numero_colunas];
        
        for (int j=0;j<numero_colunas;j++)
        {

            Vertex local = vertex_list.get(j);
            Double pos_x = local.getPos_x();
            Double pos_y = local.getPos_y();
            Double pos_z = local.getPos_z();

            retorno[0][j] = pos_x;
            retorno[1][j] = pos_y;
            retorno[2][j] = pos_z;
            retorno[3][j] = 1.00;

        }
        
        Matrix matrix_de_retorno = new Matrix(retorno);
        return(matrix_de_retorno);
    }
    
    public Matrix get2DVertexMatrix()
    {
        int numero_colunas = vertex_list.size();
        Double[][] retorno = new Double[3][numero_colunas];
        
        for (int j=0;j<numero_colunas;j++)
        {

            Vertex local = vertex_list.get(j);
            Double pos_x = local.getPos_x();
            Double pos_y = local.getPos_y();

            retorno[0][j] = pos_x;
            retorno[1][j] = pos_y;
            retorno[2][j] = 1.00;

        }
        
        Matrix matrix_de_retorno = new Matrix(retorno);
        return(matrix_de_retorno);
    }
    
    /**
     * QUEBRADO
     * @param transform_matrix 
     */
    public void apply3DTransform(Matrix transform_matrix)
    {
        
        Matrix pontos_iniciais = get3DVertexMatrix();
        
        Matrix pontos_finais = transform_matrix.multiplicacaoMatrix(pontos_iniciais);
        
        Double[][] valores = pontos_finais.toRawMatrix();
        
        int numero_colunas = vertex_list.size();
        
        for (int j=0;j<numero_colunas;j++)
        {

            Vertex local = vertex_list.get(j);
            Double pos_x = valores[j][0];
            Double pos_y = valores[j][1];
            Double pos_z = valores[j][2];

            local.setPos_x(pos_x);
            local.setPos_y(pos_y);
            local.setPos_z(pos_z);

        }
        
    }
    
    public Vertex calculateCG()
    {
        Double XMax=0.00,XMin=0.00,YMax=0.00,YMin=0.00,ZMax=0.00,ZMin=0.00;
        boolean primeira_vez = true;
        for (Vertex vertex : vertex_list)
        {
            Double x_local = vertex.getPos_x();
            Double y_local = vertex.getPos_y();
            Double z_local = vertex.getPos_z();
            
            if (primeira_vez)
            {
                primeira_vez = false;
                XMax = x_local;
                XMin = x_local;
                YMax = y_local;
                YMin = y_local;
                ZMax = z_local;
                ZMin = z_local;
            }
            else
            {
                if (x_local > XMax)
                {
                    XMax = x_local;
                }
                
                if (x_local < XMin)
                {
                    XMin = x_local;
                }
                
                if (y_local > YMax)
                {
                    YMax = y_local;
                }
                
                if (y_local < YMin)
                {
                    YMin = y_local;
                }
                
                if (z_local > ZMax)
                {
                    ZMax = z_local;
                }
                
                if (z_local < ZMin)
                {
                    ZMin = z_local;
                }
                
            }
        }
        
        Double x_final = (XMax+XMin)/2.00;
        Double y_final = (YMax+YMin)/2.00;
        Double z_final = (ZMax+ZMin)/2.00;
        
        return( new Vertex(x_final,y_final,z_final) );
    }
    
    public void apply2DTransform(Matrix transform_matrix)
    {
        
        Matrix pontos_iniciais = get2DVertexMatrix();
        
        Matrix pontos_finais = transform_matrix.multiplicacaoMatrix(pontos_iniciais);
        
        Double[][] valores = pontos_finais.toRawMatrix();
        
        int numero_colunas = vertex_list.size();
        
        for (int j=0;j<numero_colunas;j++)
        {

            Vertex local = vertex_list.get(j);
            Double pos_x = valores[j][0];
            Double pos_y = valores[j][1];

            local.setPos_x(pos_x);
            local.setPos_y(pos_y);

        }
        
    }
    
}
