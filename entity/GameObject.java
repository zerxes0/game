package entity;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class GameObject{
  
    protected int x;
    protected int y;
    
    /* TODO
     * Implementar colisiones y determinar si un objeto funciona con 
     * colisiones o no. por ejemplo, un item en el inventario
     * posee un sprite, y posicion, pero no colisionara con nada.
     */
    
    public GameObject(){}
    public GameObject( int x, int y ){
       this.x = x; 
       this.y = y;
    }

   // public BufferedImage getSprite() { return sprite; }
    public int getX() { return x; }
	public int getY() { return y; }

	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	//public void setSprite( String sprite ) throws IOException { this.sprite = ImageIO.read( new File( sprite ) ); }
}
