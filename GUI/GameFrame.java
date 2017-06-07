package GUI;

import java.awt.Dimension;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

    //se hace una funcion init en el Frame para que al
    //instanciar el objeto evitemos un warning del idle
    //tambien es buena practica mantener un constructor
    //bajo 6 lineas y con las funciones se entiende mas
    //que estamos haciendo.
    public void init( int width, int height ){
    	GamePanel panel = new GamePanel( new Dimension( width, height ) );
        this.setSize( width , height );
        this.setVisible(true);
        this.setContentPane(panel);
        panel.init();
    }

    public GameFrame(){
        super( "Juego" );
    }
}
