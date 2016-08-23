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
    
    public void deleteAt(int indice)
    {
        poligonos.remove(indice);
    }
    
    public Polygon getSuperPolygon()
    {
        //depois arrumo
        Polygon retorno = new Polygon();
        for (Polygon poligono : poligonos)
        {
            Polygon copia = new Polygon(poligono);
            retorno.vertex_list.addAll(copia.vertex_list);
            retorno.edge_list.addAll(copia.edge_list);
            retorno.face_list.addAll(copia.face_list);
            //System.out.println("SUPER POLIGONO POLY : " + copia.get3DVertexMatrix());
        }
        return( retorno );
    }

    public List<Polygon> getPoligonos() {
        return poligonos;
    }
    
}
