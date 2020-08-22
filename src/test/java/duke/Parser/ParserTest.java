package duke.Parser;

import duke.Command.*;
import duke.Exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parserAdd() throws DukeException {
        assertTrue(Parser.parse("todo Test") instanceof AddCommand);
        assertTrue(Parser.parse("deadline Test /by deadlineBy") instanceof AddCommand);
        assertTrue(Parser.parse("deadline Test /by 25/07/2015 1500") instanceof AddCommand);
        assertTrue(Parser.parse("event Test /at eventAt") instanceof AddCommand);
        assertTrue(Parser.parse("event Test /at 26/07/2014 1600") instanceof AddCommand);
    }

    @Test
    public void parserExit() throws DukeException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parserList() throws DukeException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parserComplete() throws DukeException {
        assertTrue(Parser.parse("done 2") instanceof CompleteCommand);
    }


    @Test
    public void parserDelete() throws DukeException {
        assertTrue(Parser.parse("delete 2") instanceof DeleteCommand);
    }
}
