import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.Duke;
import duke.Parser;
import duke.exception.DukeException;

public class DukeTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void parse_invalidCommand_success() {
        try {
            Parser.parse("INVALID");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals("ERROR... INPUT NOT RECOGNIZED. \n PLEASE TRY AGAIN.",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void duke_invalidPath_success() {
        new Duke("invalidPath/task.txt");

        assertEquals("ERROR DETECTED! UNABLE TO LOAD TASKLIST. \n CREATING NEW TASKLIST",
                outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
