package duke.task;

import duke.exception.DukeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ToDoTest {
    @Test
    public void todoCreation_normalInput_success() {
        ToDo todo = new ToDo("read book");
        Assertions.assertEquals("[T][\u2718] read book", todo.toString());
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
        Assertions.assertEquals("[T][\u2713] read book", todo.toString());
    }

    @Test
    public void todoExport_noInput_success() {
        ToDo todo = new ToDo("wash clothes");
        todo.setDone();
        Assertions.assertEquals("T`1`wash clothes", todo.getSaveToFileString());
    }
}
