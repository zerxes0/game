package maps;

import java.awt.image.BufferedImage;

import systems.ImageLoader;
import systems.SpriteSheet;

public class Level {

    //Creamos un Array de 3 GameMap por que solo tendremos 3 mapas.
    private  static GameMap[] level = new GameMap[3];

    //AUXILIAR FIELDS
    private static BufferedImage tiles;
    private static Tile[][] aux;
    private static SpriteSheet sheet;
    //-------------------

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

    private static void loadTileType( int index, int x, int y ){
        //recibimos un index, el index se refiere al nivel que vamos a editar
        // la x y la y hacen referencia a la posicion del frame(sprite)
        // en la sprite sheet, se debe usar un sistema de coordenadas
        // en base a la posicion del sprite y no en pixeles, los pixeles 
        // de la posicion son autocalculados al usar el 64*64
        // por ejemplo el segundo sprite en nuestra sprite sheet, seria el (0,1)
        // ( 0, 1 ) = ( 0,64) -> la posicion en pixeles de nuestro sprite
        tiles = tile( index, x*64, x*64, 64, 64 ) ;
    }

    private static BufferedImage tile( int i, int x, int y, int width, int height ){
        //En este caso i es el index, siempre referenciando al nivel,
        //la x y la y son las coordenadas del sprite
        //y width y height como su nombre lo indica son sus proporciones
        return level[i].getSheet().crop( x, y, width, height );
    }

    private static void initLevelComponents( int index, String path ){
        //instanciamos variables de manera auxiliar con el index
        //del nivel que queremos inicializar
        sheet = new SpriteSheet( ImageLoader.loadImage( path ) );
        level[index] = new GameMap( sheet, 800, 600 );
        aux = level[index].getTiles();
    }

    private static void initTiles(){
        for( int i = 0; i < aux.length; i++ ){
            for( int j = 0; j < aux[i].length; j++ ){
            	if ( j%2 == 0 )
                    aux[i][j] = new Tile( tiles, (i*64)-32, (j*16)-48 );
                else
                    aux[i][j] = new Tile( tiles, i*64, (j*16)-48 );
            }
        }
    }//func

    private static void level1( String path ){
        //el index nunca va a cambiar
        int index = 0;
        initLevelComponents( index, path );
        loadTileType( index, 2, 0 );
        initTiles();
    }

    public static GameMap getLevel( int index ){
        return level[index];
    }
}
