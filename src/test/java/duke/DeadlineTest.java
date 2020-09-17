package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testToString1() {
        Deadline e = new Deadline("sample", "2020-10-20");
        System.out.println(e);
        assertEquals(e.toString(), "[D][✗] sample (by: Oct 20 2020)");
    }

    @Test
    void testToString2() {
        Deadline e = new Deadline("sample", "20-10-2020");
        assertEquals(e.toString(), "[D][✗] sample (by: Oct 20 2020)");
    }

    @Test
    void testToString3() {
        Deadline e = new Deadline("sample", "20/10/2020");
        assertEquals(e.toString(), "[D][✗] sample (by: Oct 20 2020)");
    }

    @Test
    void testToString4() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Deadline("sample", "20/20/2020");
        });
    }
}
