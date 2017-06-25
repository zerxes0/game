package Data;

import java.awt.Point;

public abstract class GameObject{
  
    protected Point pos;
    protected Point origin;
    protected Point iso;
    
    public GameObject(){}
    public GameObject( int x, int y ){
        this.pos = new Point( x, y );
        this.origin = new Point( x, y );
        this.iso = new Point(x,y);
    }

    public void setOriginAsCenter( int widht, int height ){
        //esta funcion autocalcula y convierte el centro del objeto visualmente
        //al mismo que el centro del sprite en vez de hacerlo desde la esquina.
        this.origin.setLocation( pos.getX() + widht/2 , pos.getY() + height/2 );
    }

    public void setOrigin( int widht, int height ){
        //Esta funcion es para objetos tipo humanoides convierte el centro del sprite
        //en donde estan los pies, lo hace con autocalculos.
        this.origin.setLocation( pos.getX() + widht , pos.getY() + height );
    }
    
    public void toIso(){
        int x = (int) ( origin.getX()/64 )*64;
        int y = (int) ( origin.getY()/16 )*16;
        int sx = x / 16;

        int off = (sx % 2 == 1) ? 32 : 0;
        int isoX = (x - off) / 64; 
        int isoY =  (2 * y) / 32;
        
        iso.setLocation( isoX, isoY );
    } 

    public Point getPos(){ return this.pos; }
    public Point getOrigin(){ return this.origin; }
    public Point getIso(){ return this.iso; }

}
