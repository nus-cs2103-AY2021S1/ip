package duke;

import duke.tasks.ToDo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoTest {

    // TODO: Naming constraints
    private ToDo normalTask = new ToDo("eat good food");
    private ToDo taskVariant2 = new ToDo("eat good food");
    private ToDo taskVariant3 = new ToDo("eat bad food");
    private ToDo taskVariant4 = new ToDo("eat good food", true);

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
    }

}
