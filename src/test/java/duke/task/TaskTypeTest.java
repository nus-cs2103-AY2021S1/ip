package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaskTypeTest {

    @Test
    void testToString() {
        assertEquals("[E]", TaskType.EVENT.toString());
    }
}
