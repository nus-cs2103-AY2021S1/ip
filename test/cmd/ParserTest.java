package cmd;

import command.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Task;
import task.ToDo;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParserTest {

    List<Task> taskList = new ArrayList<>(5);
    Task dummy = new ToDo("Dummy Task");

    @BeforeEach
    void init() {
        taskList.add(dummy);
    }

    @Test
    void parseAddCommand_success(){
        String input = "todo " + dummy.getDescription();
        Command expected = new AddCommand(taskList, dummy);
        Command actual = Parser.parse(taskList, input);
        assertEquals(expected, actual);
    }

    @Test
    void parseDeleteCommand_success() {
        String input = "delete 1";
        Command expected = new DeleteCommand(taskList, dummy);
        Command actual = Parser.parse(taskList, input);
        assertEquals(expected, actual);
    }

    @Test
    void parseDoneCommand_success() {
        String input = "done 1";
        Command expected = new DoneCommand(dummy);
        Command actual = Parser.parse(taskList, input);
        assertEquals(expected, actual);
    }

    @Test
    void parseExitCommand_success() {
        String input = "bye";
        Command expected = new ExitCommand();
        Command actual = Parser.parse(taskList, input);
        assertEquals(expected, actual);
    }

    @Test
    void parseHelpCommand_success() {
        String input = "help";
        Command expected = new HelpCommand();
        Command actual = Parser.parse(taskList, input);
        assertEquals(expected, actual);
    }

    @Test
    void parseInvalidCommand_success() {
        Command expected = new InvalidCommand();

        Command actual = Parser.parse(taskList, "");
        assertEquals(expected, actual);

        actual = Parser.parse(taskList, "invalidone");
        assertEquals(expected, actual);

        actual = Parser.parse(taskList, "invalidone invalidtwo");
        assertEquals(expected, actual);
    }

    @Test
    void parseListCommand_success() {
        String input = "list";
        Command expected = new ListCommand(taskList);
        Command actual = Parser.parse(taskList, input);
        assertEquals(expected, actual);
    }

    @Test
    void parseLoadCommand_success() {
        String input = "load save.txt";
        Command expected = new LoadCommand(taskList, "save.txt");
        Command actual = Parser.parse(taskList, input);
        assertEquals(expected, actual);
    }

    @Test
    void parseSaveCommand_success() {
        String input = "save save.txt";
        Command expected = new SaveCommand(taskList, "save.txt");
        Command actual = Parser.parse(taskList, input);
        assertEquals(expected, actual);
    }

}