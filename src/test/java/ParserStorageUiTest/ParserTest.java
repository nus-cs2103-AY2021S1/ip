package ParserStorageUiTest;
import Command.DeleteCommand;
import Command.ExitCommand;
import ParserStorageUi.Parser;
import Command.AddCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseTestAddCommand(){
        assertEquals(Parser.parse("todo sleep") , new AddCommand("todo sleep"));
    }
    @Test
    public void parseTestExitCommand(){
        assertEquals(Parser.parse("bye") , new ExitCommand("bye"));
    }

    @Test
    public void parseTestDeleteCommand(){
        assertEquals(Parser.parse("delete 3") , new DeleteCommand("delete 3"));
    }
}
