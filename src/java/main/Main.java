import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {
        PrintStream printStream = new PrintStream(System.out);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] boardStatus = {" ", " ", " ", " ", " ", " ", " ", " ", " "};

        Board board = new Board(printStream, boardStatus);
        Player playerOne = new Player(reader, printStream, 1, "X");
        Player playerTwo = new Player(reader, printStream, 2, "O");

        Game game = new Game(board, playerOne, playerTwo);
        game.playGame();
    }
}
