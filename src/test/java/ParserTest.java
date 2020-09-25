import duke.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    @Test
    public void parseTask_validDeadline_success() throws DukeException {
        String fullTask = "D | 0 | Sample Task | Oct 12 2020 8:00 PM";
        Task actualTask = Parser.parseTask(fullTask);
        Task expectedTask = new Deadline("Sample Task", false,
                LocalDateTime.of(2020, 10, 12, 20, 0));
        assertEquals(expectedTask.toDisplayString(), actualTask.toDisplayString());
    }

    @Test
    public void parseTask_validEvent_success() throws DukeException {
        String fullTask = "E | 1 | Sample Event | Oct 12 2020 8:00 PM";
        Task actualTask = Parser.parseTask(fullTask);
        Task expectedTask = new Event("Sample Event", true,
                LocalDateTime.of(2020, 10, 12, 20, 0));
        assertEquals(expectedTask.toDisplayString(), actualTask.toDisplayString());
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        try {
            String fullCommand = "blah";
            Command command = Parser.parseCommand(fullCommand);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter your command in the correct format!",
                    e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidDeadlineFormat_exceptionThrown() {
        try {
            String fullCommand = "deadline Sample Task /by 121020202000";
            Command command = Parser.parseCommand(fullCommand);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a valid date and time in the format 'DD-MM-YYYY HHMM'!",
                    e.getMessage());
        }
    }

}
