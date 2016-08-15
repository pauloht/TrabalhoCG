/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pipeline.Projecao;

import Data.Base_Data.Matrix;

/**
 *
 * @author FREE
 */
public enum ProjecaoEnum {
    FRONTAL,
    SIDE,
    TOP,
    PERSPERCTIVE();
    
    private int valor = 0;
    
    public Matrix getMatrix()
    {
        if (this == FRONTAL)
        {
            return (Project.getFrontMatrix());
        }
        else if (this == SIDE)
        {
            return (Project.getSideMatrix());
        }
        else if (this == TOP)
        {
            return (Project.getTopMatrix());
        }
        else if (this == PERSPERCTIVE)
        {
            return (Project.getPerspectiveMatrix(valor + 0.00));
        }
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        switch (this)
        {
            case FRONTAL :
                return("Frontal");
            case PERSPERCTIVE :
                return("Perspectiva");
            case SIDE :
                return("Side");
            case TOP :
                return("Top");
            default :
                throw new UnsupportedOperationException();
        }
    }
    
    
}
