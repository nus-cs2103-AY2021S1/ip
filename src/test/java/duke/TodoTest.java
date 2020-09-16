package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TodoTest {

    @Test
    public void testGetType() {
        assertEquals("T", new ToDo("todo description").getTaskType());
    }

    @Test
    public void testGetDescription() {
        assertEquals("todo description",
                new ToDo("todo description")
            .getDescription());
    }
}
