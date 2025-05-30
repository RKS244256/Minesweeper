import java.util.HashMap;
import java.util.Arrays;
public class Board {
    HashMap<Character, Coord[]> board;
    String rows = "ABCDEFGHIJKLMNOP";
    
    public Board(){
        board = new HashMap<Character, Coord[]>();
        for(int i = 0; i<16; i++){
            Coord[] nrow = new Coord[16];
            for(int ni = 0; ni<16; ni++){
                nrow[ni] = new Coord(rows.charAt(i), ni, false, false, adjacentArrayContructor(rows.charAt(i), ni), 0);
            }
            board.put(rows.charAt(i), nrow);
            // board.put(rows.charAt(i), new Coord[16]);
        }
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
