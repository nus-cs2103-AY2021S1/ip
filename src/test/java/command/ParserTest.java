package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.DukeException;
import duke.command.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

public class ParserTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void processorAddTodoTest() {
        TaskList tasklist = TaskList.createTaskList();
        try {
            Parser.process("todo read book", tasklist, false);
            assertEquals("[T][X] read book", tasklist.getTask().toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDoneTest() {
        TaskList tasklist = TaskList.createTaskList();
        try {
            Parser.process("todo read book", tasklist, false);
            Parser.process("done 1", tasklist, false);
            assertEquals("[T][X] read book", tasklist.getTask().toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorDeleteTest() {
        TaskList tasklist = TaskList.createTaskList();
        try {
            Parser.process("todo read book", tasklist, false);
            Parser.process("todo read 2nd book", tasklist, false);
            Parser.process("delete 1", tasklist, false);
            assertEquals(1, tasklist.getListSize());
            assertEquals("[T][X] read 2nd book", tasklist.getTask().toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void processorPriorityTest() {
        TaskList tasklist = TaskList.createTaskList();
        try {
            Parser.process("todo read book", tasklist, false);
            Parser.process("event book reading /at Sep 7 2020 15:00", tasklist, false);
            Parser.process("deadline return book /by Aug 17 2020 23:59", tasklist, false);
            assertEquals(3, tasklist.getListSize());
            assertEquals("[D][X] return book by: Aug 17 2020 23:59", tasklist.getTask().toString());
            assertEquals("[E][X] book reading at: Sep 7 2020 15:00", tasklist.getTask().toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

}
