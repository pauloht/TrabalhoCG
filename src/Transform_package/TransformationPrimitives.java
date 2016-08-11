/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transform_package;

/**
 *
 * @author Paulo.Tenorio
 */
public class TransformationPrimitives {
    
    public static Double[][] get2Dtranslate(Double x_factor,Double y_factor)
    {
        Double[][] translate_matrix = {{1.00,0.00,0.00}
                                      ,{0.00,1.00,0.00},
                                       {0.00,0.00,1.00}};
        translate_matrix[0][2] = x_factor;
        translate_matrix[1][2] = y_factor;
        return(translate_matrix);
    }
    
    public static Double[][] get2Drotate(Double rotate_factor)
    {
        Double[][] rotate_matrix = {{1.00,0.00,0.00}
                                   ,{0.00,1.00,0.00},
                                    {0.00,0.00,1.00}};
        rotate_matrix[0][0] = Math.cos(Math.toRadians(rotate_factor));
        rotate_matrix[0][1] = -1*Math.sin(Math.toRadians(rotate_factor));
        rotate_matrix[1][0] = Math.sin(Math.toRadians(rotate_factor));
        rotate_matrix[1][1] = Math.cos(Math.toRadians(rotate_factor));
        return(rotate_matrix);
    }
    
    public static Double[][] get2Dscale(Double x_factor,Double y_factor)
    {
        Double[][] scale_matrix = {{1.00,0.00,0.00}
                                  ,{0.00,1.00,0.00},
                                   {0.00,0.00,1.00}};
        scale_matrix[0][0] = x_factor;
        scale_matrix[1][1] = y_factor;
        return(scale_matrix);
    }
    
    public static Double[][] get3Dtranslate(Double x_factor,Double y_factor,Double z_factor)
    {
        Double[][] translation_matrix = {{1.00,0.00,0.00,0.00}
                                        ,{0.00,1.00,0.00,0.00}
                                        ,{0.00,0.00,1.00,0.00}
                                        ,{0.00,0.00,0.00,1.00}};
        translation_matrix[0][3] = x_factor;
        translation_matrix[1][3] = y_factor;
        translation_matrix[2][3] = z_factor;
        return(translation_matrix);
    }
    
    public static Double[][] get3Dscale(Double x_factor,Double y_factor,Double z_factor)
    {
        Double[][] scale_matrix =       {{1.00,0.00,0.00,0.00}
                                        ,{0.00,1.00,0.00,0.00}
                                        ,{0.00,0.00,1.00,0.00}
                                        ,{0.00,0.00,0.00,1.00}};
        scale_matrix[0][0] = x_factor;
        scale_matrix[1][1] = y_factor;
        scale_matrix[2][2] = z_factor;
        return(scale_matrix);
    }
    
    public static Double[][] get3DrotateX(Double rotate_factor)
    {
        Double[][] rotate_matrix_X =       {{1.00,0.00,0.00,0.00}
                                        ,{0.00,1.00,0.00,0.00}
                                        ,{0.00,0.00,1.00,0.00}
                                        ,{0.00,0.00,0.00,1.00}};
        rotate_matrix_X[1][1] = Math.cos(rotate_factor);
        rotate_matrix_X[1][2] = -Math.sin(rotate_factor);
        rotate_matrix_X[2][1] = Math.sin(rotate_factor);
        rotate_matrix_X[2][2] = Math.cos(rotate_factor);
        return(rotate_matrix_X);
    }
    
    public static Double[][] get3DrotateY(Double rotate_factor)
    {
        Double[][] rotate_matrix_Y =       {{1.00,0.00,0.00,0.00}
                                        ,{0.00,1.00,0.00,0.00}
                                        ,{0.00,0.00,1.00,0.00}
                                        ,{0.00,0.00,0.00,1.00}};
        rotate_matrix_Y[0][0] = Math.cos(rotate_factor);
        rotate_matrix_Y[0][2] = Math.sin(rotate_factor);
        rotate_matrix_Y[2][0] = -Math.sin(rotate_factor);
        rotate_matrix_Y[2][2] = Math.cos(rotate_factor);
        return(rotate_matrix_Y);
    }
    
    /**
     * 
     * @param rotate_factor rotacao em radianos
     * @return Matriz de rotacao em Z 
     */
    public static Double[][] get3DrotateZ(Double rotate_factor)
    {
        Double[][] rotate_matrix_Z =       {{1.00,0.00,0.00,0.00}
                                        ,{0.00,1.00,0.00,0.00}
                                        ,{0.00,0.00,1.00,0.00}
                                        ,{0.00,0.00,0.00,1.00}};
        rotate_matrix_Z[0][0] = Math.cos(rotate_factor);
        rotate_matrix_Z[0][1] = -Math.sin(rotate_factor);
        rotate_matrix_Z[1][0] = Math.sin(rotate_factor);
        rotate_matrix_Z[1][1] = Math.cos(rotate_factor);
        return(rotate_matrix_Z);
    }
    
}
