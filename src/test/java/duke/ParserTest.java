package duke;

import org.junit.jupiter.api.Test;
import task.TaskType;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    private final String ddl = "deadline d /by Monday";
    private final String event = "event e /at 5pm";
    private final String todo = "todo t";

    @Test
    public void taskType_validCommand_success() {
        assertEquals(TaskType.DEADLINE, Parser.taskType(ddl));
        assertEquals(TaskType.EVENT, Parser.taskType(event));
        assertEquals(TaskType.TODO, Parser.taskType(todo));
    }

    @Test
    public void taskType_missingKeyword_exceptionThrown() {
        try {
            assertEquals(TaskType.DEADLINE, Parser.taskType("deadline b Monday"));
            fail();
        } catch (InvalidParameterException e) {
            assertEquals("Invalid input", e.getMessage());
        }
    }

    @Test
    public void getName_validCommand_success() {
        assertEquals("d", Parser.getName(ddl));
        assertEquals("e", Parser.getName(event));
        assertEquals("t", Parser.getName(todo));
    }

    @Test
    public void getTime_validCommand_success() {
        assertEquals("Monday", Parser.getTime(ddl));
        assertEquals("5pm", Parser.getTime(event));
    }


}
