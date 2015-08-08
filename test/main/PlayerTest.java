import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PlayerTest {
    private BufferedReader reader;
    private PrintStream printStream;
    private static final Integer BOARD_SQUARE = 2;

    @Before
    public void setup() {
        reader = mock(BufferedReader.class);
        printStream = mock(PrintStream.class);
    }

    @Test
    public void shouldSavePlayerInputWhenPrompted() throws IOException {
        when(reader.readLine()).thenReturn(Integer.toString(BOARD_SQUARE));
        Player player = new Player(reader, printStream, 1, "X");

        Integer input = player.getAndValidateUserInput();

        assertThat(input, is(BOARD_SQUARE));
    }

    /*@Test
    public void addXToSquareWhenOneMoveMade() {
        player.move(); //Move params = player (symbol), board,
        board.drawBoard();
    }*/
}