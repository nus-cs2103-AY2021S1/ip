package main.parser;

import main.command.*;
import main.exception.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Nested
    @DisplayName("bye command")
    class Bye {
        @Test
        @DisplayName("should generate exit command object")
        public void parseBye() throws DukeException {
            Command command = Parser.parse(new String[] { "bye" });
            assertEquals(command, new ExitCommand());
        }
    }

    @Nested
    @DisplayName("list command")
    class List {
        @Test
        @DisplayName("should generate list command object")
        public void parseList() throws DukeException {
            Command command = Parser.parse(new String[] { "list" });
            assertEquals(command, new ListCommand());
        }
    }

    @Nested
    @DisplayName("done command")
    class Done {
        @Test
        @DisplayName("should generate done command object")
        public void parseDone() throws DukeException {
            Command command = Parser.parse(new String[] { "done", "1" });
            assertEquals(command, new DoneCommand(1));
        }

        @Test
        @DisplayName("should generate done command object with alt data")
        public void parseDoneTwo() throws DukeException {
            Command command = Parser.parse(new String[] { "done", "4123" });
            assertEquals(command, new DoneCommand(4123));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parseDoneThree() {
            InvalidTaskException exception = assertThrows(InvalidTaskException.class,
                    () -> Parser.parse(new String[] { "done" }));
            assertEquals("     ☹ OOPS!!! Your selected task does not exist!",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("delete command")
    class Delete {
        @Test
        @DisplayName("should generate delete command object")
        public void parseDelete() throws DukeException {
            Command command = Parser.parse(new String[] { "delete", "1" });
            assertEquals(command, new DeleteCommand(1));
        }

        @Test
        @DisplayName("should generate delete command object with alt data")
        public void parseDeleteTwo() throws DukeException {
            Command command = Parser.parse(new String[] { "delete", "12736" });
            assertEquals(command, new DeleteCommand(12736));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parseDeleteThree() {
            InvalidTaskException exception = assertThrows(InvalidTaskException.class,
                    () -> Parser.parse(new String[] { "delete" }));
            assertEquals("     ☹ OOPS!!! Your selected task does not exist!",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("todo command")
    class Todo {
        @Test
        @DisplayName("should generate add todo object")
        public void parseTodo() throws DukeException {
            Command command = Parser.parse(new String[] { "todo", "name" });
            assertEquals(command, new AddTodoCommand("name"));
        }

        @Test
        @DisplayName("should generate add todo object with alt data")
        public void parseTodoTwo() throws DukeException {
            Command command = Parser.parse(new String[] { "todo", "another" });
            assertEquals(command, new AddTodoCommand("another"));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parseTodoThree() {
            EmptyMessageException exception = assertThrows(EmptyMessageException.class,
                    () -> Parser.parse(new String[] { "todo" }));
            assertEquals("     ☹ OOPS!!! The description of a todo cannot be empty.",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("deadline command")
    class Deadline {
        @Test
        @DisplayName("should generate add deadline object")
        public void parseDeadline() throws DukeException {
            Command command = Parser.parse(
                    new String[] { "deadline", "name /by 1400-1-31 1453" });
            assertEquals(command, new AddDeadlineCommand("name",
                    LocalDateTime.of(1400, 1, 31, 14, 53)));
        }

        @Test
        @DisplayName("should generate add deadline object with alt data")
        public void parseDeadlineTwo() throws DukeException {
            Command command = Parser.parse(
                    new String[] { "deadline", "test /by 1285-5-3 2144" });
            assertEquals(command, new AddDeadlineCommand("test",
                    LocalDateTime.of(1285, 5, 3, 21, 44)));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parseDeadlineThree() {
            EmptyMessageException exception = assertThrows(
                    EmptyMessageException.class,
                    () -> Parser.parse(new String[] { "deadline" }));
            assertEquals("     ☹ OOPS!!! The description of a deadline cannot be empty.",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if deadline format is incorrect")
        public void parseDeadlineFour() {
            InvalidDeadlineFormatException exception = assertThrows(
                    InvalidDeadlineFormatException.class,
                    () -> Parser.parse(new String[] { "deadline", "name" }));
            assertEquals("     ☹ OOPS!!! A deadline needs to have this format:\n"
                            + "       \"task name\" /by \"task deadline\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time format has no spacing")
        public void parseDeadlineFive() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "deadline", "name /by 1931-3-30" }));
            assertEquals("     ☹ OOPS!!! Your date needs to"
                            + " have this format:\n     \"YYYY-MM-DD HHMM\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if date format is not YYYY-MM-DD")
        public void parseDeadlineSix() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "deadline", "name /by 1992-03 1923" }));
            assertEquals("     ☹ OOPS!!! Your date needs to"
                            + " have this format:\n     \"YYYY-MM-DD\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time format is not HHMM")
        public void parseDeadlineSeven() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "deadline", "name /by 1992-03-12 12394" }));
            assertEquals("     ☹ OOPS!!! Your time needs to"
                            + " have this format:\n     \"HHMM\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time not in integers")
        public void parseDeadlineEight() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "deadline", "name /by abc-12-1 1923" }));
            assertEquals("     ☹ OOPS!!! Please check that you've"
                            + " entered\n       the date and time correctly",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time is not possible")
        public void parseDeadlineNine() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "deadline", "name /by 1998-12-45 1923" }));
            assertEquals("     ☹ OOPS!!! Please check that you've"
                            + " entered\n       the date and time correctly",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("event command")
    class Event {
        @Test
        @DisplayName("should generate add event object")
        public void parseEvent() throws DukeException {
            Command command = Parser.parse(
                    new String[] { "event", "name /at 1400-1-31 1453" });
            assertEquals(command, new AddEventCommand("name",
                    LocalDateTime.of(1400, 1, 31, 14, 53)));
        }

        @Test
        @DisplayName("should generate add event object with alt data")
        public void parseEventTwo() throws DukeException {
            Command command = Parser.parse(
                    new String[] { "event", "test /at 1285-5-3 2144" });
            assertEquals(command, new AddEventCommand("test",
                    LocalDateTime.of(1285, 5, 3, 21, 44)));
        }

        @Test
        @DisplayName("should throw exception if no second argument")
        public void parseEventThree() {
            EmptyMessageException exception = assertThrows(
                    EmptyMessageException.class,
                    () -> Parser.parse(new String[] { "event" }));
            assertEquals("     ☹ OOPS!!! The description of a event cannot be empty.",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if event format is incorrect")
        public void parseEventFour() {
            InvalidEventFormatException exception = assertThrows(
                    InvalidEventFormatException.class,
                    () -> Parser.parse(new String[] { "event", "name" }));
            assertEquals("     ☹ OOPS!!! An event needs to have this format:\n"
                            + "      \"task name\" /at \"event time\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time format has no spacing")
        public void parseEventFive() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "event", "name /at 1931-3-30" }));
            assertEquals("     ☹ OOPS!!! Your date needs to"
                            + " have this format:\n     \"YYYY-MM-DD HHMM\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if date format is not YYYY-MM-DD")
        public void parseEventSix() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "event", "name /at 1992-03 1923" }));
            assertEquals("     ☹ OOPS!!! Your date needs to"
                            + " have this format:\n     \"YYYY-MM-DD\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time format is not HHMM")
        public void parseEventSeven() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "event", "name /at 1992-03-12 12394" }));
            assertEquals("     ☹ OOPS!!! Your time needs to"
                            + " have this format:\n     \"HHMM\"",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time not in integers")
        public void parseEventEight() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "event", "name /at abc-12-1 1923" }));
            assertEquals("     ☹ OOPS!!! Please check that you've"
                            + " entered\n       the date and time correctly",
                    exception.getMessage());
        }

        @Test
        @DisplayName("should throw exception if time is not possible")
        public void parseEventNine() {
            InvalidDateException exception = assertThrows(
                    InvalidDateException.class,
                    () -> Parser.parse(
                            new String[] { "event", "name /at 1998-12-45 1923" }));
            assertEquals("     ☹ OOPS!!! Please check that you've"
                            + " entered\n       the date and time correctly",
                    exception.getMessage());
        }
    }

    @Nested
    @DisplayName("unknown command")
    class Unknown {
        @Test
        @DisplayName("should throw exception")
        public void parseUnknown() {
            UnknownCommandException exception = assertThrows(
                    UnknownCommandException.class,
                    () -> Parser.parse(new String[] { "yeet" }));
            assertEquals("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(",
                    exception.getMessage());
        }
    }
}
