import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

import java.util.ArrayList;


public class TaskListTest {
    TaskList taskList;
    final private ToDo a = new ToDo("Punch");
    final private Deadline b = new Deadline("Fight", "2019-03-04");
    final private Event c = new Event("Join Fight Club", "Saturday");
    final private ToDo book1 = new ToDo("Book");
    final private ToDo book2 = new ToDo("Book book");
    final private ToDo book3 = new ToDo("Book Book Book");

    @BeforeEach
    void setUp() {
        taskList = new TaskList(new ArrayList<Task>());
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void testAdd() {
        taskList.add(a);
        Assertions.assertEquals("1. [T][✘] Punch\n", taskList.listToString());
    }

    @Test
    void testRemove() {
        // Check add + Remove
        taskList.add(a);
        taskList.add(b);
        taskList.remove(1);

        Assertions.assertEquals("1. [D][✘] Fight (by: Mar 04 2019)\n", taskList.listToString());

    }

    @Test
    void testMarkDone() {

        taskList.add(b);
        taskList.add(c);
        taskList.markDone(1);
        taskList.markDone(2);

        Assertions.assertEquals("1. [D][✓] Fight (by: Mar 04 2019)\n" +
                        "2. [E][✓] Join Fight Club (at: Saturday)\n",
                taskList.listToString());
    }

    @Test
    void getSize() {

        taskList.add(a);
        taskList.add(b);
        taskList.add(c);
        taskList.remove(2);
        Assertions.assertEquals(2, taskList.getSize());
    }

    @Test
    void testFind() {

        taskList.add(book1);
        taskList.add(book2);
        taskList.add(book3);
        String actual = "1. [T][✘] Book\n" +
                "2. [T][✘] Book book\n" +
                "3. [T][✘] Book Book Book\n";
        Assertions.assertEquals(taskList.listToString(), actual);
    }

}