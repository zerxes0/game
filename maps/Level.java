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
    private static Tile[][] deco;
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
        //ESTA FUNCION ES LA QUE CARGA LOS SPRITES( tiles )
        //recibimos un index, el index se refiere al nivel que vamos a editar
        // la x y la y hacen referencia a la posicion del frame(sprite)
        // en la sprite sheet, se debe usar un sistema de coordenadas
        // en base a la posicion del sprite y no en pixeles, los pixeles 
        // de la posicion son autocalculados al usar el 64*64
        // por ejemplo el segundo sprite en nuestra sprite sheet, seria el (0,1)
        // ( 0, 1 ) = ( 0,64) -> la posicion en pixeles de nuestro sprite
        tiles = tile( index, x*64, y*64, 64, 64 ) ;
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
        deco = level[index].getDeco();
    }

    private static void initTiles(){
        for( int i = 0; i < aux.length; i++ ){
            for( int j = 0; j < aux[i].length; j++ ){
            	if ( j%2 == 0 ){
                    // TODO Cambiar 17 a 16
                    // en J*17 el numero normal es 16, lo tengo
                    // en 17 de momento para ver que tal se ve
                    // con una rejilla 
                    aux[i][j] = new Tile( tiles, (i*64)-32, (j*17)-48 );
            		deco[i][j] = new Tile( null,(i*64)-32, (j*17)-48 );
            	}else{
                    aux[i][j] = new Tile( tiles, i*64, (j*17)-48 );
                    deco[i][j] = new Tile( null,(i*64), (j*17)-48 );
            	}
            }
        }
    }//func

    private static void setDeco( int x, int y){
        deco[x][y].setSprite(tiles);
    }

    private static void initDeco( int index ){
        deco = level[index].getDeco();
        loadTileType( index, 7 , 8 ); //medio
        setDeco( 4, 3 ); //medio
        loadTileType( index, 9 , 8 );
        setDeco(5,2); //esquina derecha
        loadTileType( index, 6, 8 ); //esquina inferior
        setDeco( 5, 3 ); //esquina inferior
        loadTileType( index, 3, 8 );
        setDeco( 4, 4 );
        loadTileType( index, 0, 8 );
        setDeco( 4, 5 );
        loadTileType( index, 2, 8 );
        setDeco( 5, 4 );//medio inferior    
        loadTileType( index, 2, 8 );
        System.out.println( deco[3][3].getX() + "," + deco[3][3].getY() );

       
    }

    private static void level1( String path ){
        //el index nunca va a cambiar
        int index = 0;
        initLevelComponents( index, path );
        loadTileType( index, 1, 0 );
        initTiles();
        initDeco(index);
    }

    public static GameMap getLevel( int index ){
        return level[index];
    }

    public static Tile[][] getDeco( int index ){ return deco; }
}
