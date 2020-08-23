package duke.parser;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void localDateParsing_YYYYMMDD_datePrinted() {
        assertEquals("Jun 6 2020",
                Parser.parseDate("2020-06-06")
                        .format(DateTimeFormatter
                                .ofPattern("MMM d yyyy")));
    }

    @Test
    public void localDateParsing_notADate_null() {
        assertEquals(null, Parser.parseDate("randomString"));
    }

}
