import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.siawsam.duke.Deadline;
import com.siawsam.duke.DukeException;
import com.siawsam.duke.Event;
import com.siawsam.duke.Task;
import com.siawsam.duke.TaskList;
import com.siawsam.duke.Todo;

public class TaskListTest {

    @Test
    public void taskList_todoLiteral_addTodo() throws DukeException {
        TaskList taskList = new TaskList();
        Task actualTask = taskList.addItem("todo hello world");
        Task expectedTask = new Todo("hello world");
        assertEquals(expectedTask.toString(), actualTask.toString());
    }

    @Test
    public void taskList_deadlineLiteral_addDeadline() throws DukeException {
        TaskList taskList = new TaskList();
        Task actualTask = taskList.addItem("deadline return book /by 2020-08-30");
        Task expectedTask = new Deadline("return book", "2020-08-30");
        assertEquals(expectedTask.toString(), actualTask.toString());
    }

    @Test
    public void taskList_eventLiteral_addEvent() throws DukeException {
        TaskList taskList = new TaskList();
        Task actualTask = taskList.addItem("event birthday party /at 2pm Wednesday");
        Task expectedTask = new Event("birthday party", "2pm Wednesday");
        assertEquals(expectedTask.toString(), actualTask.toString());
    }
    
}
