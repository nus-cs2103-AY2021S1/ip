package duke.tasks;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ToDoTest {
    @Test
    public void testStringConversion(){
        assertEquals("[T][✘] join sports club", new ToDo("join sports club").toString());
    }

}