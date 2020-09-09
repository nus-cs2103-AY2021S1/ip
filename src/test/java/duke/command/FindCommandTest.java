package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeStub;
import duke.data.DukeTaskListSideEffects;
import duke.exception.EmptyKeywordException;
import duke.exception.ExceptionMessage;

public class FindCommandTest {

    private final FindCommand command = new FindCommand();

    private final DukeStub dukeStub = new DukeStub();

    private DukeTaskListSideEffects taskListSideEffects = DukeTaskListSideEffects.getInstance();

    @Test
    public void constructorTest() {
        try {
            new FindCommand();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_normalInput_success() {
        String keyword = "book";

        try {
            command.execute(keyword, dukeStub);

            assertEquals(true, taskListSideEffects.findTasks);

            taskListSideEffects.reset();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void execute_emptyInput_exceptionThrown() {
        String emptyInput = "";

        try {
            Exception exception = assertThrows(
                    EmptyKeywordException.class, () -> command.execute(emptyInput, dukeStub));

            assertEquals(ExceptionMessage.EMPTY_KEYWORD_MESSAGE, exception.getMessage());
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
