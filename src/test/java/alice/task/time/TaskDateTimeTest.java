package alice.task.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import alice.AliceException;
import alice.command.InvalidCommandException;

public class TaskDateTimeTest {
    @Test
    void parseDateTimeTest() throws InvalidCommandException {
        // with hour and minutes
        assertEquals("Wednesday, Aug 12 2020, 2:50 AM", TaskDateTime.parseDateTime("12-Aug 2:50AM").toString());

        // with hour only
        assertEquals("Wednesday, Aug 12 2020, 2PM", TaskDateTime.parseDateTime("12-Aug 2PM").toString());
    }

    @Test
    void parseDateOnly() throws InvalidCommandException {
        assertEquals("Wednesday, Aug 12 2020", TaskDateTime.parseDateTime("12-Aug").toString());

        assertEquals("Monday, Aug 12 2019", TaskDateTime.parseDateTime("12/08/2019").toString());

        LocalDateTime currDateTime = LocalDateTime.now();
        String input = currDateTime.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu"));
        assertEquals("Today", TaskDateTime.parseDateTime(input).toString());
    }

    @Test
    void parseTimeOnly() throws InvalidCommandException {
        LocalDateTime currDateTime = LocalDateTime.now();

        // with a time 1h after current time
        int hour1 = currDateTime.toLocalTime().getHour() + 1;
        String output1 = "Today, "
                + LocalTime.of(hour1, 0).format(DateTimeFormatter.ofPattern("ha"));
        assertEquals(output1, TaskDateTime.parseDateTime(hour1 + "00").toString());

        // with a time 1h before current time
        int hour2 = currDateTime.toLocalTime().getHour() - 1;
        // Expected output --> tomorrow date with same hour
        String output2 = currDateTime.toLocalDate().plusDays(1)
                .format(DateTimeFormatter.ofPattern("EEEE, MMM dd uuuu"))
                + ", "
                + LocalTime.of(hour2, 0).format(DateTimeFormatter.ofPattern("ha"));

        assertEquals(output2, TaskDateTime.parseDateTime(hour2 + "00").toString());

        // with natural time only
        assertEquals("Today, 11:59 PM", TaskDateTime.parseDateTime("MIDNIGHT").toString());
    }

    @Test
    void parseErrorCheck() {
        try {
            assertEquals("Monday, Aug 12 2019", TaskDateTime.parseDateTime("123").toString());
        } catch (InvalidCommandException ex) {
            assertEquals("Invalid datetime! Please use a recognisable date or time format", ex.getMessage());
        }
    }

    @Test
    void encodeTest() throws InvalidCommandException {
        // with date and time
        assertEquals("2020-08-12T13:50:00", TaskDateTime.parseDateTime("12-Aug 1350").encode());

        // with date only
        assertEquals("2020-08-12", TaskDateTime.parseDateTime("12-Aug").encode());
    }

    @Test
    void decodeTest() throws AliceException {
        // with date and time
        assertEquals("Wednesday, Aug 12 2020, 3:35 PM", TaskDateTime.decode("2020-08-12T15:35:00").toString());

        // with date only
        assertEquals("Thursday, Aug 13 2020", TaskDateTime.decode("2020-08-13").toString());
    }
}
