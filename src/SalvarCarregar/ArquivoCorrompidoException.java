/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalvarCarregar;

/**
 *
 * @author Paulo.Tenorio
 */
public class ArquivoCorrompidoException extends Exception{
    public ArquivoCorrompidoException()
    {
        super();
    }
    
    public ArquivoCorrompidoException(String msg)
    {
        super(msg);
    }
}
