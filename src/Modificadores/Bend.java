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
public class Bend {
    public static void bendPolygon(Polygon poligono,double grausPorSegmento,BendConstraints constanteBend)
    {
        int numeroSegmentos = poligono.segmentos.size();
        double valorEmRadianos = Math.toRadians(grausPorSegmento);
        Vertex cg = poligono.calculateCG();
        for (int i=0;i<numeroSegmentos;i++)
        {
            Polygon local = poligono.segmentos.get(i);
            Matrix translacao1 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(-cg.getPosXDummy(), -cg.getPosYDummy(), -cg.getPosZDummy()) );
            Matrix rotacaoBend;
            switch (constanteBend)
            {
                case XMinus :
                    rotacaoBend = new Matrix( Transform_package.TransformationPrimitives.get3DrotateZ(valorEmRadianos*(i+1)) );
                    break;
                case XPlus :
                    rotacaoBend = new Matrix( Transform_package.TransformationPrimitives.get3DrotateZ(-valorEmRadianos*(i+1)) );
                    break;
                case ZMinus :
                    rotacaoBend = new Matrix( Transform_package.TransformationPrimitives.get3DrotateX(valorEmRadianos*(i+1)) );
                    break;
                case ZPlus :
                    rotacaoBend = new Matrix( Transform_package.TransformationPrimitives.get3DrotateX(-valorEmRadianos*(i+1)) );
                    break;
                default : 
                    throw new IllegalArgumentException();
            }
            //System.out.println("valor deg = " + grausPorSegmento  + ",valor em rad = " + valorEmRadianos);
            //System.out.println("matrix de rotacao = " + rotacao);
            Matrix translacao2 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(+cg.getPosXDummy(), +cg.getPosYDummy(), +cg.getPosZDummy()) );
            
            List< Matrix > operacoes = new ArrayList<>();
            //System.out.println("localPoly = " + local.nome);
            //System.out.println("cg = " + cg);
            //System.out.println("local cordenadas antes : " + local.get3DVertexMatrix());
            operacoes.add(translacao1);
            operacoes.add(rotacaoBend);
            operacoes.add(translacao2);
            
            
            Matrix concatenada = Matrix.concatenacao(operacoes);
            //System.out.println("Matrix concatenada = " + concatenada);
            Matrix pontosDepois = concatenada.multiplicacaoMatrix( local.get3DVertexMatrixDummy() );
            local.set3DVertexMatrixDummy( pontosDepois );
        }
    }
}
