package duke;

import duke.ui.Ui;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    private static final String separator = System.getProperty("line.separator");
    private static final String logo = " _   _       _   _ _            \n" +
            "| | | |     | | | (_)           \n" +
            "| |_| | ___ | |_| |_ _ __   ___ \n" +
            "|  _  |/ _ \\| __| | | '_ \\ / _ \\\n" +
            "| | | | (_) | |_| | | | | |  __/\n" +
            "\\_| |_/\\___/ \\__|_|_|_| |_|\\___| \n";
    private static final String divider = "<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>";
    private static final String welcomeMessage = divider + "\n Thanks for contacting Hotline! \n" +
            " How can I help you today? \n"
            + divider + "\n";
    private static final String goodbyeMessage = "██████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████\n" +
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
    private final OutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    
    @Test
    public void testGreetUser() {
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.greetUser();
        assertEquals(logo + separator +
                welcomeMessage + separator, out.toString());
        System.setOut(originalOut);
    }
    
    @Test
    public void testShowLine() {
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.showLine();
        assertEquals(divider + separator, out.toString());
        System.setOut(originalOut);
    }
    
    @Test
    public void testGoodbyeUser() {
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.goodbyeUser();
        assertEquals(goodbyeMessage + separator, out.toString());
        System.setOut(originalOut);
    }

    @Test
    public void testShowLoadingError() {
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.showLoadingError();
        assertEquals(" Failed to load saved data :(" 
                + separator, out.toString());
        System.setOut(originalOut);
    }
    
    @Test
    public void testPrintMessage() {
        PrintStream ps = new PrintStream(out);
        System.setOut(ps);
        Ui ui = new Ui();
        ui.printMessage("Message 1", "Message 2", "Message 3");
        assertEquals("Message 1" + separator + "Message 2" +
                separator + "Message 3" + separator, out.toString());
        System.setOut(originalOut);
    }
    
    @Test
    public void testReadInput() {
        String input = "This is a test input";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Ui ui = new Ui();
        assertEquals(input, ui.readInput());
        System.setIn(originalIn);
    }
}
