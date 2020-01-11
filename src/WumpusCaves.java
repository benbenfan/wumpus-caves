///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           WumpusCaves.java main file for the wumpus cave game
// Course:          CS 200, Fall 2019
//
// Author:          Benjamin Fan
// Email:           bfan@cs.wisc.edu
// Lecturer's Name: Marc Renault
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////
//////// All my files are under 80 COLUMNS WIDE ////////////////////////////////
//////// so the spacing looks weird but its still under the requirements////////
//////// If I want to change it back the short cut is ctrl+shift+f

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * This project is an adventure game inspired by both the
 * classic Hunt the Wumpus game and the Tham Luang cave rescue.
 *     https://en.wikipedia.org/wiki/Hunt_the_Wumpus
 *     https://en.wikipedia.org/wiki/Tham_Luang_cave_rescue
 * 
 * @author Jim Williams
 * @author Benjamin Fan
 */
public class WumpusCaves {

	/**
	 * Whether the game is search and rescue or hunt the wumpus.
	 */
	enum GameMode {
		HUNT, RESCUE;
	};

	/**
	 * Update the location parameter based on the direction. The cave is
	 * in the shape of a torus meaning it wraps all directions. Movement
	 * in any direction (n,s,e,w) is handled by this method.
	 *  
	 * @param currentCave The cave being explored.
	 * @param moveLocation The current row and column that are changed, 
	 *    based on the direction, to the new row and column.
	 *     
	 * @param direction Either "n","s","e" or "w"
	 */
	public static void move( char [][]currentCave, 
			int [] moveLocation, String direction) {
		// updates user's location within the current cave based on direction
		// n for north, s for south, e for east, w for west
		switch (direction) {
		case "n":
			moveLocation[Config.ROW]--;
			if ( moveLocation[Config.ROW] <0) {
				moveLocation[Config.ROW] = currentCave.length -1;
			}
			break;
		case "s":
			moveLocation[Config.ROW]++;
			if ( moveLocation[Config.ROW] >= currentCave.length) {
				moveLocation[Config.ROW] = 0;
			}
			break;
		case "e":
			moveLocation[Config.COL] = ++moveLocation[Config.COL] 
					% currentCave[Config.ROW].length;
			break;
		case "w":
			moveLocation[Config.COL]--;
			if ( moveLocation[Config.COL] <0) {
				moveLocation[Config.COL] = currentCave[Config.ROW].length-1;
			}
			break;
		}		
	}

	/**
	 * Prints out the result of the action of moving to the current location.
	 * 
	 * Algorithm: returns true if the user is alive throughout the things that
	 * they encounter like a pit bat, child or wumpus. If the user encounters a
	 * bat, they are moved to a random room. If they encounter a pit, they fall
	 * in and die. If they encounter the child or wumpus they are also not fine.
	 * Additionally, if the child or wumpus are in their proximity, their
	 * 'peter tingle' goes off and lets them percieve a nearby danger based 
	 * on the user perceptions.
	 * 
	 * @param randomNumGen  A random number generator.
	 * @param caveCurrent The cave being explored.
	 * @param playerLocation The current location of the player
	 * @param currentMode Whether rescuing a child or hunting the wumpus
	 * @return true if alive, otherwise false.
	 */
	public static boolean status(Random randomNumGen, char[][] caveCurrent,
			int [] playerLocation, GameMode currentMode) {
		if (caveCurrent[playerLocation[Config.ROW]][playerLocation[Config.COL]]
				== Config.BAT) {
			//Put the code segment that has the bat move the player into a new 
			//method with needed parameters and call it from the status method. 

			batMove(randomNumGen, caveCurrent, playerLocation);
		} 
		else if (caveCurrent[playerLocation[Config.ROW]]
				[playerLocation[Config.COL]] == Config.PIT) {
			System.out.println("You fell into a pit.");
			return false;
		}

		System.out.println("room " + playerLocation[Config.ROW] + "" 
				+ playerLocation[Config.COL]);

		if (caveCurrent[playerLocation[Config.ROW]]
				[playerLocation[Config.COL]] == Config.END) {
			if (currentMode==GameMode.RESCUE) {
				System.out.println("You've found the child safe and happy to "
						+ "see you!");
			} else {
				System.out.println("You've been eaten by the Wumpus.");
			}
			return false;
		} else { 
			// now check for perceptions
			boolean[] userPerceptions = new boolean[Config.NUM_PERC];
			//Refactor the code that determines the player's perceptions into 
			//its own method.
			checkPerceptions(userPerceptions, playerLocation, 
					caveCurrent,currentMode);
		}
		return true;
	}

	// These are my 3 refactored code blocks 
	// They are public in case I want to test them in 
	// the TestWUmpusCaves file
	/**
	 * Refactoring code to detect threats around the user, scanning for bats
	 * pits and end cases.
	 * 
	 * @param userPerceptions Array storing percieved threats
	 * @param playerLocation The current location of the player
	 * @param caveCurrent The cave being explored.
	 * @param currentMode Whether rescuing a child or hunting the wumpus
	 */
	public static void checkPerceptions(boolean[] userPerceptions, 
			int[] playerLocation, char[][] caveCurrent, GameMode currentMode) {
		String[] directionsArray = { "n", "s", "e", "w" };
		for (int i = 0; i < directionsArray.length; i++) {
			int[] scanAround = Arrays.copyOf(playerLocation, 
					playerLocation.length);
			move(caveCurrent, scanAround, directionsArray[i]);

			switch (caveCurrent[scanAround[Config.ROW]]
					[scanAround[Config.COL]]) {
					case Config.PIT:
						userPerceptions[Config.PERCIEVE_PIT] = true;
						break;
					case Config.BAT:
						// do not user Config.COL because this array is 1D
						userPerceptions[1] = true;
						break;
					case Config.END:
						userPerceptions[2] = true;
						break;
			}
		}
		// do not user Config.COL because this array is 1D
		if (userPerceptions[1]) {
			System.out.println("you hear a rustling");
		}
		if (userPerceptions[Config.PERCIEVE_PIT]) {
			System.out.println("you feel a draft");
		}
		if (userPerceptions[2]) {
			if (currentMode == GameMode.RESCUE) {
				System.out.println("you hear a child snoring");
			} else {
				System.out.println("there's an awful smell");
			}
		}

	}

	/**
	 * This sets up which cave the user will traverse dependant on their 
	 * cave number 
	 * 
	 * @param scanner input for user choice
	 * @return a valid cave that the user can traverse based on their choice
	 */
	public static char[][] initializeCave(Scanner scanner) {
		boolean isValidNumber = false;
		int caveNumber = 0;
		while (!isValidNumber) {
			System.out.print("Please enter the number of the cave to enter: ");
			caveNumber = scanner.nextInt();
			scanner.nextLine();
			if (caveNumber >= 1 && caveNumber <= Config.CAVES.length) {
				isValidNumber = true;
			}
		}
		char[][] cavernChoice = Config.CAVES[caveNumber - 1];
		return cavernChoice;
	}

	/**
	 * Re-factoring bat encounter per instructions to move the user to a new
	 * random space that is not occupied by an obstacle
	 * @param randomNumGen  A random number generator.
	 * @param caveCurrent The cave being explored.
	 * @param playerLocation The current location of the player
	 */
	public static void batMove(Random randomNumGen, char[][] caveCurrent
			, int [] playerLocation) {
		do {
			playerLocation[Config.ROW] = 
					randomNumGen.nextInt(caveCurrent.length);
			playerLocation[Config.COL] = 
					randomNumGen.nextInt(caveCurrent[Config.ROW].length);
		} while (caveCurrent[playerLocation[Config.ROW]]
				[playerLocation[Config.COL]] != ' ');		    
		System.out.println("A huge bat picked you up and dropped you in "
				+ "another room...");
	}

	/**
	 * This is the class' main method to initiate the game and process user 
	 * choices of how to traverse and interact with the caves. 
	 * 
	 * The user first chooses which game mode they would like to play as 
	 * (hunt or rescue). Then they choose which cave they wish to enter from
	 * the config file and then chooses how they want to proceed through the
	 * given maze. They can encounter the wumpus, bats or pits (or nothing) and
	 * must handle each scenario. If they throw the given equipment at the 
	 * wumpus or child they win. Otherwise they loose to pits and bats will 
	 * take then randomly to a new space.
	 * 
	 * 
	 * @param args  unused
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Random randomNumber = new Random(Config.SEED);

		System.out.print("Would you like to go on a hunt "
				+ "or rescue a child (h/r): ");
		GameMode mode = GameMode.HUNT;
		if (scanner.nextLine().trim().toLowerCase().charAt(0) == 'r') {
			mode = GameMode.RESCUE;
		}
		//user input is only check for rescue, everything help turns into a hunt
		System.out.println("Thank you for coming to help us at Wumpus Caves.");
		if (mode == GameMode.RESCUE) {
			System.out.println("A child wandered into the "
					+ "cave and has not returned.");
			System.out.println("Please help us find our child!");
		} else {
			System.out.println("A Wumpus comes out of the cave at night and");
			System.out.println("attacks the villagers. Please hunt it down.");
		}
		System.out.println();

		// changed to Config.ROW because it is the first 'row' of the cave
		char[][] cavernChoice = Config.CAVES[Config.ROW];
		
		cavernChoice = initializeCave(scanner);
		System.out.println();
		System.out.print("Use your senses to find your way in the cave. ");
		System.out.println("Beware of the huge bats");
		System.out.println("and the bottomless pits. Good Luck!\n");
		System.out.println("You enter the cave...");

		int[] currPlayerLocation = { 0, 0 };

		/**
		 * This process the user's action, whether they want to move,
		 * attack/rescue, or get equipment
		 */
		while (status(randomNumber, cavernChoice, currPlayerLocation, mode)) {

			System.out.print("action: ");
			
			// basic directional movement
			// n for north, s for south, e for east, w for west
			String direction = scanner.nextLine().trim().toLowerCase();
			switch (direction) {
			case "n":
			case "s":
			case "e":
			case "w":
				move(cavernChoice, currPlayerLocation, direction);
				break;
			case "g":
				// attempt to win the game here
				if (mode == GameMode.RESCUE) {
					System.out.print("What direction to throw rope (nsew): ");
				} else {
					System.out.print("What direction to fire arrow (nsew): ");
				}

				String equipmentDirection = scanner.nextLine()
						.trim().toLowerCase();
				boolean success = false;
				if (mode == GameMode.RESCUE) {
					System.out.println("Rope flies " + equipmentDirection + "");
				} else {
					System.out.println("Arrow flies " 
							+ equipmentDirection + "");
				}
				// process where the equipment lands
				int[] moveWithEquip = Arrays.copyOf(currPlayerLocation, 
						currPlayerLocation.length);
				move(cavernChoice, moveWithEquip, equipmentDirection);

				// Final Scenario
				// user must face encounter with their equipment to win
				if (cavernChoice[moveWithEquip[Config.ROW]]
						[moveWithEquip[Config.COL]] == Config.END) {
					if (mode == GameMode.RESCUE) {
						System.out.println("Congratulations! The child grabbed "
								+ "the rope and you brought safely out of the "
								+ "cave!");
					} else {
						System.out.println(
								"Congratulations! You killed the Wumpus and "
										+ "saved the villagers from their "
										+ "nightly terror.");
					}
					success = true;
				}				

				if (success) {
					System.out.println("Hopefully, you can now find your way "
							+ "out of the cave....");
					return;
				}
				break;
			default:
				// prompt user when input is unknown
				if (mode == GameMode.RESCUE) {
					System.out.println("Move (nsew) or get rope (g).");
				} else {
					System.out.println("Move (nsew) or get arrow (g).");
				}
				break;
			}
		}
		System.out.println("Thanks for playing Wumpus Caves!");
		scanner.close();
	}
}
