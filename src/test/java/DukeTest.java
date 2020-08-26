import duke.task.Event;
import duke.task.Todo;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }

    @Test
    public void todoOutput() {
        Task task = new Todo("Go Swim");
        assertEquals(task.toString(), "[T][\u2718] Go Swim");
    }

    @Test
    public void eventOutput() {
        Task task = new Event("Go Swim", "pool");
        assertEquals(task.toString(), "[E][\u2718] Go Swim (at: pool)");
    }
}
