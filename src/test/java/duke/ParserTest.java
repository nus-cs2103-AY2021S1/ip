package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.DueCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.TaskType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @ParameterizedTest
    @ValueSource(strings = { "20/1/20 14:14", "20/01/20 2:14 PM", "20/1/2020 14:14", "20/01/2020 2:14 PM" })
    public void parseDateTime_validDateTimeStr_dateTimeReturned(String dateTimeStr) throws DukeException {
        LocalDateTime expectedDateTime = LocalDateTime.of(2020, 1, 20, 14, 14, 0);
        LocalDateTime actualDateTime = Parser.parseDateTime(dateTimeStr);

        assertEquals(expectedDateTime, actualDateTime);
    }

    @ParameterizedTest
    @ValueSource(strings = { "20 1400", "20-01 14:00 PM", "20-01-20 2PM", "20-01-2020 2 PM" })
    public void parseDateTime_invalidDateTimeStr_exceptionThrown(String dateTimeStr) {
        assertThrows(DukeException.class, () -> Parser.parseDateTime(dateTimeStr));
    }

    @ParameterizedTest
    @ValueSource(strings = { "20/1/20", "20/01/20", "20/1/2020", "20/01/2020" })
    public void parseDate_validDateStr_dateReturned(String dateStr) throws DukeException {
        LocalDate expectedDate = LocalDate.of(2020, 1, 20);
        LocalDate actualDate = Parser.parseDate(dateStr);

        assertEquals(expectedDate, actualDate);
    }

    @ParameterizedTest
    @ValueSource(strings = { "20", "20-01", "20-01-20", "20-01-2020" })
    public void parseDate_invalidDateStr_exceptionThrown(String dateStr) {
        assertThrows(DukeException.class, () -> Parser.parseDate(dateStr));
    }

    @ParameterizedTest
    @ValueSource(strings = { "14:14", "2:14 PM" })
    public void parseTime_validTimeStr_timeReturned(String timeStr) throws DukeException {
        LocalTime expectedTime = LocalTime.of(14, 14, 0);
        LocalTime actualTime = Parser.parseTime(timeStr);

        assertEquals(expectedTime, actualTime);
    }

    @ParameterizedTest
    @ValueSource(strings = { "1400", "14:00 PM", "2PM", "2 PM" })
    public void parseTime_invalidTimeStr_exceptionThrown(String timeStr) {
        assertThrows(DukeException.class, () -> Parser.parseTime(timeStr));
    }

    @Test
    public void parse_validAddCommand_addCommandReturned() throws DukeException {
        List<AddCommand> expectedCommands = Arrays.asList(
                new AddCommand(TaskType.TODO, "todo"),
                new AddCommand(TaskType.DEADLINE, "deadline", LocalDateTime.of(2020, 1, 20, 14, 14, 0)),
                new AddCommand(TaskType.EVENT, "event", LocalDateTime.of(2020, 1, 20, 14, 14, 0)));
        List<Command> actualCommands = Arrays.asList(
                Parser.parse("todo todo"),
                Parser.parse("deadline deadline /by 20/1/2020 2:14 PM"),
                Parser.parse("event event /at 20/1/2020 2:14 PM"));

        assertArrayEquals(expectedCommands.toArray(), actualCommands.toArray());
    }

    @Test
    public void parse_validDeleteCommand_deleteCommandReturned() throws DukeException {
        DeleteCommand expectedCommand = new DeleteCommand(1);
        Command actualCommand = Parser.parse("delete 1");

        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validDoneCommand_doneCommandReturned() throws DukeException {
        DoneCommand expectedCommand = new DoneCommand(1);
        Command actualCommand = Parser.parse("done 1");

        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validDueCommand_dueCommandReturned() throws DukeException {
        DueCommand expectedCommand = new DueCommand(LocalDate.of(2020, 1, 20));
        Command actualCommand = Parser.parse("due 20/1/2020");

        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validExitCommand_exitCommandReturned() throws DukeException {
        ExitCommand expectedCommand = new ExitCommand();
        Command actualCommand = Parser.parse("bye");

        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validFindCommand_findCommandReturned() throws DukeException {
        FindCommand expectedCommand = new FindCommand("task");
        Command actualCommand = Parser.parse("find task");

        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_validListCommand_listCommandReturned() throws DukeException {
        ListCommand expectedCommand = new ListCommand();
        Command actualCommand = Parser.parse("list");

        assertEquals(expectedCommand, actualCommand);
    }
}
