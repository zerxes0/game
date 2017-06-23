package StateMachine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import Data.CurrentData;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
//HOLA xd
public class Menu extends JComponent implements GameState {

	private GameStateManager state;
	private Graphics g;
        private JButton boton;
        private JPanel panel;
	
	public Menu( GameStateManager newGameState ){
		state = newGameState;
                panel = new JPanel();
                boton = new JButton( "Hola");  
                boton.setVisible(true);
                JButton botonazo = new JButton(" adios");
               
                //boton.setBounds(400, 400, 20, 20); 
                panel.setLayout(  new FlowLayout() );
                panel.add(boton);
                panel.add(botonazo);
                
                panel.setBackground(Color.BLACK);
               
                /////////
                CurrentData.getFrame().getContentPane().setBackground(Color.blue);
                 CurrentData.getFrame().add(panel);
                
                      
	}
	
	@Override
	public void draw(){
            System.out.println("reach menu draw");
            g = state.getGraphics();
        
            g.setColor( Color.blue );            
            cadenaxd("hola A33", 14, 400, 350, Color.BLACK); 
            
                                       
		//state.getGraphics().paint(state.getGraphics());
	}
	
	@Override
	public void menu() {
        System.out.println( "Menu state" );
	}

	@Override
	public void world() {
		System.out.println( " Entering World state"	 );
		//AudioManager.playMusic("/Resources/Music/magic.mp3");
        state.setGameState( state.getWorld() );		
	}

	@Override
	public void battle() {
       System.out.println( "No has ni entrado al mundo! " );
	}

	@Override
	public void pause() {
       System.out.println( "No puedees pausar el menu!" );
	}

	@Override
	public void gameOver() {
       System.out.println( "Como pierdes estando en el menu???" );
	}
       private void cadenaxd (String cadena, int fontsize, int Xcor, int Ycor, Color colorxd ){
       g.setColor(colorxd);
       g.setFont(new Font("/Resources/fonts/fotana/upheavtt.ttf", Font.PLAIN, fontsize));
       g.drawString(cadena, Xcor, Ycor);
       }

}

