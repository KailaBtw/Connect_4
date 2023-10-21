package edu.unca.csci201;

/**
 * Disk Class - 
 * 		Class that stores the disk object color within our column.
 * 
 * see Project 2
 * 
 * Fall 2023
 * 
 * Ellie Lagrave
 */

public class Disk {
	
	private char diskColor;
	
	/**
	 * Disk object constructor, assigns a color to the Disk object
	 * @param diskColor char representing the disk color( red is 'R' and black is 'B')
	 */
	public Disk(char diskColor) {
		if (diskColor == 'R') {
			this.diskColor = 'R';
		} else if (diskColor == 'B') {
			this.diskColor = 'B';
		}
	}
	
	/**
	 * Boolean method to check if a disk is red or not.
	 * @return boolean true if disk is red
	 */
	public boolean isRed() {
		if(diskColor == 'R') {
			return true;
		}
		return false;
	}
	
	/**
	 * Boolean method to check if a disk is black or not.
	 * @return boolean true if disk is black
	 */
	public boolean isBlack() {
		if(diskColor == 'B') {
			return true;
		}
		return false;
	}
	
	
	
}
