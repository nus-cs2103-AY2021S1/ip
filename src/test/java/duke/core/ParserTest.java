package duke.core;

import duke.command.DoneCommand;
import duke.command.EmptyCommand;
import duke.command.EndCommand;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    Parser p = new Parser();

    @Test
    public void byeTest() {
        assertTrue(p.parse("bye").getClass() == EndCommand.class);
    }

    @Test
    public void listTest() {
        assertTrue(p.parse("list").getClass() == ListCommand.class);
    }

    @Test
    public void doneTest() {
        assertTrue(p.parse("done 1").getClass() == DoneCommand.class);
    }

    @Test
    public void invalidDoneTest() {
        assertTrue(p.parse("done").getClass() == EmptyCommand.class);
    }
}
