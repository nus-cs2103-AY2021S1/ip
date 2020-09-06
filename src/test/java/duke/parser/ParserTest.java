package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.format.DateTimeFormatter;

import duke.commands.Command;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void localDateParsing_yyyymmdd_datePrinted() {
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
