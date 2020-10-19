package duke;

import duke.tasks.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EventTest {

    // TODO: Naming constraints
    private Event normalTask = new Event("eat good food", LocalDate.of(2020,11,12));
    private Event taskVariant2 = new Event("eat good food",  LocalDate.of(2020,11,12));
    private Event taskVariant3 = new Event("eat bad food",  LocalDate.of(2020,11,12));
    private Event taskVariant4 = new Event("eat good food", true, LocalDate.of(2020,11,12));
    private Event taskVariant5 = new Event("eat good food", LocalDate.of(2020,11,13));

    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(normalTask.equals(normalTask));

        // null -> returns false
        assertFalse(normalTask.equals(null));

        // same name and boolean -> returns true
        assertTrue(normalTask.equals(taskVariant2));

        // different name -> returns false
        assertFalse(normalTask.equals(taskVariant3));

        // different boolean -> returns false
        assertFalse(normalTask.equals(taskVariant4));

        // different date -> returns false
        assertFalse(normalTask.equals(taskVariant5));

    }
}
