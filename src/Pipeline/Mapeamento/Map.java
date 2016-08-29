/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pipeline.Mapeamento;
import Data.Base_Data.*;
import Data.Composta_Data.*;
import View.JanelaPrincipal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo.Tenorio
 */
public class Map {
    
    private static int menorPanelX = -1;
    private static int menorPanelY = -1;
    
    private static double menorPanelXFinal = 0;
    private static double menorPanelYFinal = 0;
    
    private static double comprimentoXMundo = -1.00;
    private static double alturaYMundo = -1.00;
    
    private static int numeroPanelsInformado = 0;
    
    private double XMax = 0.00;
    private double XMin = 0.00;
    private double YMax = 0.00;
    private double YMin = 0.00;
    
    private double UMax = 0.00;
    private double UMin = 0.00;
    private double VMax = 0.00;
    private double VMin = 0.00;
    
    private static Double valorAjustavel = 0.00;
    
    public static synchronized void informarMapeamentoIdeal(int tamanhoXPanel,int tamanhoYPanel,double tamanhoMundoX, double tamanhoMundoY)
    {
        //System.out.println("informando tamanhoXPanel = " + tamanhoXPanel + ",tamahoYPanel = " + tamanhoYPanel + ",tamanhoMundoX = " + tamanhoMundoX + ",tamanhoMundoY = " + tamanhoMundoY);
        informarDimensoesPanel(tamanhoXPanel, tamanhoYPanel);
        informarDimensoesMaximasMundo(tamanhoMundoX, tamanhoMundoY);
        if (numeroPanelsInformado == 0)
        {
            menorPanelX = -1;
            menorPanelY = -1;
            comprimentoXMundo = -1.00;
            alturaYMundo = -1.00;
        }
        incNumeroInformado();
    }
    
    private static void ajustarFinalPanel()
    {
            //System.out.println("ComprimentoXMundo = " + comprimentoXMundo + ",AlturaYMundo = " + alturaYMundo + "\nmenorPanelX = " + menorPanelX + ",menorPanelY = " + menorPanelY);
            Double comprimento = comprimentoXMundo;
            Double altura = alturaYMundo;

            Double comprimentoNoPanel;
            Double alturaNoPanel;

            Double comprimentoMaximo = menorPanelX + 0.00;
            Double alturaMaximo = menorPanelY + 0.00;

            Double sobraNoComprimento;
            Double sobraNaAltura;

            if (Constantes.aproximador(comprimento) == 0.00)
            {
                comprimento = 1.00;
            }

            if (Constantes.aproximador(altura) == 0.00)
            {
                altura = 1.00;
            }

            Double razaoComprimento = comprimentoMaximo/comprimento;
            Double razaoAltura = alturaMaximo/altura;
            Double razaoUsada;

            if (razaoComprimento < razaoAltura)
            {
                razaoUsada = razaoComprimento;
            }
            else
            {
                razaoUsada = razaoAltura;
            }

            comprimentoNoPanel = comprimento*razaoUsada;
            alturaNoPanel = altura*razaoUsada;

            sobraNoComprimento = comprimentoMaximo - comprimentoNoPanel;
            sobraNaAltura = alturaMaximo - alturaNoPanel;

            //System.err.println("Altura = " + altura + "\nComprimento = " + comprimento + "\nrazaoComprimento = " + razaoComprimento + "\nRazaoAltura = " + razaoAltura + "\nRazaoUsada = " + razaoUsada);
            
            /*
            System.out.println("comprimento = " + comprimento + ",altura = " + altura + "\n" +
                               "No panel comprimentoMaximo = " + comprimentoMaximo + ",Altura maxima = " + alturaMaximo + "\n" +
                                "No panel comprimento = " + comprimentoNoPanel + ",altura = " + alturaNoPanel + "\n" +
                                 "Sobra comprimento = " + sobraNoComprimento + ",sobra altura = " + sobraNaAltura);
            */
            
            menorPanelXFinal = comprimentoMaximo - sobraNoComprimento;
            menorPanelYFinal = alturaMaximo - sobraNaAltura;
            //System.out.println("menorPanelXFinal = " + menorPanelXFinal + ",menorPanelYFinal" + menorPanelYFinal);
    }
    
    private static synchronized void incNumeroInformado()
    {
        numeroPanelsInformado++;
        //System.out.println("novo valor numeroPanelsInformado = " + numeroPanelsInformado);
        if (numeroPanelsInformado == 4)
        {
            ajustarFinalPanel();
            if (JanelaPrincipal.janela != null)
            {
                JanelaPrincipal.janela.updateFase2();
            }
            numeroPanelsInformado = 0;
        }
    }
    
    private static synchronized void informarDimensoesPanel(int x,int y)
    {
        if (x < 0 || y < 0)
        {
            throw new IllegalArgumentException("Valores de dimensoes negativas");
        }
        if (x < menorPanelX || menorPanelX < 0)
        {
            menorPanelX = x;
        }
        if (y < menorPanelY || menorPanelY < 0)
        {
            menorPanelY = y;
        }
    }
    
    private static synchronized void informarDimensoesMaximasMundo(double x,double y)
    {
        if (x < 0.00 || y < 0.00)
        {
            throw new IllegalArgumentException("Valores de dimensoes negativas");
        }
        if (x > comprimentoXMundo || comprimentoXMundo < 0.00)
        {
            comprimentoXMundo = x;
        }
        if (y > alturaYMundo || alturaYMundo < 0.00)
        {
            alturaYMundo = y;
        }
    }
    
    public Map()
    {
        
    }
    
    public Matrix getMappingMatrix()
    {
        Double razao_larguras;
        if (Math.abs(XMax - XMin) > Constantes.PRESCISSAO)
        {
            razao_larguras = (UMax - UMin)/(XMax - XMin);
        }
        else
        {
            razao_larguras = 1.00;
        }
        Double razao_alturas;
        
        if (Math.abs(YMax - YMin) > Constantes.PRESCISSAO)
        {
            razao_alturas = (VMin - VMax)/(YMax - YMin);
        }
        else
        {
            razao_alturas = 1.00;
        }
        
        Double movimento_em_x = (-XMin*(razao_larguras)) + UMin;
        
        Double movimento_em_y = (YMin*(-razao_alturas)) + VMax;
        
        //System.err.print("XMin = " + XMin + "\nYMin = " + YMin + "UMin = " + UMin + "\nVMin= " + VMin + "\nRazao larguras = " + razao_larguras + "\nRazao alturas =  " + razao_alturas + "\nmovimentoX = " + movimento_em_x + "\nMovimentoY = " + movimento_em_y);
        
        Double[][] valores = {  { razao_larguras , 0.00            , 0.00  , movimento_em_x } ,
                                { 0.00           , razao_alturas   , 0.00  , movimento_em_y } ,
                                { 0.00           , 0.00            , 1.00  , 0.00           },
                                { 0.00           , 0.00            , 0.00  , 1.00           }};
        
        Matrix returnmatrix = new Matrix(valores);
        /*
        System.out.println(" APAGAR DEPOIS EM MAP.java getMappingMatrix" + "\n" + 
                "Matrix de retorno = " + returnmatrix);
        */
        return(returnmatrix);
    }
    
    public Matrix pegarBonsValores(int tamanhoXPanel,int tamanhoYPanel,double centroXMundo, double centroYMundo)
    {
        //System.out.println("tamanhoDessePanel(X,Y) = " + tamanhoXPanel + "," + tamanhoYPanel + "\nCentroXMundo = " + centroXMundo + ",CentroYMundo = " + centroYMundo);
        //System.out.println("ComprimentoXMundo = " + comprimentoXMundo + ",AlturaYMundo = " + alturaYMundo);
        double sobraVertical = tamanhoYPanel-menorPanelYFinal;
        double sobraHorizontal = tamanhoXPanel-menorPanelXFinal;
        
        double metadeVertical = Math.floor(sobraVertical/2.00);
        double metadeHorizontal = Math.floor(sobraHorizontal/2.00);
        VMax = tamanhoYPanel - metadeVertical;
        VMin = metadeVertical;
        UMax = tamanhoXPanel - metadeHorizontal;
        UMin = metadeHorizontal;
        
        XMax = centroXMundo + comprimentoXMundo/2.00;
        XMin = centroXMundo - comprimentoXMundo/2.00;
        YMax = centroYMundo + alturaYMundo/2.00;
        YMin = centroYMundo - alturaYMundo/2.00;
        
        //System.out.println("sobraVertical = " + sobraVertical + ",SobraHorizontal = " + sobraHorizontal + "\nVMax = " + VMax + ",VMin = " + VMin + ",UMax = " + UMax + ",UMin = " + UMin + "\nYMax = " + YMax + ",YMin = " + YMin + ",XMax = " + XMax + ",XMin = " + XMin);
        
        Matrix retorno = getMappingMatrix();
        
        //System.out.println("matrix de mapeamento = " + retorno);
        
        return( retorno );
    }
    
    /**
     * Tenta achar valores "interessantes" para plano de mapeamento da cena
     * Tentar achar um valor bom para variavel valorAjustavel(dependendo do contesto um valor bom mudaria, achar essa regra)
     * @param cena conjunto de poligonos
     * @return vetor de Double de tamanho 2 onde na posicao 0 esta o comprimento da cena e na posicao 1 a altura
     */
    public Double[] setNiceParametros(List< Polygon > cena)
    {
        boolean primeiraVez = true;
        if (cena.size() > 0)
        {
            for (Polygon poly : cena)
            {
                //System.out.println("Poligono Matrix = " + poly.get3DVertexMatrix());
                for (Edge edge : poly.edge_list)
                {
                    Vertex vertex_inicio = edge.getStart_vertex();
                    Vertex vertex_fim = edge.getEnd_vertex();
                    //System.out.println("xvertexinicio = " + edge.getStart_vertex());
                    Double x_vertex_inicio = vertex_inicio.getPosXDummy();
                    Double y_vertex_inicio = vertex_inicio.getPosYDummy();

                    Double x_vertex_fim = vertex_fim.getPosXDummy();
                    Double y_vertex_fim = vertex_fim.getPosYDummy();

                    if (primeiraVez)
                    {
                        if (x_vertex_inicio > x_vertex_fim)
                        {
                            XMax = x_vertex_inicio;
                            XMin = x_vertex_fim;
                        }
                        else
                        {
                            XMax = x_vertex_fim;
                            XMin = x_vertex_inicio;
                        }

                        if (y_vertex_inicio > y_vertex_fim)
                        {
                            YMax = y_vertex_inicio;
                            YMin = y_vertex_fim;
                        }
                        else
                        {
                            YMax = y_vertex_fim;
                            YMin = y_vertex_inicio;
                        }
                        primeiraVez = false;
                    }
                    else
                    {
                        if (x_vertex_inicio > XMax)
                        {
                            XMax = x_vertex_inicio;
                        }

                        if (x_vertex_inicio < XMin)
                        {
                            XMin = x_vertex_inicio;
                        }

                        if (y_vertex_inicio > YMax)
                        {
                            YMax = y_vertex_inicio;
                        }

                        if (y_vertex_inicio < YMin)
                        {
                            YMin = y_vertex_inicio;
                        }

                        if (x_vertex_fim > XMax)
                        {
                            XMax = x_vertex_fim;
                        }

                        if (x_vertex_fim < XMin)
                        {
                            XMin = x_vertex_fim;
                        }

                        if (y_vertex_fim > YMax)
                        {
                            YMax = y_vertex_fim;
                        }

                        if (y_vertex_fim < YMin)
                        {
                            YMin = y_vertex_fim;
                        }
                    }
                }
            }
            /*
            System.out.println("APAGAR DEPOIS EM MAP getIdealMapping\n "
                    + "XMax = " + XMax + ", XMin = " + XMin + ",YMax = " + YMax + ",YMin = " + YMin + "\n"
                    + "UMax = " + UMax + ", UMin = " + UMin + ",VMax = " + VMax + ",VMin = " + VMin  + "\n");
            */

            XMax = XMax + valorAjustavel;
            XMin = XMin - valorAjustavel;
            YMax = YMax + valorAjustavel;
            YMin = YMin - valorAjustavel;


            /*
            System.out.println("APAGAR DEPOIS EM MAP AJUSTADO\n "
            + "XMax = " + XMax + ", XMin = " + XMin + ",YMax = " + YMax + ",YMin = " + YMin + "\n" );


            System.out.println("XMax-Xmin = " + (XMax-XMin) + "YMax-YMin = " + (YMax-YMin));
            */

            Double comprimento = (XMax-XMin);
            Double altura = (YMax-YMin);

            Double[] retorno = new Double[2];
            retorno[0] = comprimento;
            retorno[1] = altura;

            //System.out.println("VALORES SETADOS");

            return(retorno);
        }
        else
        {
            return(null);
        }
    }
    
    public Double[] setNiceParametros(Polygon polygon)
    {
        List< Polygon > polyLista = new ArrayList<>();
        polyLista.add(polygon);
        return( setNiceParametros(polyLista) );
    }

    public static int getMenorPanelX() {
        return menorPanelX;
    }

    public static int getMenorPanelY() {
        return menorPanelY;
    }
    
    
    
    //<editor-fold defaultstate="collapsed" desc="gettersEsetter">
    
    public double getXMax() {
        return XMax;
    }

    public void setXMax(double XMax) {
        this.XMax = XMax;
    }

    public double getXMin() {
        return XMin;
    }

    public void setXMin(double XMin) {
        this.XMin = XMin;
    }

    public double getYMax() {
        return YMax;
    }

    public void setYMax(double YMax) {
        this.YMax = YMax;
    }

    public double getYMin() {
        return YMin;
    }

    public void setYMin(double YMin) {
        this.YMin = YMin;
    }

    public double getUMax() {
        return UMax;
    }

    public void setUMax(double UMax) {
        this.UMax = UMax;
    }

    public double getUMin() {
        return UMin;
    }

    public void setUMin(double UMin) {
        this.UMin = UMin;
    }

    public double getVMax() {
        return VMax;
    }

    public void setVMax(double VMax) {
        this.VMax = VMax;
    }

    public double getVMin() {
        return VMin;
    }

    public void setVMin(double VMin) {
        this.VMin = VMin;
    }
    
    //</editor-fold>


}
