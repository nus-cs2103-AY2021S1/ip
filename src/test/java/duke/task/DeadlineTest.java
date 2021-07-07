package duke.task;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void toString_normalInput_correctOutput() {
        Deadline deadline = new Deadline("deadline1", false, LocalDate.of(2021, 12, 31));
        assertEquals("[D][\u2718] deadline1 (by: 2021 Dec 31)", deadline.toString());
    }

    @Test
    void getDataString_noInput_correctOutput() {
        Deadline deadline = new Deadline("deadline1", false, LocalDate.of(2021, 12, 31));
        String[] expected = new String[] {"deadline", "false", "deadline1", "2021-12-31"};
        String[] actual = deadline.getDataStrings();
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < 3; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }
}
