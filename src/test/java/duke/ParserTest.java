package duke;

import duke.command.*;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseTest_nonActionCommands_Success() throws DukeException {
        String[] commands = new String[]{"Bye", "list", "DoNe", "CheCK"};
        Command[] expected = new Command[]{new ExitCommand(), new ListCommand(), new DoneCommand(),
                new CheckCommand()};
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
    public void parseTest_invalidCommands_Failure() {

        try {
            Parser.parse("deaLine");
            fail();
        } catch (DukeException e) {
            String err = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n"
                    + " I'm sorry but i do not know what you want to do. *woof*\n"
                    + ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
            assertEquals(err, e.getMessage());
        }

    }

    @ParameterizedTest
    @ValueSource(strings = {"DeadLine", "Event", "ToDo", "DeleTE"})
    public void checkActionTest_ActionCommands_Success(String s) throws DukeException {
        assertTrue(ParserStub.checkAction(s).equalsIgnoreCase(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"DeadLine", "Event", "ToDo"})
    public void parseTest_ActionAddCommands_Success(String s) throws DukeException {
        assertEquals(new AddCommand(Todo.createTask("todo buy books")), ParserStub.parse(s));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Delete"})
    public void parseTest_ActionDeleteCommands_Success(String s) throws DukeException {
        assertEquals(new DeleteCommand(), ParserStub.parse(s));
    }
}
