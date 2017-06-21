package entity;

import java.awt.Rectangle;

import Data.SpriteSheet;
import systems.Animator;
import systems.Collider;
import systems.ImageLoader;

public class Player extends Character {
	private SpriteSheet[] sheet;
    private Animator animator;
    private Collider collider;
    private int velocity = 3;
    /*TODO
     * Atributos/funciones del sistema de combate.
     */

    public Player( String name, float life, int x, int y, int w, int h, int ox, int oy ){
        super( name, life, x, y );
        init();
        animator = new Animator( sheet );
        collider = new Collider( x, y , w, h, ox, oy );
    }
    
    public void init(){
        //se usa para inicializar
        sheet = new SpriteSheet[3];
        for (int i = 0; i < sheet.length; i++) {
			sheet[i] = new SpriteSheet(null);
		}      
        //Cargamos las hojas de sprites que utilizara nuestro personaje
        //Esto se puede lograr con cualquier tipo de objeto mediante
        //la clase SpriteSheet e ImageLoader
        setSheet( 0,"/Resources/Sprites/idle.png");
        setSheet( 1,"/Resources/Sprites/walk.png");
        setSheet( 2,"/Resources/Sprites/attack.png");
    }
    
    public void move( String axis ){
        switch( axis ){
            case "up":
                 pos.y -= velocity;
                 origin.y -= velocity;
                break;
            case "left":
                 pos.x -= velocity;
                 origin.x -= velocity;
                break;
            case "right":
                 pos.x += velocity;
                 origin.x += velocity;
                break;
            case "down":
                 pos.y += velocity;
                 origin.y += velocity;
                break;
            default:
                break;
        }
    }

    public boolean checkCollision( int colX, int colY ){
            int x = (int) ( collider.getBounds().getX() );
            int y = (int) ( collider.getBounds().getY() );
            int py0 = (int) (colY*1.5), px0 = colX;
            int py1 = colY, px1 = (int) (colX*1.5);
            int py2 = colY+16, px2 = (int) (colX*1.5);
            int py3 = (int) (colY*1.5), px3 = colX+64;
            x /= 64; y /= 16;
            x *= 64; y *= 16;
            System.out.println( "j( " + (x) + ", " + (y) + " )" );
            System.out.println();

            //PIXEL PERFECT COLLITIONS
            boolean colFromBottom = ( x == px0 || y == py0 ) || ( x == px2 || y == py2 ) || ( x == px3 || y == py3 ) ;
            boolean colFromLeft = ( x == px0 || y == py0 ) || ( x == px1 || y == py1 ) || ( x == px2 || y == py2 ) ;
            boolean colFromRight = ( x == px2 || y == py2 ) || ( x == px1 || y == py1 ) || ( x == px2 || y == py2 ) ;
            boolean colFromUp = ( x == px0 || y == py0 ) || ( x == px1 || y == py1 ) || ( x == px3 || y == py3 ) ;

            if( colFromBottom )
                return true;
            else if( colFromLeft )
                return true;
            else if( colFromRight )
                return true;
            else if( colFromUp  )
                return true;

            return false;
    }
    
    public void updateBounds(){ collider.updateBound(pos.x, pos.y); }
    
    public void setVelocity( int velocity ){ this.velocity = velocity; }
    public void setSheet( int i, String path ){ sheet[i] = new SpriteSheet( ImageLoader.loadImage(path) ); }

    public Animator getAnimation(){ return animator; }
    public int getVelocity(){ return this.velocity; }
    public Rectangle getBounds(){ return collider.getBounds(); }


}
