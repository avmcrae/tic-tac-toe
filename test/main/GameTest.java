import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class GameTest {

    @Test
    public void shouldDrawBoardWhenGameIsPlayed() {
        Player player = mock(Player.class);
        Player playerTwo = mock(Player.class);

        when(player.getAndValidateUserInput()).thenReturn(5);
        when(player.getAndValidateUserInput()).thenReturn(7);

        Board board = mock(Board.class);

        Game game = new Game(board, player, playerTwo, mock(PrintStream.class));
        game.playGame();

        verify(board, atLeast(1)).drawBoard();
    }
}