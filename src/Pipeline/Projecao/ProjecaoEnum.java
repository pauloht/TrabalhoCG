/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pipeline.Projecao;

import Data.Base_Data.Matrix;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

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
    
    public Icon getIcon()
    {
        Icon retorno = null;
        try{
        switch (this)
        {
            case FRONTAL :
                retorno = new ImageIcon( ImageIO.read(new File(getClass().getResource("/Imgs/Frontal.gif").getFile() ) ) );
                break;
            case TOP :
                retorno = new ImageIcon( ImageIO.read(new File(getClass().getResource("/Imgs/Topo.gif").getFile() ) ) );
                break;
            case SIDE :
                retorno = new ImageIcon( ImageIO.read(new File(getClass().getResource("/Imgs/Lateral.gif").getFile() ) ) );
                break;
            case PERSPERCTIVE : 
                return(null);
            default :
                throw new IllegalArgumentException();
        }
        return(retorno);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("ERRO AO CARREGAR IMAGEM...RETORNANDO NUlo");
            return(null);
        }
    }
    
    
}
