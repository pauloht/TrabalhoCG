/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pipeline.CameraPacote;
import Data.Base_Data.*;

/**
 * Configuracoes de Camera
 * SINGLETON
 * @author Paulo.Tenorio
 */
public class CameraClass {
    Vertex vrp;//cordenadas da camera
    Vertex p;//ponto focal
    Vertex view_up;//view up da camera Y
    Matrix transform_matrix;//matriz de conversao de SRU para SRC
    
    private static CameraClass CAMERA;
    
    private CameraClass()
    {
        vrp = new Vertex();
        p = new Vertex();
        view_up = new Vertex(0.00,1.00,0.00);
        Double[][] base = { {1.00,0.00,0.00,0.00},
                            {0.00,1.00,0.00,0.00},
                            {0.00,0.00,1.00,0.00},
                            {0.00,0.00,0.00,1.00}};
        transform_matrix = new Matrix(base);
    }
    
    public static CameraClass getInstance()
    {
        if (CAMERA == null)
        {
            CAMERA = new CameraClass();
        }
        return(CAMERA);
    }
    
    private void updateTransform_matrix()
    {
        MyVector n = new MyVector();
        MyVector u = new MyVector();
        MyVector v = new MyVector();
        MyVector N = new MyVector();
        MyVector V = new MyVector();
        
        MyVector Y = view_up.toMyVector();
        
        N = N.sum(this.vrp.toMyVector());//N=VRP,N vale VRP
        N = N.sub(this.p.toMyVector());//N = (N-P) ou seja VRP-P ou seja agora N tem o valor certo
        //System.out.println("N="+N+"\n");
        
        n = N.div(N.getTamanho());//n = normalização de N
        
        V = Y.sub( n.mul( Y.mul_escalar(n) ) );// V = Y - (n * (Y escalar n))
        //System.out.println("V="+V);
        
        v = V.div( V.getTamanho() );//v = normalizacao de V
        
        u = v.mul_vetorial(n);
        
        Double[][] base_matrix = {  {1.00,0.00,0.00,0.00},
                                    {0.00,1.00,0.00,0.00},
                                    {0.00,0.00,1.00,0.00},
                                    {0.00,0.00,0.00,1.00}};
        
        Double[][] translacao_matrix = {    {1.00,0.00,0.00,0.00},
                                            {0.00,1.00,0.00,0.00},
                                            {0.00,0.00,1.00,0.00},
                                            {0.00,0.00,0.00,1.00}};
        translacao_matrix[0][3] = -this.getVrp().getPosXDummy();
        translacao_matrix[1][3] = -this.getVrp().getPosYDummy();
        translacao_matrix[2][3] = -this.getVrp().getPosZDummy();
        
        Matrix matrix_de_translacao = new Matrix(translacao_matrix);
        
        Double[][] rotacao_matrix = {   {1.00,0.00,0.00,0.00},
                                        {0.00,1.00,0.00,0.00},
                                        {0.00,0.00,1.00,0.00},
                                        {0.00,0.00,0.00,1.00}};
        rotacao_matrix[0][0] = u.getX();
        rotacao_matrix[0][1] = u.getY();
        rotacao_matrix[0][2] = u.getZ();
        
        rotacao_matrix[1][0] = v.getX();
        rotacao_matrix[1][1] = v.getY();
        rotacao_matrix[1][2] = v.getZ();
        
        rotacao_matrix[2][0] = n.getX();
        rotacao_matrix[2][1] = n.getY();
        rotacao_matrix[2][2] = n.getZ();
        
        Matrix matrix_de_rotacao = new Matrix(rotacao_matrix);
        
        //System.out.println("n = "+n+'\n'+"v = "+v+'\n'+"u = "+u+'\n');
        
        //System.out.println("Trans="+matrix_de_translacao+"\n"+"Rotacao="+matrix_de_rotacao+"\n");
        
        Matrix retorno = matrix_de_rotacao.multiplicacaoMatrix(matrix_de_translacao);
        //System.out.println("retorno="+retorno);
        //System.out.println("N="+N+"\nn="+n+"\nV="+V+"\nv="+v+"\nu="+u+"\nTransform_matrix->" + retorno);
        this.transform_matrix = retorno;
    }

    public Vertex getVrp() {
        return vrp;
    }

    public void setVrp(Vertex vrp) {
        this.vrp = vrp;
    }

    public Vertex getP() {
        return p;
    }

    public void setP(Vertex p) {
        this.p = p;
    }

    public Vertex getView_up() {
        return view_up;
    }

    public void setView_up(Vertex view_up) {
        this.view_up = view_up;
    }

    public Matrix getTransform_matrix() {
        updateTransform_matrix();
        return transform_matrix;
    }
    
}
