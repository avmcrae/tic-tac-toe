import java.io.PrintStream;

public class Board {
    private PrintStream printStream;
    private String[] boardStatus;

    public Board(PrintStream printStream, String[] boardStatus) {
        this.printStream = printStream;
        this.boardStatus = boardStatus;
    }

    public void drawBoard() {
        printStream.printf(String.format(" %s | %s |%s\n" +
                "---------\n" +
                " %s | %s |%s\n" +
                "---------\n" +
                " %s | %s |%s\n", boardStatus));
    }

    public void move(int squareNumber, String symbol) {
        boardStatus[squareNumber - 1] = symbol;
    }
}
