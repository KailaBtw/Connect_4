package edu.unca.csci201;

import java.util.Random;
import java.util.Scanner;

/**
 * Connect4 Class - 
 * 		Class in charge of overseeing connect 4 game and executing the play method. contains our main method.
 * 
 * see Project 2
 * 
 * Fall 2023
 * 
 * Ellie Lagrave
 */

public class Connect4 {
	
	private Board gameBoard;
	private int currentMove;
	private final int maxMoves = 42;
	private String turnColor;
	
	/**
	 * Main method for connect 4 game, only creates connect4 opject and calls play method on it.
	 * @param args unused
	 */
	public static void main(String[] args) {
		
		Connect4 cnct = new Connect4();
		cnct.play();
		
	}
	
	/**
	 * Connect4 Class constructor method. takes no parameters and creates the game board object and randomly selects a first player. 
	 */
	public Connect4() {
		currentMove = 0;
		gameBoard = new Board();
		Random rand = new Random();
		int chance = rand.nextInt(2);
		if(chance == 1) {
			turnColor = "Red";
		} else {
			turnColor = "Black";
		}
	}

	/**
	 * Play Method, scans for user input, keeps track of full game board, monitors for correct user input,
	 * and calls the check for win conditions. This "drives" the game
	 */
	public void play() {
		Scanner scan = new Scanner(System.in);
		while(currentMove < maxMoves+1) {
			System.out.println(gameBoard);
			if (currentMove >= maxMoves) {
				System.out.println("Full Board, Tie Game!"); //execute when board is full.
				System.exit(0);
			}
			System.out.println(turnColor + "'s turn");
			System.out.println("Choose a column(1-7):");
			int column = scan.nextInt() - 1;
			while(!gameBoard.insert(turnColor == "Red" ? new Disk('R') : new Disk('B'), column)) {
				System.out.println("Invalid move, please try again");
				System.out.println(turnColor + "'s turn");
				System.out.println("Choose a column(1-7):");
				column = scan.nextInt() - 1;
			}
			//System.out.print(gameBoard.checkWinCondition());
			if(gameBoard.checkWinCondition()) {
				System.out.println(gameBoard);
				System.out.println(turnColor + " wins!");
				System.out.println("Game Over.");
				System.exit(0);
			}
			if(turnColor == "Red") {
				turnColor = "Black";
			} else {
				turnColor = "Red";
			}
			currentMove++;
		} 
		scan.close();
	}
}
