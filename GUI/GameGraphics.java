package GUI;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public class GameGraphics extends JComponent{
	
	public static Dimension dimension;
	@Override
	public void paint( Graphics g){}
	/*
	@Override
	public void paintComponent( Graphics g ){
	}*/
		
	public GameGraphics( Dimension dimension ){
		super();	
		this.setSize( dimension );
		this.setPreferredSize( dimension );	
		GameGraphics.dimension = dimension;
	}
}
