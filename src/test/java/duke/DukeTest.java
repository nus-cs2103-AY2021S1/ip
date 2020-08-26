package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;

public class DukeTest {
    /**
     * Check if parse method for Parser validates commands
     */
    @Test
    public void dukeParser_invalidCommand_throwsDukeException() {
        String invalidCommand = "Do Nothing";
        DukeException thrownException = assertThrows(DukeException.class, () -> Parser.parse(invalidCommand),
                "Expected DukeException to be thrown");
        assertTrue(thrownException.getMessage().contains("☹ OOPS!!! I'm sorry, " +
                "but I don't know what that means :-("));
    }

    /**
     * Check if parse method for Parser gives correct Date and Time format for deadlines
     */
    @Test
    public void deadlineCreation_sampleObject_correctStringRepresentation() {
        LocalDateTime date = LocalDateTime.parse("2020-11-14 1900",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        assertEquals("[D][✗] CS2100 assignment (by: 14-11-2020 7:00PM)",
                new Deadline("CS2100 assignment", date).toString());
    }

    /**
     * Check if parse method for Parser gives correct Date and Time format for events
     */
    @Test
    public void eventCreation_sampleObject_correctStringRepresentation() {
        LocalDateTime date = LocalDateTime.parse("2020-08-30 1345",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        assertEquals("[E][✗] presentation (at: 30-08-2020 1:45PM)",
                new Event("presentation", date).toString());
    }
}
