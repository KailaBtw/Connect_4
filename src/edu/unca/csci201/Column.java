package edu.unca.csci201;

/**
 * Column Class - 
 * 		Class that stores a column of disk objects in an array
 * 
 * see Project 2
 * 
 * Fall 2023
 * 
 * Ellie Lagrave
 */

public class Column {
	
	/**
	 * Make a 6 high array representing our 6 slots in a column
	 */
	private Disk[] diskSlot;
	private int slotIndex;
	
	/**
	 * Constructor Method for column
	 */
	public Column() {
		diskSlot = new Disk[6];
		slotIndex = 0; //0 is the bottom of our column
		//0, 1, 2, 3, 4, 5
	}
	
	/**
	 * Insert disk into this specific column
	 * @param disk Disk object storing our color. use "new Disk('R')"
	 * @return boolean true if able to insert disk, false if column is already full.
	 */
	public boolean insert(Disk disk) {
		if(slotIndex < 6) { //index 5 is our last slot
			diskSlot[slotIndex] = disk;
			slotIndex++;
			return true;
		}
		return false;
	}
	
	/**
	 * Return the current number of disk's in the column
	 * @return int slotIndex+1
	 */
	public int size() {
		return slotIndex + 1;
	}
	
	/**
	 * Return the current slotIndex
	 * @return int slotIndex
	 */
	public int getSlotIndex() {
		return slotIndex;
	}
	
	/**
	 * Check to see if a column is full. i.e. if size() == 6
	 * @return boolean true if column is full 
	 */
	public boolean full() {
		if (size() == 6) {
			return true;
		}
		return false;
	}
	
	/**
	 * Method to return the Disk object in a specific column slot
	 * @param rowIndex int value of the Disk's position in the column (0 to 5 counting from bottom)
	 * @return Disk object storing the disk color
	 */
	public Disk get(int rowIndex) {
		if (rowIndex <= slotIndex) {
			return diskSlot[rowIndex];
		} else {
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
