package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void parse_glibberish_success() {

        new Parser().parse(new TaskList(), "asdfgh qwert");

        String expectedResult = "duke.exception.UnknownCommandException: Don't understand...";
        String actualResult = outputStreamCaptor.toString().trim();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void parse_byeEntry_success() {

        boolean actualResult = new Parser().parse(new TaskList(), "bye");
        assertTrue(actualResult);
    }

    @Test
    public void parse_showEmptyList_success() {

        new Parser().parse(new TaskList(), "list");

        String expectedResult = "Here yur tasks faggit: \n" + "empty";
        String actualResult = outputStreamCaptor.toString().trim();

        assertEquals(expectedResult, actualResult);
    }
}