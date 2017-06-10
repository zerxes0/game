package Data;

import java.awt.Rectangle;

import GUI.Animator;
import GUI.ImageLoader;
import GUI.SpriteSheet;

public class Player extends Character {
	private SpriteSheet[] sheet;
    private Animator animator;
    private Collider collider;
   
    /*TODO
     * Atributos/funciones del sistema de combate.
     */

    public Player( String name, float life, int x, int y, int w, int h ){
        super( name, life, x, y );
        init();
        animator = new Animator( sheet );
        collider = new Collider( x, y , w, h );
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

    public void setSheet( int i, String path ){ sheet[i] = new SpriteSheet( ImageLoader.loadImage(path) ); }

    public Animator getAnimation(){ return animator; }
    public Rectangle getBounds(){ return collider.getBounds(); }
}
