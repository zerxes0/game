package systems;

import java.awt.image.BufferedImage;
import java.io.FileReader;

import maps.GameMap;
import maps.Tile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MapLoader {
	
	private static GameMap level;
    private static BufferedImage tiles;
    private static JSONArray capa;
	
    private static void loadTileType32( int x, int y ){
    	//ya que algunos de los sprites en la spritesheet son realmente 64x32 creamos un metodo alternativo
    	tiles = tile( x*64, y*32, 64, 32 ) ;
    }
    
    private static BufferedImage tile( int x, int y, int width, int height ){
        //la x y la y son las coordenadas del sprite
        //y width y height como su nombre lo indica son sus proporciones
        return level.getSheet().crop( x, y, width, height );
    }
    
    private static int[] jsonToString(){
    	 int arraySize = capa.size();
    	    int[] stringArray = new int[arraySize];
    	    for(int i=0; i<arraySize; i++) {
    	        stringArray[i] = Integer.parseInt( capa.get(i).toString() );
    	    }

    	    return stringArray;
    }
    
	private static void parse(){
       Tile[][] lvlTiles = level.getTiles();
       int[] aux = jsonToString();
       int tile = 0;
       int x = 0, y;
       System.out.print( aux.length ); 
       for( int i = 0; i < lvlTiles.length; i++ ){	   
           for( int j = 0; j < lvlTiles[i].length; j++ ){
               x = (aux[tile]%10)-1; y = (aux[tile]/10);
               loadTileType32(x, y);
               System.out.print("("+ x +","+ y +")");          
               lvlTiles[i][j].setSprite(tiles); 
               tile++;
           }	
       }
       //System.out.println(tile);

	}
	
    public static void loadMap( int index, GameMap level ){
    	JSONParser parser = new JSONParser();
    	MapLoader.level = level;
    	try{
    	//Cargamos el archivo json
    	Object data = parser.parse( new FileReader("src/Resources/Map/map.json") );
    	JSONObject map = (JSONObject) data;
        //-----------------------
        //System.out.println( map );
        
        //Obtenemos las capas del mapa
        JSONArray layers = (JSONArray) map.get("layers");
        System.out.println(layers);
        
        System.out.println( layers.get(0) );
        //System.out.println( layers.get(1) );
        //System.out.println( layers.get(2) );
        //------------------------------

        JSONObject fondo = (JSONObject) layers.get(0);  
        //System.out.println( fondo.get("name").toString() );
        if( fondo.get("name").toString().contains("Fondo") ){
            System.out.println( "true" );
            System.out.println( fondo.get("data") );
            capa = (JSONArray) fondo.get("data");
            //sString info = capa.get("data");        
            System.out.println(capa);          
            parse();
        }

        
    	}catch( Exception e ){
    		e.printStackTrace();
    	}
    }
}
