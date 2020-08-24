import duke.DukeException;
import duke.Parser;
import duke.command.DeleteCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void delete_exceptionThrown() {
        String s1 = "delete";
        try {
            assertEquals("", new Parser().parse(s1));
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a task number to be selected\n",
                    e.getMessage());
        }
        String s2 = "delete a";
        try {
            assertEquals("", new Parser().parse(s2));
            fail();
        } catch (Exception e) {
            assertEquals("Please enter a task number in numeric format\n",
                    e.getMessage());
        }
    }

    @Test
    public void testDelete() {
        try {
            String s = "delete 2";
            DeleteCommand dc = (DeleteCommand) new Parser().parse(s);
            assertEquals(new DeleteCommand(1).getTaskNumber(),
                    dc.getTaskNumber());
        } catch (Exception e) {}
    }

}
