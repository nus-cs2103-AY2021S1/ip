package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.task.TaskType;

public class TaskParserTest {

    @Test
    public void parseTaskDescription_correctInput_success() {
        try {
            String description = "test /at 20-3-20 1420";
            String description1 = "exam /by 20-3-20 1600";
            String[] outputArr = TaskParser.parseTaskDescription(description, TaskType.EVENT);
            String[] outputArr1 = TaskParser.parseTaskDescription(description1, TaskType.DEADLINE);

            assertEquals("test", TaskParser.getTaskDetails(outputArr));
            assertEquals("20-3-20 1420", TaskParser.getTime(outputArr));

            assertEquals("exam", TaskParser.getTaskDetails(outputArr1));
            assertEquals("20-3-20 1600", TaskParser.getTime(outputArr1));

        } catch (DukeException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    @Test
    public void parseTaskDescription_wrongInput_success() {
        String description = "test /by 20-3-20 1420";
        String description1 = "exam /at 20-3-20 1420";
        assertThrows(InvalidEventException.class, () ->
                TaskParser.parseTaskDescription(description, TaskType.EVENT));

        assertThrows(InvalidDeadlineException.class, () ->
                TaskParser.parseTaskDescription(description1, TaskType.DEADLINE));
    }

}
