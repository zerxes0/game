package GUI;

import Data.CurrentData;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
    private Canvas canvas;
    private JPanel panel;
    private JPanel gamePanel;
    private JPanel menuPanel;
    private CardLayout cLayout;

    private JPanel panel( int width, int height ){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        return panel;
    }

    public void init( int width, int height ){
        cLayout = new CardLayout();

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));

        panel = panel( width, height);
        panel.setLayout( cLayout );

        gamePanel = panel( width, height  );
        menuPanel = panel( width, height );

        gamePanel.add(canvas);
        panel.add( menuPanel, CurrentData.menu);
        panel.add(gamePanel, CurrentData.game);

		CurrentData.layout = this.cLayout;
        CurrentData.panel = this.panel;
        CurrentData.menuPanel = this.menuPanel;
        CurrentData.canvas = this.canvas;
        CurrentData.gamePanel = this.gamePanel;
        CurrentData.frame = this;

        this.setContentPane(panel);
    	this.setSize(width, height);
    	this.pack();
    	this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        //setResizable(false);

    }

    public Canvas getCanvas(){ return canvas; }
}
