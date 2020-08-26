package duke;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseCommands_getCommand_success() {
        AddCommand c = new AddCommand(CommandEnum.TODO, "todo sleep");
        assertEquals(c.getUserInput(), "todo sleep");
    }
}
