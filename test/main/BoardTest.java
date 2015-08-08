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
}