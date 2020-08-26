import duke.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TasklistTest {

    @Test
    public void toDisplayString_emptyList_messageDisplayed() throws DukeException {
        Tasklist tasks = new Tasklist();
        assertEquals("You have no tasks!", tasks.toDisplayString());
    }

    @Test
    public void toDisplayString_filledList_tasksEnumerated() throws DukeException {
        Tasklist tasks = new Tasklist();
        Task task1 = new Todo("Sample Task");
        tasks.add(task1);
        assertEquals("1.[T][✘] Sample Task", tasks.toDisplayString());
    }

}
