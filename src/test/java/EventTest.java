import data.exception.DukeInvalidUserInputException;
import data.task.Deadline;
import data.task.Event;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void convertDateTime_nullInput_throwsException() {
        assertThrows(NullPointerException.class, () -> new Event("Description", null));
    }

    @Test
    public void convertDateTime_validInput_returnsValidDateTime() throws Exception{
        Event deadline = new Event("Description", "2020-02-02 2020-2121");
        assertEquals("[E][✘] Description (at: 2 February 2020 08:20 PM to 09:21 PM)", deadline.toString());
    }

    @Test
    public void convertDateTime_invalidDate_throwsException() {
        assertThrows(DukeInvalidUserInputException.class, () -> new Event("Description", "2020-20-20 2020-2121"));
    }

    @Test
    public void convertDateTime_invalidTime_throwsException() {
        assertThrows(DukeInvalidUserInputException.class, () -> new Event("Description", "2020-02-02 3000-3000"));
    }

    @Test
    public void toTxtFormat_validInput_returnsTxtFormat() throws Exception{
        Event event = new Event("Description", "2020-02-02 2020-2121");
        assertEquals("E | 0 | Description | 2 February 2020 08:20 PM to 09:21 PM", event.toTxtFormat());
    }

    @Test
    public void parse_nullInput_throwsException() {
        String[] txtArray = null;
        assertThrows(NullPointerException.class, () -> Event.parse(txtArray));
    }

    @Test
    public void parse_validInput_returnsDeadline() throws Exception{
        String[] txtArray = {"D", "0", "A Valid Description", "24 August 2020 08:00 PM to 01:00 AM"};
        Event event = Event.parse(txtArray);
        assertEquals("[E][✘] A Valid Description (at: 24 August 2020 08:00 PM to 01:00 AM)", event.toString());
    }
}
