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
        if (poligonos == null || poligonos.size() == 0)
        {
            //System.out.println("RETORNO NULO SUPERPOLYGONO");
            return(null);
        }
        Polygon retorno = new Polygon();
        for (Polygon poligono : poligonos)
        {
            //Polygon copia = new Polygon(poligono);
            //copia.aplicarModificadores();
            //copia.aplicarOperacoesGeometricas();
            poligono.aplicarModificadores();
            //System.out.println("Root antes de OG = " + poligono.get3DVertexMatrixRoot());
            //System.out.println("Dummy antes de OG = " + poligono.get3DVertexMatrixDummy());
            poligono.aplicarOperacoesGeometricas();
            //System.out.println("Root depois de OG = " + poligono.get3DVertexMatrixRoot());
            //System.out.println("Dummy depois de OG = " + poligono.get3DVertexMatrixDummy());
            retorno.vertex_list.addAll(poligono.vertex_list);
            retorno.edge_list.addAll(poligono.edge_list);
            retorno.face_list.addAll(poligono.face_list);
        }
        //System.out.println("(Em superPoligono) retorno depois de modificadores : " + retorno.get3DVertexMatrixDummy());
        return( retorno );
    }

    public List<Polygon> getPoligonos() {
        return poligonos;
    }
    
}
