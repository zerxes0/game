package Data;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import GUI.GameFrame;
//import Data.Player;
import systems.Time;
import StateMachine.GameStateManager;

import static java.lang.Thread.*;

class Game{
	//ticker
	private boolean running = true;
	//-----------------------------
	
	private GameStateManager gameStateMachine;
	private GameFrame window;
	private BufferStrategy bs;

    Game(Dimension dimension){
    	window = new GameFrame();
    	window.init( (int) dimension.getWidth(), (int) dimension.getHeight() );
    	gameStateMachine = new GameStateManager( dimension );
    }

    private void update(){    	
        gameStateMachine.draw();
    }


    private void menu(){
        System.out.println("start");
        //Se creo esta clase auxiliar para que el menu se estuviese dibujando
        //mientres estuviese en ese estado, hasta este punto aun no se ha inicializado el
        //reloj del juego
        while( gameStateMachine.getCurrentState() == gameStateMachine.getMenu() ){
            update();
        }
	}

    void init(){
    	try{
    		menu();
            // no queremos que bs = buffer se  inicialize hasta que el juego
            // deje de estar en el menu, por eso hemos creado el loop infinito en menu();
			KeyListener[] l = gameStateMachine.getComponent().getKeyListeners();
			window.getCanvas().addKeyListener( l[0]);
			window.getCanvas().requestFocus();
			window.getCanvas().createBufferStrategy(2);
			System.out.println("Menu stopped");
    		bs = window.getCanvas().getBufferStrategy();
    	}
    	catch(Exception e){
    		e.printStackTrace();

    	}
    	Time.start();    
        
        gameStateMachine.world();
        start();
    }
    
    private void start(){
		while( running ){
			try{
                bs = CurrentData.canvas.getBufferStrategy();
				Graphics g = bs.getDrawGraphics();
                gameStateMachine.setG(g);
                update();
                bs.show();
                g.dispose();

				sleep( Time.getTime() );
			}catch( Exception e ){
				e.getStackTrace();
			}
		}		
    }

}
