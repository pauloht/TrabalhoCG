/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.teste;

import ViewComponents.Test;
import ViewComponents.pauloDraw;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Data.Base_Data.*;
import Data.Composta_Data.Polygon;
import ViewComponents.MyJPanel;
import java.util.ArrayList;
import java.util.List;
import ViewComponents.pauloDraw;

/**
 *
 * @author Paulo.Tenorio
 */
public class JPanelTests {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);
        //frame.setLayout(null);
        
        MyJPanel test = new MyJPanel();
        test.setPreferredSize(new Dimension(300,300));
        test.setBackground(Color.red);
        test.setForeground(Color.yellow);
        frame.add(test);
        
        frame.pack();
        frame.setVisible(true);
        System.out.println(test.getSize());
        pauloDraw draw = new pauloDraw(test);
        //draw.drawLine(test, 150.0, 0.0, 150.0, 299.0);
        
        Vertex a = new Vertex(-25.0,-25.0,0.0);
        Vertex b = new Vertex(-25.0,25.0,0.0);
        Vertex c = new Vertex(25.0,25.0,0.0);
        Vertex d = new Vertex(25.0,-25.0,0.0);
        
        List< Vertex > vertexList = new ArrayList<>();
        vertexList.add(a);
        vertexList.add(b);
        vertexList.add(c);
        vertexList.add(d);
        
        Edge ab = new Edge(a,b);
        Edge bc = new Edge(b,c);
        Edge cd = new Edge(c,d);
        Edge da = new Edge(d,a);
        
        List< Edge > edgeList = new ArrayList<>();
        edgeList.add(ab);
        edgeList.add(bc);
        edgeList.add(cd);
        edgeList.add(da);
        
        List< Face > faceList = new ArrayList<>();
        Face abcd = new Face(edgeList);
        faceList.add(abcd);
        
        Polygon polyTest = new Polygon(vertexList,edgeList,faceList);
        
        double translacaoX = 25.00;
        double translacaoY = 100.00;
        double translacaoZ = 0.00;
        
        double rotationZ = 90;
        
        double escalax = 0.125;
        double escalay = 0.125;
        
        Matrix translacaoMatrix = new Matrix( Transform_package.TransformationPrimitives.get3Dtranslate(translacaoX, translacaoY, translacaoZ) );
        Matrix escalaMatrix = new Matrix( Transform_package.TransformationPrimitives.get3Dscale(escalax, escalay, 0.0) );
        Matrix rotacaoZ = new Matrix( Transform_package.TransformationPrimitives.get3DrotateZ(rotationZ) );
        //polyTest.adicionarOperacoesGeometricas(escalaMatrix);
        //polyTest.adicionarOperacoesGeometricas(rotacaoZ);
        polyTest.adicionarOperacoesGeometricas(translacaoMatrix);
        polyTest.aplicarOperacoesGeometricas();
        draw.drawPoly(polyTest);
        
        //draw.drawLine(test, 50.0, 50.0, 100.0, 50.0);
        //draw.drawLine(test, 100.0, 50.0, 100.0, 100.0);
        //draw.drawLine(test, 100.0, 100.0, 50.0, 100.0);
        //draw.drawLine(test, 50.0, 100.0, 50.0, 50.0);
        
        //draw.drawLine(test,100.0,100.0,94.68185297166175,103.28326243011081);
        
        test.repaint();
    }
    
}
