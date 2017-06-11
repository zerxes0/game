package maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import systems.ImageLoader;
import systems.SpriteSheet;

public class Level {

    //Creamos un Array de 3 GameMap por que solo tendremos 3 mapas.
    private  static GameMap[] level = new GameMap[3];

    public static void generateLevel( int index ){
        switch( index ){
            case 0:
                level1("/Resources/Map/map.png");
                break;
            default:
                System.out.println( "Index of level out of bounce" );
                break;
        }//switch
    }//func

    private static BufferedImage tile( int i, int x, int y, int width, int height ){
        return level[i].getSheet().crop( x, y, width, height );
    }

    public static void level1( String path ){
        ArrayList<BufferedImage> tiles = new ArrayList<BufferedImage>();
        SpriteSheet sheet = new SpriteSheet( ImageLoader.loadImage( path ) );
        level[0] = new GameMap( sheet, 800, 600 );

        tiles.add( tile( 0, 2*64, 0*64, 64, 64 ) );
        level[0].setTiles( tiles );

    }

    public static GameMap getLevel( int index ){
        return level[index];
    }
}
