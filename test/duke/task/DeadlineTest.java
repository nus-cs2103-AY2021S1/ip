package duke.task;

import duke.DukeDateTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class DeadlineTest {

    String string1 = "Test case 1";
    LocalDateTime now1 = LocalDateTime.now().minusHours(3);
    String input1 = now1.format(DukeDateTime.FORMAT);
    DukeDateTime time1 = new DukeDateTime(input1);

    String string2 = "Test case 2";
    LocalDateTime now2 = LocalDateTime.now().minusHours(2);
    String input2 = now2.format(DukeDateTime.FORMAT);
    DukeDateTime time2 = new DukeDateTime(input2);

    @Test
    void getDeadline() {
        Deadline testCase = new Deadline(string1, time1);
        assertEquals(now1.toString().substring(0, 16), testCase.getDeadline().toString());
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
        Task task1 = new Deadline(string1, time1);
        Task task2 = Deadline.fromCsv(task1.toCsv());
        assertEquals(task1.hashCode(), task2.hashCode());
    }
}