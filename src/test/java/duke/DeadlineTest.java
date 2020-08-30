package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testToString() {
        Deadline e = new Deadline("sample", "2020-10-20");
        System.out.println(e);
        assertEquals(e.toString(), "[D][✗] sample (by: Oct 20 2020)");
    }
}