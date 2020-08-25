package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void test() {
        try {
            String s1 = "todo work";
            String s2 = "deadline test /by 2pm";
            String s3 = "event test /at 2-4pm";
            String s4 = "list";
            String s5 = "bye";
            String s6 = "done 5";
            String s7 = "delete 2";

            assertTrue(Parser.parse(s1) instanceof AddToDoCommand);
            assertTrue(Parser.parse(s2) instanceof AddComplexTaskCommand);
            assertTrue(Parser.parse(s3) instanceof AddComplexTaskCommand);
            assertTrue(Parser.parse(s4) instanceof ShowCommand);
            assertTrue(Parser.parse(s5) instanceof ExitCommand);
            assertTrue(Parser.parse(s6) instanceof SimpleCommand);
            assertTrue(Parser.parse(s7) instanceof SimpleCommand);

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
