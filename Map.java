package cellsGeneticAlgorithm;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Map extends JPanel{

	private Target target;
	private ArrayList<Obstacle> obstacles;
	private Population population;
	
	public Map(ArrayList<Obstacle> obstacles, Population population, Target target) {
		super();
		this.obstacles = obstacles;
		this.population = population;
		this.target = target;
	}

	public Population getPopulation() {
		return population;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Config.BACKGROUND_COLOR);
		g.drawRect(0, 0, Config.FRAME_WIDTH, Config.FRAME_HEIGHT);
		for (Obstacle obs: this.obstacles) {
			obs.draw(g);
		}
		this.target.draw(g);
		this.population.draw(g);
	}
}
