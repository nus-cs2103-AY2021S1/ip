package duke;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseDate_dateStr_success() throws DukeException {
        LocalDate expectedDate = LocalDate.of(1998, 1, 20);
        LocalDate actualDate = Parser.parseDate("20/01/1998");

        assertEquals(expectedDate, actualDate);
    }
}
