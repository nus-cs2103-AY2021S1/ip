import bot.task.Deadline;
import bot.util.InvalidInputException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DeadlineTest {

    @org.junit.Test
    public void deadlineTest_correctDateFormat_success() throws InvalidInputException {
        Deadline dl = new Deadline("Name", "2020-05-01 19:30");
        assertEquals(dl.toString(), "[D][✘] Name (by: May 1 2020, 7:30 PM)");
    }

    @org.junit.Test
    public void deadlineTest_wrongDateFormat_success() throws Exception {
        try {
            Deadline dl = new Deadline("Name", "2020-05-01 19:330");
            fail();
        } catch (Exception e) {
            return;
        }
    }
}
