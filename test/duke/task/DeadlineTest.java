package duke.task;

import duke.DukeDateTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    String string1 = "Test case 1";
    DukeDateTime time1 = new DukeDateTime(LocalDateTime.now().minusHours(1).format(DukeDateTime.DEFAULT));

    String string2 = "Test case 2";
    DukeDateTime time2 = new DukeDateTime();

    @Test
    void getDeadline() {
        Deadline testCase = new Deadline(string1, time1);
        assertEquals(time1, testCase.getDeadline());
    }

    @Test
    void hashCode_equals() {
        Deadline case1 = new Deadline(string1, time1);
        Deadline case2 = new Deadline(string1, time1);
        assertEquals(case1.hashCode(), case2.hashCode());
    }

    @Test
    void hashCode_notEquals() {
        Deadline case1 = new Deadline(string1, time1);
        Deadline case2 = new Deadline(string2, time1);
        assertNotEquals(case1.hashCode(), case2.hashCode());

        case1 = new Deadline(string1, time1);
        case2 = new Deadline(string1, time2);
        assertNotEquals(case1.hashCode(), case2.hashCode());
    }

    @Test
    void toCSV_fromCSV() {
        try {
            Task task1 = new Deadline(string1, time1);
            Task task2 = TaskFactory.DEADLINE.fromCsv(task1.toCsv());
            assertEquals(task1.hashCode(), task2.hashCode());
        } catch (Exception e) {
            // Failed to convert csv to Deadline
            fail();
        }
    }
}