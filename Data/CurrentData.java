package Data;

import java.awt.Point;

import StateMachine.GameStateManager;
import systems.ListenKeys;
import entity.Player;
import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JPanel;
import maps.GameMap;
import maps.Tile;
import systems.Animator;
import GUI.GameFrame;

public class CurrentData {	
    public static GameStateManager state;
    public static Player jugador;
    public static ListenKeys lKey;
    public static GameMap lvl;
    public static Tile[][] tiles, deco;
    public static Animator anim;    
    public static Point iso, pos, origin, aux;  
    public static GameFrame frame;
    public static JPanel panel;
    public static Canvas canvas;

    public static void initCanvas(){
        frame.remove(panel); //removemos el panel inicial ( en el que se encuentra el menu).
        panel = new JPanel(); //creamos un nuevo panel.
        panel.setPreferredSize( new Dimension( frame.getWidth(),frame.getHeight() ) );
        panel.setMaximumSize( new Dimension( frame.getWidth(),frame.getHeight() ) );
        panel.setMinimumSize( new Dimension( frame.getWidth(),frame.getHeight() ) );
        canvas = frame.getCanvas(); //obtenemos el canvas que se creo en el frame para volverlo a aÑadir
        //ya que estaba dentro del panel y el canvas debe ser introducido en algo para inicializarlo
        panel.add(canvas); //entonces le añadimos el canvas...
        frame.getContentPane().add(panel); //se lo añadimos al frame.
        frame.revalidate(); // aqui en frame comprueba los componentes que tiene y el juego sigue
    }
}
