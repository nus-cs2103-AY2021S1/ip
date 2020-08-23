package duke.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeDateTimeTest {

    @Test
    public void toString_dateWithTime_success() {
        LocalDateTime dateTime = LocalDateTime.parse("2020-08-23T13:00");
        DukeDateTime dukeDateTime = new DukeDateTime(dateTime, true);
        String expected = "23 Aug 2020 1:00 PM";
        assertEquals(expected, dukeDateTime.toString());
    }

    @Test
    public void toString_dateWithoutTime_success() {
        LocalDateTime dateTime = LocalDate.parse("2020-08-23").atStartOfDay();
        DukeDateTime dukeDateTime = new DukeDateTime(dateTime, false);
        String expected = "23 Aug 2020";
        assertEquals(expected, dukeDateTime.toString());
    }

    @Test
    public void isSameDate_sameDates_success() {
        LocalDateTime dateTime1 = LocalDate.parse("2020-08-23").atStartOfDay();
        LocalDateTime dateTime2 = LocalDateTime.parse("2020-08-23T13:00");
        DukeDateTime first = new DukeDateTime(dateTime1, false);
        DukeDateTime second = new DukeDateTime(dateTime2, true);
        assertEquals(true, first.isSameDate(second));
    }

    @Test
    public void isSameDate_differentDates_success() {
        LocalDateTime dateTime1 = LocalDate.parse("2020-09-21").atStartOfDay();
        LocalDateTime dateTime2 = LocalDateTime.parse("2020-08-23T13:00");
        DukeDateTime first = new DukeDateTime(dateTime1, false);
        DukeDateTime second = new DukeDateTime(dateTime2, true);
        assertEquals(false, first.isSameDate(second));
    }

    @Test
    public void testCompareTo() {
        LocalDateTime first = LocalDate.parse("2020-08-23").atStartOfDay();
        LocalDateTime second = LocalDateTime.parse("2020-08-23T09:00");
        DukeDateTime firstDuke = new DukeDateTime(first, false);
        DukeDateTime secondDuke = new DukeDateTime(second, true);
        assertEquals(-1, firstDuke.compareTo(secondDuke));
        assertEquals(1, secondDuke.compareTo(firstDuke));
        assertEquals(0, firstDuke.compareTo(firstDuke));
    }

}
