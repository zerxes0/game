package Data;

import java.awt.*;

import StateMachine.GameStateManager;
import org.omg.CORBA.Current;
import systems.ListenKeys;
import entity.Player;

import javax.swing.*;

import maps.GameMap;
import maps.Tile;
import systems.Animator;
import GUI.GameFrame;

public class CurrentData {	
    public static GameStateManager state;
    public static Player jugador;
    public static ListenKeys lKey;
    public static GameMap lvl;
    public static GameFrame frame;
    public static JPanel menuPanel;
    public static JPanel panel;
    public static JPanel gamePanel;
    public static Canvas canvas;
    public static CardLayout layout;
    public static final String menu = "menu";
    public static final String game = "game";

    public static void initCanvas(){
        layout.show(panel, game);
        frame.revalidate();
    }
}
