package GUI;

import Data.CurrentData;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
    private Canvas canvas;

    public void init( int width, int height ){
    	//frame = new JFrame("juego");
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        CurrentData.canvas = canvas;

        JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setMaximumSize(new Dimension(width, height));
		panel.setMinimumSize(new Dimension(width, height));
        CurrentData.panel = panel;

        getContentPane().add(panel);
    	setSize(width, height);
    	//setResizable(false);
        this.setBackground(Color.black);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setVisible(true);    	     

        CurrentData.frame = this;
    }

    public Canvas getCanvas(){ return canvas; }
	public JFrame getFrame(){ return this; }

}
