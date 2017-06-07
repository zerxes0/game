package StateMachine;

public interface GameState{
	
    public void menu();
    public void world();
    public void battle();
    public void pause();
    public void gameOver();
    public void draw();
    
}
