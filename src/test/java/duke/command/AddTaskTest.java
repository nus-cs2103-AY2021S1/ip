package duke.command;

import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTaskTest {

    ArrayList<Task> actualTasks = new ArrayList<>();
    String dummyAddTask = "deadline maplesaga wedding /at 23-12-2021 1400";
    
    @Test
    public void addTaskTest() {
        AddTask addDeadlineCommand = new AddTask(actualTasks);
        addDeadlineCommand.addTask(AddTask.Type.DEADLINE, dummyAddTask.split(" "));
        ArrayList<Task> expected = new ArrayList<>();
        expected.add(new Deadline("maplesaga wedding", "23-12-2021 1400"));
        assertEquals(actualTasks, expected);
    }
    
}