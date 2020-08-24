package duke.task;

import duke.DukeDateTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EventTest {

    LocalDateTime now1 = LocalDateTime.now().minusHours(3);
    String input1 = now1.format(DukeDateTime.FORMAT);
    DukeDateTime time1 = new DukeDateTime(input1);

    LocalDateTime now2 = LocalDateTime.now().minusHours(2);
    String input2 = now2.format(DukeDateTime.FORMAT);
    DukeDateTime time2 = new DukeDateTime(input2);

    @Test
    void getStartAndGetEnd() {
        Event testCase = new Event("test case", time1, time2);
        assertEquals(now1.toString().substring(0, 16), testCase.getStart().toString());
        assertEquals(now2.toString().substring(0, 16), testCase.getEnd().toString());
    }

    @Test
    void hashCode_equals() {
        Event case1 = new Event("test case", time1, time2);
        Event case2 = new Event("test case", time1, time2);
        assertEquals(case1.hashCode(), case2.hashCode());
    }

    @Test
    void hashCode_notEquals() {
        Event case1 = new Event( true,"test case1", time1, time2);
        Event case2 = new Event( false,"test case1", time1, time2);
        assertNotEquals(case1.hashCode(), case2.hashCode());

        case1 = new Event( true,"test case1", time1, time2);
        case2 = new Event( true,"test case2", time1, time2);
        assertNotEquals(case1.hashCode(), case2.hashCode());

        case1 = new Event( true,"test case1", time1, time2);
        case2 = new Event( true,"test case1", time2, time2);
        assertNotEquals(case1.hashCode(), case2.hashCode());

        case1 = new Event( true,"test case1", time1, time1);
        case2 = new Event( true,"test case1", time1, time2);
        assertNotEquals(case1.hashCode(), case2.hashCode());

    }
    @Test
    void toCSV_fromCSV() {
        Task task1 = new Event("duke.task one", time1, time2);
        Task task2 = Event.fromCSV(task1.toCSV());
        assertEquals(task1.hashCode(), task2.hashCode());
    }

}