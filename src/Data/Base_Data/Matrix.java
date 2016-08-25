/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Base_Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paulo.tenorio
 */
public class Matrix {
    ArrayList< ArrayList < Double > > matrix;
    public static final int MATRIXBASICA = -1;
    //matrix.get(i).get(j) pega elemento na coluna i linha j
    
    public Matrix()
    {
        matrix = new ArrayList<>();
    }
    
    public Matrix(int aux)
    {
        this();
        if (aux != MATRIXBASICA)
        {
            matrix = new ArrayList<>(aux);
        }
        else
        {
            Double[][] inicial = new Double[4][4];
            for (int i=0;i<4;i++)
            {
                for (int j=0;j<4;j++)
                {
                    if (i==j)
                    {
                        inicial[i][j] = 1.00;
                    }
                    else
                    {
                        inicial[i][j] = 0.00;
                    }
                }
            }
            setarValoresDaMatrix(inicial);
        }
    }
    
    public Matrix(Double[][] other_matrix)
    {
        this();
        setarValoresDaMatrix(other_matrix);
        
    }
    
    public Matrix(Matrix outra)
    {
        this();
        Double[][] raw_matrix = outra.toRawMatrix();
        for (int i=0;i<raw_matrix.length;i++)
        {
            ArrayList< Double > new_array = new ArrayList<>();
            for (int j=0;j<raw_matrix[i].length;j++)
            {
                new_array.add(new Double(raw_matrix[i][j]));
                //System.out.println("Adicionando " + other_matrix[i][j]);
            }
            this.matrix.add(new_array);
        }
    }
    
    public void setarValoresDaMatrix(Double[][] valores)
    {
        for (int i=0;i<valores.length;i++)
        {
            ArrayList< Double > new_array = new ArrayList<>();
            for (int j=0;j<valores[i].length;j++)
            {
                new_array.add(new Double(valores[i][j]));
                //System.out.println("Adicionando " + other_matrix[i][j]);
            }
            this.matrix.add(new_array);
        }
    }
    
    public Matrix somaMatrix(Matrix matrix2)
    {
        int linha,coluna;
        int linha_2,coluna_2;
        
        linha = this.getLinhas();
        coluna = this.getColunas();
        
        linha_2 = matrix2.getLinhas();
        coluna_2 = matrix2.getColunas();
        
        if (linha!=linha_2||coluna!=coluna_2)
        {
            
        }
        else
        {
            Matrix new_matrix = new Matrix();
            for (int i=0;i<linha;i++)
            {
                ArrayList< Double > new_array = new ArrayList<>();
                for (int j=0;j<coluna;j++)
                {                
                    new_array.add( this.matrix.get(i).get(j) + matrix2.matrix.get(i).get(j) );
                }
                new_matrix.matrix.add(new_array);
            }
            return (new_matrix);
        }
        return (null);
    }
    
    public Matrix multiplicacaoMatrix(Matrix matrix2)
    {
        int linha,coluna;
        int linha_2,coluna_2;
        
        linha = this.getLinhas();
        coluna = this.getColunas();
        
        linha_2 = matrix2.getLinhas();
        coluna_2 = matrix2.getColunas();
        
        if (coluna!=linha_2)
        {
            //nao realiza multiplicacao;
            System.out.println("coluna!=linha_2,coluna = "+coluna+",linha = "+linha+"\ncoluna_2 = "+coluna_2+",linha_2 = "+linha_2);
        }
        else
        {
            int y = coluna;
            Matrix new_matrix = new Matrix();
            for (int i=0;i<linha;i++)
            {
                ArrayList< Double > new_array = new ArrayList<>();
                for (int j=0;j<coluna_2;j++)
                {
                    Double x = 0.00;
                    for (int k=0;k<y;k++)
                    {
                        //System.out.println("i="+i+",j="+j+",k="+k);
                        //System.out.println("x="+x+",adicionando em x + ("+this.matrix.get(i).get(k)+"*"+matrix2.matrix.get(k).get(j)+")");
                        x = x + this.matrix.get(i).get(k) * matrix2.matrix.get(k).get(j);
                    }
                    //System.out.println("x="+x);
                    new_array.add(x);
                }
                new_matrix.matrix.add(new_array);
            }
            return(new_matrix);
        }
        return(null);
    }
    
    public Matrix multiplicacaoConstante(Double constante)
    {
        int linha,coluna;
        
        linha = this.getLinhas();
        coluna = this.getColunas();
        Matrix new_matrix = new Matrix();
        for (int i=0;i<linha;i++)
        {
            ArrayList< Double > new_array = new ArrayList<>();
            for (int j=0;j<coluna;j++)
            {                
                new_array.add( this.matrix.get(i).get(j) * constante);
            }
            new_matrix.matrix.add(new_array);
        }
        return (new_matrix);
    }
    
    public Double[] Vetorize()
    {
        int linha,coluna;
        int contador = 0;
        linha = this.getLinhas();
        coluna = this.getColunas();
        Double[] return_vector = new Double[linha*coluna];
        for (int i=0;i<linha;i++)
        {
            for (int j=0;j<coluna;j++)
            {                
                return_vector[contador] = this.matrix.get(i).get(j);
                contador++;
            }
        }
        return(return_vector);
    }
    
    public Double[][] toRawMatrix()
    {
        int linha,coluna;
        int contador = 0;
        linha = this.getLinhas();
        coluna = this.getColunas();
        Double[][] return_matrix = new Double[linha][coluna];
        for (int i=0;i<linha;i++)
        {
            for (int j=0;j<coluna;j++)
            {                
                return_matrix[i][j] = this.matrix.get(i).get(j);
            }
        }
        return(return_matrix);
    }
    
    public int getColunas()
    {
        return(matrix.get(0).size());
    }
    
    public int getLinhas()
    {
        return(matrix.size());
    }
    
    public void printMe()
    {
        System.out.println("\n------------------------------------");
        for (int i=0;i<matrix.size();i++)
        {
            for (int j=0;j<matrix.get(i).size();j++)
            {
                System.out.print(matrix.get(i).get(j)+" ");
            }
            System.out.println("");
        }
        System.out.println("------------------------------------");
    }
    
    public static Matrix concatenacao(List< Matrix > lista)
    {
        boolean primeira_vez = true;
        Matrix retorno = null;
        for (Matrix matrix : lista)
        {
            if (primeira_vez)
            {
                primeira_vez = false;
                retorno = matrix;
            }
            else
            {
                retorno = matrix.multiplicacaoMatrix(retorno);
            }
        }
        return(retorno);
    }

    @Override
    public String toString() {
        StringBuilder aux = new StringBuilder();
        aux.append("\n------------------------------------\n");
        for (int i=0;i<matrix.size();i++)
        {
            for (int j=0;j<matrix.get(i).size();j++)
            {
                aux.append(String.format("%.8f", matrix.get(i).get(j))).append(" ");
            }
            aux.append("\n");
        }
        aux.append("------------------------------------\n");
        return(aux.toString());
    }
    
    
}
