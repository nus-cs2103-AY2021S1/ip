import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void lengthTest(){
        TaskList list = new TaskList();
        Todo todo = new Todo("Run");
        Deadline deadline = new Deadline("Return book", LocalDate.parse("2019-08-02"));
        Event event = new Event("project meeting", "tuesday morning");
        list.add(todo);
        list.add(deadline);
        list.add(event);
        assertEquals(list.length(), 3);
    }

    @Test
    public void indexTest(){
        TaskList list = new TaskList();
        Todo todo = new Todo("Run");
        Deadline deadline = new Deadline("Return book", LocalDate.parse("2019-08-02"));
        Event event = new Event("project meeting", "tuesday morning");
        list.add(todo);
        list.add(deadline);
        list.add(event);
        assertEquals(list.getIndex(deadline), 1);
    }

    @Test
    public void removeTest(){
        TaskList list = new TaskList();
        Todo todo = new Todo("Run");
        Deadline deadline = new Deadline("Return book", LocalDate.parse("2019-08-02"));
        Event event = new Event("project meeting", "tuesday morning");
        list.add(todo);
        list.add(deadline);
        list.add(event);
        list.remove(deadline);
        assertEquals(list.length(), 2);
    }


}
