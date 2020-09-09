package duke.core.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import duke.core.DukeData;
import duke.core.command.AddCommand;
import duke.core.command.DeleteCommand;
import duke.core.command.DoneCommand;
import duke.core.command.ExitCommand;
import duke.core.command.FindCommand;
import duke.core.command.HelpCommand;
import duke.core.command.ListCommand;
import duke.core.command.LoadCommand;
import duke.core.command.SaveCommand;
import duke.core.task.Task;
import duke.core.task.ToDo;

class ParseToCommandTest {

    private final DukeData dukeData = new DukeData();
    private final Task dummy = new ToDo("Dummy Task");

    @BeforeEach
    void init() {
        dukeData.getTaskList().add(dummy);
    }

    @Test
    void parse_addCommand_success() {
        String input;

        input = "todo light";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof AddCommand);

        input = "todo light a candle";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof AddCommand);

        input = "deadline light a candle /by 02022020 1800";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof AddCommand);

        input = "event light a candle /from 02022020 0900 /till 02022020 1800";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof AddCommand);
    }

    @Test
    void parse_addCommand_exceptionThrown() {

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "todo"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "deadline"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "deadline hello"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "event"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "event greeting"));
    }

    @Test
    void parse_deleteCommand_success() {
        String input = "delete 1";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof DeleteCommand);
    }

    @Test
    void parse_deleteCommand_exceptionThrown() {

        // Missing required argument
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "delete"));

        // NumberFormatException -> DukeParserException
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "delete nothing"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "delete 5"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "delete 100"));
    }

    @Test
    void parse_doneCommand_success() {
        String input = "done 1";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof DoneCommand);
    }

    @Test
    void parse_doneCommand_exceptionThrown() {

        // Missing required argument
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "done"));

        // NumberFormatException -> DukeParserException
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "done nothing"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "done 5"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "done 100"));
    }

    @Test
    void parse_exitCommand_success() {
        String input = "bye";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof ExitCommand);
    }

    @Test
    void parse_findCommand_success() {
        String input;

        input = "find Dummy";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof FindCommand);

        input = "find nonexistant";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof FindCommand);

        input = "find 1231";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof FindCommand);
    }

    @Test
    void parse_helpCommand_success() {
        String input = "help";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof HelpCommand);
    }

    @Test
    void parse_invalidCommand_exceptionThrown() {

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, ""));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "invalid"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "invalid second"));
    }

    @Test
    void parse_listCommand_success() {
        String input = "list";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof ListCommand);
    }

    @Test
    void parse_loadCommand_success() {
        String input = "load save.txt";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof LoadCommand);
    }

    @Test
    void parse_loadCommand_exceptionThrown() {
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "load"));
    }

    @Test
    void parse_saveCommand_success() {
        String input = "save save.txt";
        assertTrue(ParseToCommand.parse(dukeData, input) instanceof SaveCommand);
    }

    @Test
    void parse_saveCommand_exceptionThrown() {
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dukeData, "save"));
    }

}
