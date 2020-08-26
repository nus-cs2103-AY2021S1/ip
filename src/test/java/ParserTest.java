import duke.PARSER_RESULT;
import duke.Parser;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    Parser parser = new Parser();

    @Test
    public void parseInput_recognizableList_LIST() {
        String[] tests = {
                "list",
                " list",
                "  list   ",
                "List "};
        assertTrue(Arrays.stream(tests)
                .map(s -> parser.parseInput(s) == PARSER_RESULT.LIST)
                .reduce((x, y) -> x && y)
                .get());
    }

    @Test
    public void parseInput_recognizableBye_BYE() {
        String[] tests = {
                "bye",
                " Bye  ",
                "BYE "};
        assertTrue(Arrays.stream(tests)
                    .map(s -> parser.parseInput(s) == PARSER_RESULT.BYE)
                .reduce((x, y) -> x && y)
                .get());
    }
}
