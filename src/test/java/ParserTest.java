import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class ParserTest {
    @Test
    public void parser_invalidArgument() {
        try {
            Parser pr = new Parser();
            String input = "deadline ";
            assertFalse(pr.isValidDeadline(input));

        } catch (DukeException e) {
            System.out.println(e.toString());
        }
    }

}
