package Data;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import GUI.GameFrame;
//import Data.Player;
import systems.Time;
import StateMachine.GameStateManager;

class Game{
	//ticker
	private boolean running = true;
	//-----------------------------
	
	private GameStateManager gameStateMachine;
	private GameFrame window;
	private BufferStrategy bs;
	private Graphics g;

    Game(Dimension dimension){
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

    void init(){
    	try{
			bs = window.getCanvas().getBufferStrategy();
			if(bs == null){
				window.getCanvas().createBufferStrategy(2);
				return;
			}
			System.out.println("Menu stopped");
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	Time.start();
        start();
    }
    
    private void start(){
        Thread t = new Thread() {
			@Override public synchronized void run() {
				while (running) {
					try {
						bs = window.getCanvas().getBufferStrategy();
						g = bs.getDrawGraphics();
						gameStateMachine.setG(g);
						update();
						bs.show();
						g.dispose();

						sleep(Time.getTime());
					} catch (Exception e) {
						e.getStackTrace();
					}
				}
			}
		};
        t.start();

    }

}
