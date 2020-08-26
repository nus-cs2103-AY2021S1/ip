package tasks;

import main.java.exceptions.InvalidDescriptionException;
import main.java.exceptions.InvalidTimeException;
import main.java.tasks.Deadline;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void deadline_normalDeadlineDescriptionAndTime_success() {
        try {
            new Deadline("get rid of gooble boxes",
                    "next Thursday");
        } catch (InvalidDescriptionException | InvalidTimeException e) {
            fail();
        }
    }

    @Test
    public void deadline_blankDeadlineDescription_exceptionThrow() {
        try {
            new Deadline(" ", "today");
        } catch (InvalidDescriptionException | InvalidTimeException e) {
            assertEquals("Hey! " +
                    "Deadline description shouldn't be blank.", e.getMessage());
        }
    }

    @Test
    public void deadline_blankDeadlineEndTime_exceptionThrow() {
        try {
            new Deadline("go for family therapy", " ");
        } catch (InvalidDescriptionException | InvalidTimeException e) {
            assertEquals("Do try again by adding a time " +
                    "you need to get this done by.", e.getMessage());
        }
    }

}
