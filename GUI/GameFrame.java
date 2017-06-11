package GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GameFrame {

	
	private JFrame frame;
	private Canvas canvas;
	
    public void init( int width, int height ){
    	frame = new JFrame("juego");
    	frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
        frame.getContentPane().setBackground( Color.DARK_GRAY);        
        //frame.setFocusable(false);
        
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		
		frame.add(canvas);
		frame.pack();
    }
    
	public Canvas getCanvas(){
		return canvas;
	}
	public JFrame getFrame(){
		return frame;
	}

}
