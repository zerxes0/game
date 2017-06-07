package StateMachine;

import java.awt.Color;

import GUI.GameGraphics;

public class Battle implements GameState {

	GameStateManager state;
    GameGraphics graphics;
	
	public Battle( GameStateManager newGameState ){
		state = newGameState;
	}
	
	@Override
	public void draw(){
        System.out.println( "Battle draw" );
        state.getGraphics().setColor( Color.blue );
		state.getGraphics().fillRect(0, 0, (int)GameGraphics.dimension.getWidth(), (int)GameGraphics.dimension.getHeight() );
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
        System.out.println( "Ya esta en batalla." );
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
