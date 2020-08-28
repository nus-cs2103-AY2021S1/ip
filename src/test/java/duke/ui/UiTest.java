package duke.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    private final ByteArrayOutputStream OUT_CONTENT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;
    private final static String DIVIDER = "____________________________________________________________";
    private final static String ERROR_HEADER = "___________________________ERROR!___________________________";

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(OUT_CONTENT));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void print() {
        String msgBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        new Ui().print(msgBody);
        assertEquals(
            DIVIDER + "\n" + msgBody + "\n" + DIVIDER + "\n",
            OUT_CONTENT.toString()
        );
    }

    @Test
    public void printErr() {
        String msgBody = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
        new Ui().printErr(msgBody);
        assertEquals(
                ERROR_HEADER + "\n" + msgBody + "\n" + DIVIDER + "\n",
                OUT_CONTENT.toString()
        );
    }
}
