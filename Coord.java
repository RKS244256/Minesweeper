import java.util.Arrays;
public class Coord {
    char row;
    int col;
    boolean mine;
    boolean flagged;
    String[] adj;
    boolean revealed;
    
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
