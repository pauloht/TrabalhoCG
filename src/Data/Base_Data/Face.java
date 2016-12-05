/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Base_Data;

import java.util.List;

/**
 *
 * @author Paulo.Tenorio
 */
public class Face {
    List< Edge > edge_list;
    public Face()
    {
        edge_list = null;
    }
    
    public Face(List< Edge > edge)
    {
        this.edge_list = edge;
    }
    
    public List< Edge > getEdgeList()
    {
        return(edge_list);
    }
    
    public void setEdge(List< Edge > to_set)
    {
        edge_list = to_set;
    }
    
    public StringBuilder printMe()
    {
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        for (Edge edge : edge_list)
        {
            sb.append("Edge " + contador + " : \n");
            sb.append( edge.printMe() );
            sb.append("\n");
            contador++;
        }
        return(sb);
    }
}
