package exceptions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class InvalidDescriptionExceptionTest extends InvalidCommandExceptionTest {

    @Test
    public void invalidDescriptionException_blankTodoDescription_exceptionThrown() {
        Assertions.assertThrows(InvalidDescriptionException.class, (
                ) -> new Todo(""));
        Assertions.assertThrows(InvalidDescriptionException.class, (
                ) -> new Todo(" "));
        Assertions.assertThrows(InvalidDescriptionException.class, (
                ) -> new Todo("        "));
    }

    @Test
    public void invalidDescriptionException_blankEventDescription_exceptionThrown() {
        Assertions.assertThrows(InvalidDescriptionException.class, (
                ) -> new Event("", "next Monday"));
        Assertions.assertThrows(InvalidDescriptionException.class, (
                ) -> new Event(" ", "next Monday"));
        Assertions.assertThrows(InvalidDescriptionException.class, (
                ) -> new Event("        ", "next Monday"));
    }

    @Test
    public void invalidDescriptionException_blankDeadlineDescription_exceptionThrown() {
        Assertions.assertThrows(InvalidDescriptionException.class, (
                ) -> new Deadline("", "next Monday"));
        Assertions.assertThrows(InvalidDescriptionException.class, (
                ) -> new Deadline(" ", "next Monday"));
        Assertions.assertThrows(InvalidDescriptionException.class, (
                ) -> new Deadline("        ", "next Monday"));
    }
}
