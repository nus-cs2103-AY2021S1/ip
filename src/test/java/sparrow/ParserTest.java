package sparrow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    Parser p = new Parser(new TaskList(), new Storage("test"));

    @Test
    public void byeCommand() {
        assertFalse(p.parse("bye"));
    }

    @Test
    public void correctCommand() {
        assertTrue(p.parse("list"));
    }

}
