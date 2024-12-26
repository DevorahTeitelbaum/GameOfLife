package gol;

import java.util.List;

public class GameOfLife {
	Grid grid;
	Grid prevGrid;
	CircularQueue queue;
	int rows;
	int cols;

	public GameOfLife(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		grid = new Grid(rows, cols);
		grid.initialiseCells();
		prevGrid = new Grid(rows, cols);
		prevGrid.initialiseCells();
		copyGridStateToPrev();
		queue = new CircularQueue(rows * cols);
	}

	public void startSimulation() {
		grid.displayGrid();
	}

	public void updateGrid() {
		copyGridStateToPrev();

		grid.enqueueAll(queue);

		while (!queue.isEmpty()) {
			Cell cell = queue.dequeue();
			applyRulesToCell(cell);
		}
	}

	private void copyGridStateToPrev() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				prevGrid.getCell(i, j).setState(grid.getCell(i, j).getState());
			}
		}
	}

	public void applyRulesToCell(Cell cell) {
		List<Cell> neighbors = prevGrid.getNeighbors(cell);
		int curRow = cell.getRow();
		int curCol = cell.getCol();
		int liveNeighbors = 0;

		for (Cell neighbor : neighbors) {
			if (neighbor.getState()) {
				liveNeighbors++;
			}
		}

		if (cell.getState()) {
			if (liveNeighbors < 2 || liveNeighbors > 3) {
				grid.getCell(curRow, curCol).setState(false); // Cell dies
			}
		} else {
			if (liveNeighbors == 3) {
				grid.getCell(curRow, curCol).setState(true); // Cell comes to life
			}
		}
	}

	public void visualiseGrid() {
		grid.displayGrid();
	}
}
