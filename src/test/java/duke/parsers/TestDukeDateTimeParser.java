package duke.parsers;

import duke.exceptions.DukeDateTimeParseException;
import duke.utils.DukeDateTime;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestDukeDateTimeParser {

    @Test
    public void parse_validInputWithTime_success() {
        DukeDateTime expected = new DukeDateTime(LocalDateTime.parse("2020-08-23T13:00"), true);
        String[] dateTimes = {"23/8/2020 1:00 PM", "23 Aug 2020 1300", "23 August 2020 13:00", "23-8-2020 1:00 PM"};
        for (String dateTime : dateTimes) {
            DukeDateTime dukeDateTime = DukeDateTimeParser.parse(dateTime);
            assertEquals(0, dukeDateTime.compareTo(expected));
        }
    }

    @Test
    public void parse_validInputWithoutTime_success() {
        DukeDateTime expected = new DukeDateTime(LocalDate.parse("2020-08-23").atStartOfDay(), false);
        String[] dateTimes = {"23/8/2020", "23 Aug 2020", "23 August 2020", "23-8-2020"};
        for (String dateTime : dateTimes) {
            DukeDateTime dukeDateTime = DukeDateTimeParser.parse(dateTime);
            assertEquals(0, dukeDateTime.compareTo(expected));
        }
    }

    @Test
    public void parse_invalidInput_exceptionThrown() {
        try {
            String input = "23/13/2020";
            DukeDateTime dukeDateTime = DukeDateTimeParser.parse(input);
            fail();
        } catch (DukeDateTimeParseException e) {
            assertEquals("OOPS! Invalid date / time format!", e.getMessage());
        }
    }
}
