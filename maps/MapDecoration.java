package maps;

import java.awt.image.BufferedImage;

public class MapDecoration {

	BufferedImage sprite;

    public MapDecoration( BufferedImage sprite ){
        this.sprite = sprite;
    }

    public BufferedImage getSprite() { return sprite; }
    public void setSprite( BufferedImage sprite) { this.sprite = sprite; }
}
