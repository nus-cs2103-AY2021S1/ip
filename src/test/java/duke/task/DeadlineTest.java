package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDeadlineException;
import duke.util.DukeDateTime;

public class DeadlineTest {
    private static final String SYMBOL_DONE = "O";
    private static final String SYMBOL_NOT_DONE = "X";

    @Test
    public void toSaveString() throws InvalidDeadlineException {
        String description = "This is a test on toSaveString. /by 2020-03-20 12:00";
        Deadline deadline = Deadline.createDeadline(description);
        deadline.addTags(new String[]{"deadline1", "deadline2", "deadline3"});
        String saveString = deadline.toSaveString();
        String expected = "0deadline " + description + " "
                + Task.TAGS_DELIMITER + " " + "#deadline1 #deadline2 #deadline3";
        assertEquals(expected, saveString);
    }

    @Test
    public void createDeadLine_invalidFormat_exceptionThrown() {
        assertThrows(InvalidDeadlineException.class, () -> {
            String description = "This is an invalid format. /by 2018/09/21 15:00";
            Deadline.createDeadline(description);
        });
    }

    @Test
    public void isDueOn() throws InvalidDeadlineException {
        String description = "This is a test on isDueOn. /by 2018-09-21 15:00";
        Deadline deadline = Deadline.createDeadline(description);
        LocalDate date1 = DukeDateTime.parseDate("2018-09-21");
        LocalDate date2 = DukeDateTime.parseDate("2019-09-21");
        assertTrue(deadline.isDueOn(date1));
        assertFalse(deadline.isDueOn(date2));
    }

    @Test
    public void toStringTest() throws InvalidDeadlineException {
        String description = "This is a test on toString. /by 2022-02-03 08:00";
        Deadline deadline = Deadline.createDeadline(description);
        String expected1 = "[D][" + SYMBOL_NOT_DONE + "] This is a test on toString. (by: Feb 3 2022 08:00 AM)";
        assertEquals(expected1, deadline.toString());

        deadline.markAsDone();

        String expected2 = "[D][" + SYMBOL_DONE + "] This is a test on toString. (by: Feb 3 2022 08:00 AM)";
        assertEquals(expected2, deadline.toString());
    }

    @Test
    public void isDueInNDays() throws InvalidDeadlineException {
        String description = "This is a test on isDueInNDays /by 2021-09-08 08:00";
        Deadline deadline = Deadline.createDeadline(description);
        assertTrue(deadline.isDueInNDays(365));
        assertFalse(deadline.isDueInNDays(5));
    }
}
