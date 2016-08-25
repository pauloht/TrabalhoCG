/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modificadores;

import Data.Base_Data.Matrix;
import Data.Base_Data.Vertex;
import Data.Composta_Data.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FREE
 */
public class Twist {
    public static void twistPolygon(Polygon poligono,double grausPorSegmento)
    {
        int numeroSegmentos = poligono.segmentos.size();
        double valorEmRadianos = Math.toRadians(grausPorSegmento);
        for (int i=0;i<numeroSegmentos;i++)
        {
            Polygon local = poligono.segmentos.get(i);
            Vertex cg = local.cg;
            Matrix translacao1 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(-cg.getPosXDummy(), -cg.getPosYDummy(), -cg.getPosZDummy()) );
            Matrix rotacao = new Matrix( Transform_package.TransformationPrimitives.get3DrotateY(valorEmRadianos*(i+1)) );
            //System.out.println("valor deg = " + grausPorSegmento  + ",valor em rad = " + valorEmRadianos);
            //System.out.println("matrix de rotacao = " + rotacao);
            Matrix translacao2 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(+cg.getPosXDummy(), +cg.getPosYDummy(), +cg.getPosZDummy()) );
            
            List< Matrix > operacoes = new ArrayList<>();
            //System.out.println("localPoly = " + local.nome);
            //System.out.println("cg = " + cg);
            //System.out.println("local cordenadas antes : " + local.get3DVertexMatrix());
            operacoes.add(translacao1);
            operacoes.add(rotacao);
            operacoes.add(translacao2);
            
            
            Matrix concatenada = Matrix.concatenacao(operacoes);
            //System.out.println("Matrix concatenada = " + concatenada);
            Matrix pontosDepois = concatenada.multiplicacaoMatrix( local.get3DVertexMatrixDummy() );
            local.set3DVertexMatrixDummy( pontosDepois );
        }
    }
}
