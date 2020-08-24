import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.Command;
import duke.TaskList;
import duke.TaskType;
import duke.Todo;

public class CommandTest {
    @Test
    public void addToList_simpleTodo_Success() {
        TaskList taskListExpected = new TaskList();
        taskListExpected.add(new Todo("read book"));

        TaskList taskListActual = new TaskList();
        Command command = new Command(TaskType.TODO, "read book");
        command.execute(taskListActual);

        assertEquals(taskListExpected, taskListActual);
    }
}
