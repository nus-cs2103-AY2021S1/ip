package duke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.exception.InvalidFormatDateException;


public class DateFormatterTest {

    private DateFormatter dateFormatter;

    @BeforeEach
    void init() {
        dateFormatter = new DateFormatter();
    }

    @Test
    @DisplayName("Check invalid date format (2020/10/13 2700)")
    public void writeInvalidTimeExceptionTest() {
        assertThrows(InvalidFormatDateException.class, () ->
                dateFormatter.formatDateTime("2020/10/13 2700"));
    }

    @Test
    @DisplayName("Check impossible dates (2020-30-30 1600)")
    public void writeInvalidDateExceptionTest() {
        assertThrows(InvalidFormatDateException.class, () ->
                dateFormatter.formatDateTime("2020-30-30 1600"));
    }

    @Test
    @DisplayName("Check valid date without time(2020-12-12)")
    public void writeValidDateTest() throws InvalidFormatDateException {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 12, 23, 59);
        assertEquals(localDateTime, dateFormatter.formatDateTime("2020-12-12"));
    }

    @Test
    @DisplayName("Check valid date with time(2020-12-12 1600)")
    public void writeValidDateAndTimeTest() throws InvalidFormatDateException {
        LocalDateTime localDateTime = LocalDateTime.of(2020, 12, 12, 16, 0);
        assertEquals(localDateTime, dateFormatter.formatDateTime("2020-12-12 1600"));
    }
}
