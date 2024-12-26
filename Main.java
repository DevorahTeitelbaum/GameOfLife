package gol;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("what will be the dimensions of your board? (enter a single int)");
		int boardSize = 0;
		boolean inputCatch = false;
		while (inputCatch == false) {
			try {
				boardSize = keyboard.nextInt();
				keyboard.nextLine();
				inputCatch = true;
				if (boardSize < 3) {
					System.out.println("Invalid board size. board must be 3x3 or greater.");
					inputCatch = false;
				}
			} catch (InputMismatchException i) {
				System.out.println("Not an integer. Please try again.");
				keyboard.nextLine();
			}
		}
		System.out.println("Simulation starting. press any key to continue, type STOP to quit.");

		GameOfLife gol = new GameOfLife(boardSize, boardSize);
		gol.startSimulation();
		while (!keyboard.nextLine().equalsIgnoreCase("stop")) {
			gol.updateGrid();
			gol.visualiseGrid();
		}
	}
}