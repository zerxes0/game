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
    private int velocity = 5;
    /*TODO
     * Atributos/funciones del sistema de combate.
     */

    public Player( String name, float life, int x, int y, int w, int h, int ox, int oy ){
        super( name, life, x, y );
        init();
        animator = new Animator( sheet );
        collider = new Collider( x, y , w, h, ox, oy );
    }
    
    private void init(){
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
                 updateBounds();
                break;
            case "left":
                 pos.x -= velocity;
                 origin.x -= velocity;
                 updateBounds();
                break;
            case "right":
                 pos.x += velocity;
                 origin.x += velocity;
                 updateBounds();
                break;
            case "down":
                 pos.y += velocity;
                 origin.y += velocity;
                 updateBounds();
                break;
            default:
                break;
        }
    }

    public boolean checkCollision( int colX, int colY ) {
        int x = (int) (collider.getBounds().getX());
        int y = (int) (collider.getBounds().getY());
        int px0 = colX, py0 = colY + 16;
        int px1 = colX + 32, py1 = colY;
        int px2 = colX + 32, py2 = colY + 32;
        int px3 = colX + 64, py3 = colY + 16;

        x /= 64;
        y /= 16;
        x *= 64;
        y *= 16;
        System.out.println("j( " + (x) + ", " + (y) + " )");
        System.out.println();

        //PIXEL PERFECT COLLITIONS
        boolean p0 = (x >= px0 || y >= py0) || (x * 2 >= px0 || y >= py0) || (x >= px0 || y * 2 >= py0) || (x * 2 >= px0 || y * 2 >= py0);
        boolean p1 = (x >= px1 || y >= py1) || (x * 2 >= px1 || y >= py1) || (x >= px1 || y * 2 >= py1) || (x * 2 >= px1 || y * 2 >= py1);
        boolean p2 = (x >= px2 || y >= py2) || (x * 2 >= px2 || y >= py2) || (x >= px2 || y * 2 >= py2) || (x * 2 >= px2 || y * 2 >= py2);
        boolean p3 = (x >= px3 || y >= py3) || (x * 2 >= px3 || y >= py3) || (x >= px3 || y * 2 >= py3) || (x * 2 >= px3 || y * 2 >= py3);
        boolean colFromBottom = (p0 || p2 || p3);
        boolean colFromUp = (p0 || p1 || p3);

        return colFromBottom || colFromUp;
    }
    
    private void updateBounds(){ collider.updateBound(pos.x, pos.y); }
    
    public void setVelocity( int velocity ){ this.velocity = velocity; }
    public void setSheet( int i, String path ){ sheet[i] = new SpriteSheet( ImageLoader.loadImage(path) ); }

    public Animator getAnimation(){ return animator; }
    public int getVelocity(){ return this.velocity; }
    public Rectangle getBounds(){ return collider.getBounds(); }


}
