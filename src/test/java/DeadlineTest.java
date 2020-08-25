import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void deadlineBody_datePresent_success() throws ChatbotException {
        Deadline dl = Deadline.newDeadline("return book /by 2020-08-25");
        assertEquals(dl.getDescription(), "return book");
        assertEquals(dl.getDate().toString(), "2020-08-25");
    }

    @Test
    public void deadlineBody_missingDate_exceptionThrown() {
        try {
            Deadline.newDeadline("return book");
            fail();
        } catch (ChatbotException e) {
            assertEquals(e.getMessage(), "Deadline? By when??!!");
        }
    }

    @Test
    public void dateFormat_invalidFormat_exceptionThrown() {
        try {
            Deadline.newDeadline("return book /by wed");
            fail();
        } catch (ChatbotException e) {
            assertEquals(e.getMessage(), "Please enter a valid date or date format (yyyy-mm-dd).");
        }
    }

}
