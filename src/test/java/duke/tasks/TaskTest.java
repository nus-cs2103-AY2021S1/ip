package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.assertEquals;

public class TaskTest {
    @Test
    public void defaultConstructor_noInput_falseStatusTaskBuilt() {
        Task task = new Task("my task");
        assertEquals(false, task.getStatus());
    }

    @Test
    public void normalConstructor_trueInput_trueStatusTaskBuilt() {
        Task task = new Task("my task", true);
        assertEquals(true, task.getStatus());
    }
}
