/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data.Base_Data;

/**
 *
 * @author Paulo.Tenorio
 */
public class Vertex {
    double pos_x,pos_y,pos_z;
    Edge edge;
    
    public Vertex(){
        pos_x = pos_y = pos_z = 0.00;
        edge = null;
    }
    
    public Vertex(Double x,Double y,Double z)
    {
        pos_x=x;
        pos_y=y;
        pos_z=z;
        edge = null;
    }
    
    public Vertex(Vertex outro)
    {
        pos_x = outro.pos_x;
        pos_y = outro.pos_y;
        pos_z = outro.pos_z;
        edge = null;
    }
    
    public Vertex(Double x,Double y, Double z,Edge edge)
    {
        this(x,y,z);
        this.edge = edge;
    }
    
    public Double[] getPosArray()
    {
        Double[] vet = new Double[3];
        vet[0] = pos_x;
        vet[1] = pos_y;
        vet[2] = pos_z;
        return (vet);
    }
    
    public void setPosArray(Double[] vet)
    {
        if (vet.length<=3)
        {
            pos_x = vet[0];
            pos_y = vet[1];
            pos_z = vet[2];
        }
        else
        {
            //donothing
        }
    }
    
    public Edge getEdge()
    {
        return edge;
    }
    
    public void setEdge(Edge to_set)
    {
        edge = to_set;
    }
    
    public void printMe()
    {
        System.out.print("("+pos_x+","+pos_y+","+pos_z+")");
    }

    public Double getPos_x() {
        return pos_x;
    }

    public void setPos_x(Double pos_x) {
        this.pos_x = pos_x;
    }

    public Double getPos_y() {
        return pos_y;
    }

    public void setPos_y(Double pos_y) {
        this.pos_y = pos_y;
    }

    public Double getPos_z() {
        return pos_z;
    }

    public void setPos_z(Double pos_z) {
        this.pos_z = pos_z;
    }

    public MyVector toMyVector() {
        MyVector retorno = new MyVector(pos_x,pos_y,pos_z);
        return(retorno);
    }

    public static double distanciaEntreDoisVetores(Vertex a,Vertex b)
    {
        double aX = a.getPos_x();
        double aY = a.getPos_y();
        double aZ = a.getPos_z();
        
        double bX = b.getPos_x();
        double bY = b.getPos_y();
        double bZ = b.getPos_z();
        
        double distancia = Math.sqrt( (aX - bX)*(aX - bX) + (aY - bY)*(aY - bY) + (aZ - bZ)*(aZ - bZ) );
        return( distancia );
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.pos_x) ^ (Double.doubleToLongBits(this.pos_x) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.pos_y) ^ (Double.doubleToLongBits(this.pos_y) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.pos_z) ^ (Double.doubleToLongBits(this.pos_z) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vertex other = (Vertex) obj;
        if (Double.doubleToLongBits(this.pos_x) != Double.doubleToLongBits(other.pos_x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.pos_y) != Double.doubleToLongBits(other.pos_y)) {
            return false;
        }
        if (Double.doubleToLongBits(this.pos_z) != Double.doubleToLongBits(other.pos_z)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Vertex{" + "pos_x=" + pos_x + ", pos_y=" + pos_y + ", pos_z=" + pos_z + '}';
    }
    
    
}
