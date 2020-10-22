import clippy.command.ExitCommand;
import clippy.command.ListCommand;
import clippy.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parse_byeInput_exitCommand() throws Exception {
        assertEquals(new ExitCommand(), Parser.parse("bye"));
    }
    
    @Test
    public void parse_listInput_listCommand() throws Exception {
        assertEquals(new ListCommand(), Parser.parse("list"));
    }
}
