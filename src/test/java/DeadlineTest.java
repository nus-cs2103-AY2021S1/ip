import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import task.Deadline;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][not done] return book (by: 12 June 2019 6:00 PM)",
                new Deadline("return book", false,
                        LocalDateTime.of(2019, 6, 12, 18, 0)).toString());
    }
}
