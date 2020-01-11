///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           TestWumpusCaves.java test cases for wumpuscaves
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
// All my files are under 80 COLUMNS WIDE //////////////////////////////////////


import java.util.Random;

/**
 * This class provides the configurations for the wumpus caves games
 * and creates the base randomness experienced in the game
 * @author Jim Williams
 * @author Benjamin Fan
 *
 */
public class TestWumpusCaves {

	/**
	 * Uncomment testing methods to have them run.
	 * @param args unused
	 */
	public static void main(String[] args) {
		testMove();
		testBats();
		testEnd();
	}

	/**
	 *  This method tests the move method.
	 * 1. Check whether the bat will move you to a new square.
	 * 2. Check whether not seeing a bat will keep you at your square.
	 */
	private static void testBats() {
		boolean error = false;
		char[][] cave = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
		

		{   //test 1 with a batMove directly
			
			int[] location = {0, 1};
			int[] compareLocations = location;
			Random randomNumber = new Random(Config.SEED);
			cave [0][0] = ' ';
			cave [0][1] = Config.BAT;
			WumpusCaves.batMove(randomNumber, cave, location);
			if ( !(location[0] == compareLocations[0] && location[1] 
					== compareLocations[1])) {
				System.out.println("testBats 1: location expected new location"
						+ "execpt 0,0; actual "
						+ location[0] + "," + location[1]);
				error = true;
			}
		}
		{   //test 2 with no bat via status calls
			int[] location = {0, 0};
			Random randomNumber = new Random(Config.SEED);
			cave [0][1] = ' ';
			WumpusCaves.GameMode mode = WumpusCaves.GameMode.HUNT;
			boolean affected = WumpusCaves.status(randomNumber, cave, 
					location, mode);
			if ( !(location[0] == 0 && location[1] == 0) && affected) {
				System.out.println("testBats 2: location expected 0,0; actual "
						+ location[0] + "," + location[1]);
				error = true;
			}
		}
		if ( error) {
			System.out.println("Error in testBats.");
		} else {
			System.out.println("All tests in testBats passed.");
		}
	}
	/**
	 *  This method tests if the game will end properly.
	 * 1. Check whether the game ends if the user encounters the child.
	 * 2. Check whether the games ends if the user encounter a wumpus.
	 * 3. Check if the game continues when neither a child or wumpus are present
	 */
	private static void testEnd() {
		boolean error = false;
		char[][] cave = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
		

		{   //test 1 with a child
			int[] location = {0, 1};
			Random randomNumber = new Random(Config.SEED);
			cave [0][1] = Config.END;
			WumpusCaves.GameMode mode = WumpusCaves.GameMode.RESCUE;
			boolean affected = WumpusCaves.status(randomNumber, cave,
					location, mode);
			if (affected) {
				System.out.println("testEnd 1: status expected false; actual "
						+ affected);
				error = true;
			}
		}
		{   //test 2 with a wumpus
			int[] location = {0, 1};
			Random randomNumber = new Random(Config.SEED);
			cave [0][1] = 'w';
			WumpusCaves.GameMode mode = WumpusCaves.GameMode.HUNT;
			boolean affected = WumpusCaves.status(randomNumber, cave, 
					location, mode);
			if ((affected)) {
				System.out.println("testEnd 2: status expected false; actual "
						+ affected);
				error = true;
			}
		}
		{   //test 3 with nothing
			int[] location = {0, 0};
			Random randomNumber = new Random(Config.SEED);
			cave [0][1] = ' ';
			WumpusCaves.GameMode mode = WumpusCaves.GameMode.HUNT;
			boolean affected = WumpusCaves.status(randomNumber, cave,
					location, mode);
			if (!affected) {
				System.out.println("testEnd 3: status expected true; actual "
						+ affected);
				error = true;
			}
		}
		if ( error) {
			System.out.println("Error in testEnd.");
		} else {
			System.out.println("All tests in testEnd passed.");
		}
	}
	/**
	 * This method tests the move method.
	 * 1. Check whether the move north from 1,1 works correctly.
	 * 2. Check whether the move west from 1,1 works correctly.
	 * 3. Check whether the move east from 1,1 works correctly.
	 * 4. Check whether the move south from 1,1 works correctly.
	 *
	 */
	private static void testMove() {
		boolean error = false;

		{   //test 1
			int[] location = {1, 1};
			char[][] cave = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

			WumpusCaves.move(cave, location, "n");
			if ( !(location[0] == 0 && location[1] == 1)) {
				System.out.println("testMove 1: location expected 0,1; actual "
						+ location[0] + "," + location[1]);
				error = true;
			}
		}

		{   //test 2
			int[] location = {1, 1};
			char[][] cave = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

			WumpusCaves.move(cave, location, "w");
			if (!(location[0] == 1 && location[1] == 0)) {
				System.out.println("testMove 2: location expected 1,0; actual " 
						+ location[0] + "," + location[1]);
				error = true;
			}
		}
		{   //test 3
			int[] location = {1, 1};
			char[][] cave = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

			WumpusCaves.move(cave, location, "e");
			if (!(location[0] == 1 && location[1] == 2)) {
				System.out.println("testMove 3: location expected 1,2; actual " 
						+ location[0] + "," + location[1]);
				error = true;
			}
		}
		{   //test 4
			int[] location = {1, 1};
			char[][] cave = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};

			WumpusCaves.move(cave, location, "s");
			if (!(location[0] == 2 && location[1] == 1)) {
				System.out.println("testMove 4: location expected 2,1; actual " 
						+ location[0] + "," + location[1]);
				error = true;
			}
		}


		if ( error) {
			System.out.println("Error in testMove.");
		} else {
			System.out.println("All tests in testMove passed.");
		}
	}
}
