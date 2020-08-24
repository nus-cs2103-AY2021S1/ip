package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {
    @Test
    public void constructor_emptyDescription_taskException() {
        assertThrows(TaskException.class, () -> new Event("", LocalDateTime.now()));
    }

    @Test
    public void getTaskType() {
        try {
            Event t = new Event("Some desc", LocalDateTime.now());
            assertEquals(t.getTaskType(), TaskType.EVENT);
        } catch (Exception e) {
            fail();
        }
    }
}
