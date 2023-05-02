package Entities;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.ImageIcon;

import Main.Commons;

public class Alien extends Entities implements Commons{
	private Bomb bomb;
	Random generator = new Random();
    private final String ALIEN = "res/alien.png";
    private int alienSpeed =1,alienCount =0;
    private int score =250;
    public Alien(int x, int y) {
        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);
        ImageIcon ii = new ImageIcon(ALIEN);
        setImage(ii.getImage());
        
    }
    
    public void draw(Graphics g) {
    	if (isVisible()) {
    		g.drawImage(this.getImage(), this.getX(), this.getY(), null );        
    		
    	}
    	if(!this.bomb.isDestroyed()) {
    		g.drawImage(this.getBomb().getImage(), this.getBomb().getX(), this.getBomb().getY(), null );
    	}
        if (isDying()) {
            die();
        }
    	
    }
    public void update() {
    	alienCount++;
    	if(alienCount == 5) {
        	x += alienSpeed;
        	alienCount =0;
    	}
    	
    	int shot = generator.nextInt(15);
    	 if (shot == CHANCE && this.isVisible() && bomb.isDestroyed()) {
             bomb.setDestroyed(false);
             bomb.setVisible(true);
             bomb.setX(this.getX());
             bomb.setY(this.getY());   
         }

    	 if (!bomb.isDestroyed()) {
    		 bomb.count++;
    		 if(bomb.count == 4) {
    			 bomb.setY(bomb.getY() + 1);
    			 bomb.count =0;
    		 }
  
             if (bomb.getY() >= GROUND - BOMB_HEIGHT) {
                 bomb.setDestroyed(true);
             }
         }
    }
    public void updateY() {
    	y += GO_DOWN;
    	alienSpeed *= -1;
    }
    
    public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Bomb getBomb() {
        return bomb;
    }
    
    public class Bomb extends Entities {

        private final String bomb = "res/bomb.png";
        private boolean destroyed;
        private int count=0;
        public Bomb(int x, int y) {
            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(bomb);
            setImage(ii.getImage());
        }
        

        
        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }
}
