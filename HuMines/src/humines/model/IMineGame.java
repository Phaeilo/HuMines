package humines.model;

/**
 * This interface specifies the functionality provided by the HuMines model.
 * 
 * @author Philip Huppert
 */
public interface IMineGame {

    /**
     * Retrieve the currently visible field as an array of integers. The fields
     * are ordered from left to right and from top to bottom.
     * 
     * @return the currently visible field.
     */
    public EField[] getField();

    /**
     * @return the width of the minefield.
     */
    public int getWidth();

    /**
     * @return the height of the minefield.
     */
    public int getHeight();

    /**
     * @return the number of mines on the minefield.
     */
    public int getNumMines();

    /**
     * @return whether the game ended.
     */
    public boolean isGameOver();

    /**
     * @return whether the game was won.
     */
    public boolean isGameWon();

    /**
     * @return the number of seconds that elapsed since the start of the game.
     *         When the game is over, the clock stops.
     */
    public int getSecondsElapsed();

    /**
     * Uncover a field on the minefield.
     * 
     * @param x
     *            coordinate of the field.
     * @param y
     *            coordinate of the field.
     * @throws IllegalArgumentException
     *             if the specified coordinates are not on the field.
     */
    public void uncover(int x, int y);
}
