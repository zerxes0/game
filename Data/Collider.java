package Data;

import java.awt.Rectangle;

public class Collider {

    private Rectangle bounds;

    public Collider( int x, int y, int widht, int height ){
        bounds = new Rectangle( x, y, widht , height );
    }

    public Rectangle getBounds(){ return bounds; }
}
