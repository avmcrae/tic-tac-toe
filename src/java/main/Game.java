import java.io.PrintStream;

public class Game {
    private Board board;
    private Player p1;
    private Player p2;
    private PrintStream printStream;

    public Game(Board board, Player player, Player player2, PrintStream printStream) {
        this.board = board;
        this.p1 = player;
        this.p2 = player2;
        this.printStream = printStream;
    }

    public void playGame() {
        board.drawBoard();

        while (!board.boardIsFull()) {
            Player currentPlayer = p1;
            currentPlayer = makeMove(currentPlayer);
            makeMove(currentPlayer);
        }

        printStream.println("Game is a draw");
    }

    public Player makeMove(Player currentPlayer) {
        Integer square = currentPlayer.getAndValidateUserInput();

        while (!board.isMoveAvailable(square)) {
            printStream.println("Location already taken.");
            square = currentPlayer.getAndValidateUserInput();
        }

        board.move(square, currentPlayer.getSymbol()); //is passing two player params to board the best approach?
        board.drawBoard();

        return toggleCurrentPlayer(currentPlayer);
    }

    public Player toggleCurrentPlayer(Player current) {
        return (current.equals(p1)) ? p2 : p1;
    }
}
