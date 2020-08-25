import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


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
}
