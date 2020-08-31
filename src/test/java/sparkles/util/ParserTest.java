package sparkles.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import sparkles.command.DoneCommand;
import sparkles.command.ShowListCommand;

public class ParserTest {

    @Test
    public void test() {

        new DoneCommand("done 2");
        assertTrue(Parser.parse("done 2") instanceof DoneCommand);

        new ShowListCommand("");
        assertTrue(Parser.parse("list") instanceof ShowListCommand);
    }
}
