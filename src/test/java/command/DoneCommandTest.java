package command;

import duke.command.DoneCommand;
import duke.exception.InvalidTaskIndexException;
import duke.exception.TaskDoneException;
import duke.logic.UIManager;
import duke.task.DukeTask;
import org.junit.jupiter.api.Test;
import stub.DukeTaskStub;
import stub.TaskListStub;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoneCommandTest {
    @Test
    public void testDone() throws InvalidTaskIndexException, TaskDoneException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        DoneCommand command = new DoneCommand(0);
        DukeTask stub = new DukeTaskStub();

        TaskListStub taskListStub = new TaskListStub();
        taskListStub.addToList(stub);

        command.execute(taskListStub, new UIManager(), null);

        String expected = "Alright! I'll mark this task as done!\n" +
                "[✓] Testing DukeTaskStub\n" +
                "You now have 1 task\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testInvalidDone() {
        DukeTask stub = new DukeTaskStub();

        TaskListStub taskListStub = new TaskListStub();
        taskListStub.addToList(stub);

        DoneCommand lessThanZeroCommand = new DoneCommand(-1);
        DoneCommand moreThanIndexCommand = new DoneCommand(100);

        assertThrows(InvalidTaskIndexException.class,
                () -> lessThanZeroCommand.execute(taskListStub, new UIManager(), null));
        assertThrows(InvalidTaskIndexException.class,
                () -> moreThanIndexCommand.execute(taskListStub, new UIManager(), null));

    }
}
