package GUI;

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet( BufferedImage sheet ){
        this.sheet = sheet;
    }

    public int sheetWidht(){
    	return sheet.getWidth();
    }

    public int sheetHeight(){
        return sheet.getHeight();
    }
    
    public BufferedImage crop( int x, int y, int width, int height ){
        return sheet.getSubimage( x, y, width, height );
    }
}
