package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import main.Game.STATE;

public class Menu extends MouseAdapter {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		// play button pressed
		if(game.gameState == STATE.Menu && mouseOver(mx, my, 210, 150, 200, 64)) {
			
			game.gameState = STATE.Game;
			
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.clearEnemys();
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), 
					r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			
			AudioPlayer.getSound("menu_sound").play();
			
		}  else if(game.gameState == STATE.Menu && mouseOver(mx, my, 210, 250, 200, 64)) {
			game.gameState = STATE.Help;
			AudioPlayer.getSound("menu_sound").play();
		} else if(game.gameState == STATE.Help && mouseOver(mx, my, 210, 350, 200, 64)) {
			game.gameState = STATE.Menu;
			AudioPlayer.getSound("menu_sound").play();
		} else if(game.gameState == STATE.Menu && mouseOver(mx, my, 210, 350, 200, 64)) {
			System.exit(1);
		} else if(game.gameState == STATE.End && mouseOver(mx, my, 210, 350, 200, 64)) {
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.clearEnemys();
			hud.setLevel(1);
			hud.setScore(0);
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), 
					r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			AudioPlayer.getSound("menu_sound").play();
		}
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
		}
		
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		Font fnt3 = new Font("arial", 1, 20);
		
		if(game.gameState == STATE.Menu) {
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Dodge", 240, 70);
			
			g.setFont(fnt2);
			
			g.setColor(Color.white);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 270, 190);
			
			g.setColor(Color.white);
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);
			
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
			
		} else if(game.gameState == STATE.Help) {
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("Use WASD keys to move player and dodge enemies", 70, 200);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
			
		} else if(game.gameState == STATE.End) {
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 180, 70);
			
			g.setFont(fnt3);
			g.drawString("You lost with a score of " + hud.getScore(), 180, 200);
			
			g.setFont(fnt2);
			g.setColor(Color.white);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Try Again", 245, 390);
			
		}
		
	}
	
}
