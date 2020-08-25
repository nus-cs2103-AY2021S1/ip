package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class DeadlineTest {
    @Test
    public void toString_test_formattedString() {
        Task t = new Deadline("test", true, LocalDate.parse("2020-03-19"));
        assertEquals("[D][✓] test (by: Mar 19 2020)", t.toString());
    }
}
