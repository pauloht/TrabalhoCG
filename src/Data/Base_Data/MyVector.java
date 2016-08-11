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
public class MyVector {
    private Double x;
    private Double y;
    private Double z;
    
    public MyVector()
    {
        x = y = z = 0.00;
    }
    
    public MyVector(Double x,Double y,Double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public MyVector(Double[] xyz)
    {
        this();
        if (xyz.length==3)
        {
            this.x = xyz[0];
            this.y = xyz[1];
            this.z = xyz[2];
        }
    }
    
    public MyVector sum(MyVector other_vector)
    {
        Double retorno_x = this.getX()+other_vector.getX();
        Double retorno_y = this.getY()+other_vector.getY();
        Double retorno_z = this.getZ()+other_vector.getZ();
        MyVector retorno = new MyVector(retorno_x,retorno_y,retorno_z);
        return(retorno);
    }
    
    public MyVector sub(MyVector other_vector)
    {
        Double retorno_x = this.getX()-other_vector.getX();
        Double retorno_y = this.getY()-other_vector.getY();
        Double retorno_z = this.getZ()-other_vector.getZ();
        MyVector retorno = new MyVector(retorno_x,retorno_y,retorno_z);
        return(retorno);
    }
    
    public MyVector mul(Double factor)
    {
        Double retorno_x = this.getX()*factor;
        Double retorno_y = this.getY()*factor;
        Double retorno_z = this.getZ()*factor;
        MyVector retorno = new MyVector(retorno_x,retorno_y,retorno_z);
        return(retorno);
    }
    
    public MyVector div(Double factor)
    {
        if (factor==0.00)
        {
            factor = 1.00;
        }
        Double retorno_x = this.getX()/factor;
        Double retorno_y = this.getY()/factor;
        Double retorno_z = this.getZ()/factor;
        MyVector retorno = new MyVector(retorno_x,retorno_y,retorno_z);
        return(retorno);
    }

    public MyVector mul_vetorial(MyVector other_vector)
    {
        Double retorno_x = (this.getY()*other_vector.getZ())-(this.getZ()*other_vector.getY());
        Double retorno_y = (this.getZ()*other_vector.getX())-(this.getX()*other_vector.getZ());
        Double retorno_z = (this.getX()*other_vector.getY())-(this.getY()*other_vector.getX());
        MyVector retorno = new MyVector(retorno_x,retorno_y,retorno_z);
        return(retorno);
    }
    
    public Double mul_escalar(MyVector other_vector)
    {
        Double other_x = other_vector.getX();
        Double other_y = other_vector.getY();
        Double other_z = other_vector.getZ();
        
        Double retorno = other_x*this.x + other_y*this.y + other_z*this.z;
        
        return(retorno);
    }
    
    public Double getTamanho()
    {
        Double retorno = Math.sqrt(x*x+y*y+z*z);
        return(retorno);
    }
    
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }
    
    public Double[] getArray()
    {
        Double[] retorno = new Double[3];
        retorno[0] = this.x;
        retorno[1] = this.y;
        retorno[2] = this.z;
        return(retorno);
    }

    @Override
    public String toString() {
        return "MyVector{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
    
    
}
