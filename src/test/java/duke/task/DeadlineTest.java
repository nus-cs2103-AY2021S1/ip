package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTest {
    @Test
    public void deadlineCreation_normalInput_success() {
        Deadline deadline = new Deadline("read book", LocalDateTime.of(2020,02,14,15,50));
        Assertions.assertEquals("[D][\u2718] read book (by: Feb 14 2020 1550)", deadline.toString());
    }

    @Test
    public void deadlineCreation_noDesc_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("", LocalDateTime.now());
            Assertions.fail();
        } catch (DukeException de) {
            Assertions.assertEquals(
                    new DukeException("The description or date of \"deadline\" cannot be empty").getMessage(),
                    de.getMessage());
        }
    }

    @Test
    public void deadlineCreation_dateTimeIsNull_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("Lunch", null);
            Assertions.fail();
        } catch (DukeException de) {
            Assertions.assertEquals(
                    new DukeException("The description or date of \"deadline\" cannot be empty").getMessage(),
                    de.getMessage());
        }
    }

    @Test
    public void deadlineDone_setDone_success() {
        Deadline deadline = new Deadline("read book", LocalDateTime.parse("2019-09-11T13:40"));
        deadline.setDone();
        Assertions.assertEquals("[D][\u2713] read book (by: Sep 11 2019 1340)", deadline.toString());
    }

    @Test
    public void deadlineExport_noInput_success() {
        Deadline deadline = new Deadline("wash clothes",
                LocalDateTime.parse("10/11/2018 0800", DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")));
        deadline.setDone();
        Assertions.assertEquals("D`1`wash clothes`10/11/2018 0800", deadline.getSaveToFileString());
    }
}
