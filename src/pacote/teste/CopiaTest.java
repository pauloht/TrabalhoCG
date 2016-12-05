/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.teste;

import Data.Base_Data.Vertex;
import Data.Composta_Data.Polygon;
import Generator.Extrusao;

/**
 *
 * @author FREE
 */
public class CopiaTest {
    public static void main(String args[])
    {
        Polygon base = Generator.PolygonGenerator.generateGenericPolygon(2.00, new Vertex(0.0,0.0,0.0), 4);
        Polygon poly = Extrusao.gerarPolygonoExtrudido(base, 0, new Vertex(0.0,5.0,0.0));
        poly.nome = "HUE";
        System.out.println("ORIGINAL :");
        System.out.println( poly.printME() );
        
        Polygon copia = new Polygon(poly);
        
        System.out.println("COPIA : ");
        System.out.println( copia.printME() );
    }
}
