/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import Data.Base_Data.*;
import Data.Composta_Data.Polygon;
import Pipeline.Matrix3Dto2D.Transform;
import java.util.ArrayList;
import java.util.List;

/**
 *  Gerador de formar geometricas
 * @author FREE
 */
public class PolygonGenerator {
    
    /**
     * Gera um cubo
     * @param lado lado do cubo
     * @param Centro centro do cubo
     * @return Polygono com vertex e edges do cubo
     */
    public static Polygon generateCube(Double lado,Vertex Centro)
    {
        Double meio_lado = lado/2.00;
        
        Double base_x = Centro.getPosXDummy();
        Double base_y = Centro.getPosYDummy();
        Double base_z = Centro.getPosZDummy();
        
        Polygon retorno;
        List< Vertex > vertex_list = new ArrayList<>();
        List< Edge > edge_list = new ArrayList<>();
        List< Face > face_list = new ArrayList<>();
        
        Vertex A = new Vertex( base_x+meio_lado , base_y+meio_lado , base_z+meio_lado);
        
        Vertex B = new Vertex( base_x-meio_lado , base_y+meio_lado , base_z+meio_lado);
        
        Vertex C = new Vertex( base_x-meio_lado , base_y-meio_lado , base_z+meio_lado);
        
        Vertex D = new Vertex( base_x+meio_lado , base_y-meio_lado , base_z+meio_lado);
        
        Vertex E = new Vertex( base_x+meio_lado , base_y+meio_lado , base_z-meio_lado);
        
        Vertex F = new Vertex( base_x-meio_lado , base_y+meio_lado , base_z-meio_lado);
        
        Vertex G = new Vertex( base_x-meio_lado , base_y-meio_lado , base_z-meio_lado);
        
        Vertex H = new Vertex( base_x+meio_lado , base_y-meio_lado , base_z-meio_lado);
        
        vertex_list.add(A);
        vertex_list.add(B);
        vertex_list.add(C);
        vertex_list.add(D);
        vertex_list.add(E);
        vertex_list.add(F);
        vertex_list.add(G);
        vertex_list.add(H);
        
        Edge AB = new Edge(A,B);
        
        Edge BC = new Edge(B,C);
        
        Edge CD = new Edge(C,D);
        
        Edge DA = new Edge(D,A);
        
        Edge EF = new Edge(E,F);
        
        Edge FG = new Edge(F,G);
        
        Edge GH = new Edge(G,H);
        
        Edge HE = new Edge(H,E);
        
        Edge AE = new Edge(A,E);
        
        Edge BF = new Edge(B,F);
        
        Edge CG = new Edge(C,G);
        
        Edge DH = new Edge(D,H);
        
        edge_list.add(AB);
        edge_list.add(BC);
        edge_list.add(CD);
        edge_list.add(DA);
        edge_list.add(EF);
        edge_list.add(FG);
        edge_list.add(GH);
        edge_list.add(HE);
        edge_list.add(AE);
        edge_list.add(BF);
        edge_list.add(CG);
        edge_list.add(DH);
        
        retorno = new Polygon( vertex_list, edge_list , face_list );
        
        return(retorno);
    }
    
    /**
     * Gera um piramide com base quadrada
     * @param altura distancia do do centro até o vertex que nao faz parte da base quadrado
     * @param lado lado da base quadrada
     * @param centro centro da BASE quadrada da piramide
     * @return Polygono com vetex e edges do cubo
     */
    public static Polygon generatePiramideQuadrada(Double altura,Double lado,Vertex centro)
    {
        List< Vertex > vertex_list = new ArrayList<>();
        List< Edge > edge_list = new ArrayList<>();
        List< Face > face_list = new ArrayList<>();
        
        Vertex b1,b2,b3,b4,a1;
        
        Double meio_lado = new Double(lado/2.00);
        
        b1 = new Vertex(meio_lado+centro.getPosXDummy(),centro.getPosYDummy(),meio_lado+centro.getPosZDummy());
        b2 = new Vertex(meio_lado+centro.getPosXDummy(),centro.getPosYDummy(),-meio_lado+centro.getPosZDummy());
        b3 = new Vertex(-meio_lado+centro.getPosXDummy(),centro.getPosYDummy(),meio_lado+centro.getPosZDummy());
        b4 = new Vertex(-meio_lado+centro.getPosXDummy(),centro.getPosYDummy(),-meio_lado+centro.getPosZDummy());
        a1 = new Vertex(centro.getPosXDummy(),centro.getPosYDummy() + altura ,centro.getPosZDummy());
        
        vertex_list.add(b1);
        vertex_list.add(b2);
        vertex_list.add(b3);
        vertex_list.add(b4);
        vertex_list.add(a1);
        
        Edge edge1,edge2,edge3,edge4,edge5,edge6,edge7,edge8;
        
        
        
        edge1 = new Edge(b1,b2);
        edge2 = new Edge(b2,b4);
        edge3 = new Edge(b4,b3);
        edge4 = new Edge(b3,b1);
        edge5 = new Edge(b1,a1);
        edge6 = new Edge(b2,a1);
        edge7 = new Edge(b3,a1);
        edge8 = new Edge(b4,a1);
        
        edge_list.add(edge1);
        edge_list.add(edge2);
        edge_list.add(edge3);
        edge_list.add(edge4);
        edge_list.add(edge5);
        edge_list.add(edge6);
        edge_list.add(edge7);
        edge_list.add(edge8);
        
        Polygon retorno = new Polygon(vertex_list,edge_list,face_list);

        return( retorno );
    }
    
    public static Polygon generateBaseQuadrada(Double lado,Vertex Centro)
    {
        Polygon retorno;
        List< Face > faceLista = new ArrayList<>();
        List< Edge > edgeLista = new ArrayList<>();
        List< Vertex > vertexLista = new ArrayList<>();
        
        double centrox = Centro.getPosXDummy();
        double centroy = Centro.getPosYDummy();
        double centroz = Centro.getPosZDummy();
        
        Vertex a = new Vertex(centrox + lado/2,centroy,centroz + lado/2);
        Vertex b = new Vertex(centrox - lado/2,centroy,centroz + lado/2);
        Vertex c = new Vertex(centrox - lado/2,centroy,centroz - lado/2);
        Vertex d = new Vertex(centrox + lado/2,centroy,centroz - lado/2);
        vertexLista.add(a);
        vertexLista.add(b);
        vertexLista.add(c);
        vertexLista.add(d);
        
        Edge ab = new Edge(a,b);
        Edge bc = new Edge(b,c);
        Edge cd = new Edge(c,d);
        Edge da = new Edge(d,a);
        edgeLista.add(ab);
        edgeLista.add(bc);
        edgeLista.add(cd);
        edgeLista.add(da);
        
        retorno = new Polygon(vertexLista,edgeLista,faceLista);
        retorno.setBase(new Polygon(retorno));
        
        return(retorno);
    }
    
    public static Polygon generateBaseTriangular(Double lado,Vertex Centro)
    {
        Polygon retorno;
        List< Face > faceLista = new ArrayList<>();
        List< Edge > edgeLista = new ArrayList<>();
        List< Vertex > vertexLista = new ArrayList<>();
        
        double centrox = Centro.getPosXDummy();
        double centroy = Centro.getPosYDummy();
        double centroz = Centro.getPosZDummy();
        
        Vertex a = new Vertex(centrox + (lado*Math.sqrt(3))/4,centroy,centroz);
        Vertex b = new Vertex(centrox - (lado*Math.sqrt(3))/4,centroy,centroz + lado/2);
        Vertex c = new Vertex(centrox - (lado*Math.sqrt(3))/4,centroy,centroz - lado/2);
        
        vertexLista.add(a);
        vertexLista.add(b);
        vertexLista.add(c);
        
        Edge ab = new Edge(a,b);
        Edge bc = new Edge(b,c);
        Edge ca = new Edge(c,a);
        edgeLista.add(ab);
        edgeLista.add(bc);
        edgeLista.add(ca);
        
        retorno = new Polygon(vertexLista,edgeLista,faceLista);
        retorno.setBase(new Polygon(retorno));
        
        return(retorno);
    }

    public static Polygon generateTest(Double lado, Vertex Centro){
        Polygon retorno;
        
        retorno = generateGenericPolygon(lado,Centro,3);              
        
        return(retorno);
    }
    
    public static Polygon generateGenericPolygon(double tamanhoLado,Vertex Centro,int numLados){
        Polygon retorno;
        List< Face > faceLista = new ArrayList<>();
        List< Edge > edgeLista = new ArrayList<>();
        List< Vertex > vertexLista = new ArrayList<>();
        
        double centrox = Centro.getPosXDummy();
        double centroy = Centro.getPosYDummy();
        double centroz = Centro.getPosZDummy();
        double somaAngulos,angulosExternos;
        Vertex a = new Vertex(1.0,0.0,0.0);
        vertexLista.add(a);
        somaAngulos = (numLados - 2)*180;
        angulosExternos = 180 - (somaAngulos/(numLados+0.00));
        //System.out.println("anguloExterno = " + angulosExternos);
        double valorEmRadiano = Math.toRadians(angulosExternos);
        Matrix rotacao = new Matrix( Transform_package.TransformationPrimitives.get3DrotateY(valorEmRadiano) );
        Vertex ultimoVertex = a;
        for(int i = 0; i< numLados -1;i++){
            Vertex b = new Vertex(ultimoVertex);
            Edge ab = new Edge(ultimoVertex,b);
            vertexLista.add(b);
            edgeLista.add(ab);
            Double[][] valores = new Double[4][1];
            valores[0][0] = b.getPosXDummy();
            valores[1][0] = b.getPosYDummy();
            valores[2][0] = b.getPosZDummy();
            valores[3][0] = 1.00;
            //System.out.println("PONTOS ANTES = " + b);
            Matrix matrixDePontos = new Matrix(valores);
            Matrix pontosDepois = rotacao.multiplicacaoMatrix(matrixDePontos);
            //System.out.println("MATRIXPONTOS DEPOIS = " + pontosDepois);
            //System.out.println(pontosDepois);
            b.setPosXFIXO(pontosDepois.Vetorize()[0] );
            b.setPosYFIXO(pontosDepois.Vetorize()[1] );
            b.setPosZFIXO(pontosDepois.Vetorize()[2] );
            //System.out.println("PONTOS DEPOIS = " + b);
            ultimoVertex = b;
        }
        Edge ba = new Edge(ultimoVertex,a);
        edgeLista.add(ba);
        
        Face faceUnica = new Face(edgeLista);
        faceLista.add(faceUnica);
        
        retorno = new Polygon(vertexLista,edgeLista,faceLista);
        retorno.setBase( new Polygon(retorno) );
        
        Vertex A = retorno.vertex_list.get(0);
        Vertex B = retorno.vertex_list.get(1);
        double distancia = Vertex.distanciaEntreDoisVetores(A, B);
        
        Vertex retornoCG = retorno.base.calculateCG();
        Matrix translacao1 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(-retornoCG.getPosXDummy(), -retornoCG.getPosYDummy(), -retornoCG.getPosZDummy()));
        Matrix escala1 = new Matrix( Transform_package.TransformationPrimitives.get3Dscale((1.00/distancia), (1.00/distancia), (1.00/distancia)));
        Matrix translacao2 = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(centrox, centroy, centroz));
        Matrix escala2 = new Matrix( Transform_package.TransformationPrimitives.get3Dscale(tamanhoLado, tamanhoLado, tamanhoLado));
        List< Matrix > operacoes = new ArrayList<>();
        operacoes.add(translacao1);
        operacoes.add(escala1);
        operacoes.add(escala2);
        operacoes.add(translacao2);
        Matrix finalMatrix = Matrix.concatenacao(operacoes);
        Matrix depoisOperacoes = finalMatrix.multiplicacaoMatrix(retorno.get3DVertexMatrixDummy());
        retorno.set3DVertexMatrixRoot(depoisOperacoes);
        //System.out.println("FINAL = " + retorno.get3DVertexMatrix());
        //Vertex C = retorno.vertex_list.get(2);
        
        //System.out.println("BASE ROOT = " + retorno.get3DVertexMatrixRoot());
        //System.out.println("BASE MVOEL = " +  retorno.get3DVertexMatrixDummy());
        
        //System.out.println("Distancia AB = " + Vertex.distanciaEntreDoisVetores(A, B));
        //System.out.println("Distancia BC = " + Vertex.distanciaEntreDoisVetores(B, C));
        //System.out.println("Distancia CA = " + Vertex.distanciaEntreDoisVetores(C, A));
        //System.out.println("CG = " + retornoCG);
        
        return(retorno);
    }
}
