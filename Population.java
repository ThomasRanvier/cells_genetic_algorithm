package cellsGeneticAlgorithm;

import java.awt.Graphics;
import java.util.ArrayList;

public class Population {
	private Cell cells[];

	public Population(Cell[] cells) {
		super();
		this.cells = cells;
	}
	
	public void move() {
		for (Cell c: cells) {
			c.move();
		}
	}
	
	public boolean allCellsAreDead() {
		for (Cell c: this.cells) {
			if (c.isAlive()) {
				return false;
			}
        }
		return true;
	}
	
	public void respawn() {
		for (Cell c: this.cells) {
            c.respawn();
        }
	}
	
	public void draw(Graphics g) {
		for (Cell c: this.cells) {
            c.draw(g);
        }
	}

	public void updateAllFitnesses() {
		for (Cell c: this.cells) {
            c.setFitness(Main.calculateFitness(c));
        }
	}

	public void changeDnaForLessFits() {
		this.sortByFitness();
		for (int i = 0; i < Config.KILL_RATE; i++) {
			this.changeGenome(this.cells[i].getDna());
		}
	}

	private void changeGenome(Dna dna) {
		Cell parent1 = cells[Config.POPULATION_SIZE - 1];
		Cell parent2 = this.getRandomParent();
		this.crossOver(dna, parent1.getDna().getGenome(), parent2.getDna().getGenome());
	}

	private void crossOver(Dna dna, ArrayList<Double> parent1genome, ArrayList<Double> parent2genome) {
		int maxSize = (dna.getGenome().size() < parent1genome.size() ? dna.getGenome().size() : parent1genome.size());
		maxSize = (maxSize < parent2genome.size() ? maxSize : parent2genome.size()) - 1;
		
		for (int i = 0; i < maxSize; i++) {
			dna.getGenome().set(i, Config.RAND.nextInt(2) == 0 ? parent1genome.get(i) : parent2genome.get(i));
		}
		
		dna.mutate();
	}

	private Cell getRandomParent() {
		int index = Config.POPULATION_SIZE - Config.RAND.nextInt(Config.POPULATION_SIZE - Config.KILL_RATE - 1) - 2;
		return cells[index];
	}

	private void sortByFitness() {
		QuickSort sorter = new QuickSort();
		sorter.sort(cells);
	}
}
