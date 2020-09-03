package nekochan.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class DateParserTest {

    @Test
    public void parseStringToDateTime_slashWithTime_success() {
        String dateString = "1/1/1999 12:00";
        LocalDateTime ldt = DateParser.parseStringToDateTime(dateString);
        assertEquals(LocalDateTime.of(1999, 1, 1, 12,0), ldt);
    }

    @Test
    public void parseStringToDateTime_hyphenWithTime_success() {
        String dateString = "1-1-1999 12:00";
        LocalDateTime ldt = DateParser.parseStringToDateTime(dateString);
        assertEquals(ldt, LocalDateTime.of(1999, 1, 1, 12,0));
    }

    @Test
    public void parseStringToDateTime_naturalWithTime_success() {
        String dateString = "1 Jan 1999 12:00";
        LocalDateTime ldt = DateParser.parseStringToDateTime(dateString);
        assertEquals(LocalDateTime.of(1999, 1, 1, 12,0), ldt);
    }

    @Test
    public void parseStringToDateTime_naturalLowercaseWithTime_success() {
        String dateString = "1 jan 1999 12:00";
        LocalDateTime ldt = DateParser.parseStringToDateTime(dateString);
        assertEquals(LocalDateTime.of(1999, 1, 1, 12,0), ldt);
    }

    @Test
    public void parseStringToDateTime_slashWithoutTime_success() {
        String dateString = "1/1/1999";
        LocalDateTime ldt = DateParser.parseStringToDateTime(dateString);
        assertEquals(LocalDateTime.of(1999, 1, 1, 0, 0, 30), ldt);
    }

    @Test
    public void parseStringToDateTime_hyphenWithoutTime_success() {
        String dateString = "1-1-1999";
        LocalDateTime ldt = DateParser.parseStringToDateTime(dateString);
        assertEquals(LocalDateTime.of(1999, 1, 1, 0, 0, 30), ldt);
    }

    @Test
    public void parseStringToDateTime_naturalWithoutTime_success() {
        String dateString = "1 Jan 1999";
        LocalDateTime ldt = DateParser.parseStringToDateTime(dateString);
        assertEquals(LocalDateTime.of(1999, 1, 1, 0, 0, 30), ldt);
    }

    @Test
    public void parseStringToDateTime_naturalLowercaseWithoutTime_success() {
        String dateString = "1 jan 1999";
        LocalDateTime ldt = DateParser.parseStringToDateTime(dateString);
        assertEquals(LocalDateTime.of(1999, 1, 1, 0, 0, 30), ldt);
    }

    @Test
    public void parseDurationToMinutes_short_success() {
        String inputString = "1d 2h 3m";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(1563, duration);
    }

    @Test
    public void parseDurationToMinutes_long_success() {
        String inputString = "1day 2hours 3minutes";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(1563, duration);
    }

    @Test
    public void parseDurationToMinutes_longCaseInsensitive_success() {
        String inputString = "1Day 2Hours 3Minutes";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(1563, duration);
    }

    @Test
    public void parseDurationToMinutes_longSpaceInsensitive_success() {
        String inputString = "1 Day 2 Hours  3Minutes";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(1563, duration);
    }

    @Test
    public void parseDurationToMinutes_dayOnly_success() {
        String inputString = "1 Day";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(1440, duration);
    }

    @Test
    public void parseDurationToMinutes_hourOnly_success() {
        String inputString = "1 Hour";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(60, duration);
    }

    @Test
    public void parseDurationToMinutes_minuteOnly_success() {
        String inputString = "5 Minute";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(5, duration);
    }

    @Test
    public void parseDurationToMinutes_dayMinuteOnly_success() {
        String inputString = "1 Day 5 Minute";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(1445, duration);
    }

    @Test
    public void parseDurationToMinutes_dayHourOnly_success() {
        String inputString = "1 Day 1 Hour";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(1500, duration);
    }

    @Test
    public void parseDurationToMinutes_hourminuteOnly_success() {
        String inputString = "1 hour 1 minute";
        int duration = DateParser.parseDurationToMinutes(inputString);
        assertEquals(61, duration);
    }
}
