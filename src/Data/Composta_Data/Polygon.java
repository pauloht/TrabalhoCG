/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Composta_Data;

import Data.Base_Data.*;
import Generator.Extrusao;
import Modificadores.BendConstraints;
import Modificadores.Blend;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo.Tenorio
 */
public class Polygon {
    public String nome = "NomeDefault";
    public List<Vertex> vertex_list;
    public List<Edge> edge_list;
    public List<Face> face_list;
    
    public Polygon base = null;
    public List< Polygon > segmentos = new ArrayList<>();
    
    public Vertex cg = null;
    public Vertex alvo = null;
    
    public double fatorBevel = 1.00;
    public double fatorBend = 0.00;
    public double fatorTwist = 0.00;
    public BendConstraints constanteBend = BendConstraints.XPlus;
    
    public CGEnum calculoDaCG = CGEnum.MEDIASDOSPONTOS;
    
    private Matrix operacoes;
    
    public Polygon()
    {
        operacoes = new Matrix(Matrix.MATRIXBASICA);
        vertex_list = new ArrayList<>();
        edge_list = new ArrayList<>();
        face_list = new ArrayList<>();
    }
    
    public Polygon( List< Vertex > vertex_list , List< Edge > edge_list , List< Face > face_list )
    {
        operacoes = new Matrix(Matrix.MATRIXBASICA);
        this.vertex_list = vertex_list;
        this.edge_list = edge_list;
        this.face_list = face_list;
    }
    
    
    //copia usada anterior
    /*
    public Polygon( Polygon outro )
    {
        if (outro.operacoes != null)
        {
            operacoes = new Matrix(outro.operacoes);
        }
        else
        {
            operacoes = new Matrix(Matrix.MATRIXBASICA);
        }
        
        if (outro.base != null)
        {
            this.base = new Polygon( outro.base );
        }
        else
        {
            this.base = null;
        }
        
        if (outro.alvo != null)
        {
            this.alvo = new Vertex(outro.alvo);
        }
        else
        {
            this.alvo = null;
        }
        
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
    */
    
    //versao copia teste, supostastamente é melhor que outra pois nao tem problemas com vertex identicos
    public Polygon( Polygon outro)
    {
        if (outro.operacoes != null)
        {
            operacoes = new Matrix(outro.operacoes);
        }
        else
        {
            operacoes = new Matrix(Matrix.MATRIXBASICA);
        }
        
        if (outro.base != null)
        {
            this.base = new Polygon( outro.base );
        }
        else
        {
            this.base = null;
        }
        
        if (outro.alvo != null)
        {
            this.alvo = new Vertex(outro.alvo);
        }
        else
        {
            this.alvo = null;
        }
        
        List< Vertex > listaDeVertexCopiados = new ArrayList<>();
        List< Vertex > listaDeVertex = new ArrayList<>();
        List< Edge > listaDeEdgesCopiadas = new ArrayList<>();
        
        for (Vertex vertex : outro.vertex_list)
        {
            listaDeVertex.add(vertex);
            Vertex copiaVertex = new Vertex(vertex);
            listaDeVertexCopiados.add(copiaVertex);
        }
        
        for (Edge edge : outro.edge_list)
        {
            Vertex inicio = null;
            Vertex fim = null;
            
            boolean achouInicio = false;
            boolean achouFim = false;
            for (int i=0;i<listaDeVertex.size();i++)
            {
                Vertex vertex = listaDeVertex.get(i);
                if (achouInicio&&achouFim)
                {
                    break;
                }
                if (edge.getEnd_vertex() == vertex)
                {
                    fim = listaDeVertexCopiados.get(i);
                    achouFim = true;
                }
                if (edge.getStart_vertex() == vertex)
                {
                    inicio = listaDeVertexCopiados.get(i);
                    achouInicio = true;
                }
            }
            if (achouInicio == false || achouFim == false)
            {
                System.err.println("ISSO PODE ACONTECER?");
            }
            Edge novaEdge = new Edge(inicio,fim);
            listaDeEdgesCopiadas.add(novaEdge);
        }
        
        this.vertex_list = listaDeVertexCopiados;
        this.edge_list = listaDeEdgesCopiadas;
        this.face_list = new ArrayList<>();
    }
    
    public void refresh(Polygon novaAparencia)
    {
        if (novaAparencia == null)
        {
            System.out.println("NOVAAPARENCIA NULL");
        }
        else
        {
            if (novaAparencia.vertex_list == null)
            {
                System.out.println("vertex list == null");
            }
        }
        this.vertex_list = novaAparencia.vertex_list;
        this.edge_list = novaAparencia.edge_list;
        this.face_list = novaAparencia.face_list;
        this.base = novaAparencia.base;
        this.segmentos =  novaAparencia.segmentos;
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
    
    public void adicionarOperacoesGeometricas(Matrix operacaoAplicada)
    {
        //System.out.println("antesDeOperacao = " + operacoes);
        //System.out.println("operacaoAplicada = " + operacaoAplicada);
        this.operacoes = operacaoAplicada.multiplicacaoMatrix(this.operacoes);
        //System.out.println("depoisDeOperacao = " + operacoes);
    }
    
    protected void aplicarOperacoesGeometricas()
    {
        //System.out.println("pontos antes da operacao OG : " + this.get3DVertexMatrixDummy());
        //System.out.println("Operacao feita : " + operacoes);
        this.set3DVertexMatrixDummy( this.operacoes.multiplicacaoMatrix( this.get3DVertexMatrixDummy() ) );
        //System.out.println("pontos depois da Operacao OG :" + this.get3DVertexMatrixDummy());
    }
    
    protected void aplicarModificadores()
    {
        //System.out.println("------------EM APLICAR MODIFICADORES-------------------");
        //System.out.println("QUANTIA DE NIVEIS EXTRUSAO = " + this.segmentos.size());
        this.set3DVertexMatrixDummy( this.get3DVertexMatrixRoot() );
        //System.out.println("Matrix root antes : " + this.get3DVertexMatrixRoot());
        //System.out.println("Matrix dummy antes : " + this.get3DVertexMatrixDummy());
        //System.out.println("Base antes Root : " + this.base.get3DVertexMatrixRoot());
        //System.out.println("Base antes Dummy : " + this.base.get3DVertexMatrixDummy());
        int i=0;
        /*
        for (Polygon poligono : this.segmentos)
        {
            System.out.println("Segmento Root " + i + ":" + poligono.get3DVertexMatrixRoot());
            System.out.println("Segmento Dummy " + i + ":" + poligono.get3DVertexMatrixDummy());
            i++;
        }
        */
        //System.out.println("antes de aplicar modificadores = " + this.get3DVertexMatrixDummy());
        Polygon poligonoResegmentado = Extrusao.reSegmentar(this, this.segmentos.size()-1);
        this.refresh(poligonoResegmentado);
        Blend.blendPolygon(this, this.fatorBevel, this.fatorTwist, this.fatorBend, this.constanteBend);
        //System.out.println("depois de aplicar modificadores " + this.get3DVertexMatrixDummy());
        //System.out.println("Base depois : " + this.base.get3DVertexMatrixRoot());
        //System.out.println("Base depois Dummy : " + this.base.get3DVertexMatrixDummy());
        i=0;
        /*
        for (Polygon poligono : this.segmentos)
        {
            System.out.println("Segmento " + i + ":" + poligono.get3DVertexMatrixRoot());
            System.out.println("Segmento Dummy " + i + ":" + poligono.get3DVertexMatrixDummy());
            i++;
        }
        */
        //System.out.println("Matrix dummy depois : " + this.get3DVertexMatrixDummy());
        //System.out.println("Matrix root Depois : " + this.get3DVertexMatrixRoot());
    }
    
    public Vertex calculateCG()
    {
        switch (calculoDaCG)
        {
            case BOUNDING_BOX :
                Double XMax=0.00,XMin=0.00,YMax=0.00,YMin=0.00,ZMax=0.00,ZMin=0.00;
                boolean primeira_vez = true;
                for (Vertex vertex : vertex_list)
                {
                    Double x_local = vertex.getPosXRoot();
                    Double y_local = vertex.getPosYRoot();
                    Double z_local = vertex.getPosZRoot();

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
            case MEDIASDOSPONTOS :
                //System.out.println("quantia de pontos = " + vertex_list.size());
                double xSomado = 0.00;
                double ySomado = 0.00;
                double zSomado = 0.00;
                int n = vertex_list.size();
                for (Vertex vertex : vertex_list)
                {
                    double x_local = vertex.getPosXRoot();
                    double y_local = vertex.getPosYRoot();
                    double z_local = vertex.getPosZRoot();
                    
                    xSomado = xSomado + x_local;
                    ySomado = ySomado + y_local;
                    zSomado = zSomado + z_local;
                    //System.out.println("Vertex = " + vertex + "\nSomaX = " + xSomado + ",ySoma = " + ySomado + ",zSoma = " + zSomado);
                }
                
                Vertex retorno = new Vertex(Constantes.aproximador(xSomado/(n+0.00)) , Constantes.aproximador(ySomado/(n+0.00)) , Constantes.aproximador(zSomado/(n+0.00)));
                //System.out.println("Resultado = " + retorno);
                return(retorno);
            default :
                throw new IllegalArgumentException();
        }
    }
    
    
    //<editor-fold defaultstate="collapsed" desc="setter and getters">
    
        
    public void setOperacoes(Matrix operacoes)
    {
        this.operacoes = operacoes;
    }

    public void setBase(Polygon poly) {
        base = poly;
    }

    public void set3DVertexMatrixDummy(Matrix pontos)
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

            local.setPosXDummy(pos_x);
            local.setPosYDummy(pos_y);
            local.setPosZDummy(pos_z);
            
            
            //System.out.println("vertex depois " + "j = " + local);

        }
    }
    
    public void set3DVertexMatrixRoot(Matrix pontos)
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

            local.setPosXFIXO(pos_x);
            local.setPosYFIXO(pos_y);
            local.setPosZFIXO(pos_z);
            
            
            //System.out.println("vertex depois " + "j = " + local);

        }
    }
    
    public Matrix get3DVertexMatrixDummy()
    {
        int numero_colunas = vertex_list.size();
        Double[][] retorno = new Double[4][numero_colunas];
        
        for (int j=0;j<numero_colunas;j++)
        {

            Vertex local = vertex_list.get(j);
            Double pos_x = local.getPosXDummy();
            Double pos_y = local.getPosYDummy();
            Double pos_z = local.getPosZDummy();

            retorno[0][j] = pos_x;
            retorno[1][j] = pos_y;
            retorno[2][j] = pos_z;
            retorno[3][j] = 1.00;

        }
        
        Matrix matrix_de_retorno = new Matrix(retorno);
        return(matrix_de_retorno);
    }
    
    public Matrix get3DVertexMatrixRoot()
    {
        int numero_colunas = vertex_list.size();
        Double[][] retorno = new Double[4][numero_colunas];
        
        for (int j=0;j<numero_colunas;j++)
        {

            Vertex local = vertex_list.get(j);
            Double pos_x = local.getPosXRoot();
            Double pos_y = local.getPosYRoot();
            Double pos_z = local.getPosZRoot();

            retorno[0][j] = pos_x;
            retorno[1][j] = pos_y;
            retorno[2][j] = pos_z;
            retorno[3][j] = 1.00;

        }
        
        Matrix matrix_de_retorno = new Matrix(retorno);
        return(matrix_de_retorno);
    }
    
    
    public Matrix getOperacoes() {
        return operacoes;
    }
    
    //</editor-fold>
}
