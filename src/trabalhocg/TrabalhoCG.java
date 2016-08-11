/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhocg;

import java.awt.Dimension;
import javax.swing.JFrame;
import pacote.teste.*;
import ViewComponents.Componente;
import Data.Base_Data.*;
import Data.Composta_Data.Polygon;
import Transform_package.Transform2D;

/**
 *
 * @author Paulo.Tenorio
 */
public class TrabalhoCG {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
        matrix_teste.printMe();
        
        Polygon poly = new Polygon();
        Vertex vx1 = new Vertex(21.00,21.00,0.00);
        Vertex vx2 = new Vertex(31.00,21.00,0.00);
        Vertex vx4 = new Vertex(21.00,31.00,0.00);
        Vertex vx3 = new Vertex(31.00,31.00,0.00);
        
        poly.addVertex(vx1);
        poly.addVertex(vx2);
        poly.addVertex(vx3);
        poly.addVertex(vx4);
        poly.addEdge(vx1, vx2);
        poly.addEdge(vx2, vx3);
        poly.addEdge(vx3, vx4);
        poly.addEdge(vx4,vx1);
                
        JFrame main_frame = new JFrame("Frame");
        Componente test_component = new Componente();
        test_component.setPoly(poly);
        main_frame.setVisible(true);
        //main_frame.setSize(new Dimension(10,20));
        //main_frame.pack();
        main_frame.add(test_component);
        main_frame.setPreferredSize(new Dimension(100,200));
        main_frame.pack();
        main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
        
        camera_teste.test();
        
    }
    
}
