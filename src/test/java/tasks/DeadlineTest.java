package tasks;

import exceptions.DukeDateTimeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Class to test Deadline and TimedTask class 
 */
public class DeadlineTest {
    @Test
    public void testDeadlineClassConstructor_invalidString_throwsDukeDateTimeException() {
        assertThrows(DukeDateTimeException.class, () -> new Deadline("description", "fail"));
    }


    @Test
    public void testDeadlineConstructor_autoCorrectsBlankDateField_CorrectstoCurrentDate() {
        try {
            Deadline deadline = new Deadline("random_desc", "");
            assertEquals(deadline.getDateby(), LocalDate.now().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTimeLeftArithmetric_noInput_zeroReturned() throws Exception {
        Deadline deadline = new Deadline("random_desc", "");
        assertEquals(deadline.timeLeft(), 0);
    }
}
