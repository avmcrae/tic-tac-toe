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

        //game = new Game(board, player, playerTwo, printStream);
    }

    @Test
    public void shouldDrawBoardWhenGameIsPlayed() {
        /*when(player.getAndValidateUserInput()).thenReturn(5);
        when(player.getAndValidateUserInput()).thenReturn(7);*/

        //mock buffered reader and when/thenReturn that?

        game = new Game(board, player, playerTwo, mock(PrintStream.class));
        game.playGame();

        verify(board, atLeast(1)).drawBoard();
    }

    @Test
    public void shouldInformUserThatLocationIsTakenWhenItIsUnavailable() {
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
    public void shouldReturnOtherPlayerWhenPlayersAreToggled() {
        Player toggledPlayer = game.toggleCurrentPlayer(player);
        assertThat(toggledPlayer, is(playerTwo));
    }
}