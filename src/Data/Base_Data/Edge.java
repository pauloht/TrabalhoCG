/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Base_Data;

/**
 *
 * @author Paulo.Tenorio
 */
public class Edge {
    private Vertex start_vertex,end_vertex;
    private Face right_face,left_face;
    private Edge edge1,edge2,edge3,edge4;
    public Edge()
    {
        start_vertex = end_vertex = null;
        right_face = left_face = null;
        edge1 = edge2 = edge3 = edge4 = null;
    }
    public Edge(Vertex start_vertex,Vertex end_vertex)
    {
        this();
        this.start_vertex = start_vertex;
        this.end_vertex = end_vertex;
    }

    public Vertex getStart_vertex() {
        return start_vertex;
    }

    public void setStart_vertex(Vertex start_vertex) {
        this.start_vertex = start_vertex;
    }

    public Vertex getEnd_vertex() {
        return end_vertex;
    }

    public void setEnd_vertex(Vertex end_vertex) {
        this.end_vertex = end_vertex;
    }

    public Face getRight_face() {
        return right_face;
    }

    public void setRight_face(Face right_face) {
        this.right_face = right_face;
    }

    public Face getLeft_face() {
        return left_face;
    }

    public void setLeft_face(Face left_face) {
        this.left_face = left_face;
    }

    public Edge getEdge1() {
        return edge1;
    }

    public void setEdge1(Edge edge1) {
        this.edge1 = edge1;
    }

    public Edge getEdge2() {
        return edge2;
    }

    public void setEdge2(Edge edge2) {
        this.edge2 = edge2;
    }

    public Edge getEdge3() {
        return edge3;
    }

    public void setEdge3(Edge edge3) {
        this.edge3 = edge3;
    }

    public Edge getEdge4() {
        return edge4;
    }

    public void setEdge4(Edge edge4) {
        this.edge4 = edge4;
    }
    
    public void printMe()
    {
        System.out.print("Edge[");
        start_vertex.printMe();
        System.out.print("]->[");
        end_vertex.printMe();
        System.out.print("]");
    }
}
