package duke.command;

import duke.io.Layout;
import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskTest {

    ArrayList<Task> actualTasks = new ArrayList<>();
    Layout layout = new Layout();
    String dummyAddTask = "deadline maplesaga wedding /at 23-12-2021 1400";
    
    @Test
    public void addTaskTest() {
        AddTask addDeadlineCommand = new AddTask(AddTask.Type.DEADLINE, dummyAddTask.split(" "));
        addDeadlineCommand.execute(actualTasks, layout);
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Deadline("maplesaga wedding", "23-12-2021 1400"));
        assertEquals(actualTasks, expected);
    }
    
}