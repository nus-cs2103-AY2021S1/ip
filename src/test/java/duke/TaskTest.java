package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskTest {

    @Test
    public void getTask_getStringDescription_success() {
        Task task = new Task("Borrow book");
        assertEquals("Borrow book", task.getTask());
    }

}
