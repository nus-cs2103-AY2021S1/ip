import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    void parseDone() throws DukeException {
        int result = Parser.parseDone("done 2");
        assertEquals(result, 1);
    }

    @Test
    void parseTodo() throws DukeException {
        Todo result = Parser.parseTodo("eat");
        assertEquals(result.taskName, "eat");
    }
}
