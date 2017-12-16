package cellsGeneticAlgorithm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Target {

	private Point position;
	
    public Target(Point position) {
        this.position = position;
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        g.drawOval(this.position.x - (Config.TARGET_WIDTH / 2), this.position.y - (Config.TARGET_WIDTH / 2), 
        		Config.TARGET_WIDTH, Config.TARGET_WIDTH);
        g.fillOval(this.position.x - (Config.TARGET_WIDTH / 4), this.position.y - (Config.TARGET_WIDTH / 4), 
        		(Config.TARGET_WIDTH / 2), (Config.TARGET_WIDTH / 2));
    }

	public Point getPosition() {
		return position;
	}
}