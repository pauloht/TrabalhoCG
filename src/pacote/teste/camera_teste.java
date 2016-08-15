/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.teste;
import Pipeline.CameraPacote.CameraClass;
import Data.Base_Data.*;
/**
 *
 * @author Paulo.Tenorio
 */
public class camera_teste {
    public static void test()
    {
        CameraClass camera = CameraClass.getInstance();
        camera.setVrp(new Vertex(-40.00,80.00,-70.00));
        camera.setP(new Vertex(10.00,-20.00,18.00));
        camera.setView_up(new Vertex(1.00,1.00,0.00));
        
        Double[][] conjunto = {         {30.00,35.00,25.00,20.00,30.00,50.00},
                                        {2.00,4.00,3.00,1.00,10.00,15.00},
                                        {25.00,20.00,18.00,23.00,22.5,30.00},
                                        {1.00,1.00,1.00,1.00,1.00,1.00}
                                        };
        Matrix pontos = new Matrix(conjunto);
        System.out.println("pontos->"+pontos);
        
        Matrix operacao = camera.getTransform_matrix();
        
        Matrix novos_pontos = operacao.multiplicacaoMatrix(pontos);
        System.out.println("novos_pontos->"+novos_pontos);
    }
    
}
