import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    void toStringTest() {
        Deadline dummyDeadline = new Deadline("study for exam", LocalDate.parse("2020-12-12"));
        assertEquals("[D]" + "[\u2718]" + " study for exam" +
                "(by: 12 Dec 2020)", dummyDeadline.toString());
    }


}
