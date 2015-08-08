import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class GameTest {
    private Player player;
    private Player playerTwo;
    private Board board;
    private Game game;
    private PrintStream printStream;

    @Before
    public void setup() {
        player = mock(Player.class);
        playerTwo = mock(Player.class);
        board = mock(Board.class);
        printStream = mock(PrintStream.class);
    }

    @Test
    public void shouldDrawBoardWhenGameIsPlayed() {
        when(board.boardIsFull()).thenReturn(true);

        game = new Game(board, player, playerTwo, mock(PrintStream.class));
        game.playGame();

        verify(board).drawBoard();
    }

    @Test
    public void shouldInformUserThatLocationIsTakenWhenItIsUnavailable() {
        board = mock(Board.class); //reset board

        when(board.isMoveAvailable(3)).thenReturn(false);
        when(board.isMoveAvailable(4)).thenReturn(true);

        when(player.getAndValidateUserInput()).thenReturn(3).thenReturn(4);

        game = new Game(board, player, playerTwo, printStream);
        game.makeMove(player);

        verify(printStream).println(contains("Location already taken."));
    }

    @Test
    public void shouldDrawBoardWhenAMoveIsMade() {
        when(board.isMoveAvailable(4)).thenReturn(true);
        when(player.getAndValidateUserInput()).thenReturn(4);

        game = new Game(board, player, playerTwo, printStream);
        game.makeMove(player);

        verify(board).drawBoard();
    }

    @Test
    public void shouldReturnOtherPlayerWhenPlayersToggled() {
        when(player.getSymbol()).thenReturn("X");
        when(playerTwo.getSymbol()).thenReturn("O");

        game = new Game(board, player, playerTwo, printStream);
        Player toggledPlayer = game.toggleCurrentPlayer(player);

        assertThat(toggledPlayer.getSymbol(), is("O"));
    }

    @Test
    public void shouldInformUserWhenBoardIsFull() {
        for (int i = 1; i < 10; i++) {
            when(board.isMoveAvailable(i)).thenReturn(true);

            if (i % 2 == 0) { when(playerTwo.getAndValidateUserInput()).thenReturn(i); }
            else { when(player.getAndValidateUserInput()).thenReturn(i); }
        }

        game = new Game(board, player, playerTwo, printStream);
        game.playGame();

        verify(printStream).println(contains("Game is a draw"));
    }
}