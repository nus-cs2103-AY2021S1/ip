package duke;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList taskList;
    
    @BeforeEach
    public void initEach() {
        List<Task> task = new ArrayList<>();
        task.add(new Todo("borrow book"));
        task.add(new Todo("return book"));
        taskList = new TaskList(task);
    }
    
    @Test
    public void addTask() {
        taskList.add(new Todo("help a friend"));
        assertEquals(taskList.size(), 3);
    }
    
    @Test
    public void removeTask() {
        taskList.remove(1);
        assertEquals(taskList.size(), 1);
    }
}