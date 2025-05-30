import java.util.Scanner;

public class App {

    static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    public static void main(String[] args) {
        Board game = new Board();
        Scanner userIn = new Scanner(System.in);
        game.plantMines(40);
        while(true){
            if(game.isDeath()){
                System.out.println("You Lose");
                break;
            }
            if(game.getMines() == 0){
                System.out.println("You Win!");
                break;
            }
            game.displayBoard();
            System.out.println("Enter coordinates (Row & Column - eg. A1)");
            String in = userIn.nextLine().toUpperCase();
            if(in.length() < 2 || in.length()>4){
                System.out.println("Invalid Input - Input should be 2-4 Characters");
                userIn.nextLine();
                clearScreen();
                continue;
            }
            if(!game.rows.contains(String.valueOf(in.charAt(0))) || !in.substring(1).matches("[0-9]{1,}")){
                System.out.println("Invalid Input - Input must consist of a valid row and column");
                userIn.nextLine();
                clearScreen();
                continue;
            }
            if(Integer.parseInt(in.substring(1)) > 16 || Integer.parseInt(in.substring(1))<1){
                System.out.println("Invalid Input - Input must consist of a valid row and column");
                userIn.nextLine();
                clearScreen();
                continue;
            }
            char wrc = in.charAt(0);
            int wcc = Integer.parseInt(in.substring(1));
            System.out.println("Flag (F)/Reveal (R)/Back (B)?");
            in = userIn.nextLine().toUpperCase();
            if(in.length() != 1){
                System.out.println("Invalid Input - Input must be 1 character");
                userIn.nextLine();
                clearScreen();
                continue;
            }
            if(!"FRB".contains(in)){
                System.out.println("Invalid Input - Input must be \"F\", \"R\", or \"B\"");
                userIn.nextLine();
                clearScreen();
                continue;
            }
            if(in.equals("F")){
                game.getBoard().get(wrc)[wcc].setRevealed(true);
            }
            //Work on Updating and playing.
            clearScreen();
        }
        userIn.close();
    }
}
