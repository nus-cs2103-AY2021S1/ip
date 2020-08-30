package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_invalidDeleteCommand_exceptionThrown() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("delete letter");
        });
        String expected = "SORRY!!! Task number is not valid.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_invalidArgumentCommand_exceptionThrown() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("hey");
        });
        String expected = "SORRY!!! I don't know what that means :-(";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_emptyTodoCommand_exceptionThrown() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("todo");
        });
        String expected = "SORRY!!! The description of a task cannot be empty.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_emptyDeadlineDate_exceptionThrown() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("deadline CS2103T /by");
        });
        String expected = "SORRY!!! The description of a task cannot be empty.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_invalidEventDate_exceptionThrown() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("event CCA /at 7th June");
        });
        String expected = "SORRY!!! Wrong date format encountered!";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }
}