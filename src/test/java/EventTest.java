import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    void newEvent() {
        try {
            Event event = new Event("desc", "02-02-2020 1820");
            assertEquals(event.description, "desc");
            assertEquals(event.timeAt,
                    LocalDateTime.parse("02-02-2020 1820", DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
