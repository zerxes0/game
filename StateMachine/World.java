package StateMachine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JComponent;

import Data.CurrentData;
import entity.Player;
import maps.GameMap;
import maps.Level;
import maps.Tile;
import systems.Animator;
import systems.ListenKeys;

@SuppressWarnings("serial")
public class World extends JComponent implements  GameState {

    //FIELDS -----------------
    private GameStateManager state;
    private ListenKeys lKey;
    //--------------------------

    //AUXILIAR FIELDS ----------
    private Player jugador;
    private GameMap lvl;
    private Tile[][] tiles, deco,deco2;
    private Graphics g;
    private Animator anim;
    private boolean firstCall = true;
    
    private Point iso, pos, origin, aux;
    //--------------------------
    
	World(GameStateManager newGameState){
		state = newGameState;
		loadPlayer();
		loadLevel();
		setData();
		lKey = new ListenKeys();
        this.setFocusable(true);     
        this.addKeyListener( lKey );
	}
	
	private void loadPlayer(){
            jugador = new Player("lol",100, 352,192, 20, 20, 20,20 );
            anim = jugador.getAnimation();
            pos = jugador.getPos();
            jugador.setOrigin( 32, 32 );
            origin = jugador.getOrigin();
            iso = jugador.getIso();
            aux = new Point();
            jugador.toIso();
	}
	
	private void loadLevel(){
        Level.generateLevel( 0 );
        lvl = Level.getLevel( 0 );
        tiles = lvl.getTiles();
        deco = lvl.getLayer1();
        deco2 = lvl.getLayer2();
	}
    
    private void idle(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
		g.drawImage( anim.getSprites( anim.getCurrentSheet() ).crop( anim.state() , 0, 64, 64), pos.x , pos.y, null );
    }

    private void move(){
        //aqui esta idle, idle en nuestro contexto
        //se usar para animacion default y walking
		g.drawImage( anim.getSprites( anim.getCurrentSheet() ).crop( anim.state() , 0, 64, 64), pos.x , pos.y, null );
    }
    
    private void attack(){
		g.drawImage( anim.getSprites(2).crop( anim.state(), 0, 96, 96), pos.x, pos.y-38, null );
		if( anim.state() >= 800 ) // el limite de la sprite sheet es 800 asi que al llegar se acabo el ataque
		    anim.setCurrentSheet(0);
    }

    private void drawMap(){
        g = state.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect( 0,0,CurrentData.frame.getWidth(), CurrentData.frame.getHeight() );
        for( int i = 0; i < tiles.length; i++ ){
            for(int j = 0; j < tiles[i].length; j++ ){
                g.drawImage( tiles[i][j].getSprite(), tiles[i][j].getPos().x, tiles[i][j].getPos().y, null );
                g.drawImage( deco[i][j].getSprite(), deco[i][j].getPos().x, deco[i][j].getPos().y, null );
            }//inner for
        }//for
    }//func

    private void drawDeco(){
        g = state.getGraphics();
        for( int i = 0; i < tiles.length; i++ ) {
            for (int j = 0; j < tiles[i].length; j++) {
                g.drawImage( deco2[i][j].getSprite(), deco[i][j].getPos().x, deco[i][j].getPos().y, null );
            }//inner for
        }//for
    }

    private void drawSquares(){	
        g.setColor( new Color(21, 104, 64));
        int x,y;
        for( int i = 0; i <= 14; i++ ){
            for(int j = 0; j <= 39 ; j++ ){
            	if ( j%2 != 0 ){ 
            		x = (i*64)-32;
            		y = ( j*16 )-16;
            		g.drawLine( x, y, x+64, y + 32 );
                    g.setColor( new Color(18, 89, 26));
            		g.drawLine( x, y,x+64, y-32);
            	}
            }//inner for
        }//for             
    } //func
    
    private void debug(){
        int row = (int) ( origin.getX() );
        int col = (int) ( origin.getY() );
        jugador.toIso();
        System.out.println("j:( " + pos.x + ", " + pos.y + " ) " );
        System.out.println("jugador ( " + (row) + ", " + (col) + " )" + 
        		"[ " + (row/64) + ", " + (col/16) + " ]" + "iso x,y( " + (iso.x) + ", " + (iso.y) + " )");

        g.setColor( Color.red );
        g.drawRect(origin.x, origin.y, 64-48, 64-48);
        
        g.fillRect( (int)jugador.getBounds().getX(), (int)jugador.getBounds().getY(), (int)jugador.getBounds().getWidth(),
                (int)jugador.getBounds().getHeight() );

    }

    private void drawPlayer(){
        if ( anim.getCurrentSheet() == 0 )
            idle();
        if( anim.getCurrentSheet() == 1 )
            move();
        if( anim.getCurrentSheet() == 2 )
            attack();
    }


    private boolean overLap = false;
	@Override public void draw(){
        if( firstCall ){
            CurrentData.initCanvas();
            firstCall = false;
        }
        System.out.println( "World draw" );
		g = state.getGraphics();

        //ESCENARIO ---------------------
        debug();
		drawMap();
		drawSquares();
		// ------------------------------

        // JUGADOR ---------------------
        overLap = (origin.y > deco2[iso.y][iso.x].getPos().y) && deco2[iso.y][iso.x].isOverLapAble() &&
                !deco2[iso.y - 1][iso.x].isOverLapAble() && !deco2[iso.y + 1][iso.x].isOverLapAble();

        if( overLap){
            drawPlayer();
            drawDeco();
        }else{
            drawDeco();
            drawPlayer();
        }
		// ------------------------------
	}
	
	@Override public void menu() {
        System.out.println( "Regresando al menu..." );
	    state.setGameState( state.getMenu() );	
	}

    @Override public void battle() {
        System.out.println( "Entrando a batalla " );
        state.getBattle().battle();
	    state.setGameState( state.getMenu() );	
	}

	@Override public void pause() {
		System.out.println( "Pausa " );
	    state.setGameState( state.getPause() );	
	}

	@Override public void gameOver() { System.out.println( "Nadie se puede morir fuera de batalla!" ); }
	@Override public void world() { System.out.println( "Ya estabas en WORLD state" ); }
	
	private void setData(){
        CurrentData.jugador = jugador;
        CurrentData.lvl = lvl;
        CurrentData.lKey = lKey;
        CurrentData.state = state;
	}

}
