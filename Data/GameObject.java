package Data;

public abstract class GameObject{
  
    protected int x;
    protected int y;
    protected int ox = 0;
    protected int oy = 0;
    
    public GameObject(){}
    public GameObject( int x, int y ){
       this.x = x; 
       this.y = y;
    }

    public void setOriginAsCenter(){
        //esta funcion autocalcula y convierte el centro del objeto visualmente
        //al mismo que el centro del sprite en vez de hacerlo desde la esquina.
        
    }

    public void setOrigin( int widht, int height ){
        //Esta funcion es para objetos tipo humanoides convierte el centro del sprite
        //en donde estan los pies, lo hace con autocalculos.
        this.ox = x + widht/2;
        this.oy = y + height;
    }

    public int getX() { return x; }
	public int getY() { return y; }
    public int getOy() { return oy; }
    public int getOx() { return ox; }

	public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setOx(int ox) { this.ox = ox; }
    public void setOy(int oy) { this.oy = oy; }


}
