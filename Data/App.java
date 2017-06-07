package Data;

import javax.swing.JFrame;

import GUI.GameFrame;

public class App {

    public static void main( String[] args ){
        GameFrame window = new GameFrame();
        //evitamos un warning
        window.init(800,600);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );        
    }
}
