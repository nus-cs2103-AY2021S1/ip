import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.exception.DukeIoException;
import duke.exception.InvalidInstructionFormatException;
import duke.exception.InvalidTaskIndexException;
import duke.exception.TaskDoneException;
import duke.logic.TaskList;
import duke.logic.UiManager;
import stub.CommandStub;


public class CommandTest {
    @Test
    public void mainCommandTest() throws InvalidTaskIndexException,
            DukeIoException, TaskDoneException, InvalidInstructionFormatException {
        Command command = new CommandStub();
        assertEquals(command.getResponse(), "");
        assertFalse(command.getExitStatus());
        command.execute(null, null, null, true);
        String expectedOutput = "TESTING";

        // Do the actual assertion.
        assertEquals(expectedOutput, command.getResponse());
    }

    @Test
    public void doneCommandTest() {
        Command negativeIndexCommand = new DoneCommand(-1);
        assertThrows(InvalidTaskIndexException.class, () -> negativeIndexCommand.execute(
                new TaskList(new ArrayList<>()), new UiManager(), null, false));
        Command outOfBoundIndexCommand = new DoneCommand(10);
        assertThrows(InvalidTaskIndexException.class, () -> outOfBoundIndexCommand.execute(
                new TaskList(new ArrayList<>()), new UiManager(), null, false));
    }

    @Test
    public void deleteCommandTest() {
        Command negativeIndexCommand = new DeleteCommand(-1);
        assertThrows(InvalidTaskIndexException.class, () -> negativeIndexCommand.execute(
                new TaskList(new ArrayList<>()), new UiManager(), null, false));
        Command outOfBoundIndexCommand = new DeleteCommand(10);
        assertThrows(InvalidTaskIndexException.class, () -> outOfBoundIndexCommand.execute(
                new TaskList(new ArrayList<>()), new UiManager(), null, false));
    }
}
