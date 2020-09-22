package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.exception.DeleteOutOfRangeException;
import duke.exception.DoneOutOfRangeException;
import duke.exception.EmptyDeadlineException;
import duke.exception.EmptyEventException;
import duke.exception.EmptyTodoException;
import duke.exception.MissingDeadlineDateException;
import duke.exception.MissingDeleteArgumentException;
import duke.exception.MissingDoneArgumentException;
import duke.exception.MissingEventDateException;
import duke.exception.MissingFindArgumentException;
import duke.exception.UnknownCommandException;

public class ParserTest {
    @Test
    public void parseValidTodo_success() throws DeleteOutOfRangeException, UnknownCommandException,
            MissingDoneArgumentException, EmptyEventException, MissingEventDateException, EmptyDeadlineException,
            MissingDeadlineDateException, DoneOutOfRangeException, MissingDeleteArgumentException, EmptyTodoException,
            MissingFindArgumentException {
        assertEquals(true, Parser.parseAndExecute("todo todotest", new TaskList(), new Ui()).isListUpdated());
    }

    @Test
    public void parseInvalidInput_throwsUnknownCommandException() throws DeleteOutOfRangeException,
            MissingDoneArgumentException, EmptyEventException, MissingEventDateException, EmptyDeadlineException,
            MissingDeadlineDateException, DoneOutOfRangeException, MissingDeleteArgumentException, EmptyTodoException {
        try {
            assertEquals(false, Parser.parseAndExecute("lmao xd", new TaskList(), new Ui()));
            fail(); //the test should not reach this line
        } catch (UnknownCommandException | MissingFindArgumentException e) {
            assertEquals("\uD83D\uDE41 OOPS! I'm sorry, but I don't know what that means :-(", e.toString());
        }
    }

    @Test
    public void parseInvalidDeadlineDate_throwsDateTimeParseException() throws DeleteOutOfRangeException,
            UnknownCommandException, MissingDoneArgumentException, EmptyEventException, MissingEventDateException,
            EmptyDeadlineException, MissingDeadlineDateException, DoneOutOfRangeException,
            MissingDeleteArgumentException, EmptyTodoException, MissingFindArgumentException {
        try {
            assertEquals(false, Parser.parseAndExecute("deadline test /by xddd", new TaskList(), new Ui()));
            fail(); //the test should not reach this line
        } catch (DateTimeParseException e) {
            assertEquals("java.time.format.DateTimeParseException: Text 'xddd' could not be parsed at index 0",
                    e.toString());
        }
    }

    @Test
    public void parseInvalidEventEmptyDescription_throwsEmptyEventException() throws DeleteOutOfRangeException,
            UnknownCommandException, MissingDoneArgumentException, MissingEventDateException, EmptyDeadlineException,
            MissingDeadlineDateException, DoneOutOfRangeException, MissingDeleteArgumentException, EmptyTodoException,
            MissingFindArgumentException {
        try {
            assertEquals(false, Parser.parseAndExecute("event /at 2020-10-12", new TaskList(), new Ui()));
            fail(); //the test should not reach this line
        } catch (EmptyEventException e) {
            assertEquals("\uD83D\uDE41 OOPS! The description of an event cannot be empty.", e.toString());
        }
    }
}
