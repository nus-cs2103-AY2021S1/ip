import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2,2);
    }
    
    @Test
    public void todoExceptionTest() throws Duke.DukeException {
        try {
            Duke.TaskList Tasks = new Duke.TaskList(new ArrayList<>());
            Tasks.taskTodo("todo");
        } catch (Duke.DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.msg);
        }
    }
    
    @Test
    public void deadlineExceptionTest() throws Duke.DukeException {
        try {
            Duke.TaskList Tasks = new Duke.TaskList(new ArrayList<>());
            Tasks.taskDeadline("deadline");
        } catch (Duke.DukeException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.", e.msg);
        }
    }
    
    @Test
    public void parserExceptionTest() throws Duke.DukeException {
        try { 
            Duke.Parser testParser = new Duke.Parser("blah", new Duke.TaskList());
            testParser.parse();
        } catch (Duke.DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.msg);
        }
    }
    
    
    
    
}

