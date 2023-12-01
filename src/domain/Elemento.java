package domain;

import java.awt.image.BufferedImage;

public abstract class Elemento {
    private int x;
    private int y;
    BufferedImage imagen;

    public Elemento(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public int getX(){


        return this.x;

    }
    public int getY(){
        return this.y;
    }
    public BufferedImage getImagen(){
        return this.imagen;
    }





}
