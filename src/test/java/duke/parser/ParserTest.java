package duke.parser;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testParse() {
        assertEquals(HelpCommand.class, Parser.parse("help").getClass());
        assertEquals(AddCommand.class, Parser.parse("todo iP").getClass());
        assertEquals(AddCommand.class, Parser.parse("deadline tP /by 2020-08-23 23:59").getClass());
        assertEquals(AddCommand.class, Parser.parse("event meeting /at 2020-08-25 14:00").getClass());
        assertEquals(DeleteCommand.class, Parser.parse("delete 1").getClass());
        assertEquals(DoneCommand.class, Parser.parse("done 2").getClass());
        assertEquals(ListCommand.class, Parser.parse("list").getClass());
        assertEquals(ExitCommand.class, Parser.parse("bye").getClass());
        assertEquals(InvalidCommand.class, Parser.parse("invalid command").getClass());
    }

    @Test
    public void testTextToTask() {
        assertEquals(ToDo.class, Parser.textToTask("T|0|iP").getClass());
        assertEquals(Deadline.class, Parser.textToTask("D|1|tP|2020-08-23T23:59").getClass());
        assertEquals(Event.class, Parser.textToTask("E|0|meeting|2020-08-25T14:00").getClass());
    }
}
