import java.util.HashMap;
import java.util.Arrays;
public class Board {
    HashMap<Character, Coord[]> board;
    String rows = "ABCDEFGHIJKLMNOP";
    
    public Board(int b){
        board = new HashMap<Character, Coord[]>();
        for(int i = 0; i<16; i++){
            Coord[] nrow = new Coord[16];
            for(int ni = 0; ni<16; ni++){
                nrow[ni] = new Coord(rows.charAt(i), ni, false, false, adjacentArrayContructor(rows.charAt(i), ni), false);
            }
            board.put(rows.charAt(i), nrow);
            // board.put(rows.charAt(i), new Coord[16]);
        }
    }

    public void displayBoard(){
        StringBuilder o = new StringBuilder();
        o.append(" | 1| 2| 3| 4| 5| 6| 7| 8| 9|10|11|12|13|14|15|16|\n");
        for(char c : board.keySet()){
            StringBuilder wsb = new StringBuilder();
            wsb.append(c + "|");
            for(Coord wCoord : board.get(c)){
                if(wCoord.isRevealed()){
                    wsb.append(" " + adjacentMines(wCoord) + "|");
                } else if(wCoord.isFlagged()){
                    wsb.append(" F|");
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
        o.append(" | 1| 2| 3| 4| 5| 6| 7| 8| 9|10|11|12|13|14|15|16|\n");
        for(char c : board.keySet()){
            StringBuilder wsb = new StringBuilder();
            wsb.append(c + "|");
            for(Coord wCoord : board.get(c)){
                if(wCoord.isMine()){
                    wsb.append(" !|");
                } else{
                    wsb.append(" " + Integer.toString(adjacentMines(wCoord)) + "|");
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

    public HashMap<Character, Coord[]> getBoard() {
        return board;
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
