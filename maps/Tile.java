package maps;

import java.awt.image.BufferedImage;

import Data.GameObject;

public class Tile extends GameObject {

    private BufferedImage sprite;

    //se crea un consturctor vacio para poder crear variables auxiliares
    // que no tengan que recibir parametros y solo sirvan de referencia
    public Tile(){};
    public Tile( BufferedImage sprite, int x, int y ){
        super( x, y );
        this.sprite = sprite;
    }
    
    public void setSprite( BufferedImage sprite ){ this.sprite = sprite; }
    
    public BufferedImage getSprite(){ return this.sprite; }
}
