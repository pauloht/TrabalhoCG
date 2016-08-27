/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SalvarCarregar;

import Data.Base_Data.Vertex;
import java.util.Objects;

/**
 *
 * @author Paulo.Tenorio
 */
//<editor-fold defaultstate="collapsed" desc="ClassVertexInterno">

class VertexInterno
{
    private Vertex vertex;
    private String nome;
    
    public VertexInterno(Vertex vertex,String nome)
    {
        this.vertex = vertex;
        this.nome = nome;
    }
    
    public Vertex getVertex()
    {
        return(vertex);
    }
    
    public String getNome()
    {
        return(nome);
    }
    
    public void setNome(String novoNome)
    {
        nome = novoNome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.vertex);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VertexInterno other = (VertexInterno) obj;
        if (!Objects.equals(this.vertex, other.vertex)) {
            return false;
        }
        return true;
    }
    
    
}

//</editor-fold>
