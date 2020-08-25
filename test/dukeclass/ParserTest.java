package dukeclass;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ParserTest {

    @Test
    public void parser_emptyTodoDescription_exceptionThrown() {
        try {
            Parser.parser("todo");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Description of To Do cannot be empty", e.getMessage());
        }
    }

    @Test
    public void parser_emptyDeadlineDescription_exceptionThrown() {
        try {
            Parser.parser("deadline");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("Description of Deadline cannot be empty", e.getMessage());
        }
    }

}