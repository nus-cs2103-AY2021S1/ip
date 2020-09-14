package duke.tasks;

import duke.exceptions.DukeDateTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class to test Deadline and TimedTask class 
 */
public class DeadlineTest {
    @Test
    public void testDeadlineClassConstructor_invalidString_throwsDukeDateTimeException() {
        assertThrows(DukeDateTimeException.class, () -> Deadline.createNewDeadline("description", "fail"));
    }


    @Test
    public void testDeadlineConstructor_autoCorrectsBlankDateField_CorrectstoCurrentDate() {
        try {
            Deadline deadline = Deadline.createNewDeadline("random_desc", "");
            assertEquals(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-YYYY")),deadline.getDateby());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimeLeftArithmetric_noInput_zeroReturned() throws Exception {
        Deadline deadline = Deadline.createNewDeadline("random_desc", "");
        assertEquals(deadline.timeLeft(), 0);
    }
}
