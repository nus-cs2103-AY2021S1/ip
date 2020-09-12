package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void newDeadline_validArguments_deadlineObjectReturned() {
        try {
            LocalDate deadlineDate = LocalDate.parse("2020-05-18");
            LocalDateTime deadlineDateAndTime = deadlineDate.atTime(21, 17);
            new Deadline("homework", deadlineDate, deadlineDateAndTime);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void newDeadline_validArgumentsWithIsDoneSet_deadlineObjectReturned() {
        try {
            LocalDate deadlineDate = LocalDate.parse("2020-05-18");
            LocalDateTime deadlineDateAndTime = deadlineDate.atTime(21, 17);
            new Deadline("homework", deadlineDate, deadlineDateAndTime, true);
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toStringForMemory_taskNotDone() {
        LocalDate deadlineDate = LocalDate.parse("2020-05-18");
        LocalDateTime deadlineDateAndTime = deadlineDate.atTime(21, 17);
        Deadline deadline = new Deadline("homework", deadlineDate, deadlineDateAndTime);
        assertEquals("[D]|0|homework|2020-05-18T21:17:00", deadline.toStringForMemory());
    }

    @Test
    public void toStringForMemory_taskDone() {
        LocalDate deadlineDate = LocalDate.parse("2020-05-18");
        LocalDateTime deadlineDateAndTime = deadlineDate.atTime(21, 17);
        Deadline deadline = new Deadline("homework", deadlineDate, deadlineDateAndTime, true);
        assertEquals("[D]|1|homework|2020-05-18T21:17:00", deadline.toStringForMemory());
    }

    @Test
    public void toStringForGui_taskNotDone() {
        LocalDate deadlineDate = LocalDate.parse("2020-05-18");
        LocalDateTime deadlineDateAndTime = deadlineDate.atTime(21, 17);
        Deadline deadline = new Deadline("homework", deadlineDate, deadlineDateAndTime);
        assertEquals("[D][\u2718] homework (by: 18 May 2020 @ 09:17pm)", deadline.toStringForGui());
    }

    @Test
    public void toStringForGui_taskDone() {
        LocalDate deadlineDate = LocalDate.parse("2020-05-18");
        LocalDateTime deadlineDateAndTime = deadlineDate.atTime(21, 17);
        Deadline deadline = new Deadline("homework", deadlineDate, deadlineDateAndTime, true);
        assertEquals("[D][\u2713] homework (by: 18 May 2020 @ 09:17pm)", deadline.toStringForGui());
    }

    @Test
    public void toStringForCli_taskNotDone() {
        LocalDate deadlineDate = LocalDate.parse("2020-05-18");
        LocalDateTime deadlineDateAndTime = deadlineDate.atTime(21, 17);
        Deadline deadline = new Deadline("homework", deadlineDate, deadlineDateAndTime);
        assertEquals("[D][✘] homework (by: 18 May 2020 @ 09:17pm)", deadline.toStringForCli());
    }

    @Test
    public void toStringForCli_taskDone() {
        LocalDate deadlineDate = LocalDate.parse("2020-05-18");
        LocalDateTime deadlineDateAndTime = deadlineDate.atTime(21, 17);
        Deadline deadline = new Deadline("homework", deadlineDate, deadlineDateAndTime, true);
        assertEquals("[D][✓] homework (by: 18 May 2020 @ 09:17pm)", deadline.toStringForCli());
    }
}
