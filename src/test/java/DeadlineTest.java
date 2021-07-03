import static org.junit.Assert.fail;

import bot.task.Deadline;

public class DeadlineTest {
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
