package duke.core.parser;

import duke.core.DataStore;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParseToCommandTest {

    private final DataStore dataStore = new DataStore();
    private final Task dummy = new ToDo("Dummy Task");

    @BeforeEach
    void init() {
        dataStore.getTaskList().add(dummy);
    }

    @Test
    void parse_addCommand_success() {
        String input;

        input = "todo light";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof AddCommand);

        input = "todo light a candle";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof AddCommand);

        input = "deadline light a candle /by 02022020 1800";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof AddCommand);

        input = "event light a candle /from 02022020 0900 /till 02022020 1800";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof AddCommand);
    }

    @Test
    void parse_addCommand_exceptionThrown() {

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "todo"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "deadline"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "deadline hello"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "event"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "event greeting"));
    }

    @Test
    void parse_deleteCommand_success() {
        String input = "delete 1";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof DeleteCommand);
    }

    @Test
    void parse_deleteCommand_exceptionThrown() {

        // Missing required argument
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "delete"));

        // NumberFormatException -> DukeParserException
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "delete nothing"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "delete 5"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "delete 100"));
    }

    @Test
    void parse_doneCommand_success() {
        String input = "done 1";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof DoneCommand);
    }

    @Test
    void parse_doneCommand_exceptionThrown() {

        // Missing required argument
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "done"));

        // NumberFormatException -> DukeParserException
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "done nothing"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "done 5"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "done 100"));
    }

    @Test
    void parse_exitCommand_success() {
        String input = "bye";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof ExitCommand);
    }

    @Test
    void parse_findCommand_success() {
        String input;

        input = "find Dummy";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof FindCommand);

        input = "find nonexistant";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof FindCommand);

        input = "find 1231";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof FindCommand);
    }

    @Test
    void parse_helpCommand_success() {
        String input = "help";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof HelpCommand);
    }

    @Test
    void parse_invalidCommand_exceptionThrown() {

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, ""));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "invalid"));

        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "invalid second"));
    }

    @Test
    void parse_listCommand_success() {
        String input = "list";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof ListCommand);
    }

    @Test
    void parse_loadCommand_success() {
        String input = "load save.txt";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof LoadCommand);
    }

    @Test
    void parse_loadCommand_exceptionThrown() {
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "load"));
    }

    @Test
    void parse_saveCommand_success() {
        String input = "save save.txt";
        assertTrue(ParseToCommand.parse(dataStore, input) instanceof SaveCommand);
    }

    @Test
    void parse_saveCommand_exceptionThrown() {
        assertThrows(DukeParserException.class, () -> ParseToCommand.parse(dataStore, "save"));
    }

}
