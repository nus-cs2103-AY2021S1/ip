package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import commands.AddCommand;
import commands.DeleteCommand;
import commands.DoneCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;

public class ParserTest {

    @Test
    public void parse_exitString_exits() {
        assertEquals(new Parser().parse("exit").getClass(), ExitCommand.class);
    }
    @Test
    public void parse_listString_prints() {
        assertEquals(new Parser().parse("list").getClass(), ListCommand.class);
    }
    @Test
    public void parse_helpString_prints() {
        assertEquals(new Parser().parse("help").getClass(), HelpCommand.class);
    }
    @Test
    public void parse_doneString_prints() {
        assertEquals(new Parser().parse("done 2").getClass(), DoneCommand.class);
    }
    @Test
    public void parse_deleteString_prints() {
        assertEquals(new Parser().parse("delete 2").getClass(), DeleteCommand.class);
    }

    /**
     * Tests for each type of AddCommand that can be entered.
     */
    @Test
    public void parse_addString_prints() {
        assertEquals(new Parser().parse("todo buy new pants").getClass(), AddCommand.class);
        assertEquals(new Parser().parse("deadline Assignment /by 25/08/2020 09:00").getClass(), AddCommand.class);
        assertEquals(new Parser().parse("event IPPT /at 31/10/2020 10:30").getClass(), AddCommand.class);
    }
    @Test
    public void parse_findString_prints() {
        assertEquals(new Parser().parse("find book").getClass(), FindCommand.class);
    }
}
