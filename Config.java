package cellsGeneticAlgorithm;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Config {
	public static final Random RAND = new Random();
	public static final int FRAME_WIDTH = 1000;
	public static final int FRAME_HEIGHT = 700;
	public static final int POPULATION_SIZE = 50;
	public static final int KILL_RATE = (3  * POPULATION_SIZE)/ 4;
	public static final int MAX_MOVEMENTS = 425;
	public static final double MUTATION_RATE = 0.05;
	public static final int CELL_SPEED = 8;
	public static final int CELL_WIDTH = 15;
	public static final int CELL_STROKE = 3;
	public static final int INIT_X_CELL = FRAME_WIDTH / 2;
	public static final int INIT_Y_CELL = FRAME_HEIGHT - 125;
	public static final int X_TARGET = INIT_X_CELL;
	public static final int Y_TARGET = 75;
	public static final int TARGET_WIDTH = 30;
	public static final int MAX_ANGLE_VARIATION = 100;
	public static final int REST_TIME = 20;
	public static final Color BACKGROUND_COLOR = Color.LIGHT_GRAY;
	public static final Color CELL_COLOR = Color.ORANGE;
	public static final Color OBSTACLE_COLOR = Color.DARK_GRAY;
    public static final ArrayList<Obstacle> OBSTACLES = new ArrayList<Obstacle>();
	public static final Target TARGET = new Target(new Point(X_TARGET, Y_TARGET));
}
