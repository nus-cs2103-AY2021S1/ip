package ParserStorageUiTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import command.AddCommand;
import command.DeleteCommand;
import command.ExitCommand;
import parserstorageui.Parser;

public class ParserTest {
    @Test
    public void parseTestAddCommand() {
        assertEquals(Parser.parse("todo sleep"), new AddCommand("todo sleep"));
    }

    @Test
    public void parseTestExitCommand() {
        assertEquals(Parser.parse("bye"), new ExitCommand("bye"));
    }

    @Test
    public void parseTestDeleteCommand() {
        assertEquals(Parser.parse("delete 3"), new DeleteCommand("delete 3"));
    }
}
