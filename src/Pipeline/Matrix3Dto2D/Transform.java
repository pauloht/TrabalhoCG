/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pipeline.Matrix3Dto2D;

import Data.Base_Data.Matrix;

/**
 *
 * @author FREE
 */
public class Transform {
    public static Matrix retirarZ(Matrix entrada)
    {
        Double[][] matrix = entrada.toRawMatrix();
        int numero_colunas = matrix[0].length;
        Double[][] matrix_retorno = new Double[3][ numero_colunas ];
        
        for (int i=0;i<3;i++)
        {
            if (i!=2)
            {
                for (int j=0;j<numero_colunas;j++)
                {
                    matrix_retorno[i][j] = matrix[i][j];
                }
            }
            else
            {
                for (int j=0;j<numero_colunas;j++)
                {
                    matrix_retorno[i][j] = matrix[i+1][j];
                }
            }
        }
        
        Matrix retorno = new Matrix(matrix_retorno);
        return(retorno);
    }
}
