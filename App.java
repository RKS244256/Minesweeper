import java.util.Scanner;

public class App {
    static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    public static void main(String[] args) {
        Board game = new Board();
        Scanner userIn = new Scanner(System.in);
        int flaggedTiles = 0;
        int tMines = 0;
        game.plantMines(tMines);
        while(true){
            if(game.isDeath()){
                System.out.println("⠀⠀⠀⠀⠀⠀⢳⡀⠀⠀⠀⣴⣿⡿⠁⢀⣠⡶⢔⣃⣶⣬⠶⠖⠛⠉⠀⠀⠀⠀⠀⠐⠂⠤⣤⣴⣶⣶⢦⣼⣁⣤⣄⡀⠀⠈⢿⣷⡄⠀⣰⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⠺\n⠀⠀⣠⡤⠴⠚⠿⣇⠀⠀⣼⣟⣻⣵⣾⠿⠟⠛⠛⠛⠋⠉⠀⠀⠀⠀⠚⠛⠛⠛⠛⠛⠻⠿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣽⣶⣬⣻⣿⣶⣿⠃⠘⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⡶⠋⠁⠀⠀⠀⡟⠸⣀⣴⠿⠋⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠙⠻⠿⠿⣿⣿⣿⣿⣿⣥⡀⠀⢹⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠀⠀⠀⠀⠀⢸⡄⡿⠿⠿⠶⠶⠶⠶⠦⠤⣤⣤⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠛⠿⣿⣿⣷⣬⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⡇⠀⠀⠀⠀⠀⠈⣷⢹⣿⠻⠿⠶⣶⣦⣤⣤⣄⣈⣉⠉⠙⠛⠓⠶⠦⢤⣤⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠴⠶⠿⠿⠿⠿⠿⢧⣤⣀⣀⡀⠀⠀⠀⠀⠀\n⣿⠀⠀⠀⠀⠀⠀⢸⡆⣿⡆⢘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶⣦⣤⣤⣀⣉⠉⠛⠓⠲⠶⣤⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠛⠒⠂⠤⢤\n⡻⣇⠀⠀⠀⠀⠀⠀⣻⣾⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣶⣦⣤⣉⡉⠛⠳⠶⢦⣤⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠹⢿⡆⠀⠀⢀⡤⠚⢩⡈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣦⣤⣀⣉⡉⠙⠻⠶⠶⢦⣤⣀⣀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n⠀⠈⢿⡀⡰⠋⠀⠀⠘⣧⣻⣿⣿⣿⠿⠟⠛⠉⠋⢉⣿⠉⠉⣹⡏⢹⡇⢸⡟⠛⠿⠿⣿⣿⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣤⣤⣄⣉⣉⣉⣛⡛⠛⠂⠂⠀⠀⠀⠀\n⠀⠀⠜⣿⠃⠀⠀⠀⠀⢸⠋⣿⣽⠏⠀⠀⠀⠀⠀⣼⡏⠄⢀⣿⣥⣿⣀⡈⣷⠀⠀⠀⠀⠉⢹⡇⣸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣶\n⠀⠈⠘⠿⣇⠀⠀⠀⠀⠈⣇⢸⡿⠀⠀⠀⠀⠀⢀⣿⠇⢀⡾⣿⣵⡏⠈⠉⢻⣧⠀⠀⠀⠀⢸⡇⠀⠹⣿⣇⠹⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⠀⠀⠘⠹⣿⡀⠀⠀⠀⠀⢹⠈⣿⡄⠀⠀⠀⠀⠀⣿⢀⣿⡁⢹⣿⠃⠀⠀⠈⢿⡄⠀⣀⣀⡀⣿⠀⠀⢹⣿⠀⠙⣷⣾⠻⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿⣿⣿⣿⣿⣿⣿⣿\n⠀⠀⠀⠀⠸⣷⠀⠀⣀⠴⠚⡟⠻⡇⠀⠀⠀⠀⢰⣯⣼⠝⠀⠈⣿⠀⠀⠀⠀⠘⣿⣾⣿⢻⣿⣿⡇⠀⢀⣿⣶⣶⣜⣿⠀⠙⢿⡇⠀⠙⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⠀⠀⠘⣀⣽⣻⣦⠞⠁⠀⠀⢹⠀⣷⠀⠀⠀⠀⢺⣿⠃⠀⢀⣤⣿⣦⣤⣀⡐⠀⠀⠀⠀⠀⠙⠛⠃⢰⡿⣋⣥⣀⠙⠻⢷⣤⠘⣿⢀⠀⠀⢀⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⠀⠂⠈⠉⡩⠩⣷⡀⠀⠀⠀⠈⡆⢸⣧⠀⠀⠀⣽⠃⢠⣾⡿⠛⣩⣭⣭⡛⠇⠀⠀⠀⠀⠀⠀⠀⠀⠈⢠⣼⠿⣿⡆⠀⢸⠏⠀⣿⢠⠀⠀⠀⠀⣿⡇⠙⠻⣿⣿⣿⣿⣿⡿⣿⣿\n⠀⠀⠢⠂⡠⡀⢘⣧⠀⠀⠀⠀⢻⠀⣿⠀⠀⠀⣿⡇⢿⡏⠀⢠⣽⠋⢿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣧⣤⣿⡇⠀⠁⠀⢸⡿⡄⠀⠀⠀⠀⢸⡇⣰⣦⣬⣽⣿⡟⠋⠉⠉⠀\n⠀⠈⢈⡀⠁⢳⣄⣿⡆⠀⢀⣠⠴⡗⢻⡇⠀⠀⢹⣇⠀⠉⠀⠸⣷⣶⣾⣻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠙⠻⠛⠀⠀⠀⠀⢸⣿⣷⠀⠀⠀⠀⣸⡿⣯⡀⠀⠀⠀⠀⠀⠀⠀⠀\n⠤⣤⣠⣄⠀⣠⣼⣿⣷⡰⠋⠀⠀⢱⠀⣿⡀⠀⠘⣿⡄⠀⠀⠀⠈⠛⠿⠋⠀⠀⠀⠀⠀⠀⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⠀⠀⠀⡀⣿⡗⠈⢻⣿⣷⣶⣶⣤⣤⣤⣄\n⠀⠀⠀⠉⠉⠉⠛⢻⣿⣇⠀⠀⠀⠈⣦⢹⣧⠀⠀⢿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⣸⢫⣾⣿⠀⠀⠀⢀⣿⣧⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿\n⠀⠀⠀⠀⠀⠀⠀⢸⢿⣿⡀⠀⠀⠀⢻⢘⣿⣧⡀⠘⢿⣷⣤⣀⠀⠀⠀⠀⠀⠀⢀⣤⣶⣿⣿⣿⣿⣿⣶⡆⠀⠀⠀⠀⡴⠃⣸⣿⡇⠀⠀⡄⣾⣿⣿⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿\n⠀⠀⠀⠀⠀⠀⠀⠸⠀⢻⣧⣤⣴⠖⠋⠁⠀⠈⠙⢿⡾⣿⣿⣏⢳⡄⠀⠀⠀⠀⣿⡿⠟⠋⠉⠉⠉⠉⢻⡇⠀⠀⠀⠈⠁⣰⣿⣿⠀⠀⣸⣼⣿⣿⣿⡇⠀⠸⣿⣿⣿⣿⣿⣿⣿\n⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⡿⠿⣿⡀⠀⠀⣐⡀⠀⠀⠙⠸⣿⡇⢸⠿⡆⠀⠀⠀⢿⣀⣠⣀⣀⣀⣀⣤⣾⡇⠀⠀⠀⢀⣾⣿⣿⡏⢀⣼⣿⣿⣿⣿⣿⡇⠀⠀⣿⣿⣿⣿⣿⣿⣿\n⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠁⢀⣿⡷⢴⣛⣡⠽⠿⢇⣸⠀⣿⣿⡀⠀⢻⡆⠀⠀⠈⢿⡀⠀⠀⠀⠀⢀⡿⠀⠀⣀⣴⣿⠟⢻⡿⢡⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⣽⣿⣿⣿⣿⣿⣿\n⠀⠀⠀⠀⠀⠀⠀⢠⣿⡿⠀⠘⠓⠒⠛⠻⣧⠀⠀⠀⢸⣄⣿⣿⣿⣇⠀⢹⡳⠦⣀⡀⠙⠒⠒⠒⠚⠋⢀⡤⠞⣿⣿⠋⢠⣿⣷⣿⣿⡟⠿⢿⣿⣿⣿⣿⡀⠀⢸⣿⣿⣿⣷⣿⣿\n⠀⠀⠀⠀⠀⠀⠀⣼⣿⣧⠀⠀⠀⠀⠀⢀⣿⣄⣀⣄⣼⣿⣿⣌⢻⣿⣧⠀⢷⡀⠈⠉⠓⠲⠤⣤⠤⠚⠉⢀⣴⣿⠁⡶⠟⢉⣿⡏⣾⣇⡀⠀⠈⠉⠉⠙⠁⠀⢸⣿⣿⣿⣿⣿⣟\n⠀⠀⠀⠀⠀⠀⢰⣿⣿⣿⣯⣾⠿⠿⠟⠛⢉⣷⠀⠀⠀⢸⣿⣿⣿⣿⣿⣷⠘⣿⣷⣦⣄⣀⣀⠀⣀⣀⣼⣿⡿⠁⠀⠀⠀⢸⣿⢰⣿⣿⣿⣷⣦⣄⣀⠀⠀⠀⢸⡿⣿⣿⣿⣿⣿\n⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⡀⠀⠀⠀⣀⣰⣿⣀⡀⠤⠾⣿⣿⣮⠙⠻⠿⢷⣼⢿⣿⣿⣿⣿⣿⡿⣏⣿⣿⣷⡆⠀⠀⠀⢸⣿⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿\n⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⡄⠾⠿⠛⠛⢹⣧⠀⠀⠀⢿⣿⣿⣧⡀⠀⠈⢻⡄⠈⠛⠛⠿⠿⠟⠛⡋⣿⣿⠀⠀⠀⢦⣾⣿⡟⠹⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿\n⠀⠀⠀⠀⠀⠀⣿⣏⣿⣿⣿⣿⣷⡀⠀⣄⣀⣼⣿⣤⠴⠲⣿⣿⣿⣿⣧⡀⠀⢸⣿⡀⢦⡀⠀⠀⠀⠈⢹⣿⣿⡇⠀⠀⣾⣿⣿⡄⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⠀⠀⠀⠀⠀⢠⣿⡿⣿⣿⣿⣿⣿⣷⡀⣿⡏⠉⠐⠋⠑⡄⢾⣿⣿⣿⣿⣷⠀⢸⣿⣧⠀⠙⢦⠀⠀⠀⢸⣿⣿⡇⠀⢀⣿⢹⡏⢧⠀⠀⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⢻⣿⠃\n⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠸⣷⠀⠀⠀⠀⢿⡈⣿⣿⣿⣿⣿⣧⢸⣾⢿⣦⣀⠀⠀⠀⠀⢸⣿⣿⣧⠀⢸⣿⡆⢷⣼⣧⠀⠀⢻⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣯⠁⠀\n⠀⠀⢀⣼⣻⣿⣿⣿⣿⣿⣿⣼⣿⣽⣿⣿⣿⡄⢀⠴⠚⠙⣿⣿⣿⣿⣿⣿⣷⠈⣿⡎⣷⠈⠙⢧⠀⠀⢸⣿⣿⣿⠀⢸⡿⠶⠛⠷⠘⢧⠀⠀⠻⣿⣿⣿⣿⣿⣿⣿⣿⢿⠃⠀⣼\n⠀⣠⡾⢁⠀⣿⣿⣿⣿⣿⠈⠏⠈⢿⣿⣿⣿⣟⠁⠀⠀⠀⠙⠘⣿⠏⢸⢿⣿⡇⢸⣧⣼⡀⠀⠈⢳⡀⢸⣿⢿⣿⡆⢸⣧⠀⠀⠀⠀⢈⢷⡄⠀⣻⣿⣿⣿⣿⣿⣿⡿⠁⠀⣼⣿");
                System.out.println("\n▄███▄      ▄  █ ▄▄  █    ████▄    ▄▄▄▄▄   ▄█ ████▄    ▄     ▄ ▄ ▄ \n█▀   ▀ ▀▄   █ █   █ █    █   █   █     ▀▄ ██ █   █     █   █ █ █  \n██▄▄     █ ▀  █▀▀▀  █    █   █ ▄  ▀▀▀▀▄   ██ █   █ ██   █ █ █ █   \n█▄   ▄▀ ▄ █   █     ███▄ ▀████  ▀▄▄▄▄▀    ▐█ ▀████ █ █  █ █ █ █   \n▀███▀  █   ▀▄  █        ▀                  ▐       █  █ █         \n        ▀       ▀                                  █   ██ ▀ ▀ ▀   \n                                                                  \n▀▄    ▄ ████▄   ▄       █    ████▄    ▄▄▄▄▄   ▄███▄               \n  █  █  █   █    █      █    █   █   █     ▀▄ █▀   ▀              \n   ▀█   █   █ █   █     █    █   █ ▄  ▀▀▀▀▄   ██▄▄                \n   █    ▀████ █   █     ███▄ ▀████  ▀▄▄▄▄▀    █▄   ▄▀             \n ▄▀           █▄ ▄█         ▀                 ▀███▀               \n               ▀▀▀                                                \n                                                                  \n");
                userIn.nextLine();
                clearScreen();
                System.out.println("Answer Key:");
                game.answerKey();
                userIn.nextLine();
                break;
            }
            if(game.getMines() == 0){
                System.out.println("⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢋⡥⠀⢿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⣾⡇⡆⢸\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢻⣿⡏⢰⣿⢷⠃⠀\n⣿⣿⣿⣿⢟⡍⠥⣭⣍⠻⡿⢋⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⠻⣿⣿⣿⣏⠁⢾⡇⠘⠀⠀\n⣿⣿⣿⣟⠷⣶⢿⣾⣿⣧⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡙⣿⣿⣧⡆⠘⣧⠀⠀⣤\n⣿⣿⣿⣿⣦⣙⣋⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠐⠀⠹⠻⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡜⣿⡋⠅⠀⢻⡄⠀⢻\n⣿⣿⣿⣿⣿⣿⣿⣾⣿⣿⣿⣿⣿⣿⣿⣿⡇⣿⣿⣿⣿⣿⣿⠀⢩⣐⠐⢀⡬⠉⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡘⣿⣆⠀⠈⣧⠀⢸\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡧⠸⢿⣿⣿⣿⣿⡆⢢⣤⣤⣤⣴⣷⣧⣄⠙⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⡉⢿⣆⠀⢻⠀⠀\n⣿⡟⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠄⠀⠘⣿⣿⣿⣿⡇⢺⣿⣿⣿⣿⣿⣿⣿⣷⣄⡈⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⢰⠈⣿⡄⠸⡆⠀\n⣿⣿⠂⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡅⠀⢠⣶⡌⢻⣿⣿⣿⠸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣤⣀⠙⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠸⠀⢘⠣⠀⠿⠀\n⣿⣿⢃⣾⣿⣿⡿⣿⣿⣿⣿⣿⣿⡟⣿⡁⢀⣾⣿⣿⣦⡙⠻⣿⣇⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⡙⠿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠐⢸⡄⠈⠀\n⣿⣿⣿⠏⠻⡟⠡⠌⢻⣿⣿⣿⣿⡇⢻⡇⢸⣿⣿⣿⣿⣿⣦⢈⠙⠌⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠟⠃⠀⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⡼⢰⠀⢧\n⣿⣿⣿⣘⢓⣀⣩⡥⢚⣉⡛⠿⣿⡇⠘⡇⡼⢿⣿⣿⣿⣿⣿⣿⣷⣦⣼⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⣀⣤⣴⣶⣶⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⢃⣾⡄⣼\n⣿⣿⣿⣿⣿⣿⣿⠠⣡⡸⢠⡄⠸⡇⠀⢠⣃⡀⠉⠙⠻⠿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠋⠀⢠⣴⣶⣿⣿⣿⣿⣿⣿⡆⣾⡟⢿⣿⣿⣿⣿⣿⣿⣟⠂⠀⠀⠀⠀⢀⣿⣷⣿\n⣿⣿⣿⣿⣿⣿⣿⣷⣝⠳⠆⠐⣰⣷⠀⠸⣿⣿⣷⣦⡤⠀⢀⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣄⡀⠉⠛⠿⢿⣿⣿⡿⢠⣿⡝⣌⢿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠈⠛⠻⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⣿⣿⡿⠋⣀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠦⣤⣀⠈⢙⠃⣾⣿⣷⣼⡆⢻⣿⣿⣿⡇⠀⠀⢰⣦⠀⢠⣤⠀⠀⣿\n⣿⣿⣿⣿⣿⣿⣿⠛⡙⢿⣿⣿⣿⣿⡧⠀⡿⢁⠔⣾⣿⣿⣿⣿⣿⠿⠿⣿⣿⡿⠟⢻⣿⣿⣿⣿⣷⣍⣛⡻⠂⣸⣿⣿⣿⠃⠀⠈⢿⡿⢋⠤⠒⢀⠀⠀⠀⠈⡁⠀⣸⣿\n⣿⣿⣿⣿⣿⣿⣿⣦⣰⣾⢻⣿⡿⠻⣿⠀⠡⣡⣾⣯⣿⢠⣶⣴⣶⣾⣷⣶⣶⣶⣿⡇⣿⣿⣿⣿⣿⣿⣿⠋⣼⣿⣿⣿⠋⠀⠀⠀⠀⠁⡏⠀⠀⢁⣴⣶⣟⠔⠀⠁⣿⣿\n⣿⣿⣿⡿⠟⣿⣿⣿⣿⣿⣦⠟⣀⠙⠒⣠⡸⣿⣿⣿⣿⡌⢿⣿⣿⣿⣿⣿⣿⣿⣿⡇⣽⣿⣿⣿⣿⡿⢃⣾⣿⣿⡿⢋⣴⠀⠀⢀⣼⡀⣇⢠⣾⣿⣿⣿⣿⣷⣾⣷⣦⠙\n⣿⡟⣡⠞⣉⠠⠀⡀⠉⠋⠿⢦⡘⢧⣠⠠⠄⢻⣿⣿⣿⣿⣌⠻⣿⣿⣿⡿⢋⡙⠙⣰⣿⣿⣿⣿⣿⡃⠛⢛⣋⣡⣴⣿⣿⠗⣀⢿⠃⠁⠘⢦⡉⠛⠻⣿⣿⣿⡿⠏⣡⣼\n⣿⡶⠃⡜⠹⣧⡘⠈⠂⠄⠆⠀⠙⢷⣌⣌⠂⠀⠈⠻⠟⠿⠿⠷⣦⣭⣭⢑⠃⠙⡁⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠸⠟⠈⠀⠀⠀⠀⠙⠦⣄⣀⣀⡀⠀⣰⣿⣿\n⣿⠁⠀⠀⠓⠈⢿⠀⠈⠰⢄⢀⠀⠀⠻⣷⠀⠀⠀⢀⠀⠀⠀⠀⠠⠩⣉⡈⠛⠛⠁⠈⠉⢉⣩⣭⣬⣭⠙⣿⣿⠟⠁⠀⠡⠀⠀⠀⠀⠀⠀⣆⠶⠶⠀⢀⣥⣴⣿⣿⣿⣿\n⡇⠂⠀⠀⠀⠀⠀⢠⠀⠰⠀⠠⠁⠀⠀⠙⠧⠀⠈⠉⠀⠀⠀⢀⣷⡀⠀⣈⣉⠀⠀⠀⣶⣶⠶⠤⠤⠍⠘⠋⠁⠀⣠⠆⠀⠀⠀⠀⠠⠖⠋⡉⢥⣶⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣷⣄⣀⠐⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠴⣿⡿⢀⣼⣿⡿⠷⠀⡰⢋⢡⣶⣿⡇⢏⣵⣶⣴⡄⠀⠀⠀⠀⠀⠊⣀⣤⣄⠰⠆⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣷⣶⣶⣦⣢⣤⣤⣀⠀⠀⠀⠀⡜⣰⣿⣿⡶⢈⠄⣼⣿⣿⠀⣵⣾⣞⣛⡻⢿⣿⠀⣾⣿⣿⣿⣷⣄⠀⠀⠀⢠⣶⣯⣍⣙⣁⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣄⡘⠰⡿⣿⡿⠃⢃⣾⣿⣿⡟⢸⣑⡀⡈⠉⠙⢠⣿⢘⣿⢿⣿⣿⣿⡿⠿⠟⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣌⠛⠀⢣⣿⣿⣿⣿⠇⣼⣷⢗⣹⣿⣶⣿⣿⠀⢿⡌⢿⣿⣟⠁⣀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠁⠂⣻⣿⣿⣿⡿⢀⣿⣿⣿⣿⣿⣿⣿⣿⣷⠈⢷⣄⠀⣠⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⡟⠁⣠⣾⣿⣿⣿⣿⠁⣸⣿⣿⣿⣿⣿⣿⣿⣿⣷⣂⢀⣀⡠⢾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⡿⣻⣿⣿⡿⠛⣊⡀⠀⢐⣶⣿⣿⣿⣿⣿⣿⠇⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⡟⢀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣧⡙⢋⡠⠂⣂⠬⣩⠍⡙⢿⣿⠿⠛⠉⠁⠀⢀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⡟⠉⣰⢠⣶⣿⡁⢘⣥⠾⠋⢀⠁⠀⠀⢀⠀⣷⣉⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣇⠡⠡⡸⠿⣋⠲⠛⣉⡤⠚⠀⠀⠀⡀⠨⣶⡸⣿⣝⡿⠿⠟⣛⣋⣭⣥⣤⣤⣍⣙⠛⠿⡌⢽⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿\n⣿⣿⣿⣿⣿⣿⣿⣿⣷⣌⡓⠘⠙⠷⠋⠁⠀⠀⣠⠄⡀⠁⠀⠸⢵⠬⠴⠒⠻⠋⠙⠁⠉⢁⠈⠀⠀⠉⠭⠵⢨⣉⣘⣩⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
                System.out.println("\n8    8                8   8  8          8  \n8    8 eeeee e   e    8   8  8 e  eeeee 88 \n8eeee8 8  88 8   8    8e  8  8 8  8   8 88 \n  88   8   8 8e  8    88  8  8 8e 8e  8 88 \n  88   8   8 88  8    88  8  8 88 88  8    \n  88   8eee8 88ee8    88ee8ee8 88 88  8 88 \n                                           \n");
                userIn.nextLine();
                clearScreen();
                game.displayBoard();
                userIn.nextLine();
                break;
            }
            game.answerKey();
            game.displayBoard();
            System.out.println(flaggedTiles + "/" + tMines);
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
            int wcc = Integer.parseInt(in.substring(1)) - 1;
            System.out.println("Flag (F)/ Unflag (U)/ Reveal (R)/Back (B)?");
            in = userIn.nextLine().toUpperCase();
            if(in.length() != 1){
                System.out.println("Invalid Input - Input must be 1 character");
                userIn.nextLine();
                clearScreen();
                continue;
            }
            if(!"FURB".contains(in)){
                System.out.println("Invalid Input - Input must be \"F\", \"U\", \"R\", or \"B\"");
                userIn.nextLine();
                clearScreen();
                continue;
            }
            if(in.equals("F")){
                if(game.getBoard().get(wrc)[wcc].isRevealed()){
                    System.out.println("Invalid Input - Tile already revealed");
                    userIn.nextLine();
                    clearScreen();
                    continue;
                }
                if(game.getBoard().get(wrc)[wcc].isFlagged()){
                    System.out.println("Invalid Input - Tile is already flagged");
                    userIn.nextLine();
                    clearScreen();
                    continue;
                }
                if(flaggedTiles == tMines){
                    System.out.println("Invalid Input - Maximum flags planted");
                    userIn.nextLine();
                    clearScreen();
                    continue;
                }
                game.getBoard().get(wrc)[wcc].setFlagged(true);
                flaggedTiles++;
                if(game.getBoard().get(wrc)[wcc].isMine()){
                    game.setMines(game.getMines() - 1);
                }
            } else if(in.equals("U")){
                if(!game.getBoard().get(wrc)[wcc].isFlagged()){
                    System.out.println("Invalid Input - Tile already unflagged");
                    userIn.nextLine();
                    clearScreen();
                    continue;
                }
                game.getBoard().get(wrc)[wcc].setFlagged(false);
                flaggedTiles--;
                if(game.getBoard().get(wrc)[wcc].isMine()){
                    game.setMines(game.getMines() + 1);
                }
            } else if(in.equals("R")){
                if(game.getBoard().get(wrc)[wcc].isFlagged()){
                    System.out.println("Invalid Input - Tile is flagged");
                    userIn.nextLine();
                    clearScreen();
                    continue;
                }
                if(game.getBoard().get(wrc)[wcc].isRevealed()){
                    System.out.println("Invalid Input - Tile already revealed");
                    userIn.nextLine();
                    clearScreen();
                    continue;
                }
                game.getBoard().get(wrc)[wcc].setRevealed(true);
                if(game.getBoard().get(wrc)[wcc].isMine()){
                    game.setDeath(true);
                    continue;
                }
                game.revealAdjacentEmpties(game.getBoard().get(wrc)[wcc]);
            } else{}
            clearScreen();
        }
        userIn.close();
    }
}
