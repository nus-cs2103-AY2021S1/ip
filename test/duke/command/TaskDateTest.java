package duke.command;

import duke.exception.InvalidTaskDateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskDateTest {

    @Test
    void getDate_validDatePattern() {
        String validDate = "2020-01-01 16:00";
        String expected = "1 Jan 2020, Wednesday 04:00 PM";
        
        String actual = TaskDate.getDate(validDate);
        
        assertEquals(expected, actual);
    }

    @Test
    void getDate_invalidDatePattern_exceptionThrown() {
        String invalidDate = "2020/01/01 4pm";
        String expected = "1 Jan 2020, Wednesday 04:00 PM";

        try {
            assertEquals(expected, TaskDate.getDate(invalidDate));
            fail(); // the test should not reach this line
        } catch (InvalidTaskDateException e) {
            String expectedErrorMessage = "OOPS! " + invalidDate + " does not exist."
                    + "\nPlease check to ensure that the date and time are correct.";
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }
}