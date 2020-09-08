package alice.task.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import alice.command.InvalidCommandException;

public class TimeParserTest {
    @Test
    void parse_time24h() throws InvalidCommandException {
        // 24h format
        assertEquals(LocalTime.of(23, 59), TimeParser.parse("2359"));
        assertEquals(LocalTime.of(2, 11), TimeParser.parse("0211"));
    }

    @Test
    void parse_empty_time() throws InvalidCommandException {
        // No time, Default
        assertEquals(LocalTime.of(0, 0), TimeParser.parse(""));
    }

    @Test
    void parse_time12h_wMinute() throws InvalidCommandException {
        // 12h time format with min
        assertEquals(LocalTime.of(0, 55), TimeParser.parse("12:55 am"));
        assertEquals(LocalTime.of(12, 55), TimeParser.parse("12:55 pm"));

        assertEquals(LocalTime.of(2, 35), TimeParser.parse("02:35 AM"));
        assertEquals(LocalTime.of(14, 35), TimeParser.parse("2:35 PM"));
    }

    @Test
    void parse_time12h_noMinute() throws InvalidCommandException {
        // 12h time format with no min given
        assertEquals(LocalTime.of(3, 0), TimeParser.parse("3 am"));
        assertEquals(LocalTime.of(15, 0), TimeParser.parse("3 pm"));

        assertEquals(LocalTime.of(3, 0), TimeParser.parse("3 AM"));
        assertEquals(LocalTime.of(15, 0), TimeParser.parse("3 PM"));

        assertEquals(LocalTime.of(10, 0), TimeParser.parse("10 AM"));
        assertEquals(LocalTime.of(22, 0), TimeParser.parse("10 PM"));
    }

}
