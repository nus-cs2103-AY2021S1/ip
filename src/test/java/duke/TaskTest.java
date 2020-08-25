package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void toString_normal_formattedString(){
        Task t = new Task("test");
        assertEquals("[✘] test", t.toString());
    }
}
