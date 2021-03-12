package alice.task.time;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import alice.command.InvalidCommandException;

public class DateParserTest {
    private final int currYear = LocalDate.now().getYear();
    private final int currMonth = 9;
    // Default/now --> Monday
    private final LocalDate mockDate = LocalDate.of(currYear, currMonth, 7);

    @Test
    void parse_dMY_slash() {
        // d[/][-]M[[/][-]uuuu] --> d/M[/uuuu]
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parseByDateFormat("12/08"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parseByDateFormat("12/08/2019"));
        assertEquals(LocalDate.of(currYear, 8, 25), DateParser.parseByDateFormat("25/8"));
        assertEquals(LocalDate.of(2019, 8, 25), DateParser.parseByDateFormat("25/8/2019"));
    }

    @Test
    void parse_dMY_dash() {
        // d[/][-]M[[/][-]uuuu] --> d-M[-uuuu]
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parseByDateFormat("12-08"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parseByDateFormat("12-08-2019"));
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parseByDateFormat("12-8"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parseByDateFormat("12-8-2019"));
    }

    @Test
    void parse_mDY_slash() {
        // M[/][-]d[[/][-]uuuu] --> M/d[/uuuu]
        // Must use an obvious month to differentiate month from dayOfMonth
        assertEquals(LocalDate.of(currYear, 12, 25), DateParser.parseByDateFormat("12/25"));
        assertEquals(LocalDate.of(2019, 12, 25), DateParser.parseByDateFormat("12/25/2019"));

        // If month is not differentiable from dayOfMonth
        assertEquals(LocalDate.of(currYear, 5, 12), DateParser.parseByDateFormat("12/5"));
        assertEquals(LocalDate.of(2019, 5, 12), DateParser.parseByDateFormat("12/5/2019"));
    }

    @Test
    void parse_mDY_dash() {
        // M[/][-]d[[/][-]uuuu] --> M-d[-uuuu]
        // Must use an obvious month to differentiate month from dayOfMonth
        assertEquals(LocalDate.of(currYear, 12, 25), DateParser.parseByDateFormat("12-25"));
        assertEquals(LocalDate.of(2019, 12, 25), DateParser.parseByDateFormat("12-25-2019"));

        // If month is not differentiable from dayOfMonth
        assertEquals(LocalDate.of(currYear, 5, 12), DateParser.parseByDateFormat("12-5"));
        assertEquals(LocalDate.of(2019, 5, 12), DateParser.parseByDateFormat("12-5-2019"));
    }

    @Test
    void parse_yMD_slash() {
        // uuuu[/][-]M[/][-]d --> uuuu/M/d (compulsory year)
        assertEquals(LocalDate.of(2019, 5, 25), DateParser.parseByDateFormat("2019/05/25"));
        assertEquals(LocalDate.of(2020, 5, 25), DateParser.parseByDateFormat("2020/5/25"));
    }

    @Test
    void parse_yMD_dash() {
        // uuuu[/][-]M[/][-]d --> uuuu-M-d (compulsory year)
        assertEquals(LocalDate.of(2019, 5, 25), DateParser.parseByDateFormat("2019-05-25"));
        assertEquals(LocalDate.of(2020, 5, 25), DateParser.parseByDateFormat("2020-5-25"));
    }

    @Test
    void parse_dMmmY_dash() {
        // d-MMM[-uuuu] (Short month)
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parseByDateFormat("12-Aug"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parseByDateFormat("12-Aug-2019"));

        // Insensitive case
        assertEquals(LocalDate.of(currYear, 2, 12), DateParser.parseByDateFormat("12-FEB"));
        assertEquals(LocalDate.of(2019, 3, 12), DateParser.parseByDateFormat("12-mAR-2019"));
    }

    @Test
    void parse_dMmmmY_dash() {
        // d-MMMM[-uuuu] (Long month)
        assertEquals(LocalDate.of(currYear, 8, 12), DateParser.parseByDateFormat("12-August"));
        assertEquals(LocalDate.of(2019, 8, 12), DateParser.parseByDateFormat("12-August-2019"));

        // Insensitive case
        assertEquals(LocalDate.of(currYear, 2, 12), DateParser.parseByDateFormat("12-FEBruarY"));
        assertEquals(LocalDate.of(2019, 3, 12), DateParser.parseByDateFormat("12-mARCh-2019"));
    }

    @Test
    void parse_dayOfWeek() {
        assertEquals(LocalDate.of(currYear, currMonth, 14), DateParser.parseByNaturalDay("mon", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 14), DateParser.parseByNaturalDay("MONDAY", mockDate));
        assertEquals(DayOfWeek.MONDAY, DateParser.parseByNaturalDay("MONDAY", mockDate).getDayOfWeek());

        assertEquals(LocalDate.of(currYear, currMonth, 8), DateParser.parseByNaturalDay("Tues", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 8), DateParser.parseByNaturalDay("tue", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 8), DateParser.parseByNaturalDay("tuesday", mockDate));
        assertEquals(DayOfWeek.TUESDAY, DateParser.parseByNaturalDay("TUESDAY", mockDate).getDayOfWeek());

        assertEquals(LocalDate.of(currYear, currMonth, 9), DateParser.parseByNaturalDay("weD", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 9), DateParser.parseByNaturalDay("wednesday", mockDate));
        assertEquals(DayOfWeek.WEDNESDAY, DateParser.parseByNaturalDay("WEDNESDAY", mockDate).getDayOfWeek());

        assertEquals(LocalDate.of(currYear, currMonth, 10), DateParser.parseByNaturalDay("tHu", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 10), DateParser.parseByNaturalDay("Thurs", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 10), DateParser.parseByNaturalDay("thursday", mockDate));
        assertEquals(DayOfWeek.THURSDAY, DateParser.parseByNaturalDay("THURSDAY", mockDate).getDayOfWeek());

        assertEquals(LocalDate.of(currYear, currMonth, 11), DateParser.parseByNaturalDay("fri", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 11), DateParser.parseByNaturalDay("friday", mockDate));
        assertEquals(DayOfWeek.FRIDAY, DateParser.parseByNaturalDay("FRIDAY", mockDate).getDayOfWeek());

        assertEquals(LocalDate.of(currYear, currMonth, 12), DateParser.parseByNaturalDay("sat", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 12), DateParser.parseByNaturalDay("saturday", mockDate));
        assertEquals(DayOfWeek.SATURDAY, DateParser.parseByNaturalDay("SATURDAY", mockDate).getDayOfWeek());

        assertEquals(LocalDate.of(currYear, currMonth, 13), DateParser.parseByNaturalDay("sun", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 13), DateParser.parseByNaturalDay("sundaY", mockDate));
        assertEquals(DayOfWeek.SUNDAY, DateParser.parseByNaturalDay("SUNDAY", mockDate).getDayOfWeek());
    }

    @Test
    void parse_today_tomorrow() {
        // today
        assertEquals(LocalDate.of(currYear, currMonth, 7), DateParser.parseByNaturalDay("tdy", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 7), DateParser.parseByNaturalDay("today", mockDate));
        assertEquals(0, DateParser.parseByNaturalDay("TODAY", mockDate).compareTo(mockDate));

        // tomorrow
        assertEquals(LocalDate.of(currYear, currMonth, 8), DateParser.parseByNaturalDay("tmr", mockDate));
        assertEquals(LocalDate.of(currYear, currMonth, 8), DateParser.parseByNaturalDay("tomorrow", mockDate));
        assertEquals(1, DateParser.parseByNaturalDay("TOMORROW", mockDate).compareTo(mockDate));
    }


    @Test
    void parse_now_tonight() throws InvalidCommandException {
        LocalDateTime mockNow = LocalDateTime.of(currYear, currMonth, 5, 8, 0);

        // now
        assertEquals(mockNow.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                DateParser.parseSpecial("nOw", mockNow)
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        // tonight
        assertEquals(mockNow.withHour(22).withMinute(0).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                DateParser.parseSpecial("TONIGHT", mockNow)
                        .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
