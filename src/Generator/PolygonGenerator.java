/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Generator;

import Data.Base_Data.*;
import Data.Composta_Data.Polygon;
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
        
        Double base_x = Centro.getPos_x();
        Double base_y = Centro.getPos_y();
        Double base_z = Centro.getPos_z();
        
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
        
        b1 = new Vertex(meio_lado+centro.getPos_x(),centro.getPos_y(),meio_lado+centro.getPos_z());
        b2 = new Vertex(meio_lado+centro.getPos_x(),centro.getPos_y(),-meio_lado+centro.getPos_z());
        b3 = new Vertex(-meio_lado+centro.getPos_x(),centro.getPos_y(),meio_lado+centro.getPos_z());
        b4 = new Vertex(-meio_lado+centro.getPos_x(),centro.getPos_y(),-meio_lado+centro.getPos_z());
        a1 = new Vertex(centro.getPos_x(),centro.getPos_y() + altura ,centro.getPos_z());
        
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
}
