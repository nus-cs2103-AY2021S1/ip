import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.ParserResult;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    public void parseInput_recognizableList_parserResultList() {
        String[] tests = {
            "li",
            " l",
            "  lis   ",
            "List "};
        assertTrue(Arrays.stream(tests)
                .map(s -> parser.parseInput(s) == ParserResult.LIST)
                .reduce((x, y) -> x && y)
                .get());
    }

    @Test
    public void parseInput_recognizableBye_parserResultBye() {
        String[] tests = {
            "b",
            " By  ",
            "ByE "};
        assertTrue(Arrays.stream(tests)
                .map(s -> parser.parseInput(s) == ParserResult.BYE)
                .reduce((x, y) -> x && y)
                .get());
    }
}
