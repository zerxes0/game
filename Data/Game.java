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
	private Thread m;

    Game(Dimension dimension){
    	//window = new GameFrame();
    	//window.init( (int) dimension.getWidth(), (int) dimension.getHeight() );
		window = ( CurrentData.frame = new GameFrame() );
		CurrentData.frame.init( (int) dimension.getWidth(), (int) dimension.getHeight() );
		gameStateMachine = new GameStateManager( dimension );
		KeyListener[] l = gameStateMachine.getComponent().getKeyListeners();
		window.getCanvas().addKeyListener( l[0]);
		window.getCanvas().requestFocus();
		window.getCanvas().createBufferStrategy(2);
    }

    private void update(){    	
        gameStateMachine.draw();
    }

   /* private void menu(){
        System.out.println("menu while");
        //Se creo esta clase auxiliar para que el menu se estuviese dibujando
        //mientres estuviese en ese estado, hasta este punto aun no se ha inicializado el
        //reloj del juego
		m = new Thread(){
			@Override public synchronized void run(){
				while( gameStateMachine.getCurrentState() == gameStateMachine.getMenu() ){
					update();
				}
			}
		};
		m.start();
	}*/

    void init(){
    	try{
    		//menu();
			//m.join();
            // no queremos que bs = buffer se  inicialize hasta que el juego
            // deje de estar en el menu, por eso hemos creado el loop infinito en menu();
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
        //gameStateMachine.world();
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
