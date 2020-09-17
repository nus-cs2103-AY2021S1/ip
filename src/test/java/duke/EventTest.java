package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class EventTest {

    @Test
    void testToString1() {
        Event e = new Event("sample", "2020-10-20");
        System.out.println(e);
        assertEquals(e.toString(), "[E][✗] sample (at: Oct 20 2020)");
    }

    @Test
    void testToString2() {
        Event e = new Event("sample", "2020-10-20", "2020-10-30");
        System.out.println(e);
        assertEquals(e.toString(), "[E][✗] sample (between: Oct 20 2020 and Oct 30 2020)");
    }

    @Test
    void testToString3() {
        Event e = new Event("sample", "20-10-2020", "2020/10/30");
        System.out.println(e);
        assertEquals(e.toString(), "[E][✗] sample (between: Oct 20 2020 and Oct 30 2020)");
    }
}
