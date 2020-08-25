package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTypeTest {

    @Test
    void testToString() {
        assertEquals("[E]", TaskType.EVENT.toString());
    }
}