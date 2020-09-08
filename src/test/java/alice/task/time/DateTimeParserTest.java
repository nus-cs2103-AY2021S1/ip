package alice.task.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import alice.AliceException;
import alice.command.InvalidCommandException;

public class DateTimeParserTest {
    @Test
    void parseDateTimeTest() throws InvalidCommandException {
        // with hour and minutes
        assertEquals("Wednesday, Aug 12 2020, 2:50 AM", TaskDateTime.parseDateTime("12-Aug 2:50 AM").toString());

        // with hour only
        assertEquals("Wednesday, Aug 12 2020, 2PM", TaskDateTime.parseDateTime("12-Aug 2PM").toString());

        // without time
        assertEquals("Wednesday, Aug 12 2020", TaskDateTime.parseDateTime("12-Aug").toString());
    }

    @Test
    void encodeTest() throws InvalidCommandException {
        assertEquals("2020-08-12T13:50:00", TaskDateTime.parseDateTime("12-Aug 1350").encode());

        assertEquals("2020-08-12T00:00:00", TaskDateTime.parseDateTime("12-Aug").encode());
    }

    @Test
    void decodeTest() throws AliceException {
        assertEquals("Wednesday, Aug 12 2020, 3:35 PM", TaskDateTime.decode("2020-08-12T15:35:00").toString());

        assertEquals("Thursday, Aug 13 2020, 4:35 AM", TaskDateTime.decode("2020-08-13T04:35:00").toString());
    }
}
