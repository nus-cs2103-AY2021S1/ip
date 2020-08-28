package duke.Parser;

import duke.Command.AddCommand;
import duke.Command.ExitCommand;
import duke.Command.ListCommand;
import duke.Command.CompleteCommand;
import duke.Command.DeleteCommand;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parserAdd() {
        assertTrue(Parser.parse("todo Test") instanceof AddCommand);
        assertTrue(Parser.parse("deadline Test /by deadlineBy") instanceof AddCommand);
        assertTrue(Parser.parse("deadline Test /by 25/07/2015 1500") instanceof AddCommand);
        assertTrue(Parser.parse("event Test /at eventAt") instanceof AddCommand);
        assertTrue(Parser.parse("event Test /at 26/07/2014 1600") instanceof AddCommand);
    }

    @Test
    public void parserExit() {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    public void parserList() {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    public void parserComplete() {
        assertTrue(Parser.parse("done 2") instanceof CompleteCommand);
    }


    @Test
    public void parserDelete() {
        assertTrue(Parser.parse("delete 2") instanceof DeleteCommand);
    }
}
