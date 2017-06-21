package Data;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import GUI.GameFrame;
//import Data.Player;
import systems.Time;
import StateMachine.GameStateManager;

public class Game{
	//ticker
	private boolean running = true;
	//-----------------------------
	
	private GameStateManager gameStateMachine;
	private GameFrame window;
	private static Graphics g;
	private BufferStrategy bs;
	
    public Game( Dimension dimension ){ 
    	window = new GameFrame();
    	window.init( (int) dimension.getWidth(), (int) dimension.getHeight() );
    	gameStateMachine = new GameStateManager( dimension );
    	KeyListener[] l = gameStateMachine.getComponent().getKeyListeners();
    	window.getCanvas().addKeyListener( l[0]);
    	window.getCanvas().requestFocus();
    }

    private void update(){    	
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
    	Time.start();    	
        gameStateMachine.world();
        start();
    }
    
    private void start(){    	
		while( running ){
			try{			
				g = bs.getDrawGraphics();
	    		gameStateMachine.setG(g);
				update();
				bs.show();
				g.dispose();
				Thread.sleep( Time.getTime() );
			}catch( Exception e ){
				e.getStackTrace();
			}
		}		
    }
    
    public static Graphics getG(){ return g; } 
    
  }
