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
        Parser parser = new Parser();
        Storage storage = Storage.createDukeFile("test");
        TaskList tasklist = TaskList.createTaskList();
        try {
            parser.process("todo read book", tasklist, storage);
            assertEquals("[T][✘] read book", tasklist.getTask(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDoneTest() {
        Parser parser = new Parser();
        Storage storage = Storage.createDukeFile("test");
        TaskList tasklist = TaskList.createTaskList();
        try {
            parser.process("todo read book", tasklist, storage);
            parser.process("done 1", tasklist, storage);
            assertEquals("[T][✓] read book", tasklist.getTask(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDeleteTest() {
        Parser parser = new Parser();
        Storage storage = Storage.createDukeFile("test");
        TaskList tasklist = TaskList.createTaskList();
        try {
            parser.process("todo read book", tasklist, storage);
            parser.process("todo read 2nd book", tasklist, storage);
            parser.process("delete 1", tasklist, storage);
            assertEquals(1, tasklist.getListSize());
            assertEquals("[T][✘] read 2nd book", tasklist.getTask(0).toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
