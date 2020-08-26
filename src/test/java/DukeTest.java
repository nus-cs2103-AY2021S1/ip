import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    Task toDo = new ToDo("Eat bread");
    Task event = new Event("Eat bread", "tomorrow");
    Task deadline = new Deadline("Eat bread", "tomorrow");

    @Test
    public void ToDoTest(){
        assertEquals("Eat bread", toDo.getTask());
    }

    @Test
    public void EventTest() {
        assertEquals("Eat bread", event.getTask());
    }

    @Test
    public void DeadlineTest() {
        assertEquals("Eat bread", deadline.getTask());
    }
}