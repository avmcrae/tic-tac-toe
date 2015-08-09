import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class BoardTest {
    PrintStream printStream;
    Board board;
    String[] boardStatus = {" ", " ", " ", " ", " ", " ", " ", " ", " "};

    @Before
    public void setup() {
        printStream = mock(PrintStream.class);
        board = new Board(printStream, boardStatus);
    }

    @Test
    public void shouldPrintEmptyBoardWhenDrawBoardCalledOnEmptySquares() {
        board.drawBoard();

        verify(printStream).printf("   |   | \n" +
                "---------\n" +
                "   |   | \n" +
                "---------\n" +
                "   |   | \n");
    }

    @Test
    public void addXToSquareWhenOneMoveMade() {
        board = new Board(printStream, boardStatus);

        board.move(3, "X");
        board.drawBoard();

        verify(printStream).printf("   |   |X\n" +
                "---------\n" +
                "   |   | \n" +
                "---------\n" +
                "   |   | \n");
    }

    @Test
    public void addTwoXsToSquaresWhenTwoDifferentMovesAreMade() {
        board = new Board(printStream, boardStatus);

        board.move(3, "X");
        board.move(7, "X");
        board.drawBoard();

        verify(printStream).printf("   |   |X\n" +
                "---------\n" +
                "   |   | \n" +
                "---------\n" +
                " X |   | \n");
    }

    //name could be better
    @Test
    public void shouldPutCorrectSymbolOnBoardWhenInputInMove() {
        board.move(9, "O");
        board.move(4, "X");
        board.drawBoard();

        verify(printStream).printf("   |   | \n" +
                "---------\n" +
                " X |   | \n" +
                "---------\n" +
                "   |   |O\n");
    }

    @Test
    public void shouldReturnFalseWhenMoveIsAlreadyTakenBySymbol() {
        String[] boardStatus = {" ", " ", "X", " ", " ", " ", " ", " ", " "};
        board = new Board(printStream, boardStatus);

        Boolean moveIsAvailable = board.isMoveAvailable(3);
        assertFalse(moveIsAvailable);
    }

    @Test
    public void shouldReturnTrueWhenMoveIsAvailable() {
        String[] boardStatus = {" ", " ", " ", " ", "X", " ", " ", "X", " "};
        board = new Board(printStream, boardStatus);

        Boolean moveIsAvailable = board.isMoveAvailable(3);
        assertTrue(moveIsAvailable);
    }

    @Test
    public void returnsTrueWhenBoardIsFull() {
        String[] boardStatus = {"X", "O", "a", "O", "X", "O", "X", "X", "b"};
        board = new Board(printStream, boardStatus);

        assertTrue(board.boardIsFull());
    }

    @Test
    public void shouldReturnBoardNotFullWhenOneEmptySquare() {
        String[] boardStatus = {"X", "O", " ", "O", "X", "O", "X", "X", "b"};
        board = new Board(printStream, boardStatus);

        assertFalse(board.boardIsFull());
    }

    @Test
    public void shouldReturnTrueIfThreeInARowOnBoardAsAColumn() {
        String[] boardStatus = {"X", "X", " ",
                                "O", "X", "O",
                                "X", "X", "O"};
        board = new Board(printStream, boardStatus);

        assertTrue(board.threeInARow("X"));
    }

   @Test
    public void shouldReturnTrueIfThreeInARowOnBoardAsARow() {
        String[] boardStatus = {"O", "X", "X",
                                "X", "O", "X",
                                "O", "O", "O"};
        board = new Board(printStream, boardStatus);

        assertTrue(board.threeInARow("O"));
    }

    @Test
    public void shouldReturnTrueIfThreeInARowOnBoardAsADiagonal() {
        String[] boardStatus = {"X", "X", "O",
                                "O", "X", "O",
                                "X", "O", "X"};

        board = new Board(printStream, boardStatus);

        assertTrue(board.threeInARow("X"));
    }
}