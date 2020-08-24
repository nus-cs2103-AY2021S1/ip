package duke;

import commands.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    @Test
    public void parse_AddString_prints() {
        assertEquals(new Parser().parse("todo buy new pants").getClass(), AddCommand.class);
        assertEquals(new Parser().parse("deadline Assignment /by 25/08/2020 09:00").getClass(), AddCommand.class);
        assertEquals(new Parser().parse("event IPPT /at 31/10/2020 10:30").getClass(), AddCommand.class);
    }
}
