import main.java.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        assertEquals("[D][\u2718] read book(by: Oct 09 2020)",
                new Deadline(" read book", LocalDate.of(2020, 10, 9)).toString());
    }
}
