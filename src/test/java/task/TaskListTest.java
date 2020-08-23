package task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;

public class TaskListTest {

    protected TaskList tasks = new TaskList(new ArrayList<>());

    @BeforeEach
    public void init(){
        tasks.addTask("todo Some Work");
        tasks.addTask("deadline Some Deadline /by Some Time");
        tasks.addTask("event Some Event /at Some Time");
    }

    @Test
    public void testSize() {
        assertEquals(3, tasks.getTasks().size());
    }

    @Test
    public void testAdd() {
        tasks.addTask("todo Some Other Work");
        assertEquals(4, tasks.getTasks().size());
    }

    @Test
    public void testDelete() {
        tasks.deleteTask("delete 1");
        assertEquals(2, tasks.getTasks().size());
    }

    @Test
    public void testMarkDone() {
        tasks.markTaskDone("done 1");
        assertEquals("[T][Y] Some Work", tasks.getTasks().get(0).toString());
    }
}
