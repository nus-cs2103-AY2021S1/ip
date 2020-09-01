package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList(new ArrayList<>());
    }


    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void testAddToDo() throws DukeException {
        taskList.add("todo eat");
        assertEquals(taskList.countList(), 1);
    }

    @Test
    public void testAddDeadline() throws DukeException {
        taskList.add("deadline /by 27/08/2020 2359");
        assertEquals(taskList.countList(), 1);
    }

    @Test
    public void testAddEvent() throws DukeException {
        taskList.add("event sleep /at 28/08/2020 0000-0800");
        assertEquals(taskList.countList(), 1);
    }

    @Test
    public void delete() throws DukeException {
        taskList.add("event sleep /at 28/08/2020 0000-0800");
        taskList.add("deadline /by 27/08/2020 2359");
        taskList.delete("delete 1");
        assertEquals(taskList.countList(), 1);
    }

    @Test
    public void deleteAll() throws DukeException {
        taskList.add("event sleep /at 28/08/2020 0000-0800");
        taskList.add("deadline /by 27/08/2020 2359");
        taskList.add("todo eat");
        taskList.delete("delete all");
        assertEquals(taskList.countList(), 0);
    }
}
