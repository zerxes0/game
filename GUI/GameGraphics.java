package GUI;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GameGraphics extends JComponent{
	
	public static Dimension dimension;

	public void paintComponent( Graphics g ){
		//System.out.println("Repainting...");
		/*g.setColor( Color.white );	
		g.fillRect(0, 0, (int)this.getWidth(), (int)this.getHeight() );*/
	}
		
	public GameGraphics( Dimension dimension ){
		super();	
		
		this.setSize( dimension );
		this.setPreferredSize( dimension );	
		GameGraphics.dimension = dimension;
	}
}
