package duke;

import main.java.duke.Ui;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private Ui ui = new Ui();
    private String horizontalLine = "      ===================================";
    private String indentation = "      ";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void welcomeTest() {
        ui.showWelcome();
        String logo = indentation + " ____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        String expected = horizontalLine + "\n" + indentation + "Hello from\n" + logo + "\n" + horizontalLine;
        assertEquals(expected, outContent.toString().stripTrailing());
    }

    @Test
    public void showGoodbye() {
        ui.showGoodbye();
        String expected = horizontalLine + "\n" + indentation + "Have a nice day.\n" + horizontalLine;
        assertEquals(expected, outContent.toString().stripTrailing());
    }
}
