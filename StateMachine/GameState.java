package StateMachine;

public interface GameState{
	
    void menu();
    void world();
    void battle();
    void pause();
    void gameOver();
    void draw();
    
}
