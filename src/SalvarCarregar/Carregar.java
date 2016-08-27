/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalvarCarregar;

import Data.Base_Data.Edge;
import Data.Base_Data.Face;
import Data.Base_Data.Matrix;
import Data.Base_Data.Vertex;
import Data.Composta_Data.Polygon;
import Generator.Extrusao;
import Modificadores.BendConstraints;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo.Tenorio
 */
public class Carregar {
    private static final int ARQUIVOLIDOCOMSUCESSO = 2;
    private static final int TAGLIDACOMSUCESSO = 1;
    private static final int FALHAAOLERTAG = -1;
    private static final int FALHAAOLERARQUIVO = -2;
    
    public static List< Polygon > lerArquivo(File arquivo) throws FileNotFoundException, ArquivoCorrompidoException, IOException
    {
        FileReader fr = new FileReader(arquivo);

        BufferedReader br = new BufferedReader(fr);
        List< Polygon > retorno = new ArrayList<>();
        String linha = "";
        List< List < String > > poligonosString = new ArrayList<>();
        boolean terminouPoligono = true;
        List< String > poligonoLocal = new ArrayList<>();
        while (true)
        {
            linha = br.readLine();
            if (linha==null)
            {
                break;
            }
            linha = linha.replaceAll(" ", "");
            switch (linha)
            {
                case "</Poligono>" :
                    if (terminouPoligono)
                    {
                        throw new ArquivoCorrompidoException("Erro em tag de fechamento Poligono");
                    }
                    terminouPoligono = true;
                    poligonosString.add(poligonoLocal);
                    break;
                case "<Poligono>" :
                    if (!terminouPoligono)
                    {
                        throw new ArquivoCorrompidoException("Erro em tag de abertura Poligono");
                    }
                    poligonoLocal = new ArrayList<>();
                    terminouPoligono = false;
                    break;
                case "<Poligonos>" :
                    break;
                case "</Poligonos>" :
                    break;
                default :
                    poligonoLocal.add(linha);
            }
        }

        for (List< String > lista : poligonosString)
        {
            /*
            System.out.println("COMECO");
            for (String string : lista)
            {
                System.out.println(string);
            }
            System.out.println("FIM");
            */

            Polygon novoPoligono = readPoligono(lista);
            retorno.add(novoPoligono);
        }

        return(retorno);
           
   
    }
    
    private static Polygon readPoligono(List< String > stringPassada) throws ArquivoCorrompidoException
    {
        //Polygon retorno = new Polygon();
        int contador = 0;
        int contadorLocal = 0;
        int marcadorLoop = 0;
        boolean sairWhile = false;
        String linhaAuxiliar = "";
        StringBuilder sb = new StringBuilder();
        List< VertexInterno > listaVertexInterno = new ArrayList<>();
        List< EdgeInterno > listaEdgeInterno = new ArrayList<>();
        int numeroSegmetos = -1;
        
        Double fatorBend = null;
        Double fatorBevel = null;
        Double fatorTwist = null;
        BendConstraints bendConstante = null;
        String poligonoNome = null;
        Vertex alvoPoligono = null;
        Matrix operacoes = null;
        
        try{
            while (!sairWhile)
            {
                String linhaLida = stringPassada.get(contador);
                //System.out.println("Linha lida : " + linhaLida);
                switch (linhaLida) {
                    case "<Nome>":
                            String nomePoligono = stringPassada.get(contador+1);
                            poligonoNome = nomePoligono;
                            if (!stringPassada.get(contador+2).equals("</Nome>"))
                            {
                                throw new ArquivoCorrompidoException("Erro em finalizar tag Nome");
                            }
                            contador = contador+3;
                            break;
                    case "<Base>":
                            if (!stringPassada.get(contador+1).equals("<Vertexes>"))
                            {
                                throw new ArquivoCorrompidoException("Erro em abertura tag Vertexes");
                            }
                            contador = contador+1;
                            break;
                    case "<Vertexes>":
                            contadorLocal = contador+1;
                            while (true)
                            {
                                String linhaLidaLocal = stringPassada.get(contadorLocal);
                                String nomeVertex;
                                String valorX = "";
                                String valorY = "";
                                String valorZ = "";
                                if (linhaLidaLocal.equals("</Vertex>"))
                                {
                                    break;
                                }
                                sb.setLength(0);
                                
                                //pegar parte nome
                                for (int i=0;i<linhaLidaLocal.length();i++)
                                {
                                    if (linhaLidaLocal.charAt(i)=='(')
                                    {
                                        marcadorLoop = i;
                                        break;
                                    }
                                    sb.append(linhaLidaLocal.charAt(i));
                                }
                                nomeVertex = sb.toString();
                                
                                //pegar valores x,y,z
                                int variavelAuxiliar = 0;
                                sb.setLength(0);
                                for (int i=marcadorLoop+1;i<linhaLidaLocal.length();i++)
                                {
                                    if (linhaLidaLocal.charAt(i)==',')
                                    {
                                        if (variavelAuxiliar == 0)
                                        {
                                            valorX = sb.toString();
                                            sb.setLength(0);
                                        }
                                        else
                                        {
                                            valorY = sb.toString();
                                            sb.setLength(0);
                                        }
                                        variavelAuxiliar++;
                                    }
                                    else if (linhaLidaLocal.charAt(i)==')')
                                    {
                                        valorZ = sb.toString();
                                        sb.setLength(0);
                                        break;
                                    }
                                    else
                                    {
                                        sb.append(linhaLidaLocal.charAt(i));
                                    }
                                }
                                Vertex novoVertex = new Vertex(Double.parseDouble(valorX),Double.parseDouble(valorY),Double.parseDouble(valorZ));
                                VertexInterno novoVertexInterno = new VertexInterno(novoVertex, nomeVertex);
                                listaVertexInterno.add(novoVertexInterno);
                                contadorLocal++;
                            }
                            contador = contadorLocal + 1;
                            break;
                    case "<Edges>":
                            contadorLocal = contador+1;
                            while (true)
                            {
                                String linhaLidaLocal = stringPassada.get(contadorLocal);
                                if (linhaLidaLocal.equals("</Edges>"))
                                {
                                    break;
                                }
                                else
                                {
                                    String edgeNome = "NOMEINVALIDO";
                                    String edgeInicio = "NOMEINVALIDO";
                                    String edgeFim = "NOMEINVALIDO";
                                    
                                    sb.setLength(0);
                                    int variavelAuxiliar = 0;
                                    
                                    for (int i=0;i<linhaLidaLocal.length();i++)
                                    {
                                        char lido = linhaLidaLocal.charAt(i);
                                        if (lido == '(')
                                        {
                                            edgeNome = sb.toString();
                                            sb.setLength(0);
                                        }
                                        else if (lido == ',')
                                        {
                                            edgeInicio = sb.toString();
                                            sb.setLength(0);
                                        }
                                        else if (lido == ')')
                                        {
                                            edgeFim = sb.toString();
                                            sb.setLength(0);
                                            break;
                                        }
                                        else
                                        {
                                            sb.append(lido);
                                        }
                                    }
                                    
                                    EdgeInterno novoEdgeInterno = new EdgeInterno(edgeNome,edgeInicio,edgeFim);
                                    listaEdgeInterno.add(novoEdgeInterno);
                                }
                                contadorLocal++;
                            }
                            if (!stringPassada.get(contadorLocal+1).equals("</Base>"))
                            {
                                throw new ArquivoCorrompidoException("Erro em tag de fechamento Base");
                            }
                            contador = contadorLocal+2;
                            break;
                    case "<Alvo>":
                            linhaAuxiliar = stringPassada.get(contador+1);
                            sb.setLength(0);
                            boolean stringValida = false;
                            int variavelAuxiliar = 0;
                            String valorX = "VALORINVALIDO";
                            String valorY = "VALORINVALIDO";
                            String valorZ = "VALORINVALIDO";
                            for (int i=0;i<linhaAuxiliar.length();i++)
                            {
                                char lido = linhaAuxiliar.charAt(i);
                                if (lido == '(')
                                {
                                    //inicio
                                }
                                else if (lido == ',')
                                {
                                    if (variavelAuxiliar==0)
                                    {
                                        valorX = sb.toString();
                                        sb.setLength(0);
                                        variavelAuxiliar++;
                                    }
                                    else
                                    {
                                        valorY = sb.toString();
                                        sb.setLength(0);
                                        variavelAuxiliar++;
                                    }
                                }
                                else if (lido == ')')
                                {
                                    valorZ = sb.toString();
                                    if (variavelAuxiliar == 2)
                                    {
                                        stringValida = true;
                                    }
                                    sb.setLength(0);
                                    break;
                                }
                                else
                                {
                                    sb.append(lido);
                                }
                            }
                            if (!stringValida)
                            {
                                throw new ArquivoCorrompidoException("Erro em representacao vertex Alvo");
                            }
                            alvoPoligono = new Vertex(Double.parseDouble(valorX),Double.parseDouble(valorY),Double.parseDouble(valorZ));
                            if (!stringPassada.get(contador+2).equals("</Alvo>"))
                            {
                                throw new ArquivoCorrompidoException("Erro em tag fechamento de Alvo");
                            }
                            contador = contador+3;
                        break;
                    case "<Segmentos>":
                            linhaAuxiliar = stringPassada.get(contador+1);
                            numeroSegmetos = Integer.parseInt( linhaAuxiliar );
                            linhaAuxiliar = stringPassada.get(contador+2);
                            if (!linhaAuxiliar.equals("</Segmentos>"))
                            {
                                throw new ArquivoCorrompidoException("Erro em tag de fechamento Segmentos");
                            }
                            contador = contador+3;
                            break;
                    case "<Bevel>":
                            linhaAuxiliar = stringPassada.get(contador+1);
                            fatorBevel = Double.parseDouble(linhaAuxiliar);
                            linhaAuxiliar = stringPassada.get(contador+2);
                            if (!linhaAuxiliar.equals("</Bevel>"))
                            {
                                throw new ArquivoCorrompidoException("Erro em tag de fechamento Bevel");
                            }
                            contador = contador+3;
                            break;
                    case "<Twist>":
                            linhaAuxiliar = stringPassada.get(contador+1);
                            fatorTwist = Double.parseDouble(linhaAuxiliar);
                            linhaAuxiliar = stringPassada.get(contador+2);
                            if (!linhaAuxiliar.equals("</Twist>"))
                            {
                                throw new ArquivoCorrompidoException("Erro em tag de fechamento Twist");
                            }
                            contador = contador+3;
                            break;
                    case "<Bend>":
                            linhaAuxiliar = stringPassada.get(contador+1);
                            fatorBend = Double.parseDouble(linhaAuxiliar);
                            linhaAuxiliar = stringPassada.get(contador+2);
                            int valorConstante = Integer.parseInt(linhaAuxiliar);
                            bendConstante = BendConstraints.getInstanciaPorValor(valorConstante);
                            linhaAuxiliar = stringPassada.get(contador+3);
                            if (!linhaAuxiliar.equals("</Bend>"))
                            {
                                throw new ArquivoCorrompidoException("Erro em tag de fechamento Bend");
                            }
                            contador = contador+4;
                        break;
                    case "<Matrix>":
                            System.out.println("Matrix iniciada!");
                            Double[][] matrixDeTransformacaoRaw = new Double[4][4];
                            for (int i=0;i<4;i++)
                            {
                                linhaAuxiliar = stringPassada.get(contador+i+1);
                                double valor1=0.00,valor2=0.00,valor3=0.00,valor4=0.00;
                                int variavelDeEstado = 0;
                                sb.setLength(0);
                                for (int j=0;j<linhaAuxiliar.length();j++)
                                {
                                    char charLido = linhaAuxiliar.charAt(j);
                                    if (charLido == ',')
                                    {
                                        switch(variavelDeEstado)
                                        {
                                            case 0 :
                                                valor1 = Double.parseDouble(sb.toString());
                                                sb.setLength(0);
                                                variavelDeEstado++;
                                                break;
                                            case 1 :
                                                valor2 = Double.parseDouble(sb.toString());
                                                sb.setLength(0);
                                                variavelDeEstado++;
                                                break;
                                            case 2 :
                                                valor3 = Double.parseDouble(sb.toString());
                                                sb.setLength(0);
                                                variavelDeEstado++;
                                                break;
                                            default : throw new ArquivoCorrompidoException("Matrix com tamanho/formato incoerente!");
                                        }
                                    }
                                    else
                                    {
                                        sb.append(charLido);
                                    }
                                }
                                valor4 = Double.parseDouble(sb.toString());
                                matrixDeTransformacaoRaw[i][0] = valor1;
                                matrixDeTransformacaoRaw[i][1] = valor2;
                                matrixDeTransformacaoRaw[i][2] = valor3;
                                matrixDeTransformacaoRaw[i][3] = valor4;
                            }
                            operacoes = new Matrix( matrixDeTransformacaoRaw );
                            linhaAuxiliar = stringPassada.get(contador+5);
                            if (!linhaAuxiliar.equals("</Matrix>"))
                            {
                                throw new ArquivoCorrompidoException("Erro em tag de fechamento Matrix");
                            }
                            contador = contador+6;
                            sairWhile = true;
                            break;
                    default : 
                            throw new ArquivoCorrompidoException("Tag ilegal!");
                }
            }
        }
        catch( IndexOutOfBoundsException e)
        {
            throw new ArquivoCorrompidoException("Tamanhos incoerentes!");
        }
        catch ( ArithmeticException e)
        {
            throw new ArquivoCorrompidoException("Erro em conversão!");
        }
        catch ( IllegalArgumentException e)
        {
            e.printStackTrace();
            throw new ArquivoCorrompidoException("Argumento ilegal!");
        }
        System.out.println("numeroSegmentos = " + numeroSegmetos);
        
        /*
        System.out.println("--------DESCREVENDO VERTEX--------");
        for (VertexInterno vertex : listaVertexInterno)
        {
            System.out.println("Nome vertex = " + vertex.getNome());
            System.out.println("Cord = " + vertex.getVertex().toString());
        }
        System.out.println("----------FIMVERTEX----------------\n\n");
        
        System.out.println("---------DESCREVENDO EDGES------------");
        for (EdgeInterno edge : listaEdgeInterno)
        {
            System.out.println("Nome edge = " + edge.getNome());
            System.out.println("comeco = " + edge.getVertexInicio());
            System.out.println("fim = " + edge.getVertexFim());
        }
        System.out.println("--------------FIMEDGES-----------------\n\n");
        */
        
        List< Vertex > vertexBase = new ArrayList<>();
        List< Edge > edgeBase = new ArrayList<>();
        List< Face > faceBase = new ArrayList<>();
        for (VertexInterno vertexInterno : listaVertexInterno)
        {
            vertexBase.add(vertexInterno.getVertex());
        }
        for (EdgeInterno edgeInterno : listaEdgeInterno)
        {
            boolean achouInicial = false;
            boolean achouFinal = false;
            int posicaoInicial = -1;
            int posicaoFinal = -1;
            for (int i=0;i<listaVertexInterno.size();i++)
            {
                if (achouInicial&&achouFinal)
                {
                    break;
                }
                VertexInterno local = listaVertexInterno.get(i);
                if (edgeInterno.getVertexInicio().equals(local.getNome()))
                {
                    achouInicial=true;
                    posicaoInicial = i;
                }
                if (edgeInterno.getVertexFim().equals(local.getNome()))
                {
                    achouFinal=true;
                    posicaoFinal = i;
                }
            }
            if (posicaoInicial<0||posicaoFinal<0)
            {
                throw new ArquivoCorrompidoException("Base com edge sem vertex?");
            }
            Edge novaEdge = new Edge(vertexBase.get(posicaoInicial),vertexBase.get(posicaoFinal));
            edgeBase.add(novaEdge);
        }
        try{
            Polygon base = new Polygon(vertexBase,edgeBase,faceBase);
            base.base = new Polygon( base );
            Polygon retorno = Extrusao.gerarPolygonoExtrudido(base, numeroSegmetos, alvoPoligono);
            if (poligonoNome == null)
            {
                System.out.println("NOEM NULO????????????????????????");
            }
            if (retorno == null)
            {
                System.out.println("RETORNO NULO?");
            }
            retorno.nome = poligonoNome;
            retorno.fatorBevel = fatorBevel;
            retorno.fatorBend = fatorBend;
            retorno.constanteBend = bendConstante;
            retorno.fatorTwist = fatorTwist;
            retorno.setOperacoes(operacoes);
            return(retorno);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            throw new ArquivoCorrompidoException("Algum campo necessario nao foi encontrado!");
        }
    }
    
    
    public static void main(String args[])
    {
        File arquivoLido = new File("TESTCG.txt");
        List< Polygon > retorno = null;
        try {
            retorno = lerArquivo(arquivoLido);
        } catch (ArquivoCorrompidoException ex) {
            Logger.getLogger(Carregar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Carregar.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (retorno != null)
        {
            System.out.println("retorno.size = " + retorno.size());
            for (Polygon polygon : retorno)
            {
                System.out.println("Nome : " + polygon.nome);
                System.out.println("alvo : " + polygon.alvo);
                System.out.println("bevel : " + polygon.fatorBevel);
                System.out.println("twist : " + polygon.fatorTwist);
                System.out.println("bend : " + polygon.fatorBend);
                System.out.println("ConstanteBend : " + polygon.constanteBend.getValor());
                System.out.println("Matrix de operacoes : " + polygon.getOperacoes());
                
                System.out.println("Base vertex : ");
                for (Vertex vertex : polygon.base.vertex_list)
                {
                    System.out.println("Vertex : " + vertex);
                }
                System.out.println("Base edges : ");
                for (Edge edge : polygon.base.edge_list)
                {
                    System.out.println("Edge inicio : " + edge.getStart_vertex());
                    System.out.println("Edge fim : " + edge.getEnd_vertex());
                }
                
                System.out.println("pontos poligono : " + polygon.get3DVertexMatrixRoot());
            }
        }
        else
        {
            System.out.println("retorno nulo!");
        }
    }
}


