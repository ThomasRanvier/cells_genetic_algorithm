package cellsGeneticAlgorithm;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
    	JFrame frame;
        frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(Config.FRAME_WIDTH, Config.FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Cells Genetic Algorithm");
        Config.OBSTACLES.add(new Obstacle(new Point(0, 200), Config.FRAME_WIDTH / 4, Config.FRAME_HEIGHT - 400));
        Config.OBSTACLES.add(new Obstacle(new Point(Config.FRAME_WIDTH - Config.FRAME_WIDTH / 4, 200), 
        		Config.FRAME_WIDTH / 4, Config.FRAME_HEIGHT - 400));
        Config.OBSTACLES.add(new Obstacle(new Point(Config.FRAME_WIDTH / 2 - Config.FRAME_WIDTH / 8, 200), 
        		Config.FRAME_WIDTH / 4, Config.FRAME_HEIGHT - 400));
        Map map = new Map(Config.OBSTACLES, createInitPopulation(), Config.TARGET);
        frame.setContentPane(map);
        go(map);
    }
    
    private static Population createInitPopulation() {
		Cell[] cells = new Cell[Config.POPULATION_SIZE];
    	for (int i = 0; i < Config.POPULATION_SIZE; i++) {
    		ArrayList<Double> genome = new ArrayList<Double>();
    		genome.add(Config.RAND.nextDouble() * 360);
			cells[i] = new Cell(new Point(Config.INIT_X_CELL, Config.INIT_Y_CELL), new Dna(genome));
		}
		return new Population(cells);
	}

	private static void go(Map map) {
    	while (true) {
            map.repaint();
            try {
                Thread.sleep(Config.REST_TIME);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            map.getPopulation().move();
            if (map.getPopulation().allCellsAreDead()) {
            	map.getPopulation().updateAllFitnesses();
            	map.getPopulation().changeDnaForLessFits();
            	map.getPopulation().respawn();
            	try {
                    Thread.sleep(10 * Config.REST_TIME);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
    	}
    }
	
	public static boolean collides(int cellX1, int cellX2, int cellY1, int cellY2, int oX1, int oX2, int oY1, int oY2) {
		if (cellX1 >= oX1 && cellX1 <= oX2) {
			if (cellY1 >= oY1 && cellY1 <= oY2) {
				return true;
			}
			if (cellY2 >= oY1 && cellY2<= oY2) {
				return true;
			}
		}
		if (cellX2 >= oX1 && cellX2 <= oX2) {
			if (cellY1 >= oY1 && cellY1 <= oY2) {
				return true;
			}
			if (cellY2 >= oY1 && cellY2 <= oY2) {
				return true;
			}
		}
		return false;
	}

	public static double calculateFitness(Cell c) {
		return ((Config.FRAME_WIDTH * 1.0 - Math.sqrt(Math.pow(Config.TARGET.getPosition().x - c.getPosition().x, 2)
				+ Math.pow(Config.TARGET.getPosition().y - c.getPosition().y, 2))) / Config.FRAME_WIDTH * 1.0) * 100.0;
	}
}
