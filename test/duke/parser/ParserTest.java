package duke.parser;

import duke.command.*;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.DukeDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ParserTest {

    List<Task> taskList = new ArrayList<>(5);
    Task dummy = new ToDo("Dummy Task");

    @BeforeEach
    void init() {
        taskList.add(dummy);
    }

    @Test
    void parseAddCommand_success(){
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
    void parseAddCommand_failure() {
        String input;

        input = "todo";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "deadline";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "deadline hello";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "event";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "event greeting";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);
    }

    @Test
    void parseDeleteCommand_success() {
        String input = "delete 1";
        assertTrue(Parser.parse(taskList, input) instanceof DeleteCommand);
    }

    @Test
    void parseDeleteCommand_failure() {
        String input;

        input = "delete";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "delete nothing";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "delete 5"; // Index out of range
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "delete 100"; // Index out of range
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);
    }

    @Test
    void parseDoneCommand_success() {
        String input = "done 1";
        assertTrue(Parser.parse(taskList, input) instanceof DoneCommand);
    }

    @Test
    void parseDoneCommand_failure() {
        String input;

        input = "done";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "done nothing";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "done 5"; // Index out of range
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "done 100"; // Index out of range
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);
    }

    @Test
    void parseExitCommand_success() {
        String input = "bye";
        assertTrue(Parser.parse(taskList, input) instanceof ExitCommand);
    }

    @Test
    void parseFindCommand_success() {
        String input;

        input = "find Dummy";
        assertTrue(Parser.parse(taskList, input) instanceof FindCommand);

        input = "find nonexistant";
        assertTrue(Parser.parse(taskList, input) instanceof FindCommand);

        input = "find 1231";
        assertTrue(Parser.parse(taskList, input) instanceof FindCommand);
    }

    @Test
    void parseHelpCommand_success() {
        String input = "help";
        assertTrue(Parser.parse(taskList, input) instanceof HelpCommand);
    }

    @Test
    void parseInvalidCommand_success() {
        String input;

        input = "";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "invalid";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);

        input = "invalid two";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);
    }

    @Test
    void parseListCommand_success() {
        String input = "list";
        assertTrue(Parser.parse(taskList, input) instanceof ListCommand);
    }

    @Test
    void parseLoadCommand_success() {
        String input = "load save.txt";
        assertTrue(Parser.parse(taskList, input) instanceof LoadCommand);
    }

    @Test
    void parseLoadCommand_failure() {
        String input = "load";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);
    }

    @Test
    void parseSaveCommand_success() {
        String input = "save save.txt";
        assertTrue(Parser.parse(taskList, input) instanceof SaveCommand);
    }

    @Test
    void parseSaveCommand_failure() {
        String input = "save";
        assertTrue(Parser.parse(taskList, input) instanceof InvalidCommand);
    }

}