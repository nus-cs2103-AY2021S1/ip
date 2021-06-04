import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to ensure LocalDate to String formatting done correctly
 *
 */
class EventTest {

    @Test
    void getDate() {
        Event event = new Event("Concert /at 2020-08-26");
        assertEquals("Aug 26 2020", event.getDate());
    }
}