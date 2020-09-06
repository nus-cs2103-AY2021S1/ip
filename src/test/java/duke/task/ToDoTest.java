package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void todoCreation_normalInput_success() {
        ToDo todo = new ToDo("read book");
        Assertions.assertEquals("[T][\u2718][UNCLASSIFIED] read book", todo.toString());
    }

    @Test
    public void todoCreation_noDesc_exceptionThrown() {
        try {
            ToDo todo = new ToDo("");
            Assertions.fail();
        } catch (DukeException de) {
            Assertions.assertEquals(new DukeException("The description cannot be empty").getMessage(), de.getMessage());
        }
    }

    @Test
    public void todoDone_setDone_success() {
        ToDo todo = new ToDo("read book");
        todo.setDone();
        Assertions.assertEquals("[T][\u2713][UNCLASSIFIED] read book", todo.toString());
    }

    @Test
    public void todoExport_noInput_success() {
        ToDo todo = new ToDo("wash clothes");
        todo.setDone();
        Assertions.assertEquals("T`1`5`wash clothes", todo.getSaveToFileString());
    }

    @Test
    public void todoPriority_changePriority_success() {
        ToDo todo = new ToDo("sleep");
        todo.setPriority(1);
        Assertions.assertEquals(Priority.CRITICAL, todo.getPriority());
    }

    @Test
    public void todoPriority_changePriority_invalidPriority() {
        try {
            ToDo todo = new ToDo("sleep");
            todo.setPriority(-1);
            Assertions.fail();
        } catch (DukeException e) {
            Assertions.assertEquals(new DukeException("Invalid Priority Given...").getMessage(), e.getMessage());
        }
    }
}
