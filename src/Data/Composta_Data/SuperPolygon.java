/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Composta_Data;

import java.util.List;

/**
 * Contem metodos para tratar uma sequencia de polygonos como um unico polygono
 * @author FREE
 */
public class SuperPolygon {
    /**
     * Lista de poligonos em coordenadas de mundo
     */
    private List< Polygon > poligonos;
    
    public SuperPolygon(List<Polygon> lista)
    {
        poligonos = lista;
    }
    
    public void addPolygon(Polygon poly)
    {
        poligonos.add(poly);
    }
    
    public void addPolygon(List< Polygon > poly)
    {
        for (Polygon poligono : poly)
        {
            addPolygon(poligono);
        }
    }
    
    public void clearPolygonos()
    {
        poligonos.clear();
    }
    
    public Polygon getSuperPolygon()
    {
        //depois arrumo
        Polygon copiaBarata = new Polygon(poligonos.get(0));
        return( copiaBarata );
    }
    
}
