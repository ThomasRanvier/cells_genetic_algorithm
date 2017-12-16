package cellsGeneticAlgorithm;

import java.awt.Graphics;
import java.awt.Point;

public class Obstacle {
	private Point position;
	private int width, height;
	
	public Obstacle(Point position, int width, int height) {
		super();
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public void draw(Graphics g) {
		g.setColor(Config.OBSTACLE_COLOR);
		g.fillRect(this.position.x, this.position.y, this.width, this.height);
	}
	
	public Point getPosition() {
		return this.position;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
}
