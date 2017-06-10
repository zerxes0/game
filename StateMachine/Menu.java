package StateMachine;

import java.awt.Color;
import java.awt.Graphics;

import Data.AudioManager;
import GUI.GameGraphics;

public class Menu implements GameState {

	GameStateManager state;
	Graphics g;
	
	public Menu( GameStateManager newGameState ){
		state = newGameState;
	}
	
	@Override
	public void draw(){
		System.out.println("reach menu draw");
        g = state.getGraphics();
		g.setColor( Color.blue );
		g.fillRect(0, 0, (int)GameGraphics.dimension.getWidth(), (int)GameGraphics.dimension.getHeight() );
		state.graphics().paint(state.getGraphics());
	}
	
	@Override
	public void menu() {
        System.out.println( "Menu state" );
	}

	@Override
	public void world() {
		System.out.println( " Entering World state"	 );
		AudioManager.playMusic("/Resources/Music/magic.mp3");
        state.setGameState( state.getWorld() );		
	}

	@Override
	public void battle() {
       System.out.println( "No has ni entrado al mundo! " );
	}

	@Override
	public void pause() {
       System.out.println( "No puedees pausar el menu!" );
	}

	@Override
	public void gameOver() {
       System.out.println( "Como pierdes estando en el menu???" );
	}

}
