package duke.parser;

import duke.command.CommandType;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void getTaskDescription_validCommand_returnDescription() throws DukeException {
        assertEquals(Parser.getTaskDescription(CommandType.DEADLINE, "deadline hello world"), "hello world");
        assertNotEquals(Parser.getTaskDescription(CommandType.DEADLINE, "deadline hello world"), "rld");
    }

    @Test
    void getTaskDescription_invalidCommand_throwsException() {
        assertThrows(DukeException.class, () ->
                Parser.getTaskDescription(CommandType.DEADLINE, "deadlin hello world")
        );
    }

    @Test
    void getTaskTargetIndex_validCommand_returnIndex() throws DukeException {
        assertEquals(Parser.getTaskTargetIndex(CommandType.DELETE, "delete 100"), 100);
        assertEquals(Parser.getTaskTargetIndex(CommandType.DELETE, "delete 10"), 10);
        assertNotEquals(Parser.getTaskTargetIndex(CommandType.DELETE, "delete 9"), 10);
    }

    @Test
    void getTaskTargetIndex_invalidCommand_throwsException() {
        assertThrows(DukeException.class, () ->
                Parser.getTaskTargetIndex(CommandType.DELETE, "del hello world")
        );
    }

    @Test
    void parseDateString_validDate_returnLocalDate() throws DukeException {
        assertEquals(Parser.parseDateString("1966-04-02"), LocalDate.parse("1966-04-02"));
        assertNotEquals(Parser.parseDateString("1966-04-02"), LocalDate.parse("1966-04-01"));
    }

    @Test
    void parseDateString_invalidDate_throwsException() {
        assertThrows(DukeException.class, () ->
                Parser.parseDateString("1966-04-")
        );
    }

    @Test
    void parseCommandString_validCommandString_doesNotThrowException() throws DukeException {
        Parser.parseCommandString("deadline return book /by 2020-07-29");
    }

    @Test
    void parseCommandString_invalidCommandString_throwsException() {
        assertThrows(DukeException.class, () ->
                Parser.parseCommandString("deadlin return book /by 2020-07-29")
        );
    }
}