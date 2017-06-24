package GUI;

import Data.CurrentData;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {

	
	//private JFrame frame;
    private JPanel panel;
	private Canvas canvas;
	
    public void init( int width, int height ){
    	//frame = new JFrame("juego");
        panel = new JPanel();
        
        //frame.setFocusable(false);
		/*canvas = new Canvas();	
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));*/
		
		panel.setPreferredSize(new Dimension(width, height));
		panel.setMaximumSize(new Dimension(width, height));
		panel.setMinimumSize(new Dimension(width, height));
		
		

		//panel.setLayout( new GridLayout() );
        panel.setBounds(0, 64, width, height);
        add(panel);

		
    	setSize(width, height);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	//setResizable(false);
    	setLocationRelativeTo(null);
    	setVisible(true);
		//pack();
        CurrentData.frame = this;
        //CurrentData.panel = panel;
    }
    
	public Canvas getCanvas(){
		return canvas;
	}
	public JFrame getFrame(){
		return this;
	}

}
