package duke.task;

import duke.exception.WrongFormatException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ToDoTest {

    @Test
    public void newToDo_validInput_toDoObject() {
        try {
            new ToDo("read book");
            assertTrue(true);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void newToDo_invalidInputNoDescription_wrongFormatExceptionThrown() {
        try {
            new ToDo("");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }
}