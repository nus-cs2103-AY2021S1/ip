import duke.DukeException;
import duke.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void getTodoDescription_descriptionEmpty_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Parser.getTodoDescription("");});
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        Exception e = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("Banana");});
    }

    @Test
    public void toFileString_correctFileStringPrinted() {
        String todoFileString = new Todo("Read Book").toFileString();
        assertEquals(todoFileString, "T | 0 | Read Book");

        String eventFileString = new Event("Gym", "2020-02-02").toFileString();
        assertEquals(eventFileString, "E | 0 | Gym | 2020-02-02");

        String deadlineFileString = new Deadline("Go home", "2020-02-02").toFileString();
        assertEquals(deadlineFileString, "D | 0 | Go home | 2020-02-02");
    }
}