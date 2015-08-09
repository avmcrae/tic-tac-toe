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
        if (isMoveAvailable(squareNumber)) {
            boardStatus[squareNumber - 1] = symbol;
        }
    }

    public boolean isMoveAvailable(Integer square) {
        return boardStatus[square - 1].equals(" ");
    }

    public boolean boardIsFull() {
        for (String square : boardStatus) {
            if (square.equals(" ")) {
                return false;
            }
        }
        return true;
    }

    public boolean threeInARow(String symbol) {
        for (int i = 0; i < 3; i++) {
            if (boardStatus[i].equals(boardStatus[i+3]) && boardStatus[i+3].equals(boardStatus[i+6]) && boardStatus[i].equals(symbol)) {
                return true;
            }
        }
        for (int i = 0; i < 9; i+=3) {
            if (boardStatus[i].equals(boardStatus[i+1]) && boardStatus[i+1].equals(boardStatus[i+2]) && boardStatus[i].equals(symbol)) {
                return true;
            }
        }

        if (boardStatus[0].equals(boardStatus[4]) && boardStatus[4].equals(boardStatus[8]) && boardStatus[0].equals(symbol)) {
            return true;
        }
        if (boardStatus[2].equals(boardStatus[4]) && boardStatus[4].equals(boardStatus[6]) && boardStatus[2].equals(symbol)) {
            return true;
        }
        return false;
    }
}
