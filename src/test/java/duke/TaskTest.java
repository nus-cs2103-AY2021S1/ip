package duke;

import duke.task.Deadline;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

public class TaskTest {

    /**
     * Checks that the string output from creating todo tasks is correct. Also, it tests if updating the tasks
     * produced the correct output.
     *
     *@throws Exception Exceptions are thrown if the test case fails
     */
    @Test
    public void testStringOutput() throws Exception {
        Todo todo1 = new Todo("Make Breakfast");
        if (!todo1.toString().equals("[T][" + "\u2718" + "] Make Breakfast")) {
            throw new Exception("Default String Output is Wrong for Todo");
        }

        Todo todo2 = new Todo("Revise Lectures");
        todo2.updateTask(1);
        if (!todo2.toString().equals("[T][" + "\u2713" + "] Revise Lectures")) {
            throw new Exception("Update Task Output is Wrong");
        }

        System.out.println("All Tests Passed");
    }

    /**
     * Checks if date formatting for deadline objects is done correctly.
     *
     * @throws Exception Exceptions are thrown if the test cases fail.
     */
    @Test
    public void testCheckDateFormat() throws Exception {
        try {
            boolean isValidDate = Deadline.checkDateFormat("2020-50-20");
            if (isValidDate) {
                throw new Exception("checkDateFormat Method Failed");
            }
        } catch (DukeException ignored) {
            /* Exceptions are ignored */
        }
        System.out.println("All Tests Passed");
    }
}
