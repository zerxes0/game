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
    private ListenKeys lKey = new ListenKeys();
    //--------------------------

    //AUXILIAR FIELDS ----------
    private Player jugador;
    private GameMap lvl;
    private Tile[][] tiles;
    private Tile[][] deco;
    private Graphics g;
    private Animator anim;
    
    private int px = 0;
    private int py = 0;
    //--------------------------
    
	public World( GameStateManager newGameState ){
		state = newGameState;
		loadPlayer();
		loadLevel();   
        this.setFocusable(true);     
        this.addKeyListener( lKey );        
	}
	
	private void loadPlayer(){
		jugador = new Player("lol",100, 1216/2,616/2, 20, 20, 10, 15);
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
        for( int i = 0; i <= 15; i++ ){
            for(int j = 0; j <= 41 ; j++ ){
            	if ( j%2 != 0 ){ 
            		x = (i*64)-32;
            		y = ( j*16 )-16;
            		g.drawLine( x, y, x+64, y + 32 );
            		g.drawLine( x, y,x+64, y-32);
            	}
            }//inner for
        }//for
              
    }

    private void toIso(int x, int y){
        //int j = ( x - 32)/64;
        int j = 0;
        int i = (y*2)/32;
        if ( i%2 == 1 ){
            j = ( x - 32 )/64;
            px = j;
            py = i;
        }else{
            j = ( x )/64;
            px = j;
            py = i;
        }
        px = (int) Math.floor( x/64 + y/32 )/2;
        py = (int) Math.floor( x/64 - y/32)/2;
    }
    
    private void debug(){
        /*int row = (jugador.getX())/64;
        int col = (jugador.getY() )/32;*/
        int row = (jugador.getX());
        int col = (jugador.getY());
        toIso((row/64)*64,(col/16)*16);
        System.out.println("jugador ( " + (row/64)*64 + ", " + (col/16)*16 + " )" + 
        		"[ " + (row/64) + ", " + (col/16) + " ]" + "px,py( " + (px) + ", " + (py) + " )"); 
        
/*
        g.setColor( Color.red );
        jugador.updateBounds();
        g.fillRect( (int)jugador.getBounds().getX(), (int)jugador.getBounds().getY(), (int)jugador.getBounds().getWidth(),
                (int)jugador.getBounds().getHeight() );*/
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
        //debug();  
		// ------------------------------
        
        g.setColor(Color.MAGENTA);
        g.fillOval( (px*64), (py*16), 15, 15);
        
        // JUGADOR ---------------------  
		    if ( anim.getCurrentSheet() == 0 )
                idle();
		    if( anim.getCurrentSheet() == 1 )
		    	move();
		    if( anim.getCurrentSheet() == 2 )
                attack();
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

        private boolean checkCollision( String axis ){
            switch( axis ){
                case "up":
                    int row = (int) ( ( jugador.getX() )/ 64 );
                    int col = (int) ( ( jugador.getY() )/ 16 );
                    col -= 1;
                    int x = deco[row][col].getX();
                    int y = deco[row][col].getY(); 
                    return jugador.checkCollision( x, y );
                default:
                    return false;
            }
        }

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

            if( left ){
                jugador.move("left");
                anim.setCurrentSheet(1);
            }
            if( right ){
                jugador.move("right");
                anim.setCurrentSheet(1);
            }

            //checkCollision("up");
            debug();
            if( up /*&& !checkCollision("up")*/ ){
                jugador.move("up");
                anim.setCurrentSheet(1);
            }
            if( down ){
                jugador.move("down");
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
            
        }//func

		@Override
		public void keyReleased(KeyEvent e) {
				anim.setCurrentSheet(0);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
    }//class
}
