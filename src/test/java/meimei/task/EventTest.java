package meimei.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void testStringConversion() {
        assertEquals("[E][\u2718] Dance Class at: 3 Sep 2020, 8.30 PM",
                new Event("Dance Class",
                        LocalDateTime.parse("03/09/2020 20:30",
                                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                        .toString());
    }
}
