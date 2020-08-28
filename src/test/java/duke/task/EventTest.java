package duke.task;

import duke.storage.CsvToTask;
import duke.util.DukeDateTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.fail;

class EventTest {

    private String string1 = "Test case 1";
    private DukeDateTime time1 = new DukeDateTime(LocalDateTime.now().minusHours(1).format(DukeDateTime.DEFAULT));

    private String string2 = "Test case 2";
    private DukeDateTime time2 = new DukeDateTime();

    @Test
    void getStart() {
        Event testCase = new Event(string1, time1, time2);
        assertEquals (time1, testCase.getStart());
    }

    @Test
    void getEnd() {
        Event testCase = new Event(string1, time1, time2);
        assertEquals (time2, testCase.getEnd());
    }

    @Test
    void hashCode_equals() {
        Event case1 = new Event(string1, time1, time2);
        Event case2 = new Event(string1, time1, time2);
        assertEquals (case1.hashCode(), case2.hashCode());
    }

    @Test
    void hashCode_notEquals() {
        Event case1 = new Event(true, string1, time1, time2);
        Event case2 = new Event(false, string1, time1, time2);
        assertNotEquals(case1.hashCode(), case2.hashCode());

        case1 = new Event(true, string1, time1, time2);
        case2 = new Event(true, string2, time1, time2);
        assertNotEquals(case1.hashCode(), case2.hashCode());

        case1 = new Event(true, string1, time1, time2);
        case2 = new Event(true, string1, time2, time2);
        assertNotEquals(case1.hashCode(), case2.hashCode());

        case1 = new Event(true, string1, time1, time1);
        case2 = new Event(true, string1, time1, time2);
        assertNotEquals(case1.hashCode(), case2.hashCode());

    }

    @Test
    void toCsv_fromCsv() {
        try {
            Task task1 = new Event(string1, time1, time2);
            Task task2 = CsvToTask.EVENT.parse(task1.toCsv());
            assertEquals(task1.hashCode(), task2.hashCode());
        } catch (Exception e) {
            // Failed to convert csv to Event
            fail();
        }
    }

}
