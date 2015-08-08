import java.io.BufferedReader;
import java.io.PrintStream;

public class Game {
    private Board board;
    private Player p1;
    private Player p2;

    public Game(Board board, Player player, Player player2) {
        this.board = board;
        this.p1 = player;
        this.p2 = player2;
    }

    public void playGame() {
        board.drawBoard();

        Player currentPlayer = p1;

        Integer square = p1.getUserInput();
        board.move(square, p1.getSymbol()); //is passing two player params to board the best approach?
        board.drawBoard();
        toggleCurrentPlayer(currentPlayer);

        square = p2.getUserInput();
        board.move(square, p2.getSymbol()); //is passing two player params to board the best approach?
        board.drawBoard();
    }

    private Player toggleCurrentPlayer(Player current) {
        return (current.equals(p1)) ? p2 : p1;
    }
}
