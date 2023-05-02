package Main;

import javax.swing.JFrame;


public class GameWindow extends JFrame implements Commons{
	public GameWindow(){
		add(new GamePanel());
		setTitle("Space Invaders");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
        setResizable(false);
        
        
	}
	 public static void main(String[] args) {
	        GameWindow gameWindow =new GameWindow();
	    }
}
