import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;



public class DukeTest {

    @Test
    public void testParserTodo() throws DukeException, IOException, ClassNotFoundException {
        TaskList taskList = new TaskList();
        Parser testParser = new Parser(taskList);
        String actualOutput = testParser.parseStr("todo hello world");
        String expectedOutput = "Got it. I've added this task:\n"
                + "  [T][\u2718] hello world\n"
                + "Now you have 1 tasks in the list.\n";
        assertEquals(expectedOutput, actualOutput);
    }
}
