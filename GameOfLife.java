package gol;

import java.util.List;

public class GameOfLife {
	Grid grid;
	Grid prevGrid;
	CellQueue queue;
	int rows;
	int cols;

	public GameOfLife(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		// first need to initialise the grid
		grid = new Grid(rows, cols);
		// then need to initialise the cells as well
		grid.initialiseCells();
		// need to follow both steps for prevGrid as well...
		prevGrid = new Grid(rows, cols);
		prevGrid.initialiseCells();
		// but then will copy grid to prevGrid to have both start off the same.
		// because it is random, it doesn't make a difference which version of the grid
		// we begin with.
		copyGridStateToPrev();
		// the queue should handle as many cells as there are
		queue = new CellQueue(rows * cols);
	}

	public void startSimulation() {
		// here, all we need to do is show the user the beginning state, as any changes
		// will only be after the original gen.
		grid.displayGrid();
	}

	// this is the driver method for any time the grid updates.
	public void updateGrid() {
		// first prevGrid needs to be updated.
		copyGridStateToPrev();
		// then calls the Grid method to enqueue all of the cells.
		grid.enqueueAll(queue);

		// then it can deal with all of the cells.
		while (!queue.isEmpty()) {
			// first take off of the queue
			Cell cell = queue.dequeue();
			// then directly apply the logic to it, as it is an object and will transfer
			// through methods.
			applyRulesToCell(cell);
		}
	}

	// this ensures the prevGrid stays a "deep copy"
	private void copyGridStateToPrev() {
		// it must loop through all cells, setting the state manually to ensure all
		// objects remain separate.
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				prevGrid.getCell(i, j).setState(grid.getCell(i, j).getState());
			}
		}
	}

	// this takes one cell and decided on it's state based off its neighbors.
	public void applyRulesToCell(Cell cell) {
		// first neighbors are acquired from the Grid class, specifically from the
		// prevGrid.
		List<Cell> neighbors = prevGrid.getNeighbors(cell);
		// need the row and col to update the state in Grid grid
		int curRow = cell.getRow();
		int curCol = cell.getCol();
		int liveNeighbors = 0;

		// counts only the live neighbors.
		for (Cell neighbor : neighbors) {
			if (neighbor.getState()) {
				liveNeighbors++;
			}
		}

		// then using the count...
		if (cell.getState()) {
			// if the cell is ALREADY alive
			if (liveNeighbors < 2 || liveNeighbors > 3) {
				// but has too few or too many neighbors
				grid.getCell(curRow, curCol).setState(false); // Cell dies
			}
		} else {
			// if the cell is ALREADY dead
			if (liveNeighbors == 3) {
				// yet has exactly three neighbors
				grid.getCell(curRow, curCol).setState(true); // Cell comes to life
			}
		}
	}

	// calls the Grid class to display the current generation of grid.
	public void visualiseGrid() {
		grid.displayGrid();
	}
}
