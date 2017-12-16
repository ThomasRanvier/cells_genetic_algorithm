package cellsGeneticAlgorithm;

import java.util.ArrayList;

public class Dna {
	private ArrayList<Double> genome;
	
	public Dna(ArrayList<Double> genome) {
		this.genome = genome;
	}

	public ArrayList<Double> getGenome() {
		return genome;
	}
	
	public void setGenome(ArrayList<Double> genome) {
		this.genome = genome;
	}
	
	public void addAngle(double angle) {
		this.genome.add(angle);
	}
	
	public void mutate() {
		for (int i = 0; i < this.genome.size() - 1; i++) {
			if (Config.RAND.nextDouble() <= Config.MUTATION_RATE) {
				this.genome.set(i, Config.RAND.nextDouble() * 360);
			}
		}
	}
}
