import duke.command.DukeException;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void processorAddTodoTest() {
        Storage storage = Storage.createDukeFile("test");
        TaskList tasklist = TaskList.createTaskList();
        try {
            Parser.process("todo read book", tasklist, storage);
            assertEquals("[T][✘] read book", tasklist.getTask(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDoneTest() {
        Storage storage = Storage.createDukeFile("test");
        TaskList tasklist = TaskList.createTaskList();
        try {
            Parser.process("todo read book", tasklist, storage);
            Parser.process("done 1", tasklist, storage);
            assertEquals("[T][✓] read book", tasklist.getTask(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDeleteTest() {
        Storage storage = Storage.createDukeFile("test");
        TaskList tasklist = TaskList.createTaskList();
        try {
            Parser.process("todo read book", tasklist, storage);
            Parser.process("todo read 2nd book", tasklist, storage);
            Parser.process("delete 1", tasklist, storage);
            assertEquals(1, tasklist.getListSize());
            assertEquals("[T][✘] read 2nd book", tasklist.getTask(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
