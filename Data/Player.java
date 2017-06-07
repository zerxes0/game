package Data;

import GUI.Animator;
import GUI.ImageLoader;
import GUI.SpriteSheet;

public class Player extends Character {
	private SpriteSheet[] sheet;
    private Animator animator;
   
    /*TODO
     * Atributos/funciones del sistema de combate.
     */

    public void init(){
        //se usa para inicializar
        sheet = new SpriteSheet[3];
        for (int i = 0; i < sheet.length; i++) {
			sheet[i] = new SpriteSheet(null);
		}      
    }

    public Player( String name, float life, int x, int y ){
        super( name, life, x, y );
        init();
        //Cargamos las hojas de sprites que utilizara nuestro personaje
        //Esto se puede lograr con cualquier tipo de objeto mediante
        //la clase SpriteSheet e ImageLoader
        setSheet( 0,"/Resources/Sprites/idle.png");
        setSheet( 1,"/Resources/Sprites/walk.png");
        setSheet( 2,"/Resources/Sprites/attack.png");
        animator = new Animator( sheet );
    }
    
    public void setSheet( int i, String path ){ sheet[i] = new SpriteSheet( ImageLoader.loadImage(path) ); }

    public Animator getAnimation(){ return animator; }
}
