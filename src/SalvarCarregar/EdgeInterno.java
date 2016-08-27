/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalvarCarregar;

/**
 *
 * @author Paulo.Tenorio
 */
//<editor-fold defaultstate="collapsed" desc="ClassEdgeInterno">

class EdgeInterno
{
    private String nome;
    
    private String vertexInicio;
    
    private String vertexFim;
    
    public EdgeInterno(String nome,String vertexInicio,String vertexFim)
    {
        this.nome = nome;
        this.vertexInicio = vertexInicio;
        this.vertexFim = vertexFim;
    }

    public String getNome() {
        return nome;
    }

    public String getVertexInicio() {
        return vertexInicio;
    }

    public String getVertexFim() {
        return vertexFim;
    }
    
}

//</editor-fold>