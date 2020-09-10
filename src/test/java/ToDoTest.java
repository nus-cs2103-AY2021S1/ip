import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.ToDo;

public class ToDoTest {
    ToDo toDo = new ToDo("Test ToDo");

    @Test
    public void testToString() {
        assertEquals("[T][Not Done] Test ToDo", toDo.toString());
    }

    @Test
    public void testToStringFileFormat() {
        assertEquals("[T][Not Done] Test ToDo", toDo.toStringFileFormat());
    }

    @Test
    public void testGetStatusIcon() {
        assertEquals("Not Done", toDo.getStatusIcon());
        toDo.setDone();
        assertEquals("Done", toDo.getStatusIcon());
    }
}
