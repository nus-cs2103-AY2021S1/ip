package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_DeadlineInvalidDate_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("deadline project /by 12");
        });

        String expectedMessage = "☹ OOPS!!! The date is not valid.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parse_EventEmptyDate_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("event project /at");
        });

        String expectedMessage = "☹ OOPS!!! The description or date cannot be empty.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parse_emptyCommand_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("");
        });

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("hi");
        });

        String expectedMessage = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parse_invalidDoneCommand_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("done q");
        });

        String expectedMessage = "☹ OOPS!!! The task number is not valid.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void parse_emptyDeleteCommand_exceptionThrown() {
        Exception exception = assertThrows(DukeException.class, () -> {
            Parser.parse("delete");
        });

        String expectedMessage = "☹ OOPS!!! The description or date cannot be empty.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
