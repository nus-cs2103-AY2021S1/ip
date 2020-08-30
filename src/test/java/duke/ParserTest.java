package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

public class ParserTest {
    private Parser parser = new Parser();
    private TaskList taskList = new TaskList(new ArrayList<>());
    private StorageStub storage = new StorageStub();

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
