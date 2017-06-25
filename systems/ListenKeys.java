package systems;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Data.CurrentData;
import StateMachine.GameStateManager;
import entity.Player;
import maps.Tile;

public class ListenKeys implements KeyListener {
    private boolean up, down, left, right, attack;
    private enum Pos{ TOP, MID, BOT };
    Pos currentPos = Pos.MID;
    
    //AUXILIAR FIELDS ----------
    private GameStateManager state;
    private Player jugador;
    private Tile[][]  deco;
    private Animator anim;
    
    private Point iso, aux, origin;
    private Point pos = new Point();
    //--------------------------
    
    public ListenKeys(){
    	state = CurrentData.state;
        jugador = CurrentData.jugador;
        deco = CurrentData.deco;
        anim = CurrentData.anim;
        iso = CurrentData.iso;
        origin = CurrentData.origin;
        aux = CurrentData.aux;
    }  
    
    private boolean checkCollision( String axis ){
        try{
            switch( axis ){
                case "up":
                    aux.setLocation(aux.x = deco[iso.y-1][iso.x].getPos().x, aux.y = deco[iso.y-1][iso.x].getPos().y);
                    return deco[iso.y - 1][iso.x].isSolid() && jugador.checkCollision(aux.x, aux.y);
                case "down":
                    aux.setLocation(aux.x = deco[iso.y+1][iso.x].getPos().x, aux.y = deco[iso.y+1][iso.x].getPos().y);
                    return deco[iso.y + 1][iso.x].isSolid() && jugador.checkCollision(aux.x, aux.y);
                case "left":
                    aux.setLocation(aux.x = deco[iso.y][iso.x-1].getPos().x, aux.y = deco[iso.y][iso.x-1].getPos().y);
                    return deco[iso.y][iso.x-1].isSolid() && jugador.checkCollision(aux.x, aux.y);
                case "right":
                    aux.setLocation(aux.x = deco[iso.y][iso.x+1].getPos().x, aux.y = deco[iso.y][iso.x+1].getPos().y);
                    return deco[iso.y][iso.x+1].isSolid() && jugador.checkCollision(aux.x, aux.y);
                default:
                    return false;
        }
        }catch( Exception e ){                                  
            e.printStackTrace();
            System.out.println( "out of bounce "+ "\n " + e );
            return false;
        }
    }
  	
    @Override public void keyPressed( KeyEvent e ){
        //en cada Key Press solo debemos alterar la sheet que se reproducira
        //al presionar y no hacer el redibujado, de eso se carga el animator y el draw.
        // creo que el if mas interno es innecesario, pero da mas control
        // STANDAR AUXILIAR VARIABLES
        up = e.getKeyCode() == KeyEvent.VK_UP;
        down = e.getKeyCode() == KeyEvent.VK_DOWN; 
        left = e.getKeyCode() == KeyEvent.VK_LEFT;
        right = e.getKeyCode() == KeyEvent.VK_RIGHT;
        attack = e.getKeyCode() == KeyEvent.VK_SPACE;
        jugador.toIso();
        //-------------------------------------------
        
        //BATTLE VARIABLES
        //
        if( state.getCurrentState() == state.getWorld() )
        	inWorld();
        if( state.getCurrentState() == state.getBattle() )
            inBattle();

        if( e.getKeyCode() == KeyEvent.VK_E )
        	state.setGameState( state.getBattle() );
    }//func

    private void inWorld(){
        if( left && !checkCollision("left") ){
            jugador.move("left");
            anim.setCurrentSheet(1);
        }
        if( right  && !checkCollision("right") ){
            jugador.move("right");
            anim.setCurrentSheet(1);
        }

        if( up && !checkCollision("up") ){
            jugador.move("up");
            anim.setCurrentSheet(1);
        }
        if( down && !checkCollision("down") ){
            jugador.move("down");
            anim.setCurrentSheet(1);
        }
        if( attack && anim.getCurrentSheet() != 1 && anim.getCurrentSheet() != 2 ){
            anim.setPixels( 0 );
            System.out.println("ATTACK");
            anim.setCurrentSheet(2);           
       }
       
    }

    private void inBattle(){
        aux = jugador.getPos();
        pos.setLocation( aux.y , aux.x ); // Point(x,y)
        System.out.println("reach");
        if( up ){
            if ( currentPos == Pos.MID ){
            	System.out.println(" mid to top");
                currentPos = Pos.TOP;
                aux.setLocation( pos.x - 32, pos.y - 16 );
                }
            else if( currentPos == Pos.BOT ){
            	System.out.println("bot to mid");
                currentPos = Pos.MID;
                aux.setLocation( pos.x + 32, pos.y - 16 );
            }
        }
        if( down ){
            if( currentPos == Pos.TOP ){
            	System.out.println("top to mid");
                currentPos = Pos.MID;
                aux.setLocation( pos.x + 32, pos.y + 16 );
            }
            else if ( currentPos == Pos.MID ){
            	System.out.println("mid to bot");
                currentPos = Pos.BOT;
                aux.setLocation( pos.x - 32, pos.y + 16 );
                }
        }

    }
	@Override public void keyReleased(KeyEvent e) { anim.setCurrentSheet(0); }
	@Override public void keyTyped(KeyEvent e) { }
	
	public KeyListener getListen(){ return this; }
}
