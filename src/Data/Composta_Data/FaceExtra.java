/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Composta_Data;

import Data.Base_Data.Edge;
import Data.Base_Data.Face;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FREE
 */
public class FaceExtra{
    private List< Integer > vetorAux = new ArrayList<>();
    private Face faceAssociada;
    
    public FaceExtra(Face face)
    {
        faceAssociada = face;
    }
    
    public void setCorrespondencia(List< Edge > listaEdge)
    {
        int contador = 0;
        for (Edge edgeFora : listaEdge)
        {
            for (Edge edgeDentro : faceAssociada.getEdgeList())
            {
                if (edgeDentro == edgeFora)
                {
                    //System.out.println("Dentro = " + edgeDentro.printMe() + " IGUAL A Fora = " + edgeDentro.printMe());
                    vetorAux.add(contador);
                    break;
                }
            }
            contador++;
        }
    }

    public List<Integer> getVetorAux() {
        return vetorAux;
    }
    
    
}
