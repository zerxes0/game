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

public class Game{
	//ticker
	private boolean running = true;
	//-----------------------------
	
	private GameStateManager gameStateMachine;
	private GameFrame window;
	private static Graphics g;
	private BufferStrategy bs;
	private boolean inGame = false;
	
    public Game( Dimension dimension ){ 
    	window = new GameFrame();
    	window.init( (int) dimension.getWidth(), (int) dimension.getHeight() );
    	gameStateMachine = new GameStateManager( dimension );
        KeyListener[] l = gameStateMachine.getComponent().getKeyListeners();
        window.getCanvas().addKeyListener( l[0]);
        window.getCanvas().requestFocus();
        window.getCanvas().createBufferStrategy(2);
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

    public void init(){		
    	try{
    		menu();
            // no queremos que bs = buffer se  inicialize hasta que el juego
            // deje de estar en el menu, por eso hemos creado el loop infinito en menu();
			System.out.println("Menu stopped");
    		bs = window.getCanvas().getBufferStrategy();
    	}
    	catch(Exception e){
    		e.printStackTrace();
			System.out.println( e.getCause() );

    	}
    	Time.start();    
        
        gameStateMachine.world();
        start();
    }
    
    private void start(){    	
		while( running ){
			try{
                bs = CurrentData.canvas.getBufferStrategy();
                g = bs.getDrawGraphics();					
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
    
    public static Graphics getG(){ return g; } 
    
  }
