import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;



public class DukeTest {

    @Test
    public void testParserTodo() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        Parser testParser = new Parser(taskList);
        String actualOutput = testParser.parseStr("todo hello world");
        String expectedOutput = "Got it. I've added this task:\n"
                + "  [T][\u2718] hello world\n"
                + "Now you have 1 tasks in the list.\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testParserDeadline() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        Parser testParser = new Parser(taskList);
        String actualOutput = testParser.parseStr("deadline throw /by 2019-09-21 1900");
        String expectedOutput = "Got it. I've added this task:\n"
                + "  [D][\u2718] throw (by: 2019-09-21 1900)\n"
                + "Now you have 1 tasks in the list.\n";
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testParserEvent() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        Parser testParser = new Parser(taskList);
        String actualOutput = testParser.parseStr("event party /at 2020-10-21 2000");
        String expectedOutput = "Got it. I've added this task:\n"
                + "  [E][\u2718] party (at: 2020-10-21 2000)\n"
                + "Now you have 1 tasks in the list.\n";
        assertEquals(expectedOutput, actualOutput);
    }
}
