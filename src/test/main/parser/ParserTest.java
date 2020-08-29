package main.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import main.command.AddDeadlineCommand;
import main.command.AddEventCommand;
import main.command.AddTodoCommand;
import main.command.Command;
import main.command.DeleteCommand;
import main.command.DoneCommand;
import main.command.ExitCommand;
import main.command.FindCommand;
import main.command.ListCommand;
import main.exception.DukeException;
import main.exception.EmptyMessageException;
import main.exception.InvalidDateException;
import main.exception.InvalidDeadlineFormatException;
import main.exception.InvalidEventFormatException;
import main.exception.InvalidTaskException;
import main.exception.UnknownCommandException;

public class ParserTest {
    @Nested
    @DisplayName("bye command")
    class Bye {
        @Test
        @DisplayName("should generate exit command object")
        public void parse_byeCommand_objectExitCommand() throws DukeException {
            Command command = Parser.parse(new String[] { "bye" });
            assertEquals(command, new ExitCommand());
        }
    }

    @Nested
    @DisplayName("list command")
    class List {
        @Test
        @DisplayName("should generate list command object")
        public void parse_listCommand_objectListCommand() throws DukeException {
            Command command = Parser.parse(new String[] { "list" });
            assertEquals(command, new ListCommand());
        }
    }

    @Nested
    @DisplayName("done command")
    class Done {
        @Test
        @DisplayName("should generate done command object")
        public void parse_doneCommand_objectDoneCommand() throws DukeException {
            Command command = Parser.parse(new String[] { "done", "1" });
            assertEquals(command, new DoneCommand(1));
        }

        @Test
        @DisplayName("should generate done command object with alt data")
        public void parse_doneCommand_altDataDoneCommand() throws DukeException {
            Command command = Parser.parse(new String[] { "done", "4123" });
            assertEquals(command, new DoneCommand(4123));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parse_doneCommandNoSecondArg_throwException() {
            InvalidTaskException exception = assertThrows(
                    InvalidTaskException.class, () ->
                            Parser.parse(new String[] { "done" }));
            assertEquals("Your selected task does not exist!",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("delete command")
    class Delete {
        @Test
        @DisplayName("should generate delete command object")
        public void parse_deleteCommand_objectDeleteCommand() throws DukeException {
            Command command = Parser.parse(new String[] { "delete", "1" });
            assertEquals(command, new DeleteCommand(1));
        }

        @Test
        @DisplayName("should generate delete command object with alt data")
        public void parse_deleteCommand_altDataDeleteCommand() throws DukeException {
            Command command = Parser.parse(new String[] { "delete", "12736" });
            assertEquals(command, new DeleteCommand(12736));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parse_deleteCommandNoSecondArg_throwException() {
            InvalidTaskException exception = assertThrows(
                    InvalidTaskException.class, () ->
                            Parser.parse(new String[] { "delete" }));
            assertEquals("Your selected task does not exist!",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("todo command")
    class Todo {
        @Test
        @DisplayName("should generate add todo object")
        public void parse_todoCommand_objectAddTodoCommand()
                throws DukeException {
            Command command = Parser.parse(new String[] { "todo", "name" });
            assertEquals(command, new AddTodoCommand("name"));
        }

        @Test
        @DisplayName("should generate add todo object with alt data")
        public void parse_todoCommand_altDataAddTodoCommand() throws DukeException {
            Command command = Parser.parse(new String[] { "todo", "another" });
            assertEquals(command, new AddTodoCommand("another"));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parse_todoCommandNoSecondArg_throwException() {
            EmptyMessageException exception = assertThrows(
                    EmptyMessageException.class, () ->
                            Parser.parse(new String[] { "todo" }));
            assertEquals("The description of a todo cannot be empty.",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("deadline command")
    class Deadline {
        @Test
        @DisplayName("should generate add deadline object")
        public void parse_deadlineCommand_objectAddDeadlineCommand()
                throws DukeException {
            Command command = Parser.parse(
                    new String[] { "deadline", "name /by 1400-1-31 1453" });
            assertEquals(command, new AddDeadlineCommand("name",
                    LocalDateTime.of(1400, 1, 31, 14, 53)));
        }

        @Test
        @DisplayName("should generate add deadline object with alt data")
        public void parse_deadlineCommand_altDataAddDeadlineCommand()
                throws DukeException {
            Command command = Parser.parse(
                    new String[] { "deadline", "test /by 1285-5-3 2144" });
            assertEquals(command, new AddDeadlineCommand("test",
                    LocalDateTime.of(1285, 5, 3, 21, 44)));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parse_deadlineCommandNoSecondArg_throwException() {
            EmptyMessageException exception = assertThrows(
                    EmptyMessageException.class, () ->
                            Parser.parse(new String[] { "deadline" }));
            assertEquals("The description of a deadline cannot be empty.",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if deadline format is incorrect")
        public void parse_deadlineCommandWrongFormat_throwException() {
            InvalidDeadlineFormatException exception = assertThrows(
                    InvalidDeadlineFormatException.class, () ->
                            Parser.parse(new String[] { "deadline", "name" }));
            assertEquals("A deadline needs to have this format:\n"
                            + "\"task name\" /by \"task deadline\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time format has no spacing")
        public void parse_deadlineCommandNoSpacingTimeFormat_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "deadline",
                                "name /by 1931-3-30"
                            }));
            assertEquals("Your date needs to"
                            + " have this format:\n\"YYYY-MM-DD HHMM\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if date format is not YYYY-MM-DD")
        public void parse_deadlineCommandWrongDateFormat_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "deadline",
                                "name /by 1992-03 1923"
                            }));
            assertEquals("Your date needs to"
                            + " have this format:\n\"YYYY-MM-DD\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time format is not HHMM")
        public void parse_deadlineCommandWrongTimeFormat_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "deadline",
                                "name /by 1992-03-12 12394"
                            }));
            assertEquals("Your time needs to have this format:\n\"HHMM\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time not in integers")
        public void parse_deadlineCommandNonIntegerTime_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "deadline",
                                "name /by abc-12-1 1923"
                            }));
            assertEquals("Please check that you've entered "
                            + "the date and time correctly",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time is not possible")
        public void parse_deadlineCommandImpossibleTime_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "deadline",
                                "name /by 1998-12-45 1923"
                            }));
            assertEquals("Please check that you've entered"
                            + " the date and time correctly",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("event command")
    class Event {
        @Test
        @DisplayName("should generate add event object")
        public void parse_eventCommand_objectAddEventCommand()
                throws DukeException {
            Command command = Parser.parse(
                    new String[] { "event", "name /at 1400-1-31 1453" });
            assertEquals(command, new AddEventCommand("name",
                    LocalDateTime.of(1400, 1, 31, 14, 53)));
        }

        @Test
        @DisplayName("should generate add event object with alt data")
        public void parse_eventCommand_altDataAddEventCommand()
                throws DukeException {
            Command command = Parser.parse(
                    new String[] { "event", "test /at 1285-5-3 2144" });
            assertEquals(command, new AddEventCommand("test",
                    LocalDateTime.of(1285, 5, 3, 21, 44)));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parse_eventCommandNoSecondArg_throwException() {
            EmptyMessageException exception = assertThrows(
                    EmptyMessageException.class, () ->
                            Parser.parse(new String[] { "event" }));
            assertEquals("The description of a event cannot be empty.",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if event format is incorrect")
        public void parse_eventCommandWrongFormat_throwException() {
            InvalidEventFormatException exception = assertThrows(
                    InvalidEventFormatException.class, () ->
                            Parser.parse(new String[] { "event", "name" }));
            assertEquals("An event needs to have this format:\n"
                            + "\"task name\" /at \"event time\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time format has no spacing")
        public void parse_eventCommandTimeNoSpacing_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "event",
                                "name /at 1931-3-30"
                            }));
            assertEquals("Your date needs to"
                            + " have this format:\n\"YYYY-MM-DD HHMM\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if date format is not YYYY-MM-DD")
        public void parse_eventCommandWrongDateFormat_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "event",
                                "name /at 1992-03 1923"
                            }));
            assertEquals("Your date needs to have this format:\n\"YYYY-MM-DD\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time format is not HHMM")
        public void parse_eventCommandWrongTimeFormat_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "event",
                                "name /at 1992-03-12 12394"
                            }));
            assertEquals("Your time needs to"
                            + " have this format:\n\"HHMM\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time not in integers")
        public void parse_eventCommandNonIntegerTime_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "event",
                                "name /at abc-12-1 1923"
                            }));
            assertEquals("Please check that you've entered "
                            + "the date and time correctly",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time is not possible")
        public void parse_eventCommandImpossibleTime_throwException() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class, () ->
                            Parser.parse(new String[] {
                                "event",
                                "name /at 1998-12-45 1923"
                            }));
            assertEquals("Please check that you've entered "
                            + "the date and time correctly",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("find command")
    class Find {
        @Test
        @DisplayName("should generate find command object")
        public void parse_findCommand_objectFindCommand() throws DukeException {
            assertEquals(new FindCommand("test"),
                    Parser.parse(new String[] { "find", "test" }));
        }

        @Test
        @DisplayName("should generate find command object")
        public void parse_findCommand_altDataFindCommand() throws DukeException {
            assertEquals(new FindCommand("test"),
                    Parser.parse(new String[] { "find", "test" }));
        }

        @Test
        @DisplayName("should generate find command object with empty search term")
        public void parse_findCommand_emptySearchTermFindCommand()
                throws DukeException {
            assertEquals(new FindCommand(""),
                    Parser.parse(new String[] { "find" }));
        }
    }

    @Nested
    @DisplayName("unknown command")
    class Unknown {
        @Test
        @DisplayName("should throw exception")
        public void parse_unknownCommand_throwException() {
            UnknownCommandException exception = assertThrows(
                    UnknownCommandException.class, () ->
                            Parser.parse(new String[] { "yeet" }));
            assertEquals("I'm sorry, but I don't know what that means.",
                    exception.getMessage());
        }
    }
}
