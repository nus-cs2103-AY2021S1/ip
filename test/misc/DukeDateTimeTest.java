package misc;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class DukeDateTimeTest {

    LocalDateTime now1 = LocalDateTime.now().minusHours(3);
    String input1 = now1.format(DukeDateTime.FORMAT);
    DukeDateTime case1 = new DukeDateTime(input1);

    LocalDateTime now2 = LocalDateTime.now();
    String input2 = now2.format(DukeDateTime.FORMAT);
    DukeDateTime case2 = new DukeDateTime(input2);

    @Test
    void constructor_invalidFormat_exceptionThrown() {
        try {
            new DukeDateTime("20200101 1800");
            fail();
        } catch (DateTimeParseException e) {
            return;
        }
        fail();
    }

    @Test
    void constructor_emptyString_failure() {
        try {
            new DukeDateTime("");
            fail();
        } catch (DateTimeParseException e) {
            return;
        }
        fail();
    }

    @Test
    void pretty() {
        assertEquals(now1.format(DukeDateTime.PRETTY), case1.pretty());
    }

    @Test
    void testToString() {
        assertEquals(input1, case1.toString());
    }

    @Test
    void compareTo_equals() {
        LocalDateTime now1_1 = LocalDateTime.now().minusHours(3);
        String input1_1 = now1_1.format(DukeDateTime.FORMAT);
        DukeDateTime case1_1 = new DukeDateTime(input1_1);

        assertEquals(0, this.case1.compareTo(case1_1));
    }

    @Test
    void compareTo_lessThan() {
        assertEquals(-1, case1.compareTo(case2));
    }

    @Test
    void compareTo_greaterThan() {
        assertEquals(1, case2.compareTo(case1));
    }

}