package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;


class AddCommandTest {

    @Test
    public void addTask_deadlineTask_taskListWithOneTask() throws DukeException {
        TaskList testTaskList = new TaskList();
        new AddCommand(new String[]{"deadline", "return book", "/by", "2022-08-26", "1800" })
                .addTask(testTaskList);
        assertEquals(testTaskList.totalTask(), 1);
    }

    @Test
    public void addTask_unrecognizedTask_exceptionThrown() {
        try {
            TaskList testTaskList = new TaskList();
            new AddCommand(new String[]{"event", "sing", "song", "/by" })
                    .addTask(testTaskList);
            fail();
        } catch (DukeException e) {
            assertEquals("Input command must contain the delimiter /at between task name and date!",
                    e.getMessage());
        }
    }

    @Test
    public void processTask_deadlineTask_success() {
        try {
            Task myReturnedTask = new AddCommand(
                    new String[]{"deadline", "return", "book", "/by", "2022-08-26", "1800"})
                    .processTask(TaskType.DEADLINE);
            assertNotNull(myReturnedTask);
            assertTrue(myReturnedTask instanceof Deadline);
        } catch (DukeException e) {
            fail("Got Duke Exception");
        }
    }

    @Test
    public void processTask_deadlineNoDate_exceptionThrown() {
        try {
            new AddCommand(new String[]{"deadline", "return", "book", "/by" })
                    .processTask(TaskType.DEADLINE);
            fail();
        } catch (DukeException e) {
            assertEquals("The task must come with a date in yyyy-mm-dd format!",
                    e.getMessage());
        }
    }
}
