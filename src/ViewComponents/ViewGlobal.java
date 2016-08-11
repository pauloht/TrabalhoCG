/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Usado para funcoes que sao muito usadas pelas janelas
 * @author Paulo
 */
public class ViewGlobal {
    /**
     * Centraliza a janela passada como parametro no centro da tela
     * @param janela janela passada 
     */
    public static void centralizarJanela(JFrame janela)
    {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        janela.setLocation(dim.width/2-janela.getSize().width/2, dim.height/2-janela.getSize().height/2);
    }
}
