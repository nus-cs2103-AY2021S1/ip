import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import dev.jingyen.duke.model.Deadline;
import dev.jingyen.duke.model.Todo;
import dev.jingyen.duke.parser.TaskParser;

class TaskParserTest {

    @Test
    void parse() {
    }

    @Test
    void parseEvent() {
    }

    @Test
    void parseDeadline() {
        boolean isDone = true;
        String taskName = "A task";
        String deadlineStr = "2020-08-26";
        LocalDate date = LocalDate.of(2020, 8, 26);

        Deadline deadline = new Deadline(isDone, taskName, date);
        Deadline result = TaskParser.parseDeadline(isDone, taskName, deadlineStr);

        assertEquals(deadline, result);

        String invalidDate = "not a valid date string";
        assertThrows(DateTimeParseException.class, () ->
                TaskParser.parseDeadline(isDone, taskName, invalidDate));
    }

    @Test
    void parseTodo() {
        String taskName = "A task";
        Todo task = new Todo(taskName);

        Todo result = TaskParser.parseTodo(false, taskName);
        assertEquals(task, result);
    }

    @Test
    void parseInput() {
    }
}
