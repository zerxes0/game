package maps;

import Data.GameObject;
import systems.SpriteSheet;

public class GameMap extends GameObject {

    private SpriteSheet sheet;
    private Tile[][] tiles = new Tile[13][39];
    private Tile[][] deco = new Tile[13][39];

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
