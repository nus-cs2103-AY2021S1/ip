import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testTimeParser() {
        assertEquals("12PM", duke.Parser.timeParser("1200"));
        assertEquals("12AM", duke.Parser.timeParser("0000"));
    }

    @Test
    public void testGetFirstWord() {
        assertEquals("This", duke.Parser.getFirstWord("This is me."));
        assertEquals("Chicken", duke.Parser.getFirstWord("Chicken came first."));
    }

    @Test
    public void testParse() {
        assertEquals("ADD", String.valueOf(duke.Parser.parse("todo read book")));
        assertEquals("ADD", String.valueOf(duke.Parser.parse("deadline finish iP /by 2020/08/25")));
        assertEquals("ADD", String.valueOf(duke.Parser.parse("event meeting /at 2020/08/31 1400")));
        assertEquals("DONE", String.valueOf(duke.Parser.parse("done 1")));
        assertEquals("DELETE", String.valueOf(duke.Parser.parse("delete 2")));
        assertEquals("LIST", String.valueOf(duke.Parser.parse("list")));
        assertEquals("SAVE", String.valueOf(duke.Parser.parse("save")));
        assertEquals("ERROR", String.valueOf(duke.Parser.parse("hello")));
    }
}
