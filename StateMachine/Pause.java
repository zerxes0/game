package StateMachine;

public class Pause implements GameState{

	private GameStateManager state;

	Pause( GameStateManager newGameState ){
		state = newGameState;
	}
	@Override
	public void draw(){
		
	}
	
	@Override
	public void menu() {
		// TODO Auto-generated method stub
        System.out.println( "Mundo->Menu " );
        state.setGameState( state.getMenu() );
	}

	@Override
	public void world() {
        System.out.println( "Renaudando juego..." );
        state.setGameState( state.getWorld() );
	}

	@Override
	public void battle() {
        System.out.println( "Renaudando batalla" );
        state.setGameState( state.getBattle() );
	}

	@Override
	public void pause() {
        System.out.println( "No puedes pausar la pausa BO-LU-DO " );
	}

	@Override
	public void gameOver() {
        System.out.println( "como podes perder en la pausa..." );
	}

	
}
