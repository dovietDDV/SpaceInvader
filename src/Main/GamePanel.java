package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Entities.Bullet;
import Entities.Player;
import Inputs.KeyBoardInputs;
import Entities.Alien;
import Entities.AlienArmy;
import java.util.Random;



public class GamePanel extends JPanel  implements Runnable, Commons{
//	private ArrayList aliens;
	private AlienArmy alienAmry;
	private Player player;
    private Bullet bullet;
    private Boolean ingame = true;
    private int row =2,col=8;
    private int level=1;
    private int score =0;
    private int State =1;
    private Dimension d;
    
    private final String expl = "res/explosion.png";
    
    private Thread animator;
    
    
    public GamePanel( ) 
    {	
    	addKeyListener(new KeyBoardInputs(this));
    	setFocusable(true);
    	setSize();
    	setBackground(Color.black);
        gameInit();
        //setDoubleBuffered(true);
    }
    private void setSize() {
    	d = new Dimension(BOARD_WIDTH,BOARD_HEIGTH);
    	setPreferredSize(d);
    }
    private void gameInit() {
    	alienAmry= new AlienArmy(row, col);
    	player = new Player();
    	bullet = new Bullet();
    	animator = new Thread(this);
    	animator.start();
		
	}
	
    public void gameOver()
    {

        Graphics g = this.getGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 80);
        g.setColor(Color.white);
        g.drawRect(50, BOARD_WIDTH/2 - 30, BOARD_WIDTH-100, 80);

        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString("Game Over", (BOARD_WIDTH - metr.stringWidth("Game Over"))/2, BOARD_WIDTH/2);
        g.drawString("Score: "+score, (BOARD_WIDTH - metr.stringWidth("Score: "+score))/2, BOARD_WIDTH/2+30);
    }
    @Override
	public void run() {
		double timePerFrame = 1000000000.0/ 120;
		double timePerUpdate = 1000000000.0/ 200;
		
		long previousTime = System.nanoTime();
		
		
		
		int frames =0;
		int updates =0;
		long lastCheck = System.currentTimeMillis(); 
		
		double deltaU =0;
		double deltaF =0;
		while(ingame) {

			long curtentTime = System.nanoTime();
			
			deltaU += (curtentTime - previousTime)/timePerUpdate;
			deltaF += (curtentTime - previousTime)/timePerFrame;
			previousTime = curtentTime;
			
			if(deltaU >= 1) {
				update();
				updates++;
				deltaU--;
			}
			

			if(deltaF >= 1) {
				repaint();				
				frames++;
				deltaF--;
			}	
			
			if(System.currentTimeMillis()- lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("fps: "+frames + "UPS: "+ updates);
				frames =0;
				updates =0;
			}
		}
		gameOver();
	}
	private void update() {
		if(State == 2) {
			player.update();
			
			alienAmry.update();
			hit();
			bullet.update();
			hit2();
			NEW();
		}
	}
	
	public void paint(Graphics g)
    {	super.paint(g);
		if(State == 2) {
			g.setColor(Color.black);
		    //g.fillRect(0, 0, d.width, d.height);
		    g.setColor(Color.green); 
			player.draw(g);
			alienAmry.draw(g);
			bullet.draw(g);
			g.drawLine(0, GROUND, BOARD_WIDTH, GROUND);
			Font small = new Font("Helvetica", Font.BOLD, 14);
	        FontMetrics metr = this.getFontMetrics(small);

	        g.setColor(Color.white);
	        g.setFont(small);
	        g.drawString("Level: "+level, 10,BOARD_HEIGTH-20);
	        g.drawString("Score: "+score, 10,BOARD_HEIGTH-5);
		}
		if(State == 3) {
			g.setColor(Color.black);
	        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGTH);
			Font small = new Font("Helvetica", Font.BOLD, 14);
	        FontMetrics metr = this.getFontMetrics(small);
	        g.setColor(Color.white);
	        g.setFont(small);
	        g.drawString("Paused", (BOARD_WIDTH - metr.stringWidth("Paused"))/2, BOARD_WIDTH/2);
		}
		if(State == 1) {

			Font small = new Font("Helvetica", Font.BOLD, 14);
	        FontMetrics metr = this.getFontMetrics(small);
	        g.setColor(Color.white);
	        g.setFont(small);
	        g.drawString("SPACE INVADER", (BOARD_WIDTH - metr.stringWidth("SPACE INVADER"))/2, BOARD_WIDTH/2);
	        g.drawString("PRESS SPACE", (BOARD_WIDTH - metr.stringWidth("PRESS SPACE"))/2, BOARD_WIDTH/2+30);
		}       
     }
	
	
	public void NEW() {
		if(alienAmry.getQuantity() ==0) {
			row++;
			level++;
			alienAmry= new AlienArmy(row, col);
		}
	}
	public void hit() {
		
		if (bullet.isVisible()) {
			Iterator it = alienAmry.getAliens().iterator();
	        int bulletX = bullet.getX();
	        int bulletY = bullet.getY();
            while (it.hasNext()) {
                Alien alien = (Alien) it.next();
                int alienX = alien.getX();
                int alienY = alien.getY();

                if (alien.isVisible() && bullet.isVisible()) {
                    if (bulletX >= (alienX) && bulletX <= (alienX + ALIEN_WIDTH) && bulletY >= (alienY) && bulletY <= (alienY+ALIEN_HEIGHT) ) {
                            ImageIcon ii =  new ImageIcon(expl);
                            alien.setImage(ii.getImage());
                            alien.setDying(true);
                             //deaths++;
                            bullet.setDying(true);
                            bullet.die();
                            alienAmry.setQuantity(alienAmry.getQuantity()-1);
                            score += alien.getScore();
                    }
                }
                if(bullet.getY()<=0) {
                	bullet.setDying(true);
                }
            }
            
        }
	}
	public void hit2() {
		Iterator i3 = alienAmry.getAliens().iterator();
		while (i3.hasNext()) {
            Alien a = (Alien) i3.next();
            Alien.Bomb b = a.getBomb();

            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !b.isDestroyed()) {
                if ( bombX >= (playerX) && 
                    bombX <= (playerX+PLAYER_WIDTH) &&
                    bombY >= (playerY) && 
                    bombY <= (playerY+PLAYER_HEIGHT) ) {
                        ImageIcon ii = new ImageIcon(expl);
                        player.setImage(ii.getImage());
                        player.setDying(true);
                        b.setDestroyed(true);
                        ingame = false;
                    }
            }
            if(a.getY() == playerY && a.getX() == playerX) {
            	ingame = false;
            }
            if(a.getY() >= GROUND) {
            	ingame = false;
            }
        }
	}
	
	
	
	
	
	public Player getPlayer() {
		return player;
	}
	public Bullet getBullet() {
		return bullet;
	}
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	public Boolean getIngame() {
		return ingame;
	}
	public void setIngame(Boolean ingame) {
		this.ingame = ingame;
	}
	public int getState() {
		return State;
	}
	public void setState(int state) {
		State = state;
	}


	
	
}
