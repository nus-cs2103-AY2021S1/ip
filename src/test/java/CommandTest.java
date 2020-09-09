import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.DukeIoException;
import duke.exception.InvalidTaskIndexException;
import duke.exception.TaskDoneException;
import stub.CommandStub;


public class CommandTest {
    @Test
    public void mainCommandTest() throws InvalidTaskIndexException, DukeIoException, TaskDoneException {
        Command command = new CommandStub();
        assertEquals(command.getResponse(), "");
        assertFalse(command.getExitStatus());
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        command.execute(null, null, null, false);
        String expectedOutput = "TESTING\n";

        // Do the actual assertion.
        assertEquals(expectedOutput, outContent.toString());
    }
}
