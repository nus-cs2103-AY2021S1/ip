package gel;

import gel.exception.GelException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void doneTask_invalidInput_exceptionThrown() {
        try {
            new TaskList(new Ui()).markTaskAsDone("done 1");
            fail();
        } catch (GelException e) {
            assertEquals("\n"
                    + "    Please input a valid number from 1 - 0", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteTask_nonNumberInput_exceptionThrown() {
        try {
            new TaskList(new Ui()).deleteTask("a");
            fail();
        } catch (Exception e) {
            assertEquals("For input string: \"a\"", e.getLocalizedMessage());
        }
    }

    @Test
    public void addDeadline_invalidDate_exceptionThrown() {
        try {
            new TaskList(new Ui()).addDeadline("deadline test /by 2020-30-58 2500", 18);
            fail();
        } catch (GelException e) {
            assertEquals("\n    Please make sure that your date and time "
                    + "are numbers within the calendar and 24 hour clock", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

}
