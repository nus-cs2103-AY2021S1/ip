package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testGetType(){
        assertEquals("T", new ToDo("todo description").getTaskType());
    }

    @Test
    public void testGetDescription(){
        assertEquals("todo description",
                new ToDo("todo description")
        .getDescription());
    }
}
