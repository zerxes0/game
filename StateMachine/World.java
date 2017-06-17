package StateMachine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

import maps.Tile;
import maps.Level;
import maps.GameMap;
import entity.Player;
import systems.Animator;

@SuppressWarnings("serial")
public class World extends JComponent implements  GameState{

    //FIELDS -----------------
	private GameStateManager state;
    private Player jugador;
    private GameMap lvl;
    private Tile[][] tiles;
    private Tile[][] deco;
    private Graphics g;
    private ListenKeys lKey = new ListenKeys();
    private Animator anim;
    //--------------------------

	public World( GameStateManager newGameState ){
		state = newGameState;
		loadPlayer();
		loadLevel();   
        this.setFocusable(true);     
        this.addKeyListener( lKey );        
	}
	
	private void loadPlayer(){
		jugador = new Player("lol",100, 800/2,600/2, 20, 20, 10, 15);
		anim = jugador.getAnimation();
	}
	
	private void loadLevel(){
        Level.generateLevel( 0 );
        lvl = Level.getLevel( 0 );
        tiles = lvl.getTiles();
        deco = lvl.getDeco();
	}
	public KeyListener getListen(){ return lKey; }
    
    private void idle(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
		g.drawImage( anim.getSprites( 
                anim.getCurrentSheet() ).crop( anim.state() , 0, 42, 42), jugador.getX() , jugador.getY(), null );
    }

    private void move(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
		g.drawImage( anim.getSprites( 
                anim.getCurrentSheet() ).crop( anim.state() , 0, 42, 42), jugador.getX() , jugador.getY(), null );
    }
    
    private void attack(){
		g.drawImage( anim.getSprites(2).crop( anim.state(), 0, 80, 80), jugador.getX(), jugador.getY()-38, null );
		if( anim.state() >= 800 ) // el limite de la sprite sheet es 800 asi que al llegar se acabo el ataque
		    anim.setCurrentSheet(0);
    }


    private void loadMapDeco(){
        for( int i = 0; i < tiles.length; i++ ){
            for(int j = 0; j < tiles[i].length; j++ ){
                g.drawImage( deco[i][j].getSprite(), deco[i][j].getX(), deco[i][j].getY(), null );
            }//inner for
        }//for
    }

    private void drawMap(){
        for( int i = 0; i < tiles.length; i++ ){
            for(int j = 0; j < tiles[i].length; j++ ){
                g.drawImage( tiles[i][j].getSprite(), tiles[i][j].getX(), tiles[i][j].getY(), null );
            }//inner for
        }//for
    }//func

    private void drawSquares(){	
        g.setColor( Color.white );
        int x,y;
        for( int i = 0; i < 14; i++ ){
            for(int j = 0; j < 40 ; j++ ){
            	if ( j%2 != 0 ){ 
            		x = i*64;
            		y = ( j*16 )-32;
            		g.drawLine( x, y, x+64, y + 32 );
            		g.drawLine( x, y,x-64, y + 32);
            	}
            }//inner for
        }//for
    }

	@Override
	public void draw(){
        //System.out.println( "World draw" );
		g = state.getGraphics();

        //ESCENARIO ---------------------
        //g.setColor( Color.green );
		//g.fillRect( 0, 0, (int)GameGraphics.dimension.getWidth(), (int)GameGraphics.dimension.getHeight() );
        drawMap();     
        loadMapDeco();
        drawSquares();
		// ------------------------------
        
        // JUGADOR ---------------------  
		    if ( anim.getCurrentSheet() == 0 )
                idle();
		    if( anim.getCurrentSheet() == 1 )
		    	move();
		    if( anim.getCurrentSheet() == 2 )
                attack();

            /*g.setColor( Color.red );
            jugador.updateBounds();
            g.fillRect( (int)jugador.getBounds().getX(), (int)jugador.getBounds().getY(), (int)jugador.getBounds().getWidth(),
                    (int)jugador.getBounds().getHeight() );*/
		// ------------------------------		
		//state.graphics().paint( state.getGraphics() );
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
            boolean up = e.getKeyCode() == KeyEvent.VK_UP;
            boolean down = e.getKeyCode() == KeyEvent.VK_DOWN; 
            boolean left = e.getKeyCode() == KeyEvent.VK_LEFT;
            boolean right = e.getKeyCode() == KeyEvent.VK_RIGHT;
            boolean attack = e.getKeyCode() == KeyEvent.VK_SPACE; 
            if( left && up  ){
                jugador.setY( jugador.getY() - jugador.getVelocity()  );
                jugador.setX( jugador.getX() - jugador.getVelocity()  );
                anim.setCurrentSheet(1);
            }
            if( left && down ){
                jugador.setY( jugador.getY() + jugador.getVelocity()  );
                jugador.setX( jugador.getX() - jugador.getVelocity()  );
                anim.setCurrentSheet(1);
            }
            if( right && up ){
                jugador.setY( jugador.getY() + jugador.getVelocity()  );
                jugador.setX( jugador.getX() + jugador.getVelocity()  );
                anim.setCurrentSheet(1);
            }
            if( right && down ){
                jugador.setY( jugador.getY() + jugador.getVelocity()  );
                jugador.setX( jugador.getX() + jugador.getVelocity()  );
                anim.setCurrentSheet(1);
            }
            if( left ){
                jugador.setX( jugador.getX() - jugador.getVelocity() ); 
                anim.setCurrentSheet(1);
            }
            if( right ){
                jugador.setX( jugador.getX() + jugador.getVelocity()  );
                anim.setCurrentSheet(1);
            }
            if( up ){
                jugador.setY( jugador.getY() - jugador.getVelocity()  ); 
                anim.setCurrentSheet(1);
            }
            if( down ){
                jugador.setY( jugador.getY() + jugador.getVelocity()  );
                anim.setCurrentSheet(1);
            }
            if( attack && anim.getCurrentSheet() != 1 && anim.getCurrentSheet() != 2 ){
                anim.setPixels( 0 );
                System.out.println("ATTACK");
                anim.setCurrentSheet(2);           
           }
            if( e.getKeyCode() == KeyEvent.VK_E){
            	pause();
            }	
            System.out.println( e.getKeyChar() + ": pressed");
            
        }//func

		@Override
		public void keyReleased(KeyEvent e) {
				System.out.println(e.getKeyChar() + ": released");
				anim.setCurrentSheet(0);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
    }//class
}
