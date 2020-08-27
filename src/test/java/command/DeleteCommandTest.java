package command;

import duke.command.DeleteCommand;
import duke.exception.InvalidInstructionException;
import duke.logic.UIManager;
import duke.task.DukeTask;
import stub.TaskListStub;
import org.junit.jupiter.api.Test;
import stub.DukeTaskStub;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteCommandTest {
    @Test
    public void testDelete() throws InvalidInstructionException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DeleteCommand deleteCommand = new DeleteCommand(0);
        DukeTask stub = new DukeTaskStub();

        TaskListStub taskListStub = new TaskListStub();
        taskListStub.addToList(stub);

       deleteCommand.execute(taskListStub, new UIManager(), null);

       String expected = "Alright! I'll delete this task!\n" +
               "Take note that this is irreversible!\n" +
               "[✘] Testing DukeTaskStub\n" +
               "You now have 0 tasks\n";

       assertEquals(expected, outContent.toString());
    }

    @Test
    public void testInvalidDelete() {
        DeleteCommand deleteCommandLessThanZero = new DeleteCommand(-1);
        DukeTask stub = new DukeTaskStub();

        TaskListStub taskListStub = new TaskListStub();
        taskListStub.addToList(stub);

        assertThrows(InvalidInstructionException.class,
                () -> deleteCommandLessThanZero.execute(taskListStub, new UIManager(), null));

        DeleteCommand deleteCommandLargerThanIndex = new DeleteCommand(100);
        assertThrows(InvalidInstructionException.class,
                () -> deleteCommandLargerThanIndex.execute(taskListStub, new UIManager(), null));
    }
}
