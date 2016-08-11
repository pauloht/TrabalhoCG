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
public class Face {
    Edge edge;
    public Face()
    {
        edge = null;
    }
    
    public Face(Edge edge)
    {
        this.edge = edge;
    }
    
    public Edge getEdge()
    {
        return(edge);
    }
    
    public void setEdge(Edge to_set)
    {
        edge = to_set;
    }
    
    public void printMe()
    {
        System.out.print("Ainda tem que implementar printMe() em face, deal with it :DDD");
    }
}
