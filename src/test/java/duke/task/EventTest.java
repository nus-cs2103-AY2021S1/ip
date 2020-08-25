package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    @Test
    public void testStringConversion() {
        Event task = new Event("borrow book", LocalDate.parse("2020-09-24"));
        assertEquals("[E][\u2718] borrow book (at: Sep 24 2020)", task.toString());
        task.done();
        assertEquals("[E][\u2713] borrow book (at: Sep 24 2020)", task.toString());
    }
}