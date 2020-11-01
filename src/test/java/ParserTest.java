import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Parser;

public class ParserTest {
    private Parser parser = new Parser();
    private String toDoCommand = "todo read book";
    private String deadlineCommand = "deadline return book /by 2019-06-06";
    private String eventCommand = "event project meeting /at 2019-08-06";

    @Test
    void parseCommand_toDoCommand() {
        assertEquals(1, parser.parseCommand(toDoCommand));
    }

    @Test
    void parseCommand_deadlineCommand() {
        assertEquals(2, parser.parseCommand(deadlineCommand));
    }

    @Test
    void parseCommand_eventCommand() {
        assertEquals(3, parser.parseCommand(eventCommand));
    }
}
