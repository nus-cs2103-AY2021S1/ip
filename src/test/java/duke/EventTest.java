package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void testToString1() {
        Event e = new Event("sample", "2020-10-20");
        System.out.println(e);
        assertEquals(e.toString(), "[E][✗] sample (at: Oct 20 2020)");
    }

}