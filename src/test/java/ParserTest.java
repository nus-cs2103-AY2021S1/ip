import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void parseDate_differentDateFormats() throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-04");
        assertEquals(Parser.parseDate("2020-03-04"), date);
        assertEquals(Parser.parseDate("04/03/2020 0000"), date);
        assertEquals(Parser.parseDate("04/03/2020"), date);
        assertEquals(Parser.parseDate("Mar 04 2020"), date);
    }

    @Test
    public void parse_doneCommand_exceptionThrown() {
        try {
            Parser.parse("done junknumber");
        } catch (DukeException | TaskException ex) {
            assertEquals(ex.getMessage(), "\u2639 OOPS!!! I'm sorry, but task index is not a valid number :-(");
        }
    }

    @Test
    public void parse_addCommand_exceptionThrown() {
        try {
            Parser.parse("deadline return books /by");
        } catch (DukeException | TaskException ex) {
            assertEquals("\u2639 OOPS!!! The time of a deadline cannot be identified.", ex.getMessage());
        }

        try {
            Parser.parse("deadline   /by 2020-03-04");
        } catch (DukeException | TaskException ex) {
            assertEquals("\u2639 OOPS!!! The description of a deadline cannot be empty.", ex.getMessage());
        }
    }

    @Test
    public void parse_exceptionThrown() {
        try {
            Parser.parse("junk input");
        } catch (DukeException | TaskException ex) {
            assertEquals("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(", ex.getMessage());
        }
    }

}
