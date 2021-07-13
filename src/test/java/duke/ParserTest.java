package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parse_deleteCommand_invalidTaskNumber() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("delete bla");
        });
        String expected = "Uh-oh! Looks like you have entered an invalid task number.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_deleteCommand_taskNumberMissing() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("delete");
        });
        String expected = "Uh-oh! You did not enter the number of the task you wish to delete.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_doneCommand_invalidTaskNumber() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("done blab");
        });
        String expected = "Uh-oh! Looks like you have entered an invalid task number.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_doneCommand_taskNumberMissing() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("done");
        });
        String expected = "Uh-oh! You did not enter the number of the task you wish to complete.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_invalidEntry() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("bla");
        });
        String expected = "Uh-oh! I have no idea what that means.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_deadlineTask_invalidDateFormat() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("deadline CS2103T project /by tonight");
        });
        String expected = "Uh-oh! Looks like you have got the format for the date wrong.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_eventTask_invalidDateFormat() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("event team meeting /at friday");
        });
        String expected = "Uh-oh! Looks like you have got the format for the date wrong.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_emptyDescription() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("deadline");
        });
        String expected = "Uh-oh! The description of your task cannot be empty.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_wrongDescriptionFormat() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("event /at2020-05-09");
        });
        String expected = "Uh-oh! Looks like you have got the format for the description wrong.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }
}
