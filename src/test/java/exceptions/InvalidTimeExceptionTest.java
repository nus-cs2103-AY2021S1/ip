package exceptions;

import main.java.exceptions.InvalidDescriptionException;
import main.java.exceptions.InvalidTimeException;
import main.java.tasks.Deadline;
import main.java.tasks.Event;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidTimeExceptionTest extends InvalidCommandExceptionTest {

    @Test
    public void invalidTimeException_blankEventTime_exceptionThrown() {
        Assertions.assertThrows(InvalidTimeException.class,
                () -> new Event("event go to a spa", ""));
        Assertions.assertThrows(InvalidTimeException.class,
                () -> new Event("event get out of a simulation", " "));
        Assertions.assertThrows(InvalidTimeException.class,
                () -> new Event("event explore the miniverse", "     "));
    }

    @Test
    public void invalidTimeException_blankDeadlineEndTime_exceptionThrown() {
        Assertions.assertThrows(InvalidTimeException.class,
                () -> new Deadline("deadline change the car gears", ""));
        Assertions.assertThrows(InvalidTimeException.class,
                () -> new Deadline("deadline get some death crystals", " "));
        Assertions.assertThrows(InvalidTimeException.class,
                () -> new Deadline("deadline obtain some mega seeds", "   "));
    }

}
