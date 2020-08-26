package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EventsTest {

    @Test
    void EventsPrintTimeTest() {
        Events event=new Events("event1", "2020-08-22 17:00",false);
        assertEquals("22 Aug 2020 05:00 pm", event.printTime());
    }

    @Test
    void EventToStringTest() {
        Events event=new Events("event2", "2020-08-22 17:00",false);
        assertEquals("[E][✗] event2 (at: 22 Aug 2020 05:00 pm)", event.toString());
    }
}