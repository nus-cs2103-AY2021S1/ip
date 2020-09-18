package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.Command;
import duke.task.Event;

public class ParserTest {
    @Test
    public void parseEvent_goodDateFormat_parsedSuccessfully() throws DukeException {
        String command = "event date with girlfriend!! /at 2020-10-10T13:00";
        Event event = new Event("date with girlfriend!!", LocalDateTime.parse("2020-10-10T13:00"));
        AddCommand addCommand = new AddCommand(event);
        Command c = Parser.parse(command);
        assertEquals(addCommand, c);
    }

    @Test
    public void parseEvent_badDateFormat_exceptionThrown() {
        String command = "event make some sandwiches /at Sunday";
        try {
            assertEquals(Parser.parse(command), Parser.parse(command));
            fail();
        } catch (DukeException e) {
            assertEquals("Format of date and time is incorrect! "
                            + "Please fill in the date and time following the format below. \n"
                            + "       YYYY-MM-DDTHH:MM:SS",
                    e.getMessage());
        }
    }
}
