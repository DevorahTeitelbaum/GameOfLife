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
	
	//state is true=alive, false=dead.
	public boolean getState() {
		return state;
	}
	
	//can manually set once created to change through the generations
	public void setState(boolean state) {
		this.state = state;
	}
	
	//returns the row it is in
	public int getRow() {
		return row;
	}
	
	//returns the column it is in
	public int getCol() {
		return col;
	}
}

