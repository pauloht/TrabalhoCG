/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.teste;

import Data.Base_Data.Matrix;
import Data.Base_Data.Vertex;
import Data.Composta_Data.Polygon;
import Generator.PolygonGenerator;

/**
 *
 * @author FREE
 */
public class testConstrutorCopia {
    public static void main(String args[])
    {
        Polygon poly = PolygonGenerator.generatePiramideQuadrada(5.00, 2.00, new Vertex(0.00,0.00,0.00));
        
        Polygon poly2 = new Polygon(poly);
        
        Matrix translacao = Transform_package.Transform3D.translate(poly2.get3DVertexMatrix(), 5.00, 5.00, 5.00);
        System.out.println("translacao = " + translacao);
        poly2.set3DVertexMatrix(translacao);
        
        
        System.out.println("poly = " + poly.get3DVertexMatrix());
        
        System.out.println("poly2 = " + poly2.get3DVertexMatrix());
    }
}
