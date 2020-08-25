package duke.task;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void create_Deadline_correctly() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Deadline d = new Deadline("iP Week 3", date);
        assertEquals( "[D][✘] iP Week 3 (by: August 27 2020)", d.toString());
    }

    @Test
    void encode_incompleteDeadline_Test() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Deadline d = new Deadline("iP Week 3", date);
        assertEquals("D | 0 | iP Week 3 | 2020-08-27", d.encode());
    }

    @Test
    void encode_completedDeadline_Test() {
        LocalDate date = LocalDate.parse("2020-08-27");
        Deadline d = new Deadline("iP Week 3", date, "1");
        assertEquals("D | 1 | iP Week 3 | 2020-08-27", d.encode());
    }
}
