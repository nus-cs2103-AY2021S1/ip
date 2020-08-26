package duke.command;

import duke.command.ManageTask;
import duke.task.Deadline;
import duke.task.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ManageTaskTest {

    ArrayList<Task> actualTasks = new ArrayList<>();
    Task dummyDeadline = new Deadline("maplesaga wedding", "23-12-2021 1400");
    String dummyTaskNumber = "1";

    @Test
    public void manageTaskTest() {
        ManageTask doneTaskCommand = new ManageTask(actualTasks);
        doneTaskCommand.manageTask(ManageTask.Action.DONE, dummyTaskNumber);
        
        ArrayList<Task> expected = new ArrayList<>();
        Task expectedDeadline = new Deadline("maplesaga wedding", "23-12-2021 1400");
        expectedDeadline.markDone();
        expected.add(expectedDeadline);
        assertEquals(actualTasks, expected);
    }

    @BeforeEach
    public void init() {
        actualTasks.add(dummyDeadline);
    }

    @AfterEach
    public void cleanUp() {
        actualTasks = new ArrayList<>();
    }


}        
