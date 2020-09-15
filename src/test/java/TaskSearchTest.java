import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.siawsam.duke.DukeException;
import com.siawsam.duke.Response;
import com.siawsam.duke.Task;
import com.siawsam.duke.TaskList;
import com.siawsam.duke.TaskSearcher;
import com.siawsam.duke.Todo;

public class TaskSearchTest {
    @Test
    public void task_searchTermInDescription_true() {
        Task task = new Task("Do something nice");
        assertTrue(task.includesKeyword("nice"));
        assertFalse(task.includesKeyword("bad"));
    }
    
    @Test
    public void taskList_searchString_listOfMatchingTasks() throws DukeException {
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
    public void taskSearcher_searchString_respondWithMatchingTasks() throws DukeException {
        TaskList taskList = new TaskList();
        taskList.addItem("todo borrow book");
        taskList.addItem("todo borrow umbrella");
        
        TaskSearcher searcher = new TaskSearcher(taskList);
        Response searchResponse = searcher.searchAndDisplay("borrow");
        
        String expectedMessage = "Here are the matching tasks in your list:\n"
                                         + "[T] [\u2718] borrow book\n"
                                         + "[T] [\u2718] borrow umbrella\n";
        assertEquals(expectedMessage, searchResponse.getMessage());
    }
}
