package GUI;

import Data.CurrentData;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame {

	
	private JFrame frame;
	private Canvas canvas;
	
    public void init( int width, int height ){
    	frame = new JFrame("juego");
        //panel = new JPanel( new BorderLayout() );
    	frame.setSize(width, height);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setResizable(false);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
        frame.getContentPane().setBackground( Color.RED);
        

        //frame.setFocusable(false);
                
		canvas = new Canvas();
		
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
                JPanel panelito = new JPanel();
                panelito.setSize( width, height);
                
                frame.add(panelito);
                frame.add(canvas); 
		 
                
                CurrentData.setFrame(frame);
		frame.pack();
                
    }
    
	public Canvas getCanvas(){
		return canvas;
	}
	public JFrame getFrame(){
		return frame;
	}

}
