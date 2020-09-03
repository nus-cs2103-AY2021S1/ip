import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import duke.DukeException;
import duke.Parser;
import duke.commands.AddCommand;
import duke.task.TaskList;
import duke.commands.Command;
import org.junit.jupiter.api.Test;
import duke.task.Task;
import duke.task.TaskType;

import java.util.ArrayList;

public class DogeTest {

    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

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
        assertEquals(task, TaskList.getList().get(0));
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

    @Test
    public void checkCommaTest() {
        String input = "doge,feed";
        ArrayList<String> commaSeperated = Parser.checkCommas(input);
        assertEquals("doge", commaSeperated.get(0));
        assertEquals("feed", commaSeperated.get(1));
    }
}
