import java.util.Arrays;
public class Coord {
    /**
     * The Coord's Row. Moreso for redundancy and to pull from when a method requires a row and column rather than the whole coord itself.
     */
    private char row;
    /**
     * The Coord's Column. Moreso for redundancy and to pull from when a method requires a row and column rather than the whole coord itself.
     */
    private int col;
    /**
     * Attribute which indicates if the coord is mined
     */
    private boolean mine;
    /**
     * Attribute which indicates if the coord is flagged
     */
    private boolean flagged;
    /**
     * All adjacent coordinates in string form (can be split into column and row for methods which require it)
     */
    private String[] adj;
    /**
     * Attribute which indicates if the coord is revealed
     */
    private boolean revealed;
    
    /**
     * @param row The row coord
     * @param col The column coord
     * @param mine Whether it's a mine or not. (Most likely not)
     * @param flagged Whether it's flagged (Most likely not)
     * @param adj An array of adjacent coords
     * @param revealed Whether it's revealed (Most likely not)
     */
    public Coord(char row, int col, boolean mine, boolean flagged, String[] adj, boolean revealed) {
        this.row = row;
        this.col = col;
        this.mine = mine;
        this.flagged = flagged;
        this.adj = adj;
        this.revealed = revealed;
    }
    public char getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public boolean isMine() {
        return mine;
    }
    public void setMine(boolean mine) {
        this.mine = mine;
    }
    public boolean isFlagged() {
        return flagged;
    }
    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }
    public String[] getAdj() {
        return adj;
    }
    public boolean isRevealed() {
        return revealed;
    }
    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }
    @Override
    public String toString() {
        return "Coord [row=" + row + ", col=" + col + ", mine=" + mine + ", flagged=" + flagged + ", adj=" + Arrays.toString(adj) + ", revealed=" + revealed + "]";
    }
    
}
