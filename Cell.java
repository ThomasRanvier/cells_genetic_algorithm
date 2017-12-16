package cellsGeneticAlgorithm;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Cell {
	private Point position;
	private int index;
    private Dna dna;
    private boolean alive;
    private double angle, fitness;
	
	public Cell(Point position, Dna dna) {
		super();
		this.position = position;
		this.dna = dna;
		this.index = 0;
		this.fitness = 0;
		this.alive = true;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void move() {
		if (this.alive) {
			if (this.collides() || this.index >= Config.MAX_MOVEMENTS) {
				this.dies();
			} else {
				this.angle = Math.toRadians(this.dna.getGenome().get(this.index));
				if (this.index >= this.dna.getGenome().size() - 1) {
					this.dna.addAngle(this.dna.getGenome().get(this.index) + (Config.RAND.nextDouble() * Config.MAX_ANGLE_VARIATION * 2) - Config.MAX_ANGLE_VARIATION);
				}
				this.position.x += (int) (Config.CELL_SPEED * Math.cos(this.angle));
				this.position.y += (int) (Config.CELL_SPEED * Math.sin(this.angle));
				this.index++;
			}
		}
	}
	
	private boolean collides() {
		int cellX1 = this.position.x;
		int cellY1 = this.position.y;
		int cellX2 = cellX1 + Config.CELL_WIDTH;
		int cellY2 = cellY1 + Config.CELL_WIDTH;
		
		if (cellX1 <= 0 || cellX2 >= Config.FRAME_WIDTH || cellY1 <= 0 || cellY2 >= Config.FRAME_HEIGHT) {
			return true;
		}
		
		if (Main.collides(cellX1, cellX2, cellY1, cellY2, Config.X_TARGET - (Config.TARGET_WIDTH / 2), Config.X_TARGET 
				+ (Config.TARGET_WIDTH / 2), Config.Y_TARGET - (Config.TARGET_WIDTH / 2), Config.Y_TARGET + (Config.TARGET_WIDTH / 2))) {
			return true;
		}
		
		int oX1, oY1, oX2, oY2;
		
		for (Obstacle o: Config.OBSTACLES) {
			oY1 = o.getPosition().y;
			oY2 = o.getPosition().y + o.getHeight();
			oX1 = o.getPosition().x;
			oX2 = o.getPosition().x + o.getWidth();
			if (Main.collides(cellX1, cellX2, cellY1, cellY2, oX1, oX2, oY1, oY2)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAlive() {
		return alive;
	}

	public void dies() {
		this.alive = false;
	}
	
	public void respawn() {
		this.position.x = Config.INIT_X_CELL;
		this.position.y = Config.INIT_Y_CELL;
		this.index = 0;
		this.alive = true;
	}

	public void draw(Graphics g) {
		g.setColor(Config.CELL_COLOR);
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(Config.CELL_STROKE));
		g2.drawOval(this.position.x - (Config.CELL_WIDTH / 2), this.position.y - (Config.CELL_WIDTH / 2), Config.CELL_WIDTH, Config.CELL_WIDTH);
	}
	
	public Point getPosition() {
		return this.position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Dna getDna() {
		return this.dna;
	}
}
