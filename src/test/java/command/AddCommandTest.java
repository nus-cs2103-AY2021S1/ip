package command;

import duke.command.AddCommand;
import duke.logic.UIManager;
import duke.task.DukeTask;
import stub.TaskListStub;
import org.junit.jupiter.api.Test;
import stub.DukeTaskStub;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void testAddCommand() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        // DukeTaskStub is just a placeholder for DukeTasks.
        // Inherits from DukeTasks but doesn't do anything
        DukeTask task = new DukeTaskStub();
        AddCommand command = new AddCommand(task);
        command.execute(new TaskListStub(), new UIManager(), null);

        String expected = "Task Added: " + task.toString() + "\n" + "You now have 1 task\n";

        assertEquals(expected, outContent.toString());
    }
}
