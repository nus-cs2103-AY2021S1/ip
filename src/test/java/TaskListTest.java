import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.Deadline;
import storage.TaskList;


public class TaskListTest {
    @Test
    public void testMarkCompleted() {
        TaskList tl = new TaskList();
        tl.getListOfItems().add(Deadline.createDeadline("return book /by 2/12/2019 1800"));

        String markedTask = tl.markCompleted(0);
        String expectedOutput = "\nNice! I've marked this task as done:\n"
                + "  [D][\u2713] return book (by: 2 Dec 2019 18:00)\n";
        assertEquals(expectedOutput , markedTask);
    }


}
