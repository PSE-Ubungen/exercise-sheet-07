package de.unistuttgart.iste.sqa.pse.sheet07.homework.painter;

import de.hamstersimulator.objectsfirst.external.simple.game.SimpleHamsterGame;

/**
 * Describe the purpose of this class here.
 *
 * @author Levin Kohler
 * @version 1.0
 */
public class PainterPauleHamsterGame extends SimpleHamsterGame {

	/**
	 * Creates a new PainterPauleHamsterGame.<br>
	 * Do not modify!
	 */
	public PainterPauleHamsterGame() {
		this.loadTerritoryFromResourceFile("/territories/PainterPauleTerritory.ter");
		this.displayInNewGameWindow();
		game.startGame();
	}

	/**
	 * Put the hamster code into this method.
	 */
	@Override
	protected void run() {
		algOne();
	}

	/*@
	 @ requires paule != Null
	 @ ensures paule.mouthEmpty()
	 @*/
	/**
	 * An Algorithm to draw an Spiral of grains in a rectangular Territory.
	 */
	private void algOne(){
		int distanceNextLine = getDistanceToWall()-1;
		int lineCounter = 0;
		
		/*@
		 @ loop_invariant paule walkes and puts grains
		 @ unil his mouth is empty.
		 @*/
		while (!paule.mouthEmpty()) {

			/*@
			 @ loop_invariant paule walkes a given distance 
			 @ and puts a grain on every tile.
			 @*/
			for (int i = 0; i < distanceNextLine; i++) {
				paule.putGrain();
				paule.move();
			}

			paule.turnLeft();
			paule.turnLeft();
			paule.turnLeft();

			lineCounter = lineCounter + 1;

			if (lineCounter > 2 && (lineCounter % 2) == 1) {
				distanceNextLine = distanceNextLine - 2;
			}
		}
	}

	////////////////////////////////////////
	// helpers to ascertain preconditions //
	////////////////////////////////////////

	/**
	 * Calculate paules distance to the next wall.
	 *
	 * (Only Works, if the territory is quadratic and has no inner walls)
	 *
	 * @return paule's distance to the next wall.
	 */
	private int getDistanceToWall() {
		int size = game.getTerritory().getTerritorySize().getColumnCount();
		switch (paule.getDirection()) {
			case NORTH: {
				return paule.getLocation().getRow();
			}
			case EAST: {
				return size - paule.getLocation().getColumn();
			}
			case SOUTH: {
				return size - paule.getLocation().getRow();
			}
			case WEST: {
				return paule.getLocation().getColumn();
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + paule.getDirection());
		}
	}

	/**
	 * Calculate the number of grains paule currently hold in his mouth.
	 *
	 * @return number of grains currently in paule's mouth.
	 */
	private int getPaulesGrainCount() {
		int grainCounter = 0;
		/*@
		@ loop_invariant paule put a grain for each already executed loop iteration.
		@ decreasing grains remaining in paules mouth.
		@*/
		while (!paule.mouthEmpty()) {
			paule.putGrain();
			grainCounter++;
		}
		/*@
		@ loop_invariant paule picked a grain for each already executed loop iteration.
		@ decreasing grains remaining on the tile.
		@*/
		while (paule.grainAvailable()) {
			paule.pickGrain();
		}
		return grainCounter;
	}

	/**
	 * Calculate the number of grains required to paint the desired spiral on the current territory.
	 *
	 * @return number of grains required to paint the desired spiral on the current territory.
	 */
	private int getNumbeOfRequiredGrains() {
		final int territorySize = game.getTerritory().getTerritorySize().getColumnCount();
		int lineSize = territorySize - 3;

		int total = lineSize;
		/*@
		@ loop_invariant total is the sum of the previous total plus two times the current linesize + 2
		@ decreasing lineSize
		@*/
		while (lineSize > 0) {
			total += 2 * lineSize;
			lineSize -= 2;
		}
		return total;
	}
}
