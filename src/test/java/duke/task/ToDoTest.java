package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.ExceptionMessage;
import duke.exception.NoDescriptionException;

public class ToDoTest {

    @Test
    public void createToDo_normalInput_success() {
        String input01 = "read book";
        String input02 = "return book";
        String input03 = "have dinner";

        try {
            ToDo.createToDo(input01);
            ToDo.createToDo(input02);
            ToDo.createToDo(input03);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void createToDo_invalidInput_exceptionThrown() {
        Exception exception = assertThrows(NoDescriptionException.class, () -> ToDo.createToDo(""));

        String errMessage = ExceptionMessage.TODO_NO_DESCRIPTION_MESSAGE;

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void getTaskTypeTest() {
        assertEquals("todo", ToDo.createToDo("read book").getTaskType());
    }
}
