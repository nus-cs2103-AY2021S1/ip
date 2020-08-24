package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
    @Test
    public void constructor_emptyDescription_taskException() {
        assertThrows(TaskException.class, () -> new Deadline("", LocalDateTime.now()));
    }

    @Test
    public void getTaskType() {
        try {
            Deadline t = new Deadline("Some desc", LocalDateTime.now());
            assertEquals(t.getTaskType(), TaskType.DEADLINE);
        } catch (Exception e) {
            fail();
        }
    }
}
