import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parse_delete_test() throws DukeException{
        Command command = Parser.parse("delete 2");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    public void parse_unkown_test() throws DukeException{
        assertThrows(DukeException.class, () -> Parser.parse("hi"));
    }
}
