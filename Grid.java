package gol;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
	Cell[][] cells;
	int rows;
	int cols;

	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		cells = new Cell[rows][cols];
	}

	// uses the random class to create then assign states to Cells as to fill the
	// grid.
	public void initialiseCells() {
		Random rand = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// looping through the full grid.
				cells[i][j] = new Cell(j, i);
				// creates a cell to fill a slot
				cells[i][j].setState(rand.nextBoolean());
				// then randomly assigns a state.
			}
		}
	}

	public Cell getCell(int row, int col) {
		// returns a cell based on position.
		return cells[row][col];
	}

	// will return a List of the neighboring Cells
	public List<Cell> getNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<>();
		for (int i = -1; i <= 1; i++) {
			// for the rows, one above, in, and one below.
			for (int j = -1; j <= 1; j++) {
				// and the columns, one left, one right, and the current column
				if (i == 0 && j == 0)
					// if both are 0, that is the current cell, and doesn't count on itself.
					continue;
				int neighborRow = (cell.getRow() + i + rows) % rows;
				// the neighboring row is figured out by using the current cell's location and
				// mod function for the toroidal grid.
				int neighborCol = (cell.getCol() + j + cols) % cols;
				// the neighboring column is figured out by using the current cell's location
				// and mod function for the toroidal grid.
				neighbors.add(cells[neighborRow][neighborCol]);
				// then add the cell to the list once found.
			}
		}
		return neighbors;
	}

	// simple display of zeros and ones for dead and alive
	public void displayGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(cells[i][j].getState() ? "1 " : "0 ");
				// using ternary for simplified if.
			}
			System.out.println();
		}
	}

	public void enqueueAll(CellQueue queue) {
		// simply loops through the grid and enqueues all Cells
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				queue.enqueue(getCell(i, j));
			}
		}
	}
}
