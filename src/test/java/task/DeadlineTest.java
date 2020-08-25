package task;

import main.java.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testNormalCase() {
        Deadline task = new Deadline("read book", LocalDateTime.of(2020,12,20,19,30));
        String actual = task.serialize();
        String expected = "deadline%%%read book%%%20/12/2020 1930%%%0";
        assertEquals(actual, expected);
    }
}
