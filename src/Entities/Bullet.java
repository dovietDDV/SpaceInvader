package Entities;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import Main.Commons;

public class Bullet extends Entities implements Commons{ 
	 	private String shot = "res/shot.png";
	    private final int H_SPACE = 3;
	    private final int V_SPACE = 1;
	    public Bullet( ) {
	        setDying(true);
	    }


		public Bullet(int x, int y) {

	        ImageIcon ii = new ImageIcon(shot);
	        setImage(ii.getImage());
	        setX(x + H_SPACE);
	        setY(y - V_SPACE);
	        setDying(false);

	    }
		public void draw(Graphics g) {
			if(isVisible()) {
				g.drawImage(getImage(), x, y, null);
			}
		}
		public void update() {
			if(isVisible()) {
				y--;
			}
		}
}
