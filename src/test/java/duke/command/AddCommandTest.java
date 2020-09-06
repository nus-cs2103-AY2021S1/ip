package duke.command;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;


class AddCommandTest {

    @Test
    public void addTask_deadlineTask_taskListWithOneTask() throws DukeException {
        TaskList testTaskList = new TaskList();
        new AddCommand(new String[]{"deadline", "return book", "/by", "2022-08-26", "1800" })
                .addTask(testTaskList);
        assertTrue(testTaskList.totalTask() == 1);
    }

    @Test
    public void addTask_unrecognizedTask_exceptionThrown() {
        try {
            TaskList testTaskList = new TaskList();
            new AddCommand(new String[]{"birthday", "sing", "song", "/by" })
                    .addTask(testTaskList);
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("I don't understand what task you want to be added! Only deadline/todo/event!",
                    e.getMessage());
        }
    }

    @Test
    public void processTask_deadlineTask_success() {
        try {
            Task myReturnedTask = new AddCommand(new String[]{"deadline", "return", "book", "/by", "2022-08-26", "1800"
            }).processTask("/by", "deadline");
            assertNotNull(myReturnedTask); //check if the object is != null
            //check if the returned object is of class Task
            assertTrue(myReturnedTask instanceof Task);
        } catch (DukeException e) {
            // let the test fail, if the function throws a Duke Exception.
            fail("Got Duke Exception");
        }
    }

    @Test
    public void processTask_deadlineNoDate_exceptionThrown() {
        try {
            Task myReturnedTask = new AddCommand(new String[]{"deadline", "return", "book", "/by" })
                    .processTask("/by", "deadline");
            fail(); // the test should not reach this line
        } catch (DukeException e) {
            assertEquals("The task must come with a date in yyyy-mm-dd format!",
                    e.getMessage());
        }
    }
}
