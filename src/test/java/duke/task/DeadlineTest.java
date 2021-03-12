package duke.task;

import static duke.util.Keyword.DATE_TIME_INPUT_FORMAT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_oneParam_success() {
        Deadline newDeadline = new Deadline("workout", LocalDateTime.parse("23-8-20 1420",
                DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT)));
        assertFalse(newDeadline.isDone());
        assertEquals("[D][\u2718] workout (by: 23 Aug 2020 @ 2.20 PM)", newDeadline.toString());
    }

    @Test
    public void constructor_fullParams_success() {
        Deadline newDeadline = new Deadline("workout", true, "-", LocalDateTime.parse("23-8-20 1420",
                DateTimeFormatter.ofPattern(DATE_TIME_INPUT_FORMAT)));
        assertTrue(newDeadline.isDone());
        assertEquals("[D][\u2713] workout (by: 23 Aug 2020 @ 2.20 PM)", newDeadline.toString());
    }

    @Test
    public void getTimeFrame_defaultTimeFrame_success() {
        Deadline deadline = new Deadline("test", LocalDateTime.now());
        assertEquals("-", deadline.getTimeFrame());
    }

}
