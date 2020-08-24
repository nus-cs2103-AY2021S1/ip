import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DateAndTimeTest {
    @Test
    public void dateAndTime_correctlyFormattedToString_MMMdYYYY() {
        assertEquals("Oct 20 2020", new DateAndTime(LocalDate.parse("2020-10-20")).toString());
    }
}
