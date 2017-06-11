package maps;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entity.GameObject;
import systems.SpriteSheet;

public class GameMap extends GameObject {

    private SpriteSheet sheet;
    private ArrayList<BufferedImage> tile;

    public GameMap( SpriteSheet sheet, int widht, int height ){
        super( widht, height );
        this.sheet = sheet;
    }

    public void setSheet(SpriteSheet tiles) { this.sheet = tiles; }
    public void setTiles(ArrayList<BufferedImage> tile) { this.tile = tile; }

    public SpriteSheet getSheet() { return sheet; }
    public ArrayList<BufferedImage> getTiles() { return tile; }
}
