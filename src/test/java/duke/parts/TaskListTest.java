package duke.parts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.ToDo;


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
        assertEquals(xs.get(0), new ToDo("do something"));
    }
}
