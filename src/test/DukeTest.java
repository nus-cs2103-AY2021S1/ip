import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    Task toDo = new ToDo("Read Book");
    Task event = new Event("Project Meeting", "today");
    Task deadline = new Deadline("Return Book", "2020-09-01");

    @Test
    public void ToDoTest(){
        assertEquals("Read Book", toDo.getTask());
    }

    @Test
    public void EventTest() {
        assertEquals("today", event.getDate());
    }

    @Test
    public void DeadlineTest() {
        assertEquals("Sep 9, 2020", deadline.getDate());
    }
}