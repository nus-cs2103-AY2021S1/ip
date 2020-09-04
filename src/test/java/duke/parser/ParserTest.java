package duke.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.AddComplexTaskCommand;
import duke.command.AddToDoCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ShowCommand;
import duke.command.SimpleCommand;
import duke.exception.DukeException;
import duke.exception.UnknownCommandException;

public class ParserTest {
    @Test
    public void testParseBasic() {
        try {
            String s1 = "tODo work";
            String s2 = "deadline test /by 2pm";
            String s3 = "event test /at 2-4pm";
            String s4 = "list";
            String s5 = "done 5";
            String s6 = "delete 2";
            String s7 = "finD book";
            String s8 = "bYe";

            assertTrue(Parser.parse(s1) instanceof AddToDoCommand);
            assertTrue(Parser.parse(s2) instanceof AddComplexTaskCommand);
            assertTrue(Parser.parse(s3) instanceof AddComplexTaskCommand);
            assertTrue(Parser.parse(s4) instanceof ShowCommand);
            assertTrue(Parser.parse(s5) instanceof SimpleCommand);
            assertTrue(Parser.parse(s6) instanceof SimpleCommand);
            assertTrue(Parser.parse(s7) instanceof FindCommand);
            assertTrue(Parser.parse(s8) instanceof ExitCommand);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void testParseException() {
        String s1 = "random";
        String s2 = "bye1";
        String s3 = "findd";

        assertThrows(UnknownCommandException.class, () -> Parser.parse(s1));
        assertThrows(UnknownCommandException.class, () -> Parser.parse(s2));
        assertThrows(UnknownCommandException.class, () -> Parser.parse(s3));
    }
}
