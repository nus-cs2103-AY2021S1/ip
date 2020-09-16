package focus.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import focus.command.AddCommand;
import focus.command.DeleteCommand;
import focus.command.DoneCommand;
import focus.command.ExitCommand;
import focus.command.HelpCommand;
import focus.command.ListCommand;
import focus.exception.FocusException;
import focus.task.Deadline;
import focus.task.Event;
import focus.task.ToDo;

public class ParserTest {
    @Test
    public void testParse() {
        try {
            assertEquals(HelpCommand.class, Parser.parse("help").getClass());
            assertEquals(AddCommand.class, Parser.parse("todo iP").getClass());
            assertEquals(AddCommand.class, Parser.parse("deadline tP /by 2020-08-23 23:59").getClass());
            assertEquals(AddCommand.class, Parser.parse("event meeting /at 2020-08-25 14:00").getClass());
            assertEquals(DeleteCommand.class, Parser.parse("delete 1").getClass());
            assertEquals(DoneCommand.class, Parser.parse("done 2").getClass());
            assertEquals(ListCommand.class, Parser.parse("list").getClass());
            assertEquals(ExitCommand.class, Parser.parse("bye").getClass());
            Parser.parse("invalid command");
        } catch (FocusException e) {
            assertEquals("\tERROR: Oops! I'm not sure what you meant!\n"
                    + "\tPlease try again!", e.getMessage());
        }
    }

    @Test
    public void testTextToTask() {
        assertEquals(ToDo.class, Parser.textToTask("T|0|iP").getClass());
        assertEquals(Deadline.class, Parser.textToTask("D|1|tP|2020-08-23T23:59").getClass());
        assertEquals(Event.class, Parser.textToTask("E|0|meeting|2020-08-25T14:00").getClass());
    }
}
