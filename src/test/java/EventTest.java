import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    @Test
    public void eventBody_datePresent_success() throws ChatbotException {
        Event evt = Event.newEvent("meeting /at 2020-08-25");
        assertEquals(evt.getDescription(), "meeting");
        assertEquals(evt.getDate().toString(), "2020-08-25");
    }

    @Test
    public void eventBody_missingDate_exceptionThrown() {
        try {
            Event.newEvent("meeting");
            fail();
        } catch (ChatbotException e) {
            assertEquals(e.getMessage(), "Event? At what date??!!");
        }
    }

    @Test
    public void dateFormat_invalidFormat_exceptionThrown() {
        try {
            Event.newEvent("meeting /at 2020-20-20");
            fail();
        } catch (ChatbotException e) {
            assertEquals(e.getMessage(), "Please enter a valid date or date format (yyyy-mm-dd).");
        }
    }
}
