package gel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import gel.exception.GelException;

public class ParserTest {
    @Test
    public void toDateTime_invalidDateTimeFormat_gelExceptionThrown() {
        try {
            Parser.toDateTime("20200307234");
            fail();
        } catch (GelException e) {
            assertEquals("    Your datetime has an invalid format... please use"
                    + " the format:YYYY-MM-DD HHMM", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toDateTime_invalidDateFormat_gelExceptionThrown() {
        try {
            Parser.toDateTime("2020-0307 2359");
            fail();
        } catch (GelException e) {
            assertEquals("    Your date has an invalid format... please use"
                    + " the format:YYYY-MM-DD", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toDateTime_invalidTimeFormat_gelExceptionThrown() {
        try {
            Parser.toDateTime("2020-03-07 235959");
            fail();
        } catch (GelException e) {
            assertEquals("    Your time has an invalid format... please use"
                    + " the format:HHMM", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }
}
