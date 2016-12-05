/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import Data.Base_Data.Edge;
import Data.Base_Data.Face;
import Data.Base_Data.Matrix;
import Data.Base_Data.Vertex;
import Data.Composta_Data.Polygon;

/**
 *
 * @author FREE
 */
public class Extrusao {
    public static Polygon gerarPolygonoExtrudido(Polygon base,int numeroSegmentos,Vertex centroDaBase,double translacaoX,double translacaoY,double translacaoZ)
    {
        //System.out.println("----FAZENDO EXTRUSAO EM " + numeroSegmentos + " NIVEIS!!!!--------");
        if (base.base == null)
        {
            return(null);
        }
        Polygon retorno = new Polygon(base);
        Polygon baseLocal = retorno;
        retorno.base = new Polygon( retorno );
        Vertex alvo = new Vertex(centroDaBase.getPosXRoot()+translacaoX,centroDaBase.getPosYRoot()+translacaoY,centroDaBase.getPosZRoot()+translacaoZ);
        retorno.alvo = alvo;
        int numeroExtrusoes = numeroSegmentos + 1;
        double translacaoXParcial = translacaoX/numeroExtrusoes;
        double translacaoYParcial = translacaoY/numeroExtrusoes;
        double translacaoZParcial = translacaoZ/numeroExtrusoes;
        
        for (int j=0;j<numeroExtrusoes;j++)
        {
            Polygon extrusao;
            if (j == 0 )
            {
                extrusao = new Polygon(baseLocal);
            }
            else
            {
                extrusao = new Polygon( retorno.segmentos.get(j-1) );
            }
            //System.out.println("base = " + base.get3DVertexMatrix());
            //System.out.println("extrusao começo = ");
            //System.out.println(extrusao.get3DVertexMatrixRoot());
            
            //MOTIVO DE REMOCAO
            //Aparentemente apenas gera novas edges e conecta aos vertices, mas tais edges ja sao gerados ao gerar construtor de copia
            /*
            for (int i=0;i<base.vertex_list.size();i++)
            {
                Vertex vertexComeço;
                if ( j == 0)
                {
                    vertexComeço = baseLocal.vertex_list.get(i);
                }
                else
                {
                    vertexComeço = retorno.segmentos.get(j-1).vertex_list.get(i);
                }
                Vertex vertexFim = extrusao.vertex_list.get(i);
                Edge novaEdge = new Edge(vertexComeço,vertexFim);
                baseLocal.edge_list.add(novaEdge);
            }
            */
            Matrix translacaoMatrix = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(translacaoXParcial, translacaoYParcial, translacaoZParcial) );
            Matrix depoisTranslacao = translacaoMatrix.multiplicacaoMatrix(extrusao.get3DVertexMatrixRoot());
            //System.out.println("depoisTranslacao matrix = " + depoisTranslacao);
            extrusao.set3DVertexMatrixRoot(depoisTranslacao);
            //System.out.println("extrusaodepois translacao = " + extrusao.get3DVertexMatrixRoot());
            extrusao.nome = "ExtrusaoNumero " + Integer.toString(j);
            extrusao.cg = extrusao.calculateCG();
            
            //extrusao.face_list.get(0).printMe();
            
            retorno.segmentos.add(extrusao);
            retorno.vertex_list.addAll(extrusao.vertex_list);
            retorno.edge_list.addAll(extrusao.edge_list);
            retorno.face_list.addAll(extrusao.face_list);
            
            /*
            System.out.println("apagar dps 2 ");
            int contadorZ = 0;
            for (Face face : retorno.face_list)
            {
                System.out.println("Face " + contadorZ + " : ");
                face.printMe();
                contadorZ++;
            }
            */
            
            //baseLocal = extrusao;
            //System.out.println("Extrusao : " + extrusao.nome);
            //System.out.println("pontos : " + extrusao.get3DVertexMatrix());
        }
        
        
        //System.out.println("fim = " + retorno.get3DVertexMatrix());
        retorno.nome = "PoligonoExtrudido";
        
        //System.out.println("poligono extrudido : " + retorno.get3DVertexMatrixRoot());
        retorno.vertex_list.addAll(retorno.base.vertex_list);
        retorno.vertex_list.add(retorno.alvo);
        return(retorno);
    }
    
    public static Polygon gerarPolygonoExtrudido(Polygon base,int numeroSegmentos,Vertex alvo)
    {
        //System.out.println("----FAZENDO EXTRUSAO EM " + numeroSegmentos + " NIVEIS!!!!--------");
        double distanciaX = alvo.getPosXRoot();
        double distanciaY = alvo.getPosYRoot();
        double distanciaZ = alvo.getPosZRoot();
        return(gerarPolygonoExtrudido(base,numeroSegmentos,base.calculateCG(),distanciaX,distanciaY,distanciaZ));
    }
    
    public static Polygon reSegmentar(Polygon poligono,int numeroSegmentos)
    {
        //System.out.println("--------FAZENDO RESEGMENTACAO--------------");
        //System.out.println("Cordenadas poligono ; " + poligono.get3DVertexMatrixDummy());
        Polygon base = poligono.base;
        base.base = new Polygon(base);
        Vertex alvo = poligono.alvo;
        Vertex origem = base.calculateCG();
        //System.out.println("base = " + base.calculateCG());
        //System.out.println("BaseCalculada = " + origem);
        //System.out.println("alvo = " + alvo);
        double distanciaX = alvo.getPosXRoot() - origem.getPosXRoot();
        double distanciaY = alvo.getPosYRoot() - origem.getPosYRoot();
        double distanciaZ = alvo.getPosZRoot() - origem.getPosZRoot();
        return( gerarPolygonoExtrudido(base, numeroSegmentos, origem, distanciaX, distanciaY, distanciaZ) );
    }
}
