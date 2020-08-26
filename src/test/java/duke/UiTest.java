package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.ui.Ui;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class UiTest {
    private static final String SEPARATOR = System.getProperty("line.separator");
    private static final String LOGO = " _   _       _   _ _            \n" +
            "| | | |     | | | (_)           \n" +
            "| |_| | ___ | |_| |_ _ __   ___ \n" +
            "|  _  |/ _ \\| __| | | '_ \\ / _ \\\n" +
            "| | | | (_) | |_| | | | | |  __/\n" +
            "\\_| |_/\\___/ \\__|_|_|_| |_|\\___| \n";
    private static final String DIVIDER = "<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>";
    private static final String WELCOME_MESSAGE = DIVIDER + "\n Thanks for contacting Hotline! \n" +
            " How can I help you today? \n"
            + DIVIDER + "\n";
    private static final String GOODBYE_MESSAGE = "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
            "██                  ████      ████████  ██████████████████      ████    ██  ▒▒██████████  ████████  ████████        ░░██    ██████████\n" +
            "██                  ████      ████████    ████████  ██████      ██      ██    ████████    ░░████▒▒    ████            ██      ▓▓      \n" +
            "██                  ████      ██████      ████████    ▒▒██      ██              ██████      ████                              ██      \n" +
            "████      ████      ████      ██████        ██████              ██            ████████                        ████            ██      \n" +
            "████      ████      ▒▒        ████          ▒▒████              ██          ████████████            ██      ██████            ██      \n" +
            "████      ████                ████    ░░      ████              ██        ████████████████          ██      ██████            ██      \n" +
            "████      ████                ██                ██              ██          ██████████████        ████      ████▒▒            ██      \n" +
            "████      ████      ████    ██░░                ▒▒      ██      ██            ▓▓██████████        ████                ██              \n" +
            "████      ████      ████    ██        ████              ████░░  ██            ████████████        ████▒▒              ██              \n" +
            "████      ████      ████    ██▒▒      ████    ████      ██████████      ██    ████████████        ██████░░          ██████          ██\n" +
            "████████████████████████████████████████████████████████████████████████████████████████████████████████████    ████████████    ██████\n" +
            "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████████      ████████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████            ██████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████      ██    ██████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████            ██████████████████████████████████████████████████████████████\n" +
            "████████████████████████████████████████████████████████████▓▓        ██  ████████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████                  ██████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████      ██          ██████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████                  ██████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████▒▒              ████████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
            "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
            "██████████████████        ████████████      ██████████      ██████▒▒        ██████▒▒        ██████  ██████░░████████████▒▒  ██████████\n" +
            "██████████████              ░░████            ████            ██              ██              ██    ██████    ░░            ██████████\n" +
            "████████████░░              ████                                                                      ██                    ██████████\n" +
            "████████████      ░░████░░████        ████            ████            ██░░            ██    ▒▒██              ██      ████████████████\n" +
            "██████████      ████                ██████          ██████            ████                  ░░████          ▒▒██          ████████████\n" +
            "██████████      ████                ██████          ██████            ████                      ████        ████          ████████████\n" +
            "██████████      ██████              ████            ████              ██      ██      ████      ████      ██████            ██████████\n" +
            "██████████░░                ▒▒                ██              ██              ██                ████      ██████            ██████████\n" +
            "████████████                ████            ████            ░░██            ████              ██████      ██████            ██████████\n" +
            "██████████████            ████████          ██████          ████          ██████            ████████      ██████            ██████████";
    private final OutputStream OUT = new ByteArrayOutputStream();
    private final PrintStream ORIGINAL_OUT = System.out;
    private final InputStream ORIGINAL_IN = System.in;
    
    @Test
    public void testGreetUser() {
        PrintStream ps = new PrintStream(OUT);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.greetUser();
        assertEquals(LOGO + SEPARATOR +
                WELCOME_MESSAGE + SEPARATOR, OUT.toString());
        System.setOut(ORIGINAL_OUT);
    }
    
    @Test
    public void testShowLine() {
        PrintStream ps = new PrintStream(OUT);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.showLine();
        assertEquals(DIVIDER + SEPARATOR, OUT.toString());
        System.setOut(ORIGINAL_OUT);
    }
    
    @Test
    public void testGoodbyeUser() {
        PrintStream ps = new PrintStream(OUT);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.showGoodbyeUser();
        assertEquals(GOODBYE_MESSAGE + SEPARATOR, OUT.toString());
        System.setOut(ORIGINAL_OUT);
    }

    @Test
    public void testShowLoadingError() {
        PrintStream ps = new PrintStream(OUT);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.showLoadingError();
        assertEquals(" Failed to load saved data :(" 
                + SEPARATOR, OUT.toString());
        System.setOut(ORIGINAL_OUT);
    }
    
    @Test
    public void testPrintMessage() {
        PrintStream ps = new PrintStream(OUT);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.printMessage("Message 1", "Message 2", "Message 3");
        assertEquals("Message 1" + SEPARATOR + "Message 2" +
                SEPARATOR + "Message 3" + SEPARATOR, OUT.toString());
        System.setOut(ORIGINAL_OUT);
    }
    
    @Test
    public void testReadInput() {
        String input = "This is a test input";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Ui ui = new Ui();
        assertEquals(input, ui.readInput());
        System.setIn(ORIGINAL_IN);
    }
}
