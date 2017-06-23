package maps;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Data.GameObject;
import Data.SpriteSheet;

public class GameMap extends GameObject {

    private SpriteSheet sheet;
    private Tile[][] tiles = new Tile[14][38];
    private Tile[][] deco = new Tile[14][38];
    private Tile[][] deco2 = new Tile[14][38];

    public GameMap( SpriteSheet sheet, int widht, int height ){
        super( widht, height );
        this.sheet = sheet;
    }
    
    public void setSheet(SpriteSheet tiles) { this.sheet = tiles; }
    public void setTiles(Tile[][] tiles) { this.tiles = tiles; }

    public SpriteSheet getSheet() { return sheet; }
    public Tile[][] getTiles() { return tiles; }
    public Tile[][] getDeco(){ return deco; }

}
