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

    /**
     * Tests the basic commands given to the parser.
     */
    @Test
    public void testValidInputBasic() {
        try {
            String s1 = "todo work";
            String s2 = "deadline test /by 2pm";
            String s3 = "event test /at 2-4pm";
            String s4 = "list";
            String s5 = "done 5";
            String s6 = "delete 2";
            String s7 = "find book";
            String s8 = "bye";
            // Tests
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

    /**
     * Tests parser with inputs with random number of spaces.
     */
    @Test
    public void testValidInputAdvanced() {
        try {
            String s1 = "\t todo";
            String s2 = "deadline\t";
            String s3 = "event \t";
            String s4 = "\nlist\n";
            String s5 = "done done";
            String s6 = "delete random";
            String s7 = "   find   ";
            String s8 = "bye 444";
            // Tests
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

    /**
     * Tests parsing with invalid words.
     */
    @Test
    public void testInvalidInput() {
        String s1 = "random";
        String s2 = "bye1";
        String s3 = "findd";
        String s4 = "    ";
        String s5 = " do find";
        // Tests
        assertThrows(UnknownCommandException.class, () -> Parser.parse(s1));
        assertThrows(UnknownCommandException.class, () -> Parser.parse(s2));
        assertThrows(UnknownCommandException.class, () -> Parser.parse(s3));
        assertThrows(UnknownCommandException.class, () -> Parser.parse(s4));
        assertThrows(UnknownCommandException.class, () -> Parser.parse(s5));
    }
}
