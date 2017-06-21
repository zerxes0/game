package Data;

import java.awt.Point;

public abstract class GameObject{
  
    protected Point pos;
    protected Point origin;
    protected int x;
    protected int y;
    protected int ox = 0;
    protected int oy = 0;
    
    public GameObject(){}
    public GameObject( int x, int y ){
        this.pos = new Point( x, y );
        this.origin = new Point( x, y );
        this.x = x; 
        this.y = y;
    }

    public void setOriginAsCenter( int widht, int height ){
        //esta funcion autocalcula y convierte el centro del objeto visualmente
        //al mismo que el centro del sprite en vez de hacerlo desde la esquina.
        this.origin.setLocation( pos.getX() + widht/2 , pos.getY() + height/2 );
    }

    public void setOrigin( int widht, int height ){
        //Esta funcion es para objetos tipo humanoides convierte el centro del sprite
        //en donde estan los pies, lo hace con autocalculos.
        this.origin.setLocation( pos.getX() + widht/2 , pos.getY() + height );
        this.ox = x + widht/2;
        this.oy = y + height;
    }

    public int getX() { return this.x; }
	public int getY() { return this.y; }
    public int getOy() { return this.oy; }
    public int getOx() { return this.ox; }
    public Point getPos(){ return this.pos; }
    public Point getOrigin(){ return this.origin; }
        
	public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setOx(int ox) { this.ox = ox; }
    public void setOy(int oy) { this.oy = oy; }


}
