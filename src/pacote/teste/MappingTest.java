/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pacote.teste;

import Data.Base_Data.*;
import Data.Composta_Data.Polygon;
import Generator.PolygonGenerator;
import Pipeline.CameraPacote.CameraClass;
import View.ViewGlobal;
import ViewComponents.MyJPanel;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author FREE
 */
public class MappingTest {
    public static void main(String args[])
    {
        //Polygon poly = PolygonGenerator.generateCube(5.00, new Vertex(0.00,0.00,0.00));
        Polygon poly = PolygonGenerator.generatePiramideQuadrada(5.00, 2.00, new Vertex(0.00,0.00,0.00));
        List< Polygon > scene = new ArrayList<>();
        scene.add(poly);
        
        int altura_panel = 400;
        int largura_panel = 400;
        
        CameraClass camera = new CameraClass();
        //camera.setP(new Vertex(0.00,0.00,-10.00));
        //camera.setVrp(new Vertex(0.00,0.00,20.00));
        camera.setP(new Vertex(-10.00,0.00,0.00));
        camera.setVrp(new Vertex(5.00,0.00,0.00));
        camera.setView_up(new Vertex(0.00,1.00,0.00));
        
        System.out.println("Pontos inicias = " + poly.get3DVertexMatrix());
        
        Matrix operacao_de_camera = camera.getTransform_matrix();
        Matrix depois_da_camera = operacao_de_camera.multiplicacaoMatrix(poly.get3DVertexMatrix());
        poly.set3DVertexMatrix(depois_da_camera);
        
        System.out.println("Pontos depois da camera = " + poly.get3DVertexMatrix());
        
        Matrix operacao_de_projecao = Pipeline.Projecao.Project.getPerspectiveMatrix(10.00);
        System.out.println("matrix proj = " + operacao_de_projecao);
        
        Matrix depois_proj = operacao_de_projecao.multiplicacaoMatrix(poly.get3DVertexMatrix());
        poly.set3DVertexMatrix(depois_proj);
        
        System.out.println("Pontos depois da projecao = " + poly.get3DVertexMatrix());
        
        Matrix Matrix2d = Pipeline.Matrix3Dto2D.Transform.retirarZ( poly.get3DVertexMatrix() );
        poly.set2DVertexMatrix( Matrix2d );
        System.out.println("Pontos depois de ficar 2D = " + poly.get2DVertexMatrix());
        
        Matrix operacao_mapeamento = Pipeline.Mapeamento.Map.getIdealMapping(scene, largura_panel + 0.00, 0.00, altura_panel + 0.00, 0.00);
        Matrix depois_mapeamento = operacao_mapeamento.multiplicacaoMatrix( poly.get2DVertexMatrix() );
        poly.set2DVertexMatrix(depois_mapeamento);
        
        System.out.println("Pontos depois de mapeamento = " + poly.get2DVertexMatrix());
        
        MyJPanel ondeDesenhar = new MyJPanel();
        ondeDesenhar.setPreferredSize(new Dimension(largura_panel,altura_panel));
        ondeDesenhar.addPolygon(poly);
        
        JFrame frame = new JFrame();
        frame.add(ondeDesenhar);
        frame.pack();
        ViewGlobal.centralizarJanela(frame);
        frame.setVisible(true);
        
        
        
        //Vertex vertex1 = new Vertex(1.00,)
    }
}
