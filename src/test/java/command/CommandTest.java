package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.InvalidTaskIndexException;
import duke.exception.TaskDoneException;
import stub.CommandChildStub;




public class CommandTest {

    @Test
    public void testGetExitStatus() {
        Command falseCommand = new CommandChildStub(false);
        Command trueCommand = new CommandChildStub(true);

        assertFalse(falseCommand.getExitStatus());
        assertTrue(trueCommand.getExitStatus());
    }

    // This test tests if the inherited execute() in ChildStub can execute properly or not.
    @Test
    public void testCommandExecute() throws IOException, InvalidTaskIndexException, TaskDoneException {
        Command testCommand = new CommandChildStub(true);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // After this all System.out.println() statements will come to outContent stream.
        // This is a generic test so the inputs are ignored.
        // Will test more in detail for the specific children of Command
        testCommand.execute(null, null, null);
        String expectedOutput = "Testing Command\n";

        // Do the actual assertion.
        assertEquals(expectedOutput, outContent.toString());
    }
}
