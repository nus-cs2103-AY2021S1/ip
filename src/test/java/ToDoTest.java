import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void getToDoDescription_returnToDoDescription () {
        assertEquals("Borrow book", new ToDo("Borrow book").getDescription());
    }

    @Test
    public void getDoneStatus_notDone() {
        assertEquals(false, new ToDo("Borrow book").getDoneStatus());
    }

    @Test
    public void getDoneStatus_markToDoAsDone_done() {
        ToDo task = new ToDo("Borrow book");
        task.markAsDone();
        assertEquals(true, task.getDoneStatus());
    }
}
