package exceptions;

import main.java.exceptions.InvalidDescriptionException;
import main.java.tasks.Deadline;
import main.java.tasks.Event;
import main.java.tasks.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidDescriptionExceptionTest extends InvalidCommandExceptionTest {

    @Test
    public void invalidDescriptionException_blankTodoDescription_exceptionThrown() {
        Assertions.assertThrows(InvalidDescriptionException.class,
                () -> new Todo(""));
        Assertions.assertThrows(InvalidDescriptionException.class,
                () -> new Todo(" "));
        Assertions.assertThrows(InvalidDescriptionException.class,
                () -> new Todo("        "));
    }

    @Test
    public void invalidDescriptionException_blankEventDescription_exceptionThrown() {
        Assertions.assertThrows(InvalidDescriptionException.class,
                () -> new Event("", "next Monday"));
        Assertions.assertThrows(InvalidDescriptionException.class,
                () -> new Event(" ", "next Monday"));
        Assertions.assertThrows(InvalidDescriptionException.class,
                () -> new Event("        ", "next Monday"));
    }

    @Test
    public void invalidDescriptionException_blankDeadlineDescription_exceptionThrown() {
        Assertions.assertThrows(InvalidDescriptionException.class,
                () -> new Deadline("", "next Monday"));
        Assertions.assertThrows(InvalidDescriptionException.class,
                () -> new Deadline(" ", "next Monday"));
        Assertions.assertThrows(InvalidDescriptionException.class,
                () -> new Deadline("        ", "next Monday"));
    }
}
