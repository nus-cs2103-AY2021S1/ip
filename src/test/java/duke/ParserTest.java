package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Task;

public class ParserTest {
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        DukeException exception = assertThrows(DukeException.class, () ->
                Parser.parse("This command will throw DukeException"));
        InvalidCommandException invalidCommandException = new InvalidCommandException();
        String expectedExceptionMessage = invalidCommandException.getLocalizedMessage();
        System.out.println(exception.getLocalizedMessage());
        assertEquals(expectedExceptionMessage, exception.getLocalizedMessage());
    }

    @Test
    public void parse_byeCommand_parsedCorrectly() {
        try {
            Command command = Parser.parse("bye");
            assertTrue(command.isExit());
        } catch (DukeException dukeException) {
            System.out.println("Error: " + dukeException.getLocalizedMessage());
        }
    }

    @Test
    public void parse_addCommand_parsedCorrectly() {
        try {
            Command command = Parser.parse("todo read book");
            Task task = command.getTask();
            assertEquals("read book", task.getDescription());
        } catch (DukeException dukeException) {
            System.out.println("Error: " + dukeException.getLocalizedMessage());
        }

    }
}
