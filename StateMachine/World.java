package StateMachine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComponent;

import Data.AudioManager;
import Data.Player;
import GUI.Animator;
import GUI.GameGraphics;

@SuppressWarnings("serial")
public class World extends JComponent implements  GameState{

    //FIELDS -----------------
	private GameStateManager state;
    private Player jugador;
    private Graphics g;
    private ListenKeys lKey = new ListenKeys();
    private Animator anim;
    //--------------------------

	public World( GameStateManager newGameState ){
		state = newGameState;
		jugador = new Player("lol",100, 300,300, 20, 20);
		anim = jugador.getAnimation();
        this.addKeyListener( lKey );
        this.setFocusable(true);      
	}
    

    public void idle(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
		g.drawImage( anim.getSprites( 
                anim.getCurrentSheet() ).crop( anim.state() , 0, 42, 42), jugador.getX() , jugador.getY(), null );
    }

    public void attack(){
		g.drawImage( anim.getSprites(2).crop( anim.state(), 0, 80, 80), jugador.getX(), jugador.getY()-38, null );
		if( anim.state() >= 800 ) // el limite de la sprite sheet es 800 asi que al llegar se acabo el ataque
		    anim.setCurrentSheet(0);
    }

	@Override
	public void draw(){
        //System.out.println( "World draw" );
        g = state.getGraphics();

        //ESCENARIO ---------------------
        g.setColor( Color.green );
		g.fillRect( 0, 0, (int)GameGraphics.dimension.getWidth(), (int)GameGraphics.dimension.getHeight() );
		state.graphics().paint(state.getGraphics());
		// ------------------------------
        
        // JUGADOR ---------------------  
		    if( anim.getCurrentSheet() == 2 )
                attack();
		    else
                idle();
		// ------------------------------
		//g.dispose();
	}
	
	@Override
	public void menu() {
        System.out.println( "Regresando al menu..." );
	    state.setGameState( state.getMenu() );	
	}

	@Override
	public void world() {
        System.out.println( "Ya estabas en WORLD STATE!" );
	}

	@Override
	public void battle() {
        System.out.println( "Entrando a batalla " );
	    state.setGameState( state.getMenu() );	
	}

	@Override
	public void pause() {
		System.out.println( "Pausa " );
	    state.setGameState( state.getPause() );	
	}

	@Override
	public void gameOver() {
		System.out.println( "Nadie se puede morir fuera de batalla!" );
	}

    private class ListenKeys implements KeyListener{
      	@Override
        public void keyPressed( KeyEvent e ){
            //en cada Key Press solo debemos alterar la sheet que se reproducira
            //al presionar y no hacer el redibujado, de eso se carga el animator y el draw.
            // creo que el if mas interno es innecesario, pero da mas control
            g = state.getGraphics();
            if( e.getKeyCode() == KeyEvent.VK_LEFT ){
                System.out.println("left");
                jugador.setX( jugador.getX() - 10); 
                if ( anim.getCurrentSheet() != 1 )
                    anim.setCurrentSheet(1);
            }
            if( e.getKeyCode() == KeyEvent.VK_RIGHT ){
                System.out.println("right");
                jugador.setX( jugador.getX() + 10 );
                if ( anim.getCurrentSheet() != 1 )
                    anim.setCurrentSheet(1);
            }
            if( e.getKeyCode() == KeyEvent.VK_UP ){
                System.out.println("up");
                jugador.setY( jugador.getY() - 10); 
                if ( anim.getCurrentSheet() != 1 )
                    anim.setCurrentSheet(1);
            }
            if( e.getKeyCode() == KeyEvent.VK_DOWN ){
                System.out.println("down");
                jugador.setY( jugador.getY() + 10);
                if ( anim.getCurrentSheet() != 1 )
                    anim.setCurrentSheet(1);
            }
            if( e.getKeyCode() == KeyEvent.VK_SPACE && anim.getCurrentSheet() != 1 && anim.getCurrentSheet() != 2 ){
                anim.setPixels( 0 );
                System.out.println("ATTACK");
                anim.setCurrentSheet(2);
                
           }
            if( e.getKeyCode() == KeyEvent.VK_E){
            	pause();
            	
            }	
            
        }//func

		@Override
		public void keyReleased(KeyEvent e) {
            if( e.getKeyCode() == KeyEvent.VK_LEFT ){
                anim.setCurrentSheet(0);
            }
            if( e.getKeyCode() == KeyEvent.VK_RIGHT ){
                anim.setCurrentSheet(0);
            }
            if( e.getKeyCode() == KeyEvent.VK_DOWN ){
                anim.setCurrentSheet(0);
            }
            if( e.getKeyCode() == KeyEvent.VK_UP ){
                anim.setCurrentSheet(0);
            }
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
    }//class
}
