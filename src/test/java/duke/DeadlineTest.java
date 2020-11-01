package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void testToString1() {
        Deadline e = new Deadline("sample", "2020-10-20");
        assertEquals("[D][✗] sample (by: Oct 20 2020)", e.toString());
    }

    @Test
    void testToString2() {
        Deadline e = new Deadline("sample", "20-10-2020");
        assertEquals("[D][✗] sample (by: Oct 20 2020)", e.toString());
    }

    @Test
    void testToString3() {
        Deadline e = new Deadline("sample", "20/10/2020");
        assertEquals("[D][✗] sample (by: Oct 20 2020)", e.toString());
    }

    @Test
    void testToString4() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Deadline("sample",
            "20/20/2020"));
    }
}
