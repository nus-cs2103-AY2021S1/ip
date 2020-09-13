package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.commands.Command;


public class ParserTest {
    private Parser parser = new Parser();
    @Test
    public void doneCommand_noNumberGiven_exceptionThrown() {
        try {
            Command command = parser.findCommand("done five");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Hi my friend, you need to give a number.");
        }
    }

    @Test
    public void todoCommand_noDescriptionGiven_exceptionThrown() {
        try {
            Command command = parser.findCommand("todo");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Hi my friend, you don't even know what you want to do.");
        }
    }

    @Test
    public void findCommand_wrongCommandGiven_exceptionThrown() {
        try {
            Command command = parser.findCommand("Todo");
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "Hi my friend, type in 'todo', 'deadline', 'event' to start!\n"
                    + "Also, type 'schedule' and key in a date in YYYY-MM-DD format "
                    + "to view schedule on that date!\n"
                    + "Or type 'time' and key in time in HH:mm format "
                    + "to search for events/deadline happening on that time!");
        }
    }
}
