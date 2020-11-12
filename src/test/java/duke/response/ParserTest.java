package duke.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.backend.Storage;
import duke.command.AddCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.common.exception.DukeException;
import duke.response.exception.DukeEmptyAtException;
import duke.response.exception.DukeEmptyByException;
import duke.response.exception.DukeEmptyDescriptionException;
import duke.response.exception.DukeEmptyIndexException;
import duke.response.exception.DukeEmptyKeywordException;
import duke.response.exception.DukeInvalidDateTimeInputException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a test class for Parser.
 */
public class ParserTest {
    public static final String VALID_TODO = "todo task1";
    public static final String INVALID_TODO = "todo";
    public static final String VALID_DEADLINE = "deadline task2 /by 15/9/2020 1800";
    public static final String INVALID_DEADLINE = "deadline task2 ";
    public static final String VALID_EVENT = "event task3 /at 15/9/2020 1700";
    public static final String INVALID_EVENT = "event task4 /at ";

    private final TaskList taskList1 = new TaskList();
    private final TaskList taskList2 = new TaskList();
    private final Response response = new Response();
    private final Storage testStorage1 = new Storage(System.getProperty("user.home") + "/test");
    private final Storage testStorage2 = new Storage(System.getProperty("user.home") + "/test2");

    @Test
    public void parser_validTodo_success()
        throws DukeEmptyDescriptionException, DukeEmptyKeywordException, DukeEmptyByException,
        DukeInvalidDateTimeInputException, DukeEmptyIndexException, DukeEmptyAtException {
        AddCommand result = new AddCommand(new Todo("task1"));
        assertEquals(Parser.parse(VALID_TODO).execute(taskList1, response, testStorage1),
                result.execute(taskList2, response, testStorage2));
    }

    @Test
    public void parser_invalidTodo_failure() {
        DukeEmptyDescriptionException exception = new DukeEmptyDescriptionException("todo");
        try {
            Parser.parse(INVALID_TODO).execute(taskList1, response, testStorage1);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), exception.getMessage());
        }
    }

    @Test
    public void parser_validDeadline_success()
        throws DukeEmptyDescriptionException, DukeEmptyKeywordException, DukeEmptyByException,
        DukeInvalidDateTimeInputException, DukeEmptyIndexException, DukeEmptyAtException {
        AddCommand result = new AddCommand(new Deadline("task2", LocalDate.of(2020, 9, 15), LocalTime.of(18, 0)));
        assertEquals(Parser.parse(VALID_DEADLINE).execute(taskList1, response, testStorage1),
                result.execute(taskList2, response, testStorage2));
    }

    @Test
    public void parser_invalidDeadline_failure() {
        DukeEmptyByException exception = new DukeEmptyByException();
        try {
            Parser.parse(INVALID_DEADLINE).execute(taskList1, response, testStorage1);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), exception.getMessage());
        }
    }

    @Test
    public void parser_validEvent_success()
        throws DukeEmptyDescriptionException, DukeEmptyKeywordException, DukeEmptyByException,
        DukeInvalidDateTimeInputException, DukeEmptyIndexException, DukeEmptyAtException {
        AddCommand result = new AddCommand(new Event("task3", LocalDate.of(2020, 9, 15), LocalTime.of(17, 0)));
        assertEquals(Parser.parse(VALID_EVENT).execute(taskList1, response, testStorage1),
                result.execute(taskList2, response, testStorage2));
    }

    @Test
    public void parser_invalidEvent_failure() {
        DukeEmptyAtException exception = new DukeEmptyAtException();
        try {
            Parser.parse(INVALID_EVENT).execute(taskList1, response, testStorage1);
        } catch (DukeException e) {
            assertEquals(e.getMessage(), exception.getMessage());
        }
    }

    @Test
    public void parser_validHelp_success()
        throws DukeEmptyDescriptionException, DukeEmptyKeywordException, DukeEmptyByException,
        DukeInvalidDateTimeInputException, DukeEmptyIndexException, DukeEmptyAtException {
        HelpCommand helpCommand = new HelpCommand();
        assertEquals(Parser.parse("help").execute(taskList1, response, testStorage1),
                helpCommand.execute(taskList2, response, testStorage2));
    }

    @Test
    public void parser_validList_success()
        throws DukeEmptyDescriptionException, DukeEmptyKeywordException, DukeEmptyByException,
        DukeInvalidDateTimeInputException, DukeEmptyIndexException, DukeEmptyAtException {
        ListCommand listCommand = new ListCommand();
        assertEquals(Parser.parse("list").execute(taskList1, response, testStorage1),
                listCommand.execute(taskList2, response, testStorage2));
    }

    @Test
    public void parser_unknownCommand_success()
        throws DukeEmptyDescriptionException, DukeEmptyKeywordException, DukeEmptyByException,
        DukeInvalidDateTimeInputException, DukeEmptyIndexException, DukeEmptyAtException {
        UnknownCommand unknownCommand = new UnknownCommand();
        assertEquals(Parser.parse("unknown command").execute(taskList1, response, testStorage1),
                unknownCommand.execute(taskList2, response, testStorage2));
    }
}
