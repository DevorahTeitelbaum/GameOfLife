package gol;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("what will be the dimensions of your board? (enter a single int)");
		int boardSize = 0;
		// initialised a default for the compiler
		boolean inputCatch = false;
		// will alert the while for any improper form of input
		while (inputCatch == false) {
			try {
				boardSize = keyboard.nextInt();
				keyboard.nextLine();
				// clear buffer
				inputCatch = true;
				// assume input ok
				if (boardSize < 3) {
					// if it isn't...
					System.out.println("Invalid board size. board must be 3x3 or greater.");
					inputCatch = false;
				}
			} catch (InputMismatchException i) {
				// if they put something besides an int
				System.out.println("Not an integer. Please try again.");
				keyboard.nextLine();
			}
		}
		System.out.println("Simulation starting. press any key to continue, type STOP to quit.");

		// creating a new gol object, then starting it off.
		GameOfLife gol = new GameOfLife(boardSize, boardSize);
		gol.startSimulation();
		// user input belongs in main, to be fed back through the objects.
		while (!keyboard.nextLine().equalsIgnoreCase("stop")) {
			gol.updateGrid();
			gol.visualiseGrid();
		}
	}
}