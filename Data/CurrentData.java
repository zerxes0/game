package Data;

import java.awt.Point;

import StateMachine.GameStateManager;
import systems.ListenKeys;
import entity.Player;
import java.awt.Canvas;
import javax.swing.JFrame;
import javax.swing.JPanel;
import maps.GameMap;
import maps.Tile;
import systems.Animator;

public class CurrentData {	
    public static GameStateManager state;
    public static Player jugador;
    public static ListenKeys lKey;
    public static GameMap lvl;
    public static Tile[][] tiles, deco;
    public static Animator anim;    
    public static Point iso, pos, origin, aux;  
    public static JFrame frame;
    public static JPanel panel;
}
