import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test 
    public void testDateParser() {
        assertEquals("2020-08-04", Parser.dateParser("2020/08/04"));
        assertEquals("2020/08/04", Parser.dateParser("2020/08/04"));
    }

    @Test 
    public void testTimeParser() {
        assertEquals("12PM", Parser.timeParser("1200"));   
        assertEquals("12AM", Parser.timeParser("0000"));
    }

    @Test 
    public void testGetFirstWord() {
        assertEquals("This", Parser.getFirstWord("This is me."));
        assertEquals("Chicken", Parser.getFirstWord("Chicken came first.")); 
    }

    @Test
    public void testParse() {
        assertEquals("ADD", String.valueOf(Parser.parse("todo read book")));
        assertEquals("ADD", String.valueOf(Parser.parse("deadline finish iP /by 2020/08/25")));
        assertEquals("ADD", String,valueOf(Parser.parse("event meeting /at 2020/08/31 1400"))); 
        assertEquals("DONE", String.valueOf(Parser.parse("done 1")));
        assertEquals("DELETE", String.valueOf(Parser.parse("delete 2")));
        assertEquals("LIST", String.valueOf(Parser.parse("list"))); 
        assertEquals("SAVE", String.valueOf(Parser.parse("save")));
        assertEquals("ERROR", String.valueOf(Parser.parse("hello")));
    }
}
