package duke.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class ParsingHelperTest {
    @Test
    public void ensureArgsPresent_emptyString_throwException() {
        assertThrows(DukeParsingException.class, () -> ParsingHelper.ensureArgsPresent("", "error message"));
    }

    @Test
    public void ensureArgsPresent_whitespaceString_throwException() {
        assertThrows(DukeParsingException.class, () -> ParsingHelper.ensureArgsPresent("  \t ", "error message"));
    }

    @Test
    public void ensureArgsPresent_validString() {
        assertDoesNotThrow(() -> ParsingHelper.ensureArgsPresent("test", "error message"));
    }

    @Test
    public void ensureNoArgs_emptyString() {
        assertDoesNotThrow(() -> ParsingHelper.ensureNoArgs("", "error message"));
    }

    @Test
    public void ensureNoArgs_whitespaceString() {
        assertDoesNotThrow(() -> ParsingHelper.ensureNoArgs(" ", "error message"));
        assertDoesNotThrow(() -> ParsingHelper.ensureNoArgs("\t", "error message"));
    }

    @Test
    public void ensureNoArgs_nonBlankString_throwException() {
        assertThrows(DukeParsingException.class, () -> ParsingHelper.ensureNoArgs(" yeet ", "error message"));
    }

    @Test
    public void parseDate_dateOnly() {
        assertDoesNotThrow(() -> {
            Date date = ParsingHelper.parseDate("15/01/2020");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            assertTrue(cal.get(Calendar.DATE) == 15 && cal.get(Calendar.MONTH) == Calendar.JANUARY
                    && cal.get(Calendar.YEAR) == 2020 && cal.get(Calendar.HOUR_OF_DAY) == 0
                    && cal.get(Calendar.MINUTE) == 0);
        });
    }

    @Test
    public void parseDate_shortDateOnly() {
        assertDoesNotThrow(() -> {
            Date date = ParsingHelper.parseDate("15/1/20");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            assertTrue(cal.get(Calendar.DATE) == 15 && cal.get(Calendar.MONTH) == Calendar.JANUARY
                    && cal.get(Calendar.YEAR) == 2020 && cal.get(Calendar.HOUR_OF_DAY) == 0
                    && cal.get(Calendar.MINUTE) == 0);
        });
    }

    @Test
    public void parseDate_dateTime() {
        assertDoesNotThrow(() -> {
            Date date = ParsingHelper.parseDate("15/01/2020 17:15");
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            assertTrue(cal.get(Calendar.DATE) == 15 && cal.get(Calendar.MONTH) == Calendar.JANUARY
                    && cal.get(Calendar.YEAR) == 2020 && cal.get(Calendar.HOUR_OF_DAY) == 17
                    && cal.get(Calendar.MINUTE) == 15);
        });
    }
}
