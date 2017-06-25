package systems;

import java.awt.Rectangle;

public class Collider {
    private Rectangle bounds;
    private int widht, height;
    private int offX;
    private int offY;

    public Collider( int x, int y, int widht, int height, int offX , int offY ){
        bounds = new Rectangle( x + offX, y + offY, widht , height );
        this.widht = widht;
        this.height = height;
        this.offX = offX;
        this.offY = offY;
    }

    public void updateBound( int x, int y){
        //actualizamos la posicion del cuadro en base al sprite
        //para que cambie cual es el bound actual sobre
        //el cual se puede colisionar
        bounds.setBounds( x + offX , y + offY, widht, height);
    }
    	
    public Rectangle getBounds(){ return bounds; }

}
