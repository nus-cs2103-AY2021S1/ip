package duke.parser;

import duke.command.Command;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testTodoCommand_exceptionInvalidThrown() {
        try {
            String input = "todo";
            Command newCommand = Parser.parse(input);
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.toString());
        }
    }

    @Test
    public void testDeadlineCommand_exceptionInvalidThrown() {
        try {
            String input = "deadline 12345 6";
            Command newCommand = Parser.parse(input);
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Ensure that deadlines have correct description.", e.toString());
        }
    }

    @Test
    public void testEventCommand_exceptionInvalidThrown() {
        try {
            String input = "event /at 0";
            Command newCommand = Parser.parse(input);
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! Ensure that events have correct description.", e.toString());
        }
    }

    @Test
    public void testDeleteCommand_exceptionInvalidThrown() {
        try {
            String input = "delete a";
            Command newCommand = Parser.parse(input);
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! delete should be followed by a single task number.", e.toString());
        }
    }

    @Test
    public void testDoneCommand_exceptionInvalidThrown() {
        try {
            String input = "done";
            Command newCommand = Parser.parse(input);
        } catch (DukeException e) {
            assertEquals("☹ OOPS!!! done should be followed by a single task number.", e.toString());
        }
    }
}
