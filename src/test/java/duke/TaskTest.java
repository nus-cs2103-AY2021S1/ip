package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;



public class TaskTest {

    @Test
    public void getTask_getStringDescription_success() {
        Task task = new Task("Borrow book");
        assertEquals("Borrow book", task.getTask());
    }

}
