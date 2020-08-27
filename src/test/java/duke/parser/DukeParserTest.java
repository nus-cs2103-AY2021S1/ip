package duke.parser;

import duke.DukeException;
import duke.command.Command;
import org.junit.jupiter.api.Test;

public class DukeParserTest {
    @Test
    public void parseTest_testList() throws DukeException {
        String fullCommand = "list";
        Command command = Parser.parse(fullCommand);
        assert command.getClass().getTypeName().equals("duke.command.ListCommand");
    }

    @Test
    public void parseTest_testDone() throws DukeException {
        String fullCommand = "done 1";
        Command command = Parser.parse(fullCommand);
        assert command.getClass().getTypeName().equals("duke.command.DoneCommand");
    }

    @Test
    public void parseTest_testTodo() throws DukeException {
        String fullCommand = "todo Test ToDo: parse";
        Command command = Parser.parse(fullCommand);
        assert command.getClass().getTypeName().equals("duke.command.ToDoCommand");
    }

    @Test
    public void parseTest_testDeadline() throws DukeException {
        String fullCommand = "deadline Test deadline: parse /by 2-2-2020 1800";
        Command command = Parser.parse(fullCommand);
        assert command.getClass().getTypeName().equals("duke.command.DeadlineCommand");
    }

    @Test
    public void parseTest_testEvent() throws DukeException {
        String fullCommand = "event Test Event: parse /at 2-2-2020 1800-1900";
        Command command = Parser.parse(fullCommand);
        assert command.getClass().getTypeName().equals("duke.command.EventCommand");
    }

    @Test
    public void parseTest_testFind() throws DukeException {
        String fullCommand = "find book";
        Command command = Parser.parse(fullCommand);
        assert command.getClass().getTypeName().equals("duke.command.FindCommand");
    }

    @Test
    public void parseTest_testDelete() throws DukeException {
        String fullCommand = "delete 1";
        Command command = Parser.parse(fullCommand);
        assert command.getClass().getTypeName().equals("duke.command.DeleteCommand");
    }

    @Test
    public void parseTest_testBye() throws DukeException {
        String fullCommand = "bye";
        Command command = Parser.parse(fullCommand);
        assert command.getClass().getTypeName().equals("duke.command.ByeCommand");
    }

    @Test
    public void parseTest_testNoSuchCommand() throws DukeException {
        String fullCommand = "Test No Such Command: parse";
        try {
            Parser.parse(fullCommand);
        } catch (DukeException e) {
            assert e.getMessage().equals("☹ OOPS!!! I'm sorry, but I don't know what that means");
        }
    }
}
