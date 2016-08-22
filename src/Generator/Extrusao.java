/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import Data.Base_Data.Edge;
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
        if (base.base == null)
        {
            return(null);
        }
        Polygon retorno = new Polygon(base);
        Polygon baseLocal = retorno;
        retorno.base = retorno;
        
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
            //System.out.println("extrusao começo = ");
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
            Matrix translacaoMatrix = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(translacaoXParcial, translacaoYParcial, translacaoZParcial) );
            Matrix depoisTranslacao = translacaoMatrix.multiplicacaoMatrix(extrusao.get3DVertexMatrix());
            //System.out.println("depoisTranslacao matrix = " + depoisTranslacao);
            extrusao.set3DVertexMatrix(depoisTranslacao);
            //System.out.println("extrusaodepois translacao = " + extrusao.get3DVertexMatrix());
            extrusao.nome = "ExtrusaoNumero " + Integer.toString(j);
            extrusao.cg = extrusao.calculateCG();
            retorno.segmentos.add(extrusao);
            retorno.vertex_list.addAll(extrusao.vertex_list);
            retorno.edge_list.addAll(extrusao.edge_list);
            //baseLocal = extrusao;
            //System.out.println("Extrusao : " + extrusao.nome);
            //System.out.println("pontos : " + extrusao.get3DVertexMatrix());
        }
        
        
        //System.out.println("fim = " + retorno.get3DVertexMatrix());
        retorno.nome = "PoligonoExtrudido";
        
        System.out.println("poligono extrudido : " + retorno.get3DVertexMatrix());
        
        return(retorno);
    }
}
