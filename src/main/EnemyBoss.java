package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class EnemyBoss extends GameObject {

	private Handler handler;
	private Random r = new Random();
	
	private int timer = 60;
	private int timer2 = 50;
	
	private float bulletSpeed = 10;
	
	public EnemyBoss(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 2;
		
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y, 96, 96);
	}
	
	public void tick() {
		
		x += velX;
		y += velY;
		
		if(timer <= 0) velY = 0;
		else timer--;
		
		if(timer <= 0) timer2--;
		if(timer2 <= 0) {
			
			if(velX == 0) velX = 2;
			
			if(velX >= 0) velX += 0.01f;
			else velX -= 0.01f;
			
			int spawn = r.nextInt((int) bulletSpeed);
			if(bulletSpeed > 0) bulletSpeed -= 0.01f;
			if(spawn == 0) handler.addObject(new EnemyBossBullet(x + (96 / 2), 
					y + (96 / 2), ID.BasicEnemy, handler));
			
		}
		
		if(x <=0 || x >= Game.WIDTH - 96) velX  *= -1;
		
	}

	public void render(Graphics g) {
		
		g.setColor(Color.red);
		g.fillRect((int) x,(int) y, 96, 96);
		
	}

}
