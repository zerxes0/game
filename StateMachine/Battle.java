package StateMachine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


import Data.CurrentData;
import entity.Player;
import maps.Tile;
import systems.Animator;

@SuppressWarnings("serial")
public class Battle implements GameState {

    //FIELDS -----------------
	private GameStateManager state;
    private boolean inBattle = false;
    //--------------------------
	
    //AUXILIAR FIELDS ----------
    private Player jugador;
    private Tile[][] tiles, deco;
    private Graphics g;
    private Animator anim;

    private Point pos;

    //--------------------------
    //
    Battle(GameStateManager newGameState){
		state = newGameState;
        //this.setFocusable(true);     
        //this.addKeyListener( lKey ); 
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
        for( int i = 0; i < tiles.length; i++ ){
            for(int j = 0; j < tiles[i].length; j++ ){
                g.drawImage( tiles[i][j].getSprite(), tiles[i][j].getPos().x, tiles[i][j].getPos().y, null );
                g.drawImage( deco[i][j].getSprite(), deco[i][j].getPos().x, deco[i][j].getPos().y, null );
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

	@Override
	public void draw(){
		if(!inBattle){
			battle();
			inBattle = true;
			g.dispose();
		}
        //System.out.println( "Battle draw" );
		g = state.getGraphics();		
		
        //ESCENARIO ---------------------
		drawMap();     
        drawSquares();
		//debug();
		
		// ------------------------------
        
        // JUGADOR ---------------------  
		    if ( anim.getCurrentSheet() == 0 )
                idle();
		    if( anim.getCurrentSheet() == 1 )
		    	move();
		    if( anim.getCurrentSheet() == 2 )
                attack();
		// ------------------------------		
		    
	}
	
	@Override
	public void menu() {
        System.out.println( "Regresando al menu..." );
        state.setGameState( state.getMenu() );
	}

	@Override
	public void world() {
        System.out.println( "Batalla terminada." );
        state.setGameState( state.getWorld() );
	}

	@Override
	public void battle() {
        jugador = CurrentData.jugador;
        tiles = CurrentData.tiles;
        deco = CurrentData.deco;
        anim = CurrentData.anim;
        pos = CurrentData.pos;
        //lKey = CurrentData.lKey;
	}

	@Override
	public void pause() {
        System.out.println( "Pausando batalla..." );
        state.setGameState( state.getPause() );
	}

	@Override
	public void gameOver() {
        System.out.println( "Batalla perdida." );
        state.setGameState( state.getGameOver() );
	}

}
