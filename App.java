public class App {
    public static void main(String[] args) {
        Board game = new Board();
        System.out.println(game);
        System.out.println(game.getBoard().get('A')[0]);
    }
}
