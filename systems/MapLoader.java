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
    private static JSONArray fondo;
    private static JSONArray capa1;
    private static JSONArray capa2;

    private static void loadTileType32( int x, int y ){
    	//ya que algunos de los sprites en la spritesheet son realmente 64x32 creamos un metodo alternativo
    	tiles = tile( x*64, y*32, 64, 32 ) ;
    }
    
    private static BufferedImage tile( int x, int y, int width, int height ){
        //la x y la y son las coordenadas del sprite
        //y width y height como su nombre lo indica son sus proporciones
        return level.getSheet().crop( x, y, width, height );
    }
    
    private static int[] jsonToString( JSONArray jArray){
    	 int arraySize = jArray.size();
    	    int[] stringArray = new int[arraySize];
    	    for(int i=0; i<arraySize; i++) {
    	        stringArray[i] = Integer.parseInt( jArray.get(i).toString() );
    	    }
    	    return stringArray;
    }

    private static boolean validNumber( int number ){
        //debido a que si un numero esta en la ultima columna
        //del spritesheet el algoritmo para parsear de json a
        //no traduce correctamente, este algoritmo hace un pequeÑo fix.
        if( number != 0 ){
            return  ( number%10 == 0) ? false : true;
        }
        return true;
    }

	private static void parse( JSONArray jArray, String layerName){
        Tile[][] lvlTiles = null;
        if(layerName == "fondo") lvlTiles = level.getTiles();
        if(layerName == "capa1") lvlTiles = level.getLayer1();
        if(layerName == "capa2") lvlTiles = level.getLayer2();

       int[] aux = jsonToString( jArray );
       int tile = 0;
       int x = 0, y;
       System.out.print( aux.length );
        for( int i = 0; i < lvlTiles.length; i++ ){
            for( int j = 0; j < lvlTiles[i].length; j++ ){
                x = ( aux[tile]%10 > 0) ? (aux[tile]%10)-1: aux[tile]%10;
                y = (aux[tile]/10);
                if( !validNumber( aux[tile] )){
                    x = 9;
                    y = (aux[tile]/10)-1;
                }
                loadTileType32(x, y);
                System.out.print("("+ x +","+ y +")");
                lvlTiles[i][j].setSprite(tiles);
                if( layerName == "capa1" && ( x == 0 && y == 0) )
                    lvlTiles[i][j].setSolid(false);
                else if( layerName == "capa1" && y != 0 )
                    lvlTiles[i][j].setSolid(true);

                if( layerName == "capa2" && y != 0 )
                    lvlTiles[i][j].setOverLap(true);
                tile++;
            }
        }
       //System.out.println(tile);
	}
	
    public static void loadMap( GameMap level ){
    	JSONParser parser = new JSONParser();
    	MapLoader.level = level;
    	try{
            //Cargamos el archivo json
            Object data = parser.parse( new FileReader("src/Resources/Map/map.json") );
            JSONObject map = (JSONObject) data;
            //-----------------------

            //Obtenemos las capas del mapa
            JSONArray layers = (JSONArray) map.get("layers");
            System.out.println(layers);
            //------------------------------

            JSONObject aux = (JSONObject) layers.get(0);
            //System.out.println( fondo.get("name").toString() );
            if( aux.get("name").toString().contains("Fondo") ){
                //System.out.println( aux.get("data") );
                fondo = (JSONArray) aux.get("data");
                //System.out.println(fondo);
                parse(fondo,"fondo");
            }

            aux = (JSONObject) layers.get(1);
            if( aux.get("name").toString().contains("capa1") ){
                //System.out.println( aux.get("data") );
                capa1 = (JSONArray) aux.get("data");
                System.out.println(capa1);
                parse(capa1 , "capa1");
            }

            aux = (JSONObject) layers.get(2);
            if( aux.get("name") .toString().contains("capa2")){
                //System.out.println( aux.get("data") );
                capa2 = (JSONArray) aux.get("data");
                //System.out.println(capa1);
                parse(capa2, "capa2");
        }

    	}catch( Exception e ){
    		e.printStackTrace();
    	}
    }
}
