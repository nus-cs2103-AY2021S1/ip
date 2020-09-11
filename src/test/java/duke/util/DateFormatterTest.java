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
    public void testFormatDateTime() {
        try {
            LocalDateTime obj = LocalDateTime.of(2020, 8, 25, 16, 23);
            assertEquals(obj, dateFormatter.formatDateTime("2020-08-25 1623"));
        } catch (InvalidFormatDateException e) {
            System.out.println(e);
        }
    }

    @Test
    @DisplayName("Testing if user did not input in a correct date format")
    public void writeInvalidFormatDateException() {
        assertThrows(InvalidFormatDateException.class, () -> dateFormatter.formatDateTime("2020/20/13 1600"));
    }

    @Test
    @DisplayName("Test if the user input in impossible dates")
    public void writeInvalidDateTimeException() {
        assertThrows(InvalidFormatDateException.class, () -> dateFormatter.formatDateTime("2020-30-30 1600"));
    }
}
