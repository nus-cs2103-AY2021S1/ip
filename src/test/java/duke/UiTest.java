package duke;

import duke.io.InputHandlerStub;
import duke.io.OutputHandlerStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void displayGreet_noInput_correctPrint() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        (new Ui(new InputHandlerStub(), new OutputHandlerStub())).displayGreet();
        assertEquals("Hello from\n" + logo, outputStreamCaptor.toString().trim() + "\n");
    }

    @Test
    public void displayGoodbye_noInput_correctPrint() {
        (new Ui(new InputHandlerStub(), new OutputHandlerStub())).displayGoodbye();
        assertEquals("Bye. Hope to see you again soon!", outputStreamCaptor.toString().trim());
    }

    @Test
    public void display_testString_correctPrint() {
        (new Ui(new InputHandlerStub(), new OutputHandlerStub())).display("duke test string.\"@#$\"\\");
        assertEquals("duke test string.\"@#$\"\\", outputStreamCaptor.toString().trim());
    }

    @Test
    public void displayException_testException_printExceptionMessage() {
        try {
            throw new Exception("duke test string.\"@#$\"\\");
        } catch (Exception e) {
            (new Ui(new InputHandlerStub(), new OutputHandlerStub()))
                    .displayException(e);
            assertEquals("duke test string.\"@#$\"\\", outputStreamCaptor.toString().trim());
        }

    }

    @Test public void readCommand_testInputString_correctStringOutput() {
        assertEquals("test input",
                (new Ui(new InputHandlerStub(), new OutputHandlerStub())).readCommand());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

}
