package alice.task.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import alice.command.InvalidCommandException;

public class DateParserTest {
    private final int currYear = LocalDate.now().getYear();
    private final int currMonth = LocalDate.now().getMonthValue();

    @Test
    void parse_dMY_slash() throws InvalidCommandException {
        // d[/][-]M[[/][-]uuuu] --> d/M[/uuuu]
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parse("12/08"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parse("12/08/2019"));
        assertEquals(LocalDate.of(currYear, 8, 25), DateParser.parse("25/8"));
        assertEquals(LocalDate.of(2019, 8, 25), DateParser.parse("25/8/2019"));
    }

    @Test
    void parse_dMY_dash() throws InvalidCommandException {
        // d[/][-]M[[/][-]uuuu] --> d-M[-uuuu]
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parse("12-08"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parse("12-08-2019"));
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parse("12-8"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parse("12-8-2019"));
    }

    @Test
    void parse_mDY_slash() throws InvalidCommandException {
        // M[/][-]d[[/][-]uuuu] --> M/d[/uuuu]
        // Must use an obvious month to differentiate month from dayOfMonth
        assertEquals(LocalDate.of(currYear, 12, 25), DateParser.parse("12/25"));
        assertEquals(LocalDate.of(2019, 12, 25), DateParser.parse("12/25/2019"));

        // If month is not differentiable from dayOfMonth
        assertEquals(LocalDate.of(currYear, 5, 12), DateParser.parse("12/5"));
        assertEquals(LocalDate.of(2019, 5, 12), DateParser.parse("12/5/2019"));
    }

    @Test
    void parse_mDY_dash() throws InvalidCommandException {
        // M[/][-]d[[/][-]uuuu] --> M-d[-uuuu]
        // Must use an obvious month to differentiate month from dayOfMonth
        assertEquals(LocalDate.of(currYear, 12, 25), DateParser.parse("12-25"));
        assertEquals(LocalDate.of(2019, 12, 25), DateParser.parse("12-25-2019"));

        // If month is not differentiable from dayOfMonth
        assertEquals(LocalDate.of(currYear, 5, 12), DateParser.parse("12-5"));
        assertEquals(LocalDate.of(2019, 5, 12), DateParser.parse("12-5-2019"));
    }

    @Test
    void parse_yMD_slash() throws InvalidCommandException {
        // uuuu[/][-]M[/][-]d --> uuuu/M/d (compulsory year)
        assertEquals(LocalDate.of(2019, 5, 25), DateParser.parse("2019/05/25"));
        assertEquals(LocalDate.of(2020, 5, 25), DateParser.parse("2020/5/25"));
    }

    @Test
    void parse_yMD_dash() throws InvalidCommandException {
        // uuuu[/][-]M[/][-]d --> uuuu-M-d (compulsory year)
        assertEquals(LocalDate.of(2019, 5, 25), DateParser.parse("2019-05-25"));
        assertEquals(LocalDate.of(2020, 5, 25), DateParser.parse("2020-5-25"));
    }

    @Test
    void parse_dMmmY_dash() throws InvalidCommandException {
        // d-MMM[-uuuu] (Short month)
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parse("12-Aug"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parse("12-Aug-2019"));

        // Insensitive case
        assertEquals(LocalDate.of(currYear, 2, 12), DateParser.parse("12-FEB"));
        assertEquals(LocalDate.of(2019, 3, 12), DateParser.parse("12-mAR-2019"));
    }

    @Test
    void parse_dMmmmY_dash() throws InvalidCommandException {
        // d-MMMM[-uuuu] (Long month)
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parse("12-August"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parse("12-August-2019"));

        // Insensitive case
        assertEquals(LocalDate.of(currYear, 2, 12), DateParser.parse("12-FEBruarY"));
        assertEquals(LocalDate.of(2019, 3, 12), DateParser.parse("12-mARCh-2019"));
    }

    @Test
    void parse_only_day() throws InvalidCommandException {
        // d (day only)
        assertEquals(LocalDate.of(currYear, currMonth, 1), DateParser.parse("01"));
        assertEquals(LocalDate.of(currYear, currMonth, 27), DateParser.parse("27"));
        assertEquals(LocalDate.of(currYear, currMonth, 5), DateParser.parse("5"));
    }
}
