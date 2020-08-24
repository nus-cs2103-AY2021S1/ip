package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void getSize_sizeZero_success() {
        assertEquals(0, new TaskList().getSize());
    }
}
