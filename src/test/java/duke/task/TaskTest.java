package duke.task;

import duke.exceptions.DukeException;
import duke.exceptions.DukeTaskCreationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    @Test
    public void constructor_success() {
        ConcreteTaskStub task = new ConcreteTaskStub("Description");
        assertEquals("[\u2718] Description", task.toString());
    }

    @Test
    public void constructor_missingDescription_throwsException() {
        DukeException thrown = assertThrows(DukeTaskCreationException.class, () -> {
            ConcreteTaskStub task = new ConcreteTaskStub("");
        });
        assertTrue(thrown.getMessage().contains("That's really descriptive..."));
    }

    @Test
    public void setCompleted_success() {
        ConcreteTaskStub task = new ConcreteTaskStub("Description");
        task.setCompleted();
        assertEquals("[\u2713] Description", task.toString());
    }
}
