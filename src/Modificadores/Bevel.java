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
public class Bevel {
    /**
     * 
     * @param razaoBaseFim razao entre base do começo e fim da extrusao
     */
    public static void bevelPolygon(Polygon poligono,double razaoBaseFim)
    {
        int numeroSegmentos = poligono.segmentos.size();
        double razaoBaseDividida = (1.00-razaoBaseFim)/numeroSegmentos;
        for (int i=0;i<numeroSegmentos;i++)
        {
            double razao = 1.00 - razaoBaseDividida*(i+1);
            Polygon local = poligono.segmentos.get(i);
            Vertex cg = local.cg;
            Matrix translacao1 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(-cg.getPos_x(), -cg.getPos_y(), -cg.getPos_z()) );
            Matrix escala = new Matrix( Transform_package.TransformationPrimitives.get3Dscale(razao, razao, razao));
            Matrix translacao2 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(+cg.getPos_x(), +cg.getPos_y(), +cg.getPos_z()) );
            
            List< Matrix > operacoes = new ArrayList<>();
            //System.out.println("cg = " + cg);
            //System.out.println("local cordenadas antes : " + local.get3DVertexMatrix());
            operacoes.add(translacao1);
            operacoes.add(escala);
            operacoes.add(translacao2);
            //System.out.println("local cordenadas depois : " + local.get3DVertexMatrix());
            
            
            Matrix concatenada = Matrix.concatenacao(operacoes);
            //System.out.println("Matrix concatenada = " + concatenada);
            Matrix pontosDepois = concatenada.multiplicacaoMatrix( local.get3DVertexMatrix() );
            local.set3DVertexMatrix( pontosDepois );
        }
    }
}
