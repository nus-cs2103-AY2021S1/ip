package gel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import gel.exception.GelException;


public class TaskListTest {

    @Test
    public void doneTask_invalidInput_exceptionThrown() {
        try {
            new TaskList(new Ui()).markTaskAsDone(new String[] {"1"});
            fail();
        } catch (GelException e) {
            assertEquals("    Oi tell me what you want to mark as done la", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteTask_nonNumberInput_exceptionThrown() {
        try {
            new TaskList(new Ui()).deleteTask(new String[] {"a"});
            fail();
        } catch (Exception e) {
            assertEquals("    Yo tell me what you want to delete la", e.getLocalizedMessage());
        }
    }

    @Test
    public void addDeadline_invalidDate_exceptionThrown() {
        try {
            new TaskList(new Ui()).addDeadline("deadline test /by 2020-30-58 2500", 18);
            fail();
        } catch (GelException e) {
            assertEquals("    Please make sure that your date and time "
                    + "are numbers within the calendar and 24 hour clock", e.getMessage());
        } catch (Exception e) {
            fail();
        }
    }

}
