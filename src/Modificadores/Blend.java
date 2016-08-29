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
 * Aplica Bevel/Twist/Bend nessa ordem
 * @author Paulo.Tenorio
 */
public class Blend {
    public static void blendPolygon(Polygon poligono,double fatorBevel,double fatorTwist,double fatorBend,BendConstraints bendConstante)
    {
        /*
        System.out.println("-----------------APLICANDO TODOS OS MODIFICADORES(CLASE BLEND)-----------------");
        System.out.println("Quantia de niveis = " + poligono.segmentos.size());
        System.out.println("fatorBevel = " + fatorBevel + ",fator Twist = " + fatorTwist + ",fator Bend = " + fatorBend);
        System.out.println("Pontos antes de modificador = " + poligono.get3DVertexMatrixDummy());
        */
        int numeroSegmentos = poligono.segmentos.size();
        double valorEmRadianosTwist = Math.toRadians(fatorTwist);
        double valorEmRadianosBend = Math.toRadians(fatorBend);
        double razaoBaseDividida = (1.00-fatorBevel)/numeroSegmentos;
        for (int i=0;i<numeroSegmentos;i++)
        {
            double razao = 1.00 - razaoBaseDividida*(i+1);
            Polygon local = poligono.segmentos.get(i);
            Vertex cg = local.cg;
            Matrix translacao1 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(-cg.getPosXDummy(), -cg.getPosYDummy(), -cg.getPosZDummy()) );
            Matrix escala = new Matrix( Transform_package.TransformationPrimitives.get3Dscale(razao, razao, razao));
            Matrix rotacaoTwist = new Matrix( Transform_package.TransformationPrimitives.get3DrotateY(valorEmRadianosTwist*(i+1)) );
            Matrix translacao2 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(+cg.getPosXDummy(), +cg.getPosYDummy(), +cg.getPosZDummy()) );
            Matrix rotacaoBend;
            switch (bendConstante)
            {
                case XMinus :
                    rotacaoBend = new Matrix( Transform_package.TransformationPrimitives.get3DrotateZ(valorEmRadianosBend*(i+1)) );
                    break;
                case XPlus :
                    rotacaoBend = new Matrix( Transform_package.TransformationPrimitives.get3DrotateZ(-valorEmRadianosBend*(i+1)) );
                    break;
                case ZMinus :
                    rotacaoBend = new Matrix( Transform_package.TransformationPrimitives.get3DrotateX(-valorEmRadianosBend*(i+1)) );
                    break;
                case ZPlus :
                    rotacaoBend = new Matrix( Transform_package.TransformationPrimitives.get3DrotateX(+valorEmRadianosBend*(i+1)) );
                    break;
                default : 
                    throw new IllegalArgumentException();
            }
            
            List< Matrix > operacoes = new ArrayList<>();
            //System.out.println("localPoly = " + local.nome);
            //System.out.println("cg = " + cg);
            //System.out.println("local cordenadas antes : " + local.get3DVertexMatrix());
            operacoes.add(translacao1);
            operacoes.add(escala);
            operacoes.add(rotacaoTwist);
            operacoes.add(translacao2);
            operacoes.add(rotacaoBend);
            
            Matrix concatenada = Matrix.concatenacao(operacoes);
            //System.out.println("Matrix concatenada = " + concatenada);
            Matrix pontosDepois = concatenada.multiplicacaoMatrix( local.get3DVertexMatrixDummy() );
            local.set3DVertexMatrixDummy( pontosDepois );
        }
        /*
        System.out.println("pontos depois de modificadores = " + poligono.get3DVertexMatrixDummy());
        System.out.println("--------------FIM MODIFICADADORES(CLASSE BLEND)------------------");
        */
    }
}
