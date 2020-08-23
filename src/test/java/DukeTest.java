import main.java.duke.Duke;
import main.java.duke.exception.DukeException;
import main.java.duke.Parser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void invalidInputTest() {
        try {
            Parser.parse("INVALID");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals("ERROR... INPUT NOT RECOGNIZED. \n PLEASE TRY AGAIN",
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void startTest() {
        new Duke("invalidPath/task.txt");

        assertEquals("ERROR DETECTED! UNABLE TO LOAD PROGRAM. \n SYSTEM SHUTTING DOWN",
                outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
