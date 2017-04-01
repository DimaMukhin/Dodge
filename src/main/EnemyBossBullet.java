package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBossBullet extends GameObject {

	private Handler handler;
	private Random r = new Random();
	
	public EnemyBossBullet(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = r.nextInt(10) + -5;
		velY = 5;
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y, 16, 16);
	}
	
	public void tick() {
		
		x += velX;
		y += velY;
		
		if(y >= Game.HEIGHT) handler.removeObject(this);
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.2f, handler));
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int) x,(int) y, 16, 16);
		
	}

}
