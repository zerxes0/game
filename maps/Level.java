package maps;

import java.awt.image.BufferedImage;

import Data.SpriteSheet;
import systems.ImageLoader;

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

    /*private static void loadTileType( int index, int x, int y ){
        //ESTA FUNCION ES LA QUE CARGA LOS SPRITES( tiles )
        //recibimos un index, el index se refiere al nivel que vamos a editar
        // la x y la y hacen referencia a la posicion del frame(sprite)
        // en la sprite sheet, se debe usar un sistema de coordenadas
        // en base a la posicion del sprite y no en pixeles, los pixeles 
        // de la posicion son autocalculados al usar el *64
        // por ejemplo el segundo sprite en nuestra sprite sheet, seria el (0,1)
        // ( 0, 1 ) = ( 0,64) -> la posicion en pixeles de nuestro sprite
        tiles = tile( index, x*64, y*64, 64, 64 ) ;
    }*/
    
    private static void loadTileType32( int index, int x, int y ){
    	//ya que algunos de los sprites en la spritesheet son realmente 64x32 creamos un metodo alternativo
    	tiles = tile( index, x*64, y*32, 64, 32 ) ;
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
        level[index] = new GameMap( sheet, 896,616 );
        aux = level[index].getTiles();
        deco = level[index].getDeco();
    }
    
    private static void initTiles(){
    	//int mult = 16;
    	int offx;
        for( int i = 0; i < aux.length; i++ ){

            for( int j = 0; j< aux[i].length; j++ ){ 
           	 if( j%2 == 1)
        		 offx = 64 / 2;
        	 else
        		 offx = 0;
            	aux[i][j] = new Tile( tiles, (i * 64) + offx , (j*32)/2 ); 
            	deco[i][j] = new Tile( null, (i * 64) + offx , (j*32)/2  ); 
            	/*if ( j%2 == 0 ){
                    aux[i][j] = new Tile( tiles, ((i*64))*1, ((j*mult))*1  );
                    deco[i][j] = new Tile( null, (i*64)*1, (j*mult)*1, false );
            	}else{
            		aux[i][j] = new Tile( tiles, ( (i*64)-32 )*1, ((j*mult))*1 );
            		deco[i][j] = new Tile( null, (i*64)-32*1, (j*mult)*1, false );
            	} */
            }

        }
    }//func

    private static void setDeco( int x, int y){
        deco[x][y].setSprite(tiles);
    }

    private static void initDeco( int index ){
        deco = level[index].getDeco();
        
        loadTileType32( index, 7 , 17 ); //medio
        setDeco( 4,4 ); //medio 
        deco[4][4].setSolid(true);
        
        loadTileType32( index, 9 , 17 ); //esquina derecha
        setDeco(4,3); //esquina derecha
        deco[4][3].setSolid(true);
        
        loadTileType32( index, 6, 17 ); //esquina inferior derecha
        setDeco( 5, 4 ); //esquina inferior derecha
        deco[5][4].setSolid(true);
        
        loadTileType32( index, 3, 17 );//esquina superior izquierda
        setDeco( 3, 5 );// esquina superior izquierda
        deco[3][5].setSolid(true);
        
        loadTileType32( index, 0, 17 ); //esquina inferior izquierda
        setDeco( 4, 6 ); //esquina inferior izquierda
        deco[4][6].setSolid(true);
        
        loadTileType32( index, 2, 17 ); //medio inferior
        setDeco( 4, 5 );//medio inferior   
        deco[4][5].setSolid(true);
    }

    private static void level1( String path ){
        //el index nunca va a cambiar
        int index = 0;
        initLevelComponents( index, path );
        loadTileType32( index, 1, 1 );
        initTiles();
        initDeco(index);
    }

    public static GameMap getLevel( int index ){
        return level[index];
    }

    public static Tile[][] getDeco( int index ){ return deco; }
}
