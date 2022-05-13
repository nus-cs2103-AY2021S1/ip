package duke.tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class TaskTest {
    @Test
    public void defaultConstructor_noInput_falseStatusTaskBuilt() {
        Task task = new Task("my task");
        Assertions.assertEquals(false, task.getStatus());
    }

    @Test
    public void normalConstructor_trueInput_trueStatusTaskBuilt() {
        Task task = new Task("my task", true);
        Assertions.assertEquals(true, task.getStatus());
    }
}
