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

	public void initialiseCells() {
		Random rand = new Random();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				cells[i][j] = new Cell(j, i);
				cells[i][j].setState(rand.nextBoolean());
			}
		}
	}

	public Cell getCell(int row, int col) {
		return cells[row][col];
	}

	public List<Cell> getNeighbors(Cell cell) {
		List<Cell> neighbors = new ArrayList<>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0)
					continue;
				int neighborRow = (cell.getRow() + i + rows) % rows;
				int neighborCol = (cell.getCol() + j + cols) % cols;
				neighbors.add(cells[neighborRow][neighborCol]);
			}
		}
		return neighbors;
	}

	public void displayGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(cells[i][j].getState() ? "1 " : "0 ");
			}
			System.out.println();
		}
	}

	public void enqueueAll(CircularQueue queue) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				queue.enqueue(getCell(i, j));
			}
		}
	}
}
