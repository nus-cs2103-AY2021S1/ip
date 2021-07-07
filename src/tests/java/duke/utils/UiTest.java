package duke.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final String line = "_".repeat(80) + "\n";
    private final String leftPadding = " ".repeat(7);

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPrintPadding(){
        Ui ui = new Ui();
        ui.printLeftPadding();
        assertEquals(leftPadding, outContent.toString());
    }

    @Test
    public void testPrintLine(){
        Ui ui = new Ui();
        ui.printDivider();
        assertEquals(line, outContent.toString());
    }

    @Test
    public void testPrint(){
        Ui ui = new Ui();
        ui.print("Test");
        String expectedOutput = leftPadding + line
                + leftPadding + "Test\n"
                + leftPadding + line;
        assertEquals(expectedOutput, outContent.toString());
    }

}
