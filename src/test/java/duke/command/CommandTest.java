package duke.command;

import duke.main.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void ByeTest() {
        Assertions.assertEquals(new ByeCommand(), Parser.parse("bye"));
    }

    @Test
    public void DisplayListTest() {
        assertEquals(new DisplayListCommand(), Parser.parse("list"));
    }

    @Test
    public void HelpTest() {
        assertEquals(new HelpCommand(), Parser.parse("help"));
    }
}