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
    
    private int px;
    private int py;
    //--------------------------
    
	public World( GameStateManager newGameState ){
		state = newGameState;
		loadPlayer();
		loadLevel();   
        this.setFocusable(true);     
        this.addKeyListener( lKey );        
	}
	
	private void loadPlayer(){
		jugador = new Player("lol",100, 192,192, 20, 20, 20,20 );
		anim = jugador.getAnimation();
        jugador.setOrigin( 48, 48 );
		toIso( jugador.getOx(), jugador.getOy() );
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
                anim.getCurrentSheet() ).crop( anim.state() , 0, 64, 64), jugador.getX() , jugador.getY(), null );
    }

    private void move(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
		g.drawImage( anim.getSprites( 
                anim.getCurrentSheet() ).crop( anim.state() , 0, 64, 64), jugador.getX() , jugador.getY(), null );
    }
    
    private void attack(){
		g.drawImage( anim.getSprites(2).crop( anim.state(), 0, 96, 96), jugador.getX(), jugador.getY()-38, null );
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
        int sx = x / 32;

        int off = (sx % 2 == 1) ? 32 : 0;
        int isoX = (2 * y) / 32;
        int isoY = (x - off) / 64;     
        px = isoY;
        py = isoX;
    } 
    
    private void debug(){
        /*int row = (jugador.getOx())/64;
        int col = (jugador.getOy() )/32;*/
        int row = (int) ( jugador.getOx() );
        int col = (int) ( jugador.getOy() );
        toIso((row/64)*64, (col/16)*16	);
        System.out.println("j:( " + jugador.getX() + ", " + jugador.getY() + " ) " );
        System.out.println("jugador ( " + (row) + ", " + (col) + " )" + 
        		"[ " + (row/64) + ", " + (col/16) + " ]" + "px,py( " + (px) + ", " + (py) + " )"); 
        
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
        g.fillOval( (jugador.getOx()/1)*1, (jugador.getOy()/1)*1, 15, 15);
        
        g.setColor( Color.red );
        jugador.updateBounds();
        g.fillRect( (int)jugador.getBounds().getX(), (int)jugador.getBounds().getY(), (int)jugador.getBounds().getWidth(),
                (int)jugador.getBounds().getHeight() );
        
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
                	try{
                		if( deco[px][py-1].isSolid() ){
                        int x = deco[px][py-1].getX();
                        int y = deco[px][py-1].getY();
                        System.out.println( " bounce " + x +", " + y );
                        return jugador.checkCollision( x, y );
                		}
                    }catch( Exception e ){                        
                        System.out.println( "out of bounce "+ "\n " + e.getCause());
                    }
                    
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

            checkCollision("up");
            debug();
            if( up && !checkCollision("up") ){
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
