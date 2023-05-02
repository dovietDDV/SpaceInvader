package Entities;

import java.awt.Graphics;

import javax.swing.ImageIcon;

import Main.Commons;



public class Player extends Entities implements Commons{
	private final int START_Y = 280; 
    private final int START_X = 270;
    private boolean attack = false;
    private final String player = "res/player.png";
    private int width;
    private boolean left, right;
    public Player() {

        ImageIcon ii = new ImageIcon(player);

        width = ii.getImage().getWidth(null); 

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }
    public void draw(Graphics g) {
    	g.drawImage(this.getImage(), this.getX(), this.getY(), null );
    }
    
    public void update() {
		updatePos();
		
	}
    
    


private void updatePos() {
		if(left  && !right) {
			if(x >= 0 + 2) x--;
		}
		if(right  && !left) {
			if(x <= BOARD_WIDTH-PLAYER_WIDTH) x++;
		}
		
	}
public boolean isLeft() {
		return left;
	}
	public void setLeft(boolean left) {
		this.left = left;
	}
	public boolean isRight() {
		return right;
	}
	public void setRight(boolean right) {
		this.right = right;
	}
	public boolean isAttack() {
		return attack;
	}
	public void setAttack(boolean attack) {
		this.attack = attack;
	}
	
	
}
