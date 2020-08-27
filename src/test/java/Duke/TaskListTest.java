package duke;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests if the TaskList is correctly implemented.
 *
 * @author Joshua
 */
public class TaskListTest {

    @Test
    public void testTaskList() {
        Event unfinishedEvent = new Event("return book ");
        Deadline unfinishedDeadline = new Deadline("return book ");
        Todo unfinishedTodo = new Todo("return book");
        LocalDateTime dateTime = LocalDateTime.parse("2020-02-20T23:59");
        unfinishedDeadline.setTime(dateTime);
        unfinishedEvent.setTime(dateTime);

        ArrayList<Task> testTaskList = new ArrayList<Task>();
        testTaskList.add(unfinishedDeadline);
        testTaskList.add(unfinishedEvent);
        testTaskList.add(unfinishedTodo);

        assertEquals("[D] [✗]return book (by:Feb 20 2020 23:59PM)", testTaskList.get(0).toString());
        assertEquals("[E] [✗]return book (at:Feb 20 2020 23:59PM)", testTaskList.get(1).toString());
        assertEquals( "[T] [✗]return book", testTaskList.get(2).toString());

    }
}
