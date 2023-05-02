package Inputs;

import java.awt.Taskbar.State;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Main.GamePanel;
import Entities.Bullet;

public class KeyBoardInputs implements KeyListener{
	private GamePanel gamePanel;
	private int x,y;
	public KeyBoardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	private enum STATE{
    	Menu,Game
    }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(gamePanel.getState() == 2) {
			// TODO Auto-generated method stub
			if(e.getKeyCode() == KeyEvent.VK_LEFT) {
				gamePanel.getPlayer().setLeft(true);
			}
			if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
				gamePanel.getPlayer().setRight(true);
			}
			if (e.getKeyChar() == KeyEvent.VK_SPACE) {
				if(gamePanel.getBullet().isDying()) {
					x = gamePanel.getPlayer().getX();
		            y = gamePanel.getPlayer().getY();
		            gamePanel.setBullet(new Bullet(x, y)) ;
				}	
	        }
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				gamePanel.setState(3);
	        }
		}
		if(gamePanel.getState() == 1) {
			// TODO Auto-generated method stub
			if (e.getKeyChar() == KeyEvent.VK_SPACE) {
				gamePanel.setState(2);	
	        }
		}
		
		if(gamePanel.getState() == 3) {
			if (e.getKeyCode() == KeyEvent.VK_P) {
				gamePanel.setState(2);
	        }
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			gamePanel.getPlayer().setLeft(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gamePanel.getPlayer().setRight(false);
		}
	}

}
