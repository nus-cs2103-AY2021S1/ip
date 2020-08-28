package duke;

import duke.command.Command;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testParserUnknownCommand(){
        DukeException exception = assertThrows(DukeException.class,
                () -> Parser.parse("This command will throw DukeException"));
        assertEquals(exception.getLocalizedMessage(), ":( Oops!!! I'm sorry, but I don't know what that means :-(\n\tCommands: " +
                "list | done | delete | todo | deadline | event");
    }

    @Test
    public void testParserByeCommand() {
        try {
            Command command = Parser.parse("bye");
            assertTrue(command.isExit());
        } catch (DukeException dukeException) {
            System.out.println("Error: " + dukeException.getLocalizedMessage());
        }
    }

    @Test
    public void testParserAddCommand(){
        try {
            Command command = Parser.parse("todo read book");
            Task task = command.getTask();
            assertEquals("read book", task.getDescription());
        } catch (DukeException dukeException) {
            System.out.println("Error: " + dukeException.getLocalizedMessage());
        }

    }
}
