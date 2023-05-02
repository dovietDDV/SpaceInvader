package Entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;

import Entities.Alien;
import Main.Commons;

public class AlienArmy implements Commons{
	private ArrayList<Alien> aliens;
	private int alienX = 150;
    private int alienY = 5;
    private int quantity;
	public AlienArmy(int col, int row) {
		aliens =new ArrayList<Alien>();
		for(int i=0 ;i < col; i++) {
			for(int j=0; j<row; j++) {
				 Alien alien = new Alien(alienX + 18*j, alienY + 18*i);
				 aliens.add(alien);
			}
		}
		quantity =col*row;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void draw(Graphics g) {
		Iterator it = aliens.iterator();

        while (it.hasNext()) {
            Alien alien = (Alien) it.next();
            //Alien.Bomb b = alien.getBomb();
            alien.draw(g);
        }
	}
	
	public void update() {
		Iterator it = aliens.iterator();
        while (it.hasNext()) {
            Alien a1= (Alien) it.next();
            a1.update(); 
            int x = a1.getX();
            if (x  >= BOARD_WIDTH - ALIEN_WIDTH && a1.isVisible()) {
                Iterator i1 = aliens.iterator();
                while (i1.hasNext()) {
                    Alien a2 = (Alien) i1.next();
                    a2.setScore(a2.getScore()-10);
                    a2.updateY();
                }
            }
            if (x <= BORDER_LEFT) {

                Iterator i2 = aliens.iterator();
                while (i2.hasNext()) {
                    Alien a = (Alien)i2.next();
                    a.updateY();
                }
            }
        }
         
    }
	public ArrayList<Alien> getAliens() {
		return aliens;
	}
	public void setAliens(ArrayList<Alien> aliens) {
		this.aliens = aliens;
	}
	
}
