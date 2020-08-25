package misc;

import main.java.exception.InvalidArgumentException;
import main.java.misc.Parser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParseCommandNormalCase() {
        List<String> actual = Parser.parseCommand("deadline read book /by 21/12/2012 1900");
        List<String> expected = new ArrayList<>(Arrays.asList("deadline", "read book", "21/12/2012 1900", "0"));
        assertEquals(expected, actual);
    }

    @Test
    public void testStringToTimeNormalCase() throws InvalidArgumentException {
        LocalDateTime expected = LocalDateTime.of(2012,12,21,19,00);
        LocalDateTime actual = Parser.stringToTime("21/12/2012 1900");
        assertEquals(expected, actual);
    }
}
