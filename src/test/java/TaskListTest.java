import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testDeleteTask() {
        Ui ui = new Ui();
        TaskList tasks = new TaskList(new ArrayList<Task>());
        tasks.addTask(new Task("shopping"), ui);
        tasks.addTask(new Task("study"), ui);
        tasks.deleteTask(0, ui);
        assertEquals(1, tasks.getSize());
    }
}
