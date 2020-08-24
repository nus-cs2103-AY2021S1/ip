import data.exception.DukeInvalidUserInputException;
import data.task.Deadline;
import data.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void convertDateTime_nullInput_throwsException() {
        assertThrows(NullPointerException.class, () -> new Deadline("Description", null));
    }

    @Test
    public void convertDateTime_validInput_returnsValidDateTime() throws Exception{
        Deadline deadline = new Deadline("Description", "2020-02-02 2020");
        assertEquals("[D][✘] Description (by: 2 February 2020 08:20 PM)", deadline.toString());
    }

    @Test
    public void convertDateTime_invalidDate_throwsException() {
        assertThrows(DukeInvalidUserInputException.class, () -> new Deadline("Description", "2020-20-20 2020"));
    }

    @Test
    public void convertDateTime_invalidTime_throwsException() {
        assertThrows(DukeInvalidUserInputException.class, () -> new Deadline("Description", "2020-02-02 3000"));
    }

    @Test
    public void toTxtFormat_validInput_returnsTxtFormat() throws Exception{
        Deadline deadline = new Deadline("Description", "2020-02-02 2020");
        assertEquals("D | 0 | Description | 2 February 2020 08:20 PM", deadline.toTxtFormat());
    }

    @Test
    public void parse_nullInput_throwsException() {
        String[] txtArray = null;
        assertThrows(NullPointerException.class, () -> Deadline.parse(txtArray));
    }

    @Test
    public void parse_validInput_returnsDeadline() throws Exception{
        String[] txtArray = {"D", "0", "A Valid Description", "24 August 2020 08:00 PM"};
        Deadline deadline = Deadline.parse(txtArray);
        assertEquals("[D][✘] A Valid Description (by: 24 August 2020 08:00 PM)", deadline.toString());
    }
}
