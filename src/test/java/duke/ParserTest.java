package duke;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    Parser parser = new Parser();
    TaskList taskList = new TaskList(new ArrayList<Task>());
    StorageStub storage = new StorageStub();

    @Test
    public void testCommand() throws DukeException {
        parser.command("todo 1", taskList, storage);
        assertEquals(1, taskList.getTasks().size());
    }

    @Test
    public void command_exceptionThrown() {
        assertThrows(InvalidCommandException.class, () -> {
            parser.command("project", taskList, storage);
        });
    }
}
