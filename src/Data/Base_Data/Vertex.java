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
    
    double posXMovel,posYMovel,posZMovel;
    
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
        
        posXMovel = pos_x;
        posYMovel = pos_y;
        posZMovel = pos_z;
        edge = null;
    }
    
    public Vertex(Vertex outro)
    {
        pos_x = outro.pos_x;
        pos_y = outro.pos_y;
        pos_z = outro.pos_z;
        
        posXMovel = outro.posXMovel;
        posYMovel = outro.posYMovel;
        posZMovel = outro.posZMovel;
        
        edge = null;
    }
    
    public Vertex(Double x,Double y, Double z,Edge edge)
    {
        this(x,y,z);
        this.edge = edge;
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

    public Double getPosXDummy() {
        return posXMovel;
    }
    
    public Double getPosXRoot()
    {
        return pos_x;
    }

    public void setPosXFIXO(Double pos_x) {
        this.pos_x = pos_x;
        this.posXMovel = pos_x;
    }
    
    public void setPosXDummy(Double pos_x)
    {
        this.posXMovel = pos_x;
    }

    public Double getPosYDummy() {
        return posYMovel;
    }
    
    public Double getPosYRoot()
    {
        return pos_y;
    }

    public void setPosYFIXO(Double pos_y) {
        this.pos_y = pos_y;
        this.posYMovel = pos_y;
    }
    
    public void setPosYDummy(Double pos_y)
    {
        this.posYMovel = pos_y;
    }

    public Double getPosZRoot()
    {
        return pos_z;
    }
    
    public Double getPosZDummy() {
        return posZMovel;
    }

    public void setPosZFIXO(Double pos_z) {
        this.pos_z = pos_z;
        this.posZMovel = pos_z;
    }
    
    public void setPosZDummy(Double pos_z)
    {
        this.posZMovel = pos_z;
    }

    public MyVector toMyVector() {
        MyVector retorno = new MyVector(pos_x,pos_y,pos_z);
        return(retorno);
    }

    public static double distanciaEntreDoisVetores(Vertex a,Vertex b)
    {
        double aX = a.getPosXDummy();
        double aY = a.getPosYDummy();
        double aZ = a.getPosZDummy();
        
        double bX = b.getPosXDummy();
        double bY = b.getPosYDummy();
        double bZ = b.getPosZDummy();
        
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
        return '(' + Double.toString(pos_x) + ',' + Double.toString(pos_y) + ',' + Double.toString(pos_z) + ')';
    }
    
    
}
