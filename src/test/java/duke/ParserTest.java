package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void parse_deleteCommand_invalidTaskNumber_testExpectedException() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("delete blabla");
        });
        String expected = "Uh-oh! Looks like you have entered an invalid task number.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_deleteCommand_taskNumberMissing_testExpectedException() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("delete");
        });
        String expected = "Uh-oh! You did not enter the number of the task you wish to delete.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_doneCommand_invalidTaskNumber_testExpectedException() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("done blabla");
        });
        String expected = "Uh-oh! Looks like you have entered an invalid task number.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_doneCommand_taskNumberMissing_testExpectedException() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("done");
        });
        String expected = "Uh-oh! You did not enter the number of the task you wish to complete.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_invalidEntry_testExpectedException() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("blabla");
        });
        String expected = "Uh-oh! I have no idea what that means.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_deadlineTask_invalidDateFormat_testExpectedException() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("deadline CS2103T project /by tonight");
        });
        String expected = "Uh-oh! Looks like you have got the format for the date wrong.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_eventTask_invalidDateFormat_testExpectedException() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("event team meeting /at friday");
        });
        String expected = "Uh-oh! Looks like you have got the format for the date wrong.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_emptyDescription_testExpectedException() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("deadline");
        });
        String expected = "Uh-oh! The description of your task cannot be empty.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }

    @Test
    public void parse_wrongDescriptionFormat_testExpectedException() {
        Exception e = assertThrows(DukeException.class, () -> {
            Parser.parse("event /at2020-05-09");
        });
        String expected = "Uh-oh! Looks like you have got the format for the description wrong.";
        String actual = e.getMessage();
        assertEquals(expected, actual);
    }
}