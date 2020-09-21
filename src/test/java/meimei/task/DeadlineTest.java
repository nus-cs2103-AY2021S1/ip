package meimei.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testStringConversion() {
        assertEquals("[D][\u2718] Assignment by: 6 Nov 2020, 11.59 pm",
                new Deadline("Assignment",
                        LocalDateTime.parse("06/11/2020 23:59",
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                        .toString());
    }
}
