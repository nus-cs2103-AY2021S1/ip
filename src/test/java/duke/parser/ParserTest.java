package duke.parser;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.LoadCommand;
import duke.command.SaveCommand;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    private final List<Task> taskList = new ArrayList<>(5);
    private final Task dummy = new ToDo("Dummy Task");

    @BeforeEach
    void init() {
        taskList.add(dummy);
    }

    @Test
    void parse_addCommand_success() {
        String input;

        input = "todo light";
        assertTrue(Parser.parse(taskList, input) instanceof AddCommand);

        input = "todo light a candle";
        assertTrue(Parser.parse(taskList, input) instanceof AddCommand);

        input = "deadline light a candle /by 02022020 1800";
        assertTrue(Parser.parse(taskList, input) instanceof AddCommand);

        input = "event light a candle /from 02022020 0900 /till 02022020 1800";
        assertTrue(Parser.parse(taskList, input) instanceof AddCommand);
    }

    @Test
    void parse_addCommand_exceptionThrown() {

        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "todo"));

        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "deadline"));

        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "deadline hello"));

        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "event"));

        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "event greeting"));
    }

    @Test
    void parse_deleteCommand_success() {
        String input = "delete 1";
        assertTrue(Parser.parse(taskList, input) instanceof DeleteCommand);
    }

    @Test
    void parse_deleteCommand_exceptionThrown() {

        // Missing required argument
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "delete"));

        // NumberFormatException -> DukeParserException
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "delete nothing"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "delete 5"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "delete 100"));
    }

    @Test
    void parse_doneCommand_success() {
        String input = "done 1";
        assertTrue(Parser.parse(taskList, input) instanceof DoneCommand);
    }

    @Test
    void parse_doneCommand_exceptionThrown() {

        // Missing required argument
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "done"));

        // NumberFormatException -> DukeParserException
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "done nothing"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "done 5"));

        // Index out of range
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "done 100"));
    }

    @Test
    void parse_exitCommand_success() {
        String input = "bye";
        assertTrue(Parser.parse(taskList, input) instanceof ExitCommand);
    }

    @Test
    void parse_findCommand_success() {
        String input;

        input = "find Dummy";
        assertTrue(Parser.parse(taskList, input) instanceof FindCommand);

        input = "find nonexistant";
        assertTrue(Parser.parse(taskList, input) instanceof FindCommand);

        input = "find 1231";
        assertTrue(Parser.parse(taskList, input) instanceof FindCommand);
    }

    @Test
    void parse_helpCommand_success() {
        String input = "help";
        assertTrue(Parser.parse(taskList, input) instanceof HelpCommand);
    }

    @Test
    void parse_invalidCommand_exceptionThrown() {

        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, ""));

        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "invalid"));

        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "invalid second"));
    }

    @Test
    void parse_listCommand_success() {
        String input = "list";
        assertTrue(Parser.parse(taskList, input) instanceof ListCommand);
    }

    @Test
    void parse_loadCommand_success() {
        String input = "load save.txt";
        assertTrue(Parser.parse(taskList, input) instanceof LoadCommand);
    }

    @Test
    void parse_loadCommand_exceptionThrown() {
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "load"));
    }

    @Test
    void parse_saveCommand_success() {
        String input = "save save.txt";
        assertTrue(Parser.parse(taskList, input) instanceof SaveCommand);
    }

    @Test
    void parse_saveCommand_exceptionThrown() {
        assertThrows(DukeParserException.class, () -> Parser.parse(taskList, "save"));
    }

}
