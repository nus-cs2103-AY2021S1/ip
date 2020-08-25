package duke.parts;

import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTest() {
        TaskList tasks = new TaskList();
        assertEquals(tasks.numTask(), 0);
        tasks.addTask(new ToDo("1"), new Storage("./data/test1.txt"));
        assertEquals(tasks.numTask(), 1);
    }

    @Test
    public void updateList() throws IOException {
        Storage s = new Storage("./data/test2.txt");
        TaskList tasks = new TaskList();
        tasks.addTask(new ToDo("do something"), s);
        ArrayList<Task> xs = s.load();
        assertEquals(xs.get(0).equals(new ToDo("do something")), true);
    }
}
