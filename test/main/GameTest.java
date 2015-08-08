import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GameTest {

    @Test
    public void shouldDrawBoardWhenGameIsPlayed() {
        Player player = mock(Player.class);
        Player playerTwo = mock(Player.class);

        when(player.getUserInput()).thenReturn(5);
        when(player.getUserInput()).thenReturn(7);

        Board board = mock(Board.class);

        Game game = new Game(board, player, playerTwo);
        game.playGame();

        verify(board, atLeast(1)).drawBoard();
    }
}