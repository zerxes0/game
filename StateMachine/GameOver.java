package StateMachine;


public class GameOver implements GameState {

	private GameStateManager state;

	GameOver(GameStateManager newGameState){
		state = newGameState;
	}

	@Override
	public void draw(){
		
	}
	
	@Override
	public void menu() {
        System.out.println( "Regresando al menu...(Derrota)" );
	    state.setGameState( state.getMenu() );	
	}

	@Override
	public void world() {
        System.out.println( "Tenes que regresar al menu primero...(world func)" );
	}

	@Override
	public void battle() {
        System.out.println( "Tenes que regresar al menu primero... (battle func)" );
	}

	@Override
	public void pause() {
        System.out.println( "No podes pausar si estas muerto..." );
		
	}

	@Override
	public void gameOver() {
        System.out.println( "No podes perder el game over!!" );
		
	}
	
}
