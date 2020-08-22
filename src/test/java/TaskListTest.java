import duke.*;
import duke.task.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    TaskList taskList;

    @BeforeEach
    public void initTaskList(){
        // Initialize an empty task list before each test
        taskList = new TaskList();
    }

    @Test
    void addTodo_normalInput_success(){
        Todo todo = new Todo("borrow book");
        taskList.addTask(todo);
        assertEquals(taskList.get(0).toString(), "[T][✗] borrow book");
    }

    @Test
    void addEvent_normalInput_success(){
        Event event = new Event("project meeting", "06-06-2020 11:00");
        taskList.addTask(event);
        assertEquals(taskList.get(0).toString(), "[E][✗] project meeting (at: 6 Jun 2020, 11:00 am)");
    }

    @Test
    void addDeadline_normalInput_success(){
        Deadline deadline = new Deadline("return book", "06-08-2020 12:00");
        taskList.addTask(deadline);
        assertEquals(taskList.get(0).toString(),
                "[D][✗] return book (by: 6 Aug 2020, 12:00 pm)");
    }

    @Test
    void size_normalInput_success(){
        Deadline deadline = new Deadline("return book", "06-08-2020 12:00");
        taskList.addTask(deadline);
        Event event = new Event("project meeting", "06-06-2020 11:00");
        taskList.addTask(event);
        assertEquals(taskList.size(),
                2);
    }

    @Test
    void completeTask_normalInput_success(){
        Deadline deadline = new Deadline("return book", "06-08-2020 12:00");
        taskList.addTask(deadline);
        taskList.completeTask(1);
        assertEquals(deadline.toString(),
                "[D][✓] return book (by: 6 Aug 2020, 12:00 pm)");
    }

    @Test
    void deleteTask_normalInput_success(){
        Deadline deadline = new Deadline("return book", "06-08-2020 12:00");
        taskList.addTask(deadline);
        Event event = new Event("project meeting", "06-06-2020 11:00");
        taskList.addTask(event);
        taskList.deleteTask(1);

        List<Task> expectedTaskList = Arrays.asList(event);
        assertEquals(taskList.getList(), expectedTaskList);
    }
}
