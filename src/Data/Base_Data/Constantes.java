/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Base_Data;

/**
 *
 * @author FREE
 */
public class Constantes {
    public static final double PRESCISSAO = 0.000001;
    
    public static double aproximador(double entrada)
    {
        if (Math.abs( entrada ) > PRESCISSAO)
        {
            return(entrada);
        }
        return(0.00);
    }
}
