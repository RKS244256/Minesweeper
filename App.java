public class App {
    public static void main(String[] args) {
        Board game = new Board(0);
        System.out.println(game.getBoard().get('A')[0]);
        game.getBoard().get('A')[1].setRevealed(true);
        game.getBoard().get('A')[2].setFlagged(true);
        game.getBoard().get('C')[11].setMine(true);
        game.getBoard().get('C')[9].setMine(true);
        game.displayBoard();
        game.answerKey();
        System.out.println(game.adjacentMines(game.getBoard().get('B')[12]));
    }
}
