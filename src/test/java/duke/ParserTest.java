package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void parseCommands_getCommand_success() {
        AddCommand c = new AddCommand(CommandEnum.TODO, "todo sleep");
        assertEquals(c.getUserInput(), "todo sleep");
    }
}
