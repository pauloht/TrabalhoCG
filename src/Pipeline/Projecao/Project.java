/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pipeline.Projecao;
import Data.Base_Data.Matrix;
/**
 *
 * @author Paulo.Tenorio
 */
public class Project {
    /**
     * Retira Z
     * @return retorna matriz de transformação para projeção ortogonal frontal
     * 
     */
    public static Matrix getFrontMatrix()
    {
        Double[][] valores = {  {1.00,0.00,0.00,0.00} ,
                                {0.00,1.00,0.00,0.00} ,
                                {0.00,0.00,0.00,0.00} ,
                                {0.00,0.00,0.00,1.00} };
        
        Matrix returnmatrix = new Matrix(valores);
        return(returnmatrix);
    }
    
    /**
     * TROCA X POR Z
     * @return retorna matriz de transformação para projeção ortogonal lateral
     */
    public static Matrix getSideMatrix()
    {
        Double[][] valores = {  {0.00,0.00,1.00,0.00} ,
                                {0.00,1.00,0.00,0.00} ,
                                {0.00,0.00,0.00,0.00} ,
                                {0.00,0.00,0.00,1.00} };
        
        Matrix returnmatrix = new Matrix(valores);
        return(returnmatrix);
    }
    
    /**
     * TROCA Y POR Z
     * @return retorna matriz de transformação para projeção ortogonal topo
     */
    public static Matrix getTopMatrix()
    {
        Double[][] valores = {  {1.00,0.00,0.00,0.00} ,
                                {0.00,0.00,1.00,0.00} ,
                                {0.00,0.00,0.00,0.00} ,
                                {0.00,0.00,0.00,1.00} };
        
        Matrix returnmatrix = new Matrix(valores);
        return(returnmatrix);
    }
    
    /**
     * 
     * @return retorna matriz de transformação para projeção perspectiva com um ponto de fuga
     * //NAO FUNCIONA AINDA POR RAZOES! :D
     */
    public static Matrix getPerspectiveMatrix(Double dp)
    {
        Double[][] valores = {  {1.00,0.00,0.00,0.00} ,
                                {0.00,1.00,0.00,0.00} ,
                                {0.00,0.00,1.00,0.00} ,
                                {0.00,0.00,( -1.00/(dp) ),0.00} };
        
        
        Matrix returnmatrix = new Matrix(valores);
        
        
        return(returnmatrix);
    }
    
    
}
