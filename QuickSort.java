package cellsGeneticAlgorithm;

public class QuickSort  {
    private Cell[] array;
    private int size;

    public void sort(Cell[] array) {
        this.array = array;
        size = array.length;
        quicksort(0, size - 1);
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;

        double pivot = array[low + (high-low)/2].getFitness();

        while (i <= j) {
            while (array[i].getFitness() < pivot) {
                i++;
            }
            while (array[j].getFitness() > pivot) {
                j--;
            }
            if (i <= j) {
                swap(i++, j--);
            }
        }

        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void swap(int i, int j) {
        Cell temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}