package StateMachine;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import Data.CurrentData;

public class GameStateManager {
	//FIELDS ---------------	
	private GameState menu;
	private GameState world;
	private GameState battle;
	private GameState pause;
	private GameState gameOver;
	private GameState currentState;
        public CurrentData graphics;
    
    public Graphics g;
    //---------------------
	
	public GameStateManager( Dimension dimension ){	      
		menu = new Menu(this);
		world = new World(this);
		battle = new Battle(this);
		pause = new Pause(this);
		gameOver = new GameOver(this);
		
        currentState = menu;
	}
	
	public void draw(){     
		currentState.draw();
	}
	
    //SETTERS ----------------
    public void menu(){ currentState.menu(); }
    public void world(){ currentState.world(); }
    public void battle(){ currentState.battle(); }
    public void pause(){ currentState.pause(); }
    public void gameOver(){ currentState.gameOver(); }	
    public void setGameState( GameState state ){ currentState = state;}
    public void setG( Graphics g ){ this.g = g; }
    //------------------------

    //GETTERS ---------------
    public GameState getMenu(){ return menu; }
    public GameState getWorld(){ return world; }
    public GameState getBattle(){ return battle; }
    public GameState getPause(){ return pause; }
    public GameState getGameOver(){ return gameOver; }
    public CurrentData graphics(){ return graphics; }
    public Component getComponent(){ return (Component)world; }
    public Graphics getGraphics(){ return this.g; }
    public GameState getCurrentState(){ return currentState; }
    //----------------------

}
