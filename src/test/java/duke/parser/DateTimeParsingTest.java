package duke.parser;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DateTimeParsingTest {
    @Test
    public void parseDate_validFormat_success() {
        String date1 = "2012-12-21";
        LocalDate expected1 = LocalDate.parse(date1);
        assertEquals(expected1, DateTimeParsing.parseDate(date1));

        String date2 = "2020-08-25";
        LocalDate expected2 = LocalDate.parse(date2);
        assertEquals(expected2, DateTimeParsing.parseDate(date2));
    }

    @Test
    public void parseDate_invalidFormat_exceptionThrown() {
        try {
            String date1 = "2012/12/21";
            LocalDate.parse(date1);
            fail();
        } catch (Exception e) {
            assertEquals(DateTimeParseException.class, e.getClass());
        }
    }

    @Test
    public void localDateToString() {
        String date1 = "2000-05-12";
        LocalDate localDate1 = LocalDate.parse(date1);
        assertEquals(date1, DateTimeParsing.localDateToString(localDate1));

        String date2 = "2024-01-24";
        LocalDate localDate2 = LocalDate.parse(date2);
        assertEquals(date2, DateTimeParsing.localDateToString(localDate2));
    }

    @Test
    public void to12HTimeFormat_validFormat_success() {
        String time1 = "23:59";
        assertEquals("11:59 PM", DateTimeParsing.to12HTimeFormat(time1));

        String time2 = "08:20";
        assertEquals("08:20 AM", DateTimeParsing.to12HTimeFormat(time2));
    }

    @Test
    public void to12HTimeFormat_invalidFormat_exceptionThrown() {
        try {
            String time1 = "2359";
            DateTimeParsing.to12HTimeFormat(time1);
            fail();
        } catch (Exception e) {
            assertEquals(DateTimeParseException.class, e.getClass());
        }
    }

    @Test
    public void to24HTimeFormat_validFormat_success() {
        String time1 = "10:45 PM";
        assertEquals("22:45", DateTimeParsing.to24HTimeFormat(time1));

        String time2 = "05:33 AM";
        assertEquals("05:33", DateTimeParsing.to24HTimeFormat(time2));
    }

    @Test
    public void to24HTimeFormat_invalidFormat_exceptionThrown() {
        try {
            String time1 = "01:00";
            DateTimeParsing.to24HTimeFormat(time1);
            fail();
        } catch (Exception e) {
            assertEquals(DateTimeParseException.class, e.getClass());
        }
    }
}
