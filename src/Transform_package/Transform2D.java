/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transform_package;
import Data.Base_Data.Vertex;
import Data.Base_Data.Matrix;
/**
 *
 * @author Paulo.Tenorio
 */
public class Transform2D {
    
    public static Double[][] ConcatenateMatrix(Double[][] transformation_matrix_1,Double[][] transformation_matrix_2)
    {
        Matrix matrix1 = new Matrix(transformation_matrix_1);
        Matrix matrix2 = new Matrix(transformation_matrix_2);
        Matrix matrix3 = matrix1.multiplicacaoMatrix(matrix2);
        return(matrix3.toRawMatrix());
    }
    
    public static void GenericTransformation(Vertex vertex,Double[][] transformation_matrix)
    {
        Double[] aux_vertex = vertex.getPosArray();
        Double[] return_vector;
        
        Double[][] vertex_coord = {{0.00}
                                  ,{0.00},
                                   {1.00}};
        vertex_coord[0][0] = aux_vertex[0];
        vertex_coord[1][0] = aux_vertex[1];
        
        Matrix trans_matrix = new Matrix(transformation_matrix);
        Matrix ver_matrix = new Matrix(vertex_coord);
        Matrix return_matrix;
        return_matrix = trans_matrix.multiplicacaoMatrix(ver_matrix);
        return_vector = return_matrix.Vetorize();
        vertex.setPosArray(return_vector);
    }
    
    public static void translate(Vertex vertex,Double x_translate,Double y_translate)
    {
        Double[][] translate_matrix = TransformationPrimitives.get2Dtranslate(x_translate, y_translate);
        GenericTransformation(vertex,translate_matrix);
    }
    
    public static void scale(Vertex vertex,Double x_scale,Double y_scale)
    {
        Double[][] scale_matrix = TransformationPrimitives.get2Dscale(x_scale, y_scale);
        GenericTransformation(vertex,scale_matrix);
    }
    
    //em graus
    public static void rotate(Vertex vertex,Double rotate)
    {
        Double[][] rotate_matrix = TransformationPrimitives.get2Drotate(rotate);
        GenericTransformation(vertex,rotate_matrix);
    }
}
