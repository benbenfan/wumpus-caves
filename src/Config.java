///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           Config.java configuration file for wumpus game
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
/**
 * This class provides the configurations for the wumpus caves games
 * and creates the base board and rng experienced in the game
 * @author Jim Williams
 * @author Benjamin Fan
 *
 */
public class Config {

	/**
	 * A set of caves to search through. Most of the caves are empty, just like
	 * my life right now. These provide the caves for the users to explore 
	 * in the wumpuscaves files
	 */
	public static final char[][][] CAVES= new char[][][] {
		{ 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', Config.BAT, ' ', Config.PIT, ' ' }, 
			{ ' ', Config.PIT, Config.END, ' ', ' ' },
			{ ' ', ' ', Config.BAT, ' ', ' ' }, 
			{ ' ', ' ', ' ', Config.BAT, Config.PIT } 
		},
		{ 
			{ ' ', Config.BAT, ' ', ' ', ' ' , ' ', ' ', ' ', Config.BAT
				, ' ' , ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', Config.BAT, ' ', Config.PIT, ' ' , ' ', ' ', ' ', ' '
					, ' ' , ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', Config.PIT, ' ', ' ', ' ' , ' ', Config.BAT, ' ', ' '
						, ' ' , ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', Config.BAT, ' ', ' ' , ' ', ' ', ' ', ' '
							, ' ' , ' ', ' ', Config.BAT, ' ', ' ' }, 
			{ ' ', ' ', ' ', Config.BAT, Config.PIT , Config.END, ' ', ' ', ' '
								, ' ' , ' ', ' ', ' ', ' ', ' ' } 
		},{ 
			{ ' ', ' ', ' ', ' ', Config.BAT },
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', Config.BAT, ' ', Config.BAT, ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', Config.BAT }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', Config.BAT, ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', Config.BAT, Config.BAT, Config.PIT, ' ' }, 
			{ ' ', Config.PIT, Config.END, Config.PIT, ' ' },
			{ ' ', ' ', Config.BAT, ' ', ' ' }, 
			{ ' ', ' ', ' ', Config.BAT, Config.PIT } 
		},{ 
			{ ' ', ' ', ' ', ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', Config.BAT, ' ', Config.PIT, ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', Config.PIT, Config.END, ' ', ' ' },
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', ' ', ' ', ' ' }, 
			{ ' ', ' ', Config.BAT, ' ', ' ' }, 
			{ ' ', ' ', ' ', Config.BAT, Config.PIT } 
		}
		
	};

	/**
	 * The methods in WumpusCaves should refer to these constants,
	 * e.g., Config.PIT, and not the literals themselves.
	 */
	public static final char PIT = 'p';
	public static final char BAT = 'b';
	public static final char END = 'w';

	/**
	 * The number of perceptions and the indexes in a perceptions
	 * array.
	 */
	// numb of perceptions
	public static final int NUM_PERC = 3;
	// index value
	public static final int PERCIEVE_PIT = 0;

	/**
	 * indices for location arrays
	 */
	// changed from r to row and c to col because i think it's enough
	// for someone to knwo what index they are dealing with
	public static final int ROW = 0;
	public static final int COL = 1;

	/**
	 * Random number generator SEED. Passed to the random number generator
	 * to get repeatable random numbers which can aid with debugging.
	 */
	public static final int SEED = 123;
}
