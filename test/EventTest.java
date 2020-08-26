import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @org.junit.jupiter.api.Test
    void constructor_missingTime_exceptionThrown() {
        try {
            new Event("unit testing", "today");
            fail("Should have thrown exception.");
        } catch (DukeException e) {
            assertEquals("!!Whoops!! Missing date or time!", e.getMessage());
        }
    }
}