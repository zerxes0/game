package Data;

import java.awt.Rectangle;

public class Collider {
    private Rectangle bounds;
    private int widht, height;

    public Collider( int x, int y, int widht, int height ){
        bounds = new Rectangle( x, y, widht , height );
        this.widht = widht;
        this.height = height;
    }

    public void updateBound( int x, int y){
        //actualizamos la posicion del cuadro en base al sprite
        //para que cambie cual es el bound actual sobre
        //el cual se puede colisionar
        bounds.setBounds( x, y, widht, height);
    }

    public Rectangle getBounds(){ return bounds; }

}
