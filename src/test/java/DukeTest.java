import Duke.Deadline;
import Duke.DukeException;
import Duke.Event;
import Duke.Parser;
import Duke.Storage;
import Duke.Task;
import Duke.TaskList;
import Duke.TaskSearcher;
import Duke.Todo;
import Duke.Ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class DukeTest {
    private final PrintStream standardOut = System.out;
    private static final ByteArrayOutputStream outputCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputCaptor));
    }

    @AfterEach
    public void tearDown() {
        // discard all previous contents
        outputCaptor.reset();
        System.setOut(standardOut);
    }

    @Test
    public void testWelcomeMessage() {
        Ui.showWelcomeMessage();
        assertEquals(
               "Hi I'm Duke, your personal task-tracker bot!\n" +
                       "You can add todos, deadlines, or events to my list.\n",
               outputCaptor.toString()
        );
    }

    @Test
    public void testNoExistingSave() {
        Ui.showNoExistingSave();
        assertEquals("You don't have an existing saved task list.\n",
                outputCaptor.toString()
        );
    }

    @Test
    public void testByeCommand() throws IOException {
        String command = "bye\n";
        // store original System.in stream
        InputStream standardIn = System.in;
        ByteArrayInputStream testInputStream = new ByteArrayInputStream(command.getBytes());
        System.setIn(testInputStream);

        Parser parser = new Parser(new Storage("test"));
        parser.scan();
        // restore System.in
        System.setIn(standardIn);
        assertEquals("Bye. Hope to see you again\n", outputCaptor.toString());
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
        searcher.searchAndDisplay("borrow");
        
        String expectedMessage = "Here are the matching tasks in your list:\n"
                + "[T] [✘] borrow book\n"
                + "[T] [✘] borrow umbrella\n";
        assertEquals(expectedMessage, outputCaptor.toString());
    }
}
