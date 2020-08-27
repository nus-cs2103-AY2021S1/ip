package command;

import duke.command.DoneCommand;
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

public class DoneCommandTest {
    @Test
    public void testDone() throws InvalidInstructionException {
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

        assertThrows(InvalidInstructionException.class,
                () -> lessThanZeroCommand.execute(taskListStub, new UIManager(), null));
        assertThrows(InvalidInstructionException.class,
                () -> moreThanIndexCommand.execute(taskListStub, new UIManager(), null));

    }
}
