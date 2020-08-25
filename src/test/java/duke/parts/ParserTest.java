package duke.parts;


import duke.command.Command;
import duke.error.UnknownAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parserTest() throws UnknownAction {
        Command c1 = Parser.parse("todo 1");
        Command c2 = Parser.parse("list");
        Command c3 = Parser.parse("event something/2020-10-10");
        assertEquals(c1.getClass().getName(), "duke.command.AddCommand");
        assertEquals(c2.getClass().getName(), "duke.command.PrintCommand");
        assertEquals(c3.getClass().getName(), "duke.command.AddCommand");
    }
}
