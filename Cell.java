package gol;

public class Cell {
	boolean state;
	int row;
	int col;
	
	public Cell(int col, int row) {
		this.state = false;
		this.col = col;
		this.row = row;
	}
	
	public boolean getState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}

