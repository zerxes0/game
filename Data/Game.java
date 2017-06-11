package Data;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import GUI.GameFrame;
//import Data.Player;
import StateMachine.GameStateManager;

public class Game{
	//ticker
    private int fps = 12; //frames per second
	private long targetTime = 1000 / fps; //milisegundos entre fps
	private boolean running = true;
	//-----------------------------
	
	private GameStateManager gameStateMachine;
	private GameFrame window;
	private static Graphics g;
	private BufferStrategy bs;
	
    public Game( Dimension dimension ){ 
    	window = new GameFrame();
    	window.init(800,600);
    	gameStateMachine = new GameStateManager( dimension );
    	KeyListener[] l = gameStateMachine.getComponent().getKeyListeners();
    	window.getCanvas().addKeyListener( l[0]);
    }

    public void update(){    	
        gameStateMachine.draw();
    }
    
    public void init(){		
    	try{
    		window.getCanvas().createBufferStrategy(2);
    		bs = window.getCanvas().getBufferStrategy();
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	Ticker();  	
        gameStateMachine.world();
    }
    
    public static Graphics getG(){ return g; } 

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
						g = bs.getDrawGraphics();
						gameStateMachine.setG(g);
						update();
						Thread.sleep(wait);
						bs.show();
						g.dispose();

					}catch( Exception e ){
						e.getStackTrace();
					}
				}		
			}
		};
		thread.start();
    }//ticker

  }
