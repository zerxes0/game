package systems;

import Data.SpriteSheet;

public class Animator {
	private SpriteSheet[] sheet = new SpriteSheet[3];
    private int initialSpritePixels = 0;
    private int currentSheet = 0;
    private int nextFrame[];
 
    private void calculatePixelsPerFrame(){
        //Esta funcion realiza operaciones matematicas para
        //calcular cada cuantos pixeles es el siguiente frame
        //de la hoja de sprites para poder hacer la animacion
        int aux;
        for( int i = 0; i < sheet.length; i ++ ){
        aux = sheet[i].sheetWidht() / sheet[i].sheetHeight();
        nextFrame[i] = sheet[i].sheetWidht() / aux ;
        }
    }

    private void init(){
        // la cantidad de espacios y nextFrame y
        // SpriteSheet debe ser siempre la misma.
        nextFrame = new int[sheet.length];
        calculatePixelsPerFrame();
    }
    
    public Animator( SpriteSheet[] sheet ){
    	this.sheet = sheet;
        init();
    }

    public int state(){
        //esta funcion se utilizada para devolver que animacion se esta reproduciendo.
        switch( currentSheet ){
            case 0:
                return animationCurrentState(0);
            case 1:
                return animationCurrentState(1);
            case 2: 
                return animationCurrentState(2);
            default:
                System.out.println( "Error, Animation state out of default animations values");
                return 0;
        }
    }

    private int animationCurrentState( int state ){
        //esta funcion devuelve que animacion se esta reproduciendo y tambien la setea
        //al setearla, estamos totalmente seguros que animacion se reproduce
        //y la reproducimos de una vez. el verdadero manejador de que animacion se va a 
        //reproducir es el setCurrentSheet(), ya que es lo que evaluamos en nuestro state();
        currentSheet = state;
        if( initialSpritePixels >= sheet[state].sheetWidht() && currentSheet == state )
            initialSpritePixels = 0;
        return initialSpritePixels += nextFrame[state];
        // initialSpritePixels es nuestro iterador entre los frame de la spriteSheet
        // y se debe reiniciar cada vez que llegue al ultimo frame.
    }

    //SETTERS -------------------------
    public void setCurrentSheet(int currentSheet) { this.currentSheet = currentSheet; }
    public void setPixels( int position ){ initialSpritePixels = position; }
    //---------------------------------

    //GETTERS -------------------------
    public int getCurrentSheet() { return currentSheet; }
    public SpriteSheet getSprites( int i ){ return this.sheet[i]; }
    //---------------------------------
}
