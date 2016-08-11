/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transform_package;

import Data.Base_Data.Matrix;
import Data.Base_Data.Vertex;

/**
 *
 * @author Paulo.Tenorio
 */
public class Transform3D {
    public static Double[][] ConcatenateMatrix(Double[][] transformation_matrix_1,Double[][] transformation_matrix_2)
    {
        Matrix matrix1 = new Matrix(transformation_matrix_1);
        Matrix matrix2 = new Matrix(transformation_matrix_2);
        Matrix matrix3 = matrix1.multiplicacaoMatrix(matrix2);
        return(matrix3.toRawMatrix());
    }
    
    public static Matrix ConcatenateMatrix(Matrix transformation_matrix_1,Matrix transformation_matrix_2)
    {
        Matrix retorno = transformation_matrix_1.multiplicacaoMatrix(transformation_matrix_2);
        return(retorno);
    }
    
    public static Matrix GenericTransformation(Matrix transformation_matrix,Matrix pontos)
    {
        Matrix retorno = transformation_matrix.multiplicacaoMatrix(pontos);
        return(retorno);
    }
    
    public static Matrix translate(Matrix pontos,Double x_translate,Double y_translate,Double z_translate)
    {
        Double[][] translate_matrix = TransformationPrimitives.get3Dtranslate(x_translate, y_translate,z_translate);
        Matrix transform = new Matrix(translate_matrix);
        return( GenericTransformation(transform,pontos) );
    }
    
    public static Matrix scale(Matrix pontos,Double x_scale,Double y_scale,Double z_scale)
    {
        Double[][] scale_matrix = TransformationPrimitives.get3Dscale(x_scale, y_scale,z_scale);
        Matrix transform = new Matrix(scale_matrix);
        return( GenericTransformation(transform,pontos) );
    }
    
    //em graus
    public static void rotateX(Vertex vertex,Double rotate)
    {
        //Double[][] rotate_matrix = TransformationPrimitives.get2Drotate(rotate);
        //GenericTransformation(vertex,rotate_matrix);
    }
    
    public static void rotateY(Vertex vertex,Double rotate)
    {
        //Double[][] rotate_matrix = TransformationPrimitives.get2Drotate(rotate);
        //GenericTransformation(vertex,rotate_matrix);
    }
    
    public static void rotateZ(Vertex vertex,Double rotate)
    {
        //Double[][] rotate_matrix = TransformationPrimitives.get2Drotate(rotate);
        //GenericTransformation(vertex,rotate_matrix);
    }
}
