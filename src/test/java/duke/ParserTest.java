package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import duke.command.AddCommand;
import duke.command.CheckCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.Todo;

public class ParserTest {

    @Test
    public void parseTest_nonActionCommands_success() throws DukeException {
        String[] commands = new String[]{"Bye", "list", "DoNe", "CheCK", "Delete", "fiNd"};
        Command[] expected = new Command[]{new ExitCommand(), new ListCommand(), new DoneCommand(),
            new CheckCommand(), new DeleteCommand(), new FindCommand("s")};
        List<Command> response = new ArrayList<>();

        for (String s : commands) {
            response.add(Parser.parse(s));
        }

        int ind = 0;
        boolean isSame = true;

        for (Command r : response) {
            if (!r.equals(expected[ind])) {
                isSame = false;
            }
            ind++;

        }
        assertTrue(isSame);
    }

    @Test
    public void parseTest_invalidCommands_failure() {

        try {
            Parser.parse("deaLine");
            fail();
        } catch (DukeException e) {
            String err = " I'm sorry but i do not know what you want to do. *woof*\n";
            assertEquals(err, e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"DeadLine", "Event", "ToDo"})
    public void checkActionTest_actionCommands_success(String s) throws DukeException {
        assertTrue(ParserStub.checkAction(s).equalsIgnoreCase(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"DeadLine", "Event", "ToDo"})
    public void parseTest_actionAddCommands_success(String s) throws DukeException {
        assertEquals(new AddCommand(Todo.createTask("todo buy books")), ParserStub.parse(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Delete"})
    public void parseTest_actionDeleteCommands_success(String s) throws DukeException {
        assertEquals(new DeleteCommand(), ParserStub.parse(s));
    }
}
