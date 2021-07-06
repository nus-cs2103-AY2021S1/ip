package duke;

import duke.parser.Parser;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    /**
     * Check if parser returns appropriate command
     */
    @Test
    public void dukeParser_invalidCommand_throwsDukeException() {
        String badCommand = "lepak rsfsefew";

        DukeException thrown = assertThrows(DukeException.class, () -> Parser.parse(badCommand),
                "Expected parse() to throw error, but it didn't");
        assertTrue(thrown.getMessage().contains("OOPS!!! I'm sorry, but I don't know what that means :-("));

    }

    /**
     * Check if parsing of date during creation of DeadlineTask works
     */
    @Test
    public void deadlineTaskCreation_sampleObject_correctToString() {
        assertEquals(new DeadlineTask("return book", LocalDate.parse("2019-10-15")).toString(),
                "[D][✘] return book (by: Oct 15 2019)");
    }

    /**
     * Check if parsing of date during creation of EventTask works
     */
    @Test
    public void eventTaskCreation_sampleObject_correctToString() {
        assertEquals(new EventTask("COVID", LocalDate.parse("2020-10-15")).toString(),
                "[E][✘] COVID (at: Oct 15 2020)");
    }
}