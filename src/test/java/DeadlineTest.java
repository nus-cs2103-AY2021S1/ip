import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        LocalDateTime localDateTime = LocalDateTime.parse("2020-08-25 00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        Deadline deadline = new Deadline("return book", localDateTime);
        assertEquals("[D][✗] return book (by: Aug 25 2020, 12:00AM)" , deadline.toString());
    }
}