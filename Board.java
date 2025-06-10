import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
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
        while(p.size() < m){ // Checks whether the array size is m (ie, there are m mines) and continues to generate random coords to plant mines if not.
            int rr = (int) (Math.random()*16); // Picks a "random row"
            int rc = (int) (Math.random()*16); // Picks a "random column"
            if(!p.contains(rows.charAt(rr) + String.valueOf(rc))){ // Checks if mine already exists at the coord generated
                board.get(rows.charAt(rr))[rc].setMine(true); // Plants mine by setting coord's "mine" property to true
                p.add(rows.charAt(rr) + String.valueOf(rc)); // Adds coord to p to ensure mines aren't planted at this coord again
            }
        }
    }

    public int plantMinesFromFile(){
        int o = 0; // Integer output <- Returns number of mines planted from file.
        try{
            File f = new File("custom.txt"); //Can be any file but txts are the most recognized for notepad and allows for easier manual editing
            Scanner rf = new Scanner(f).useDelimiter(","); //Coords should be separated by commas
            while(rf.hasNext()){
                String wc = rf.next().toUpperCase();
                if(wc.matches("[A-P]([1-9]|1[0-6])")){ // Checks if a coord is valid through regex
                    board.get(wc.charAt(0))[Integer.parseInt(wc.substring(1))-1].setMine(true); // Plants a mine if true
                    o++; // Increments output to represent a new mine being added to the total mines on the board
                }
            }
            rf.close();
            setMines(o); // Sets the internal counter for mines to o
            
        } catch(FileNotFoundException e){ // Game won't error out and rather, sets the output to 0 which tells the game the file is either missing or invalid as it doesn't have any proper mine coordinates.
            o = 0;
        }
        return o;
    }
    
    public void displayBoard(){
        StringBuilder o = new StringBuilder(); // "Output" string that is to be displayed when answer key is requested
        o.append(Colors.cyan + " | 1| 2| 3| 4| 5| 6| 7| 8| 9|10|11|12|13|14|15|16|\n" + Colors.reset); // Display top row (columns). Hardcoded.
        for(char c : board.keySet()){ // Iterating through rows. Each key within the hashmap is stored as a "character"
            o.append(" |——|——|——|——|——|——|——|——|——|——|——|——|——|——|——|——|\n"); // Hardcoded row separators
            StringBuilder wsb = new StringBuilder(); // "Working StringBuilder" <- Used to contruct rows then appended to the "Output" StringBuilder
            wsb.append(Colors.cyan + c + Colors.reset + "|"); // Adds the row letter first
            for(Coord wCoord : board.get(c)){ // Iterates the "Working Coordinates" from the Coord array of each row in the "board" hashmap
                if(wCoord.isRevealed()){ // Checks if the coordinate had already been revealed by the player
                    if(adjacentMines(wCoord) == 0){ // Checks if the mine has 0 adjacent mines
                        wsb.append("  |"); // Appends empty box if so
                    } else{
                        wsb.append(Colors.yellow + " " + adjacentMines(wCoord) + Colors.reset + "|"); // Appends number of adjacent mines if not.
                    }
                } else if(wCoord.isFlagged()){ // Check if the coordinate is flagged if not revealed
                    wsb.append(Colors.red + " F" + Colors.reset + "|"); // If so, display a red 'F'
                } else{
                    wsb.append(" *|"); // If not revealed or flagged, still hidden so add a star to the box.
                }
            }
            wsb.append("\n"); // Append a new line to ensure board is displayed properly
            o.append(wsb); // Append the working StringBuilder into the overall output.
        }
        System.out.println(o); // Display game board
    }

    public void answerKey(){
        StringBuilder o = new StringBuilder(); // "Output" string that is to be displayed when answer key is requested
        o.append(Colors.cyan + " | 1| 2| 3| 4| 5| 6| 7| 8| 9|10|11|12|13|14|15|16|\n" + Colors.reset); // Display top row (columns). Hardcoded.
        for(char c : board.keySet()){ // Iterating through rows. Each key within the hashmap is stored as a "character"
            o.append(" |——|——|——|——|——|——|——|——|——|——|——|——|——|——|——|——|\n"); // Hardcoded row separators
            StringBuilder wsb = new StringBuilder(); // "Working StringBuilder" <- Used to contruct rows then appended to the "Output" StringBuilder
            wsb.append(Colors.cyan + c + Colors.reset + "|"); // Adds the row letter first
            for(Coord wCoord : board.get(c)){ // Iterates the "Working Coordinates" from the Coord array of each row in the "board" hashmap
                if(wCoord.isMine()){ // Displays a red "!" if the coordinate is a mine
                    wsb.append(Colors.red + " !" + Colors.reset + "|");
                } else{ // Displays the number adjacent mines if not.
                    wsb.append(" " + Colors.yellow + Integer.toString(adjacentMines(wCoord)) + Colors.reset + "|");
                }
            }
            wsb.append("\n"); // Append a new line to ensure board is displayed properly
            o.append(wsb); // Appends wsb to the output
        }
        System.out.println(o); // Prints the output to console (the game)
    }

    String[] adjacentArrayContructor(char r, int c){ // Constructs an array of coordinates stored as Strings after taking a coord in the form of a char "row" and int "column"
        int wr = rows.indexOf(r); // Converts the char row into a integer "Working Row"
        int i = 0; // Iterator to traverse the array below.
        String[] o = new String[8]; // Output array. It will be filled with whatever adjacent coordinates in string form. Any other slots are filled with null values.
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

    int adjacentMines(Coord wc){ // Returns the number of adjacent mines of the coord specified in the argument.
        int o = 0; // Integer output of the number of adjacent mines
        int i = 0; // Iterator, iterates through the String array below
        String[] adjC = wc.getAdj(); // Gets the coordinates in string form from the "adj" property of the coord
        while(adjC[i] != null){ // All arrays of the "adj" property are 8 strings in length. If a coord does not have 8 adjacent tiles, the array is filled with null values at the end. Hence, the loop will end if when iterating, the program reaches a null value, indicating a lack of further coords to test.
            if(board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isMine()){ // Using String coord, pulls the real coordinate object and checks whether it's a mine using the mine property.
                o++; // If so, increment the output.
            }
            i++;
            if(i>7){ // Prevents the iterator from going out of bounds.
                return o;
            }
        }
        return o;
    }

    public void revealAdjacentEmpties(Coord wc){// Updates the revealed property of adjacent tiles with 0 adjacent mines to true. Takes a Coord as the "Working coord"
        int i = 0;
        String[] adjC = wc.getAdj(); // Retrieves "Adjacent Coords" from "adj" property of the coord
        while(adjC[i] != null){ // All arrays of the "adj" property are 8 strings in length. If a coord does not have 8 adjacent tiles, the array is filled with null values at the end. Hence, the loop will end if when iterating, the program reaches a null value, indicating a lack of further coords to test.
            if(adjacentMines(board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))]) == 0 && !board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isRevealed() && !board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isFlagged() && !board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].isMine()){ // Checks whether specified coordinates are not mines, already revealed, or flagged AND has 0 adjacent mines.
                board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))].setRevealed(true); // Reveals the tile.
                revealSurroundings(board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))]); // Reveals the surrounding tiles.
                revealAdjacentEmpties(board.get(adjC[i].charAt(0))[Integer.parseInt(adjC[i].substring(1))]); // Recursive call to further reveal tiles with 0 adjacent mines.
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
