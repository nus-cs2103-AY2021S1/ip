import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import util.Command;
import util.DukeException;
import util.Parser;

public class ParserTest {
    @Test
    public void parse_invalidCommand_returnInput() {
        String actual = "something";
        try {
            if (Parser.parse(actual) != Command.INVALID) {
                actual = Parser.parse(actual).toString();
            }
        } catch (DukeException e) {
            actual = e.getMessage();
        }
        String expected = "something";
        assertEquals(expected, actual);
    }

    @Test
    public void parse_todoValidInput_returnInput() {
        String actual = "todo read book";
        try {
            if (Parser.parse(actual) != Command.TODO) {
                actual = Parser.parse(actual).toString();
            }
        } catch (DukeException e) {
            actual = e.getMessage();
        }
        String expected = "todo read book";
        assertEquals(expected, actual);
    }

    @Test
    public void parse_deadlineValidInput_returnInput() {
        String actual = "deadline return book /by 2020-06-06";
        try {
            if (Parser.parse(actual) != Command.DEADLINE) {
                actual = Parser.parse(actual).toString();
            }
        } catch (DukeException e) {
            actual = e.getMessage();
        }
        String expected = "deadline return book /by 2020-06-06";
        assertEquals(expected, actual);
    }

    @Test
    public void parse_deadlineInvalidDate_exceptionThrown() {
        String actual = "deadline return book /by 2020/06/06";
        try {
            if (Parser.parse(actual) != Command.DEADLINE) {
                actual = Parser.parse(actual).toString();
            }
        } catch (DukeException e) {
            actual = e.getMessage();
        }
        String expected = "Whoops! I think there is an error in your date."
                + "\nPlease Try Again!";
        assertEquals(expected, actual);
    }

    @Test
    public void parse_deadlineMissingDate_exceptionThrown() {
        String actual = "deadline return book ";
        try {
            if (Parser.parse(actual) != Command.DEADLINE) {
                actual = Parser.parse(actual).toString();
            }
        } catch (DukeException e) {
            actual = e.getMessage();
        }
        String expected = "Whoops! I think you forgot to finish your "
                + "command. Sorry but I need it. D:";
        assertEquals(expected, actual);
    }
}
