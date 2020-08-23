package Duke;

import org.junit.jupiter.api.Test;

public class TaskTest{

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

    @Test
    public void testCheckDateFormat() throws Exception {
        try {
            boolean isValidDate = Deadline.checkDateFormat("2020-50-20");
            if (isValidDate) {
                throw new Exception("checkDateFormat Method Failed");
            }
        } catch (DukeException ignored) {

        }
        System.out.println("All Tests Passed");
    }
}