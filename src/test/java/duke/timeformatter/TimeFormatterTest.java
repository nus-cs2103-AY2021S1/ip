package duke.timeformatter;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TimeFormatterTest {

    @Test
    public void convertDate() {
        String by ="2019/10/10";
        String date = by.trim().replaceAll("/", "-");
        LocalDate localDate = LocalDate.parse(date);
        assertTrue(localDate.equals(LocalDate.parse("2019-10-10")));
    }

    @Test
    public void convertToPrettyDate() {
        LocalDate localDate =LocalDate.parse("2019-10-10");
        String testString = TimeFormatter.prettyDate(localDate);
        String expectedOutput="THURSDAY,OCT 10 2019";
        assertTrue(testString.equals(expectedOutput));
    }
}