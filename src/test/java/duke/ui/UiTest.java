package duke.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String ERROR_HEADER = "___________________________ERROR!___________________________";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void print() {
        String msgBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        new Ui().print(msgBody);
        assertEquals(
            DIVIDER + "\n" + msgBody + "\n" + DIVIDER + "\n",
            outContent.toString()
        );
    }

    @Test
    public void printErr() {
        String msgBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        new Ui().printErr(msgBody);
        assertEquals(
                ERROR_HEADER + "\n" + msgBody + "\n" + DIVIDER + "\n",
                outContent.toString()
        );
    }
}
