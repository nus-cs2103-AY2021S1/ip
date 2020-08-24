import org.junit.jupiter.api.Test;
/**
 * Tests method in Deadline.
 */
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toStringTest() {
        assertEquals("[D][\u2718] buy gift(Apr 28 2021)",
                new Deadline("buy gift", LocalDate.parse("2021-04-28")));
    }

}
