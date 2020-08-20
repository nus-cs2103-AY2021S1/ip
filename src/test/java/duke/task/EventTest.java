package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class EventTest {
    @Test
    public void getDateString() {
        LocalDate date = LocalDate.parse("2020-03-19");
        Deadline deadline = new Deadline("name", false, date);
        assertEquals("Mar 19 2020", deadline.getSchedule());
    }
}

