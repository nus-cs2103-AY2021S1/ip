package duke.tasks;

import duke.Storage;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {
    DateTimeFormatter formatter = Storage.FORMATTER;

    @Test
    public void constructorTest() {
        Deadline deadlineTask = new Deadline("read book ",
                LocalDateTime.parse("2020-10-10 10:10", formatter));
        assertEquals("[D][\u2718] read book (by: 10/10/2020 10:10 AM)", deadlineTask.toString());
    }

    @Test
    void getState() {
        Deadline deadlineTask = new Deadline("read book ",
                LocalDateTime.parse("2020-10-10 10:10", formatter));
        assertEquals("D|0|read book |2020-10-10 10:10", deadlineTask.getState());
    }

}