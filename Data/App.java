package Data;

import java.awt.Dimension;

public class App {

    public static void main( String[] args ){
    	Game window = new Game( new Dimension(932,658) );
    	window.init();
        //GameFrame window = new GameFrame();
        //evitamos un warning
        //window.init(800,600);
        //window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );        
    }
}
