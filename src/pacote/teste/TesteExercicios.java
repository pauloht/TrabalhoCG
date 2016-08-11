/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.teste;

import Data.Base_Data.*;
import Data.Composta_Data.Polygon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FREE
 */
public class TesteExercicios {
    
    public static void main(String[] args)
    {
        Vertex A = new Vertex(10.00,10.00,0.00);
        Vertex B = new Vertex(30.00,10.00,0.00);
        Vertex C = new Vertex(30.00,20.00,0.00);
        Vertex D = new Vertex(10.00,20.00,0.00);

        Edge AB = new Edge(A,B);
        Edge BC = new Edge(B,C);
        Edge CD = new Edge(C,D);
        Edge DA = new Edge(D,A);
        
        ArrayList< Vertex > v_list = new ArrayList<>();
        v_list.add(A);
        v_list.add(B);
        v_list.add(C);
        v_list.add(D);
        
        ArrayList< Edge > e_list = new ArrayList<>();
        e_list.add(AB);
        e_list.add(BC);
        e_list.add(CD);
        e_list.add(DA);
        
        ArrayList< Face > f_list = new ArrayList<>();
        
        Polygon quadrado = new Polygon(v_list,e_list,f_list);
        Matrix backup = new Matrix( quadrado.get3DVertexMatrix() );
        
        System.out.println("A)");
        System.out.println("Inicio = " + quadrado.get3DVertexMatrix());
        System.out.println("CG = " + quadrado.calculateCG());
         
        Matrix depois_escala = Transform_package.Transform3D.scale(quadrado.get3DVertexMatrix(), 0.50, 1.00 , 1.00);
        
        quadrado.set3DVertexMatrix(depois_escala);
        
        System.out.println("Depois escala = " + quadrado.get3DVertexMatrix());
        System.out.println("CG = " + quadrado.calculateCG());
        
        quadrado.set3DVertexMatrix( backup );
        
        System.out.println("B)");
        System.out.println("Inicio = " + quadrado.get3DVertexMatrix());
        System.out.println("CG = " + quadrado.calculateCG());
        
        Vertex cg = quadrado.calculateCG();
        Double x_cg = cg.getPos_x();
        Double y_cg = cg.getPos_y();
        Double z_cg = cg.getPos_z();
        
        Matrix translacao_1 = new Matrix(Transform_package.TransformationPrimitives.get3Dtranslate(-x_cg, -y_cg, -z_cg));
        
        Matrix escala_1 = new Matrix(Transform_package.TransformationPrimitives.get3Dscale(0.5, 1.00, 1.00) );
        
        Matrix translacao_2 = new Matrix(Transform_package.TransformationPrimitives.get3Dtranslate(x_cg, y_cg, z_cg));
        
        System.out.println("trans_1 = " + translacao_1);
        
        System.out.println("escala_1 = " + escala_1);
        
        System.out.println("trans_2 = " + translacao_2);
        
        List< Matrix > lista = new ArrayList<>();
        lista.add(translacao_1);
        lista.add(escala_1);
        lista.add(translacao_2);
        
        Matrix concatenada = Matrix.concatenacao(lista);
        
        System.out.println("concatenada = " + concatenada);
        
        Matrix pontos_depois = Transform_package.Transform3D.GenericTransformation(concatenada, quadrado.get3DVertexMatrix());
        
        quadrado.set3DVertexMatrix(pontos_depois);
        
        System.out.println("Pontos depois = " + quadrado.get3DVertexMatrix());
        System.out.println("CG = " + quadrado.calculateCG());
        
        System.out.println("2)");
        
        A = new Vertex(10.00,10.00,10.00);
        
        B = new Vertex(30.00,12.00,8.00);
        
        C = new Vertex(23.00,12.00,5.00);
        
        D = new Vertex(16.00,14.00,1.00);
        
        Vertex E = new Vertex(20.00,25.00,6.00);
        
        AB = new Edge(A,B);
        BC = new Edge(B,C);
        CD = new Edge(C,D);
        DA = new Edge(D,A);
        
        Edge AE = new Edge(A,E);
        Edge BE = new Edge(B,E);
        Edge CE = new Edge(C,E);
        Edge DE = new Edge(D,E);
        
        v_list.clear();
        e_list.clear();
        
        v_list.add(A);
        v_list.add(B);
        v_list.add(C);
        v_list.add(D);
        v_list.add(E);
        
        e_list.add(AB);
        e_list.add(BC);
        e_list.add(CD);
        e_list.add(DA);
        e_list.add(AE);
        e_list.add(BE);
        e_list.add(CE);
        e_list.add(DE);
        
        Polygon piramide = new Polygon(v_list,e_list,f_list);
        backup = new Matrix( piramide.get3DVertexMatrix() );
        
        System.out.println("Pontos inicias = " + piramide.get3DVertexMatrix());
        
        //D deve ficar na origem ou seja D=(0,0,0)
        
        //A deve ficar sobre OZ positivo, ou seja A=(0,0,?)
        
        //B sobre o plano XZ, ou seja B=(?,0,?)
        
        //Primeiro zera D
        Matrix translacao = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(-D.getPos_x(), -D.getPos_y(), -D.getPos_z()) );
        
        pontos_depois = Transform_package.Transform3D.GenericTransformation(translacao, piramide.get3DVertexMatrix() );
        
        piramide.set3DVertexMatrix(pontos_depois);
        
        System.out.println("Matriz de translacao = " + translacao);
        
        //System.out.println("Pontos depois = " + pontos_depois);
        System.out.println("Pontos depois da translacao = " + piramide.get3DVertexMatrix());
        
        //Agora zerar X em A
        Double aux_x = A.getPos_x();
        Double aux_y = A.getPos_y();
        
        Double angulo = Math.atan(aux_x/aux_y)*180/Math.PI;
        
        Matrix rotacao = new Matrix( Transform_package.TransformationPrimitives.get3DrotateZ( Math.atan(aux_x/aux_y) ) );
        
        System.out.println("Angulo = " + angulo);
        System.out.println("Matriz de rotacao = " + rotacao);
        
        pontos_depois = Transform_package.Transform3D.GenericTransformation(rotacao, piramide.get3DVertexMatrix() );
        
        piramide.set3DVertexMatrix(pontos_depois);
        
        System.out.println("Pontos depois da rotacao = " + piramide.get3DVertexMatrix());
        
        //Agora zerar Y em A(girando em relacao a X para nao alterar X)
        Double aux_z = A.getPos_z();
        aux_y = A.getPos_y();
        
        angulo = Math.atan(aux_y/aux_z)*180/Math.PI;
        
        rotacao = new Matrix( Transform_package.TransformationPrimitives.get3DrotateX( Math.atan(aux_y/aux_z) ) );
        
        System.out.println("Angulo = " + angulo);
        System.out.println("Matriz de rotacao = " + rotacao);
        
        pontos_depois = Transform_package.Transform3D.GenericTransformation(rotacao, piramide.get3DVertexMatrix() );
        
        piramide.set3DVertexMatrix(pontos_depois);
        
        System.out.println("Pontos depois da rotacao = " + piramide.get3DVertexMatrix());
        
        //Agora zerar Y em B(girando em relacao a Z)
        aux_x = B.getPos_x();
        aux_y = B.getPos_y();
        
        angulo = -Math.atan(aux_y/aux_z)*180/Math.PI;
        
        rotacao = new Matrix( Transform_package.TransformationPrimitives.get3DrotateZ( -Math.atan(aux_y/aux_x) ) );
        
        System.out.println("Angulo = " + angulo);
        System.out.println("Matriz de rotacao = " + rotacao);
        
        pontos_depois = Transform_package.Transform3D.GenericTransformation(rotacao, piramide.get3DVertexMatrix() );
        
        piramide.set3DVertexMatrix(pontos_depois);
        
        System.out.println("Pontos depois da rotacao = " + piramide.get3DVertexMatrix());
    }
}
