import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests methods in Parser.
 */
public class ParserTest {
    @Test
    public void byeTest() throws IOException {
        assertEquals("    Bye-bye, see you next time!", Parser.process("bye"));
    }
}
