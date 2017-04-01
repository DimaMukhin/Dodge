package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	private Handler handler;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);

		this.handler = handler;
		
	}


	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}
	
	public void tick() {
		
		x += velX;
		y += velY;
		
		x = Game.clamp((int) x, 0, Game.WIDTH - 37);
		y = Game.clamp((int) y, 0, Game.HEIGHT - 60);
		
		collision();
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.1f, handler));
		
	}

	private void collision() {
		
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy 
					|| tempObject.getId() == ID.FastEnemy
					|| tempObject.getId() == ID.SmartEnemy
					|| tempObject.getId() == ID.EnemyBoss) {
			
				if(getBounds().intersects(tempObject.getBounds())) {
					
					// collision code
					HUD.HEALTH -= 2;
					
				}
				
			}
			
		}
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect((int) x, (int) y, 32, 32);
		
	}

}
