/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modificadores;

/**
 *
 * @author FREE
 */
public enum BendConstraints {
    XPlus(0),
    XMinus(1),
    ZPlus(2),
    ZMinus(3);
    
    private int valor;
    
    BendConstraints(int valor)
    {
        this.valor = valor;
    }
    
    public int getValor()
    {
        return(valor);
    }
    
    public static BendConstraints getInstanciaPorValor(int valor)
    {
        switch(valor)
        {
            case 0 : return BendConstraints.XPlus;
            case 1 : return BendConstraints.XMinus;
            case 2 : return BendConstraints.ZPlus;
            case 3 : return BendConstraints.ZMinus;
            default : throw new IllegalArgumentException();
        }
    }
}
