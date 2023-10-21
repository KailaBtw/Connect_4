package edu.unca.csci201;

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

public class Board {

	private Column[] columns;

	/**
	 * Construtor method for Board object, requires no parameters. Sets up array of 7 column objects
	 */
	public Board() {
		columns = new Column[7];
		for (int i=0; i<columns.length; i++) {
			columns[i] = new Column();
		}
	}
	
	/**
	 * Method to insert a disk into column of supplied index. Requires disk object.
	 * 
	 * @param disk Disk object storing out 'R' or 'B' char value. Use "new Disk('R')"
	 * @param columnIndex index 0 is left most, index 6 is right most column (player sees 1 to 7)
	 * @return boolean true if successfully placed disk, false if rejected
	 */
	public boolean insert(Disk disk, int columnIndex) {
		if(columnIndex >= 0 && columnIndex < 7) {
			return columns[columnIndex].insert(disk);
		}
		return false;
	}
	
	/**
	 * Method to check entire game board object for win conditions. This checks for horizontal, verticle, and diagonal wins.
	 * Place this method after a user places a game piece to check for win conditions
	 * 
	 * @return boolean true if player's last move won the game.
	 */
	public boolean checkWinCondition() {
		//Each inner array is one column of our data.
		String[][] dataFrame = { new String[7],new String[7], new String[7], new String[7], new String[7], new String[7], new String[7]};
		//Variables to build our row/column/diagonal strings from
		String[] cols = {"", "", "", "", "", "", ""};
		String[] rows = {"", "", "", "", "", ""};
		String[] diagonalsLR = {"", "", "", "", "", ""};
		String[] diagonalsRL = {"", "", "", "", "", ""};
		
		//Go through our column and disk objects and store their colors within our dataFrame.
		for(int i=0; i<6; i++) {  //go through each column
			for(int j=0; j<7; j++) { //check each value in the row
				if(columns[j].get(i) != null) {
					if(columns[j].get(i).isRed()) {
						dataFrame[j][i] = "R";
					} else if(columns[j].get(i).isBlack()) {
						dataFrame[j][i] = "B";
					} else {
						dataFrame[j][i] = "_";
					}
				} else {
					dataFrame[j][i] = "_";
				}
				//System.out.print(rowString[j][i]);
			}
		}
		
		//check horizontal and verticle and one diagonal
		for(int i=0; i<7; i++) {  
			//iterate through our columns and store the data in cols
			//We have 7 cols and 6 rows so i<7 and j<6
			for(int row=0; row<6; row++) { 
				cols[i] = cols[i] + dataFrame[i][row];
			}
			//these are the 5 rows with 0>5 index
			if (i<6) {
				//iterate through our rows and store 
				for(int j=0; j<6; j++) {
					rows[i] = rows[i] + dataFrame[j][i];
				}
				//also capture the 3rd diagonal from the left (coming from the left corner)
				diagonalsLR[2] = diagonalsLR[2] + dataFrame[i][i]; 
			}
			//These are the 2 diagonals coming from the left wall of the board
			if (i<2) {
				//check diagonals going to the right  (0,0) (0,1) and (0,2)
				for(int j=0; j<4+i; j++) { 
					diagonalsLR[0+i] = diagonalsLR[0+i] + dataFrame[j][j+2-i]; 
				}
			}
			//These are the 6 diagonal rows coming from the right side to the left side
			if (i<3) {
				//check diagonals going left
				int row = 5-i;
				for(int col=0; col<6-i; col++) { 
					//These are the 3 diagonals going from right to left, coming from the bottom of the board
					diagonalsRL[3+i] = diagonalsRL[3+i] + dataFrame[row][col];  
					row--;
				}
				row = 6;
				for(int col=2-i; col<6; col++) { 
					diagonalsRL[0+i] = diagonalsRL[0+i] + dataFrame[row][col];  
					row--;
				}
			}
			//These are the 3 diagonals going from left to right, coming from the bottom of the board
			if (i>=1 && i<4) {
				for(int j=0; j<7-i; j++) { 
					diagonalsLR[2+i] = diagonalsLR[2+i] + dataFrame[j + i][j]; 
				}
			}
		}
		
		//Check the Strings for winning conditions
		for(int i=0; i<7; i++) { 
			//Enable these println statements to see the col strings
			//System.out.println(cols[i]);

			//check the Column Strings
			if (contains(cols[i]) == true) {
				return true;
			}
			if (i < 6) {
				//Enable these println statements to see the row, and diagonal strings
				
				//System.out.println(diagonalsLR[i]);
				//System.out.println(diagonalsRL[i]); 
				System.out.println(rows[i]);
				
				//check the Row Strings
				if (contains(rows[i]) == true) {
					return true;
				}
				//check diagonals going to the right
				if (contains(diagonalsLR[i]) == true) {
					return true;
				}
				//check diagonals going to the left
				if (contains(diagonalsRL[i]) == true) {
					return true;
				}
			}
		}
		return false;
		
	}
	
	private boolean contains(String str) {
		for (int i=0; i<str.length()-3; i++) {
			//System.out.print(str.substring(i,i+4) + "   ");
			if (str.substring(i,i+4).equals("RRRR") || str.substring(i,i+4).equals("BBBB")) {
				//System.out.print("true");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * i represents each of our column values, L to R
	 * j represents which verticle slot in the column
	 */
	public String toString() {
		String result = "| 1 | 2 | 3 | 4 | 5 | 6 | 7 |\n|---|---|---|---|---|---|---|\n";
		for(int i=5; i>=0; i--) {
			result = result + "|";
			for(int j=0; j<columns.length; j++) {
				if(columns[j].get(i) != null) {
					if(columns[j].get(i).isRed()) {
						result = result + " R |";
					} else if(columns[j].get(i).isBlack()) {
						result = result + " B |";
					} else {
						result = result + "   |";
					}
					//System.out.print(columns[j].get(i));
				} else {
					result = result + "   |";
				}
			}
			result = result + "\n";
		}
		result = result + "|---|---|---|---|---|---|---|\n";
		return result;
	}
	
//	for(int j=0; j<columns[i].size(); j++) {
//		System.out.println();
//		
//	}
	
	
	
	
	
}
