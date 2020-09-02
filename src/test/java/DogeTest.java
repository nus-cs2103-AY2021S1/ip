import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.DukeException;
import duke.Parser;
import duke.task.TaskList;
import duke.commands.Command;
import org.junit.jupiter.api.Test;
import duke.task.Task;
import duke.task.TaskType;

public class DogeTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    /*@Test
    public void uiTest() {
        duke.Ui ui = new duke.Ui();
        String testString = "--------------------------------------" + "\n"
                + "Added to list : " + "printable message" + "\n"
                + "--------------------------------------";
    }*/

    @Test
    public void taskTest() {
        Task task = new Task("Cycling", TaskType.E);
        task.markAsDone();
        assertEquals("\u2713", task.getTaskStatusIcon());
    }

    @Test
    public void taskListTest() {
        Task task = new Task("Cycling", TaskType.E);
        TaskList.addToList(task);
        assertEquals(task, TaskList.toDoList.get(0));
    }

    @Test
    public void parserTest() {
        boolean isAddCommand = false;
        try {
            Command addCommand = Parser.manage("todo feed doge");
            isAddCommand = addCommand instanceof AddCommand;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(isAddCommand);
    }
}
