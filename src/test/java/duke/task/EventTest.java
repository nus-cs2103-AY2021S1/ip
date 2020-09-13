package duke.task;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class EventTest {
    @Test
    public void createEvent_withDescription_stringReturned() {
        Event test;
        try {
            test = new Event("dinner party", "2020/02/14 14:50");
            assertTrue(test.toString().contains("E |"));
        } catch (DukeException e) {
            System.out.println("Test failed");
        }
    }

    @Test
    public void createEvent_withWrongDateFormat_exceptionThrown() {
        Event test;
        try {
            test = new Event("dinner party", "200/202/14 14:50");
        } catch (DukeException e) {
            assertTrue(e.getMessage().contains("Please input date and time"));
        }
    }
}
