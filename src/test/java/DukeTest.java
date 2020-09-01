import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.siawsam.duke.Deadline;
import com.siawsam.duke.DukeException;
import com.siawsam.duke.Event;
import com.siawsam.duke.Response;
import com.siawsam.duke.Task;
import com.siawsam.duke.TaskList;
import com.siawsam.duke.TaskSearcher;
import com.siawsam.duke.Todo;
import com.siawsam.duke.Ui;

public class DukeTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputCaptor = new ByteArrayOutputStream();

    @Test
    public void testWelcomeMessage() {
        assertEquals(
               "Hi I'm Duke, your personal task-tracker bot!\n"
                       + "You can add todos, deadlines, or events to my list.",
               Ui.showWelcomeMessage()
        );
    }

    @Test
    public void testNoExistingSave() {
        assertEquals("You don't have an existing saved task list.",
                     Ui.showNoExistingSave()
        );
    }

    @Test
    public void testTaskListAddItemTodo() throws DukeException {
        TaskList taskList = new TaskList();
        Task actualTask = taskList.addItem("todo hello world");
        Task expectedTask = new Todo("hello world");
        assertEquals(expectedTask.toString(), actualTask.toString());
    }

    @Test
    public void testTaskListAddItemDateline() throws DukeException {
        TaskList taskList = new TaskList();
        Task actualTask = taskList.addItem("deadline return book /by 2020-08-30");
        Task expectedTask = new Deadline("return book", "2020-08-30");
        assertEquals(expectedTask.toString(), actualTask.toString());
    }

    @Test
    public void testTaskListAddItemEvent () throws DukeException {
        TaskList taskList = new TaskList();
        Task actualTask = taskList.addItem("event birthday party /at 2pm Wednesday");
        Task expectedTask = new Event("birthday party", "2pm Wednesday");
        assertEquals(expectedTask.toString(), actualTask.toString());
    }
    
    @Test
    public void testTaskIncludesKeyword() {
        Task task = new Task("Do something nice");
        assertTrue(task.includesKeyword("nice"));
        assertFalse(task.includesKeyword("bad"));
    }
    
    @Test
    public void testTaskListSearch() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addItem("todo borrow book");
        taskList.addItem("todo borrow umbrella");
        
        List<Task> actualResults = taskList.searchByKeyword("borrow");
        
        List<Task> expectedResults = new ArrayList<>();
        expectedResults.add(new Todo("borrow book"));
        expectedResults.add(new Todo("borrow umbrella"));
        
        assertEquals(
                Arrays.toString(actualResults.toArray()),
                Arrays.toString(expectedResults.toArray())
        );
    }
    
    @Test
    public void testTaskSearcher() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addItem("todo borrow book");
        taskList.addItem("todo borrow umbrella");
        
        TaskSearcher searcher = new TaskSearcher(taskList);
        Response searchResponse = searcher.searchAndDisplay("borrow");
        
        String expectedMessage = "Here are the matching tasks in your list:\n"
                + "[T] [✘] borrow book\n"
                + "[T] [✘] borrow umbrella\n";
        assertEquals(expectedMessage, searchResponse.getMessage());
    }
}
