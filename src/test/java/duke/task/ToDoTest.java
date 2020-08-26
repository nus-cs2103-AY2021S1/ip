package duke.task;

import duke.exception.NoDescriptionException;
import duke.ui.UIPrint;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        String line = UIPrint.getLine(UIPrint.star, 50);
        String errMessage =
                line + "\nOOPS!!! The description of a todo cannot be empty.\n" + line;

        assertEquals(errMessage, exception.getMessage());
    }

    @Test
    public void getTaskTypeTest() {
        assertEquals("todo", ToDo.createToDo("read book").getTaskType());
    }
}
