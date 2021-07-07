package duke.types;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTypeTest {
    private static TaskType deadlineType;
    private static TaskType eventType;
    private static TaskType todoType;

    @BeforeAll
    static void setUp() {
        deadlineType = TaskType.DEADLINE;
        eventType = TaskType.EVENT;
        todoType = TaskType.TODO;
    }

    @Test
    void getType() {
        String[] expectedArr = {deadlineType.getType(),
                eventType.getType(),
                todoType.getType()};
        String[] actualArr = {"deadline", "event", "todo"};
        assertArrayEquals(expectedArr, actualArr);
    }

    @Test
    void hasTime() {
        assertTrue(TaskType.hasTime("deadline"));
        assertTrue(TaskType.hasTime("event"));
        assertFalse(TaskType.hasTime("todo"));
    }

    @Test
    void valueOfDeadlineType() {
        TaskType deadlineType = TaskType.valueOfType("deadline");
        assertEquals(TaskType.DEADLINE, deadlineType);
    }

    @Test
    void valueOfEventType() {
        TaskType deadlineType = TaskType.valueOfType("event");
        assertEquals(TaskType.EVENT, deadlineType);
    }

    @Test
    void valueOfTodoType() {
        TaskType deadlineType = TaskType.valueOfType("todo");
        assertEquals(TaskType.TODO, deadlineType);
    }
}
