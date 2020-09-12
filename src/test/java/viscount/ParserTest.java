package viscount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import viscount.command.AddCommand;
import viscount.command.Command;
import viscount.command.DeleteAllCommand;
import viscount.command.DeleteAllDoneCommand;
import viscount.command.DeleteCommand;
import viscount.command.DoneAllCommand;
import viscount.command.DoneCommand;
import viscount.command.EditDateTimeCommand;
import viscount.command.EditDescriptionAndDateTimeCommand;
import viscount.command.EditDescriptionCommand;
import viscount.command.ListCommand;
import viscount.exception.ViscountException;
import viscount.task.Deadline;
import viscount.task.Event;
import viscount.task.Task;
import viscount.task.TaskType;
import viscount.task.Todo;

public class ParserTest {
    @ParameterizedTest
    @DisplayName("Parse valid date time input")
    @MethodSource("provideValidDateTimeForParseDateTime")
    public void parseDateTime_validUserInput_success(String userInput, LocalDateTime expectedResult) {
        LocalDateTime actualResult = Parser.parseDateTime(userInput, Parser.INPUT_DATE_TIME_FORMATTER);

        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideValidDateTimeForParseDateTime() {
        return Stream.of(
                Arguments.of("09-09-2020", LocalDateTime.of(2020, 9, 9, 0, 0)),
                Arguments.of("28-09-1998", LocalDateTime.of(1998, 9, 28, 0, 0)),
                Arguments.of("03-12-2019 1430", LocalDateTime.of(2019, 12, 3, 14, 30)),
                Arguments.of("23-12-2000 0820", LocalDateTime.of(2000, 12, 23, 8, 20))
        );
    }

    @ParameterizedTest
    @DisplayName("Parse invalid date time input")
    @ValueSource(strings = {"wrong", "09092020", "09-28-1998", "03-12-2019 2430", "23/12/2000 0820"})
    public void parseDateTime_invalidUserInput_exceptionThrown(String userInput) {
        try {
            Parser.parseDateTime(userInput, Parser.INPUT_DATE_TIME_FORMATTER);
            fail();
        } catch (DateTimeParseException e) {
            // Exception thrown and caught successfully
            assertTrue(true);
        }
    }

    @ParameterizedTest
    @DisplayName("Parse valid date time task data")
    @MethodSource("provideValidTaskDataForParseDateTime")
    public void parseDateTime_validTaskData_success(String taskData, LocalDateTime expectedResult) {
        LocalDateTime actualResult = Parser.parseDateTime(taskData, Parser.TASK_DATA_DATE_TIME_FORMATTER);

        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideValidTaskDataForParseDateTime() {
        return Stream.of(
                Arguments.of("20200909 0000", LocalDateTime.of(2020, 9, 9, 0, 0)),
                Arguments.of("19980928 0000", LocalDateTime.of(1998, 9, 28, 0, 0)),
                Arguments.of("20191203 1430", LocalDateTime.of(2019, 12, 3, 14, 30)),
                Arguments.of("20001223 0820", LocalDateTime.of(2000, 12, 23, 8, 20))
        );
    }

    @ParameterizedTest
    @DisplayName("Parse invalid date time task data")
    @ValueSource(strings = {"wrong", "20200909", "19982809", "20191203 2430", "20002312 0820"})
    public void parseDateTime_invalidTaskData_exceptionThrown(String userInput) {
        try {
            Parser.parseDateTime(userInput, Parser.TASK_DATA_DATE_TIME_FORMATTER);
            fail();
        } catch (DateTimeParseException e) {
            // Exception thrown and caught successfully
            assertTrue(true);
        }
    }

    @ParameterizedTest
    @DisplayName("Parse valid task data")
    @MethodSource("provideValidTaskDataForParseTaskData")
    public void parseTaskData_validTaskData_success(String taskData, Task expectedResult) {
        try {
            Task actualResult = Parser.parseTaskData(taskData);

            assertEquals(expectedResult, actualResult);
        } catch (IOException e) {
            fail();
        }
    }

    private static Stream<Arguments> provideValidTaskDataForParseTaskData() {
        return Stream.of(
                Arguments.of(
                        "TODO|0|t1", new Todo("t1", false)),
                Arguments.of(
                        "DEADLINE|1|t2|20200909 0000", new Deadline("t2", true, LocalDateTime.of(2020, 9, 9, 0, 0))),
                Arguments.of(
                        "EVENT|0|t3|20201223 0820", new Event("t3", false, LocalDateTime.of(2020, 12, 23, 8, 20)))
        );
    }

    @ParameterizedTest
    @DisplayName("Parse invalid task data")
    @ValueSource(strings = {"TASK|0|t1", "DEADLINE||t2|20200909 0000", "EVENT|0||20201223 0800"})
    public void parseTaskData_invalidTaskData_exceptionThrown(String taskData) {
        try {
            Parser.parseTaskData(taskData);
            fail();
        } catch (IOException e) {
            // Exception thrown and caught successfully
            assertTrue(true);
        }
    }

    @ParameterizedTest
    @DisplayName("Parse valid list command")
    @MethodSource("provideValidListCommandForParseListCommand")
    public void parse_validListCommand_success(String listCommand, Command expectedResult) {
        try {
            Command actualResult = Parser.parse(listCommand);

            assertEquals(expectedResult, actualResult);
        } catch (ViscountException e) {
            fail();
        }
    }

    private static Stream<Arguments> provideValidListCommandForParseListCommand() {
        return Stream.of(
                Arguments.of(
                        "list", new ListCommand("", "", "")),
                Arguments.of(
                        "list todo", new ListCommand("todo", "", "")),
                Arguments.of(
                        "list event /on 09-09-2020 /find book", new ListCommand("event", "09-09-2020", "book")),
                Arguments.of(
                        "list deadline /find book /on 09-09-2020", new ListCommand("deadline", "09-09-2020", "book"))
        );
    }

    @ParameterizedTest
    @DisplayName("Parse invalid list command")
    @ValueSource(strings = {"list all", "list todo /on 09-09-2020", "list /on"})
    public void parse_invalidListCommand_exceptionThrown(String listCommand) {
        try {
            Parser.parse(listCommand);
            fail();
        } catch (ViscountException e) {
            // Exception thrown and caught successfully
            assertTrue(true);
        }
    }

    @ParameterizedTest
    @DisplayName("Parse valid add command")
    @MethodSource("provideValidAddCommandForParseAddCommand")
    public void parse_validAddCommand_success(String addCommand, Command expectedResult) {
        try {
            Command actualResult = Parser.parse(addCommand);

            assertEquals(expectedResult, actualResult);
        } catch (ViscountException e) {
            fail();
        }
    }

    private static Stream<Arguments> provideValidAddCommandForParseAddCommand() {
        return Stream.of(
                Arguments.of(
                        "add todo t1", new AddCommand(TaskType.TODO, "t1", null)),
                Arguments.of(
                        "add deadline t2 /by 09-09-2020",
                        new AddCommand(TaskType.DEADLINE, "t2", LocalDateTime.of(2020, 9, 9, 0, 0))),
                Arguments.of(
                        "add event t3 /at 09-09-2020 2000",
                        new AddCommand(TaskType.EVENT, "t3", LocalDateTime.of(2020, 9, 9, 20, 0)))
        );
    }

    @ParameterizedTest
    @DisplayName("Parse invalid add command")
    @ValueSource(strings = {"add", "add todo", "add deadline t2 /by", "add event t3 /by 09-09-2020 2000"})
    public void parse_invalidAddCommand_exceptionThrown(String addCommand) {
        try {
            Parser.parse(addCommand);
            fail();
        } catch (ViscountException e) {
            // Exception thrown and caught successfully
            assertTrue(true);
        }
    }

    @ParameterizedTest
    @DisplayName("Parse valid edit command")
    @MethodSource("provideValidEditCommandForParseEditCommand")
    public void parse_validEditCommand_success(String editCommand, Command expectedResult) {
        try {
            Command actualResult = Parser.parse(editCommand);

            assertEquals(expectedResult, actualResult);
        } catch (ViscountException e) {
            fail();
        }
    }

    private static Stream<Arguments> provideValidEditCommandForParseEditCommand() {
        return Stream.of(
                Arguments.of(
                        "edit 1 /desc t0", new EditDescriptionCommand(0, "t0")),
                Arguments.of(
                        "edit 2 /date 28-09-2020", new EditDateTimeCommand(1, LocalDateTime.of(2020, 9, 28, 0, 0))),
                Arguments.of(
                        "edit 3 /desc t0 /date 28-09-2020 1020",
                        new EditDescriptionAndDateTimeCommand(2,
                                new EditDescriptionCommand(2, "t0"),
                                new EditDateTimeCommand(2, LocalDateTime.of(2020, 9, 28, 10, 20))))
        );
    }

    @ParameterizedTest
    @DisplayName("Parse invalid edit command")
    @ValueSource(strings = {"edit", "edit 1", "edit 1 /desc", "edit 1 /date 28092020"})
    public void parse_invalidEditCommand_exceptionThrown(String editCommand) {
        try {
            Parser.parse(editCommand);
            fail();
        } catch (ViscountException e) {
            // Exception thrown and caught successfully
            assertTrue(true);
        }
    }

    @ParameterizedTest
    @DisplayName("Parse valid done command")
    @MethodSource("provideValidDoneCommandForParseDoneCommand")
    public void parse_validDoneCommand_success(String doneCommand, Command expectedResult) {
        try {
            Command actualResult = Parser.parse(doneCommand);

            assertEquals(expectedResult, actualResult);
        } catch (ViscountException e) {
            fail();
        }
    }

    private static Stream<Arguments> provideValidDoneCommandForParseDoneCommand() {
        return Stream.of(
                Arguments.of(
                        "done 1", new DoneCommand(0)),
                Arguments.of(
                        "done all", new DoneAllCommand())
        );
    }

    @ParameterizedTest
    @DisplayName("Parse invalid done command")
    @ValueSource(strings = {"done", "done task"})
    public void parse_invalidDoneCommand_exceptionThrown(String doneCommand) {
        try {
            Parser.parse(doneCommand);
            fail();
        } catch (ViscountException e) {
            // Exception thrown and caught successfully
            assertTrue(true);
        }
    }

    @ParameterizedTest
    @DisplayName("Parse valid delete command")
    @MethodSource("provideValidDeleteCommandForParseDeleteCommand")
    public void parse_validDeleteCommand_success(String deleteCommand, Command expectedResult) {
        try {
            Command actualResult = Parser.parse(deleteCommand);

            assertEquals(expectedResult, actualResult);
        } catch (ViscountException e) {
            fail();
        }
    }

    private static Stream<Arguments> provideValidDeleteCommandForParseDeleteCommand() {
        return Stream.of(
                Arguments.of(
                        "delete 1", new DeleteCommand(0)),
                Arguments.of(
                        "delete all", new DeleteAllCommand()),
                Arguments.of(
                        "delete done", new DeleteAllDoneCommand())
        );
    }

    @ParameterizedTest
    @DisplayName("Parse invalid delete command")
    @ValueSource(strings = {"delete", "delete task"})
    public void parse_invalidDeleteCommand_exceptionThrown(String deleteCommand) {
        try {
            Parser.parse(deleteCommand);
            fail();
        } catch (ViscountException e) {
            // Exception thrown and caught successfully
            assertTrue(true);
        }
    }
}
