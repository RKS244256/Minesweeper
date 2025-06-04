import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Board {
    HashMap<Character, Coord[]> board;
    boolean death;
    int mines;
    final String rows = "ABCDEFGHIJKLMNOP"; //Constant stating available rows.

    public Board(){
        board = new HashMap<Character, Coord[]>(); // Creates a Hashmap using characters A-P as rows with an array of Coord objects.
        death = false;// No one died yet therefore death = false
        mines = 0; // When creating the board, there aren't any mines yet. Therefore, mines = 0 until plantMines() or plantMinesFromFile() is called.
        for(int i = 0; i<16; i++){//Nested loop to populate the board with "empty" coords. 
        //Empty coords are defined as not revealed, not mined, and not flagged
            Coord[] nrow = new Coord[16]; // Creates a new array of Coord objects which serve as a row for the current column.
            for(int ni = 0; ni<16; ni++){
                nrow[ni] = new Coord(rows.charAt(i), ni, false, false, adjacentArrayContructor(rows.charAt(i), ni), false); // Populates row with empty coords.
            }
            board.put(rows.charAt(i), nrow); // Adds new key pair (Row, Empty Coord Array)
        }
    }

    public void plantMines(int m){ // Takes in a specified number of mines.
        ArrayList<String> p = new ArrayList<String>(); //An array of coords of "planted" mines in string form. Ensures the algorithm doesn't plant mines where it already has,
        mines = m;
        while(p.size() <= m){ // Checks whether the array size is m (ie, there are m mines) and continues to generate random coords to plant mines if not.
            int rr = (int) (Math.random()*16); // Picks a "random row"
            int rc = (int) (Math.random()*16); // Picks a "random column"
            if(!p.contains(rows.charAt(rr) + String.valueOf(rc))){ // Checks if mine already exists at the coord generated
                board.get(rows.charAt(rr))[rc].setMine(true); // Plants mine by setting coord's "mine" property to true
                p.add(rows.charAt(rr) + String.valueOf(rc)); // Adds coord to p to ensure mines aren't planted at this coord again
            }
        }
    }

    public int plantMinesFromFile(){
        int o = 0;
        try{
            File f = new File("custom.txt");
            Scanner rf = new Scanner(f).useDelimiter(",");
            while(rf.hasNext()){
                String wc = rf.next().toUpperCase();
                if(wc.matches("[A-P]([1-9]|1[0-6])")){
                    board.get(wc.charAt(0))[Integer.parseInt(wc.substring(1))-1].setMine(true);
                    o++;
                }
            }
            rf.close();
            mines = o;
            
        } catch(FileNotFoundException e){
            o = 0;
        }
        return o;
    }

    public void displayBoard(){
        StringBuilder o = new StringBuilder();
        o.append(Colors.cyan + " | 1| 2| 3| 4| 5| 6| 7| 8| 9|10|11|12|13|14|15|16|\n" + Colors.reset);
        for(char c : board.keySet()){
            o.append(" |——|——|——|——|——|——|——|——|——|——|——|——|——|——|——|——|\n");
            StringBuilder wsb = new StringBuilder();
            wsb.append(Colors.cyan + c + Colors.reset + "|");
            for(Coord wCoord : board.get(c)){
                if(wCoord.isRevealed()){
                    if(adjacentMines(wCoord) == 0){
                        wsb.append("  |");
                    } else{
                        wsb.append(Colors.yellow + " " + adjacentMines(wCoord) + Colors.reset + "|");
                    }
                } else if(wCoord.isFlagged()){
                    wsb.append(Colors.red + " F" + Colors.reset + "|");
                } else{
                    wsb.append(" *|");
                }
            }
            wsb.append("\n");
            o.append(wsb);
        }
        System.out.println(o);
    }

    public void answerKey(){
        StringBuilder o = new StringBuilder();
        o.append(Colors.cyan + " | 1| 2| 3| 4| 5| 6| 7| 8| 9|10|11|12|13|14|15|16|\n" + Colors.reset);
        for(char c : board.keySet()){
            o.append(" |——|——|——|——|——|——|——|——|——|——|——|——|——|——|——|——|\n");
            StringBuilder wsb = new StringBuilder();
            wsb.append(Colors.cyan + c + Colors.reset + "|");
            for(Coord wCoord : board.get(c)){
                if(wCoord.isMine()){
                    wsb.append(Colors.red + " !" + Colors.reset + "|");
                } else{
                    wsb.append(" " + Colors.yellow + Integer.toString(adjacentMines(wCoord)) + Colors.reset + "|");
                }
            }
            wsb.append("\n");
            o.append(wsb);
        }
        System.out.println(o);
    }

    String[] adjacentArrayContructor(char r, int c){//Rewrite possible
        int wr = rows.indexOf(r);
        int i = 0;
        String[] o = new String[8];
        if(wr-1 >= 0 && c-1 >= 0){
            o[i] = rows.charAt(wr-1) + String.valueOf(c-1);
            i++;
        }
        if(wr-1 >= 0){
            o[i] = rows.charAt(wr-1) + String.valueOf(c);
            i++;
        }
        if(wr-1 >= 0 && c+1 < 16){
            o[i] = rows.charAt(wr-1) + String.valueOf(c+1);
            i++;
        }
        if(c-1>=0){
            o[i] = rows.charAt(wr) + String.valueOf(c-1);
            i++;
        }
        if(c+1<16){
            o[i] = rows.charAt(wr) + String.valueOf(c+1);
            i++;
        }
        if(wr+1<16 && c-1>=0){
            o[i] = rows.charAt(wr+1) + String.valueOf(c-1);
            i++;
        }
        if(wr+1<16){
            o[i] = rows.charAt(wr+1) + String.valueOf(c);
            i++;
        }
        if(wr+1<16 && c+1<16){
            o[i] = rows.charAt(wr+1) + String.valueOf(c+1);
            i++;
        }
        return o;
    }

    int adjacentMines(Coord wc){
        int o = 0;
        int i = 0;
        String[] adjC = wc.getAdj();
        while(adjC[i] != null){
            if(board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isMine()){
                o++;
            }
            i++;
            if(i>7){
                return o;
            }
        }
        return o;
    }

    public void revealAdjacentEmpties(Coord wc){
        int i = 0;
        String[] adjC = wc.getAdj();
        while(adjC[i] != null){
            if(adjacentMines(board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))]) == 0 && !board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isRevealed() && !board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isFlagged() && !board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isMine()){
                board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].setRevealed(true);
                revealSurroundings(board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))]);
                revealAdjacentEmpties(board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))]);
            }
            i++;
            if(i>7){
                break;
            }
        }
    }

    public void revealSurroundings(Coord wc){
        int i = 0;
        String[] adjC = wc.getAdj();
        while(adjC[i] != null){
            if(adjacentMines(board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))]) != 0 && !board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isMine() && !board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isFlagged()){
                board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].setRevealed(true);
            }
            i++;
            if(i>7){
                break;
            }
        }
    }

    public HashMap<Character, Coord[]> getBoard() {
        return board;
    }

    public boolean isDeath() {
        return death;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(char s : board.keySet()){
            sb.append(s + ":" + Arrays.deepToString(board.get(s)) + "\n");
        }
        return String.valueOf(sb);
    }
    
}
