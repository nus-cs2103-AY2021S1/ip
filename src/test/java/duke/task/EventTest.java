package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toStringTest1() {
        assertEquals(new Event("this event", "a typed non-formatted string of time").toString()
                ,"[E][✘] this event (at: a typed non-formatted string of time)");
    }

    @Test
    public void toStringTest2() {
        assertEquals(new Event("that event", "2020-10-10").toString()
                ,"[E][✘] that event (at: Oct 10 2020)");
    }

    @Test
    public void toStringTest3() {
        Event event = new Event("new event", "2020-10-10");
        event.markAsDone();
        assertEquals(event.toString()
                ,"[E][\u2713] new event (at: Oct 10 2020)");
    }
}