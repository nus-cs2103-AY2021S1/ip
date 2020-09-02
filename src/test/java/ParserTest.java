import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ParserTest {
    @Test
    public void parseTest() {
        assertArrayEquals(Parser.parse("todo finish cs2103t"), new String[]{"todo", "finish cs2103t"});
    }
}
