import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void testToString() {
        Task task = new Deadline("do homework /by 2020-08-26");
        assertEquals("[D][✘] do homework (by: Aug 26 2020)", task.toString());
    }
}