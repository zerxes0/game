package GUI;

import java.awt.Dimension;
import javax.swing.JPanel;

//import Data.Player;
import StateMachine.GameStateManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel{

    GameStateManager stateManager;
    private int fps = 12; //frames per second
	private long targetTime = 1000 / fps; //milisegundos entre fps
	private boolean running = true;
	
	private GameStateManager gameStateMachine;
	private GameGraphics graphics;
	
    public GamePanel( Dimension dimension ){
        //  TESTING STATE MACHINE
    	this.setSize(dimension);
    	this.setPreferredSize( dimension );  
   
    	gameStateMachine = new GameStateManager( dimension );
        graphics = gameStateMachine.graphics();
	
    	add( graphics );
    	add( gameStateMachine.getComponent() );
    }

    public void update(){
        gameStateMachine.draw();                
    }
    
    public void init(){
    	Ticker();
        gameStateMachine.world();
    }

    private void Ticker(){
    	Thread thread = new Thread() {
			public void run(){
                //ticker -- se usa para manejar el tiempo del juego
				long start;
				long elapsed;
				long wait;
				while( running ){
					start = System.nanoTime();//obtenemos tiempo inicial
					elapsed = System.nanoTime() - start; //final - inicial == tiempo transcurrido
					wait = targetTime - elapsed / 1000000;
					try{
						update();
						Thread.sleep(wait);
						
					}catch( Exception e ){
						e.getStackTrace();
					}
				}		
			}
		};
		thread.start();
    }//ticker

  }
