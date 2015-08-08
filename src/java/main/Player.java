import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Player {
    private BufferedReader reader;
    private PrintStream printStream;
    private int playerNumber;
    private String symbol;
    
    public Player(BufferedReader reader, PrintStream printStream, int pNum, String symbol) {
        this.reader = reader;
        this.printStream = printStream;
        this.playerNumber = pNum;
        this.symbol = symbol;
    }

    public Integer getUserInput() {
        printStream.println("Please enter a number between 1 and 9, Player " + playerNumber + ":");
        try {
            return  Integer.parseInt(reader.readLine());
        } catch(NumberFormatException e) {
            printStream.println("Please only enter a number");
        } catch (IOException e) {
            printStream.println("There was an error processing your input");
        }
        return -1;
    }

    //Getters = bad, but what is alternative?
    public String getSymbol() {
        return this.symbol;
    }
}
