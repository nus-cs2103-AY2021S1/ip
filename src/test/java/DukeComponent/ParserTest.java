package DukeComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;

import Command.Command;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void parseTest() {
        assertEquals(Parser.parse("list", 0).getType(), Command.CommandType.LIST);
        assertEquals(Parser.parse("bye", 0).getType(), Command.CommandType.EXIT);
        assertEquals(Parser.parse("delete 2", 0).getType(), Command.CommandType.ERROR);
        assertEquals(Parser.parse("delete 2", 2).getType(), Command.CommandType.DELETE);
        assertEquals(Parser.parse("done 1", 1).getType(), Command.CommandType.DONE);
        assertEquals(Parser.parse("todo 1", 5).getType(), Command.CommandType.ADD);
        assertEquals(Parser.parse("asdjoasjd", 5).getType(), Command.CommandType.ERROR);
    }
}
