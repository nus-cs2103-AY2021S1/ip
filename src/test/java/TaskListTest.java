import duke.TaskList;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;


import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the methods and exceptions for the TaskList class.
 */
public class TaskListTest {
    Task TODO = new ToDo("Todo Testing", false);
    Task DEADLINE = new Deadline("Deadline Testing", false,
            LocalDateTime.of(2020, 8, 30, 16, 0 ));
    Task EVENT  = new Deadline("Event Testing", false,
            LocalDateTime.of(2020, 11, 21, 9, 30 ));

    @Test
    public void taskList_getTask(){
        TaskList taskList = new TaskList(new ArrayList<>());

        taskList.addToPlanner(TODO);
        taskList.addToPlanner(DEADLINE);
        taskList.addToPlanner(EVENT);

        assertEquals(taskList.getTask(1), DEADLINE);
    }

    @Test
    public void taskList_addToPlanner(){
        TaskList taskList = new TaskList(new ArrayList<>());

        taskList.addToPlanner(TODO);
        taskList.addToPlanner(DEADLINE);
        taskList.addToPlanner(EVENT);

        assertEquals(taskList.getTask(2), EVENT);
    }

    @Test
    public void taskList_getSize(){
        TaskList taskList = new TaskList(new ArrayList<>());

        taskList.addToPlanner(TODO);
        taskList.addToPlanner(DEADLINE);
        taskList.addToPlanner(EVENT);

        assertEquals(taskList.getSize(), 3);
    }

    @Test
    public void taskList_deleteTask(){
        TaskList taskList = new TaskList(new ArrayList<>());

        taskList.addToPlanner(TODO);
        taskList.addToPlanner(DEADLINE);
        taskList.addToPlanner(EVENT);

        taskList.deleteTask(2);

        assertEquals(taskList.getPlanner().contains(EVENT), false);
    }

    @Test
    public void taskList_markAsDone(){
        TaskList taskList = new TaskList(new ArrayList<>());

        Task DEADLINE_DONE = new Deadline("Deadline Testing", true,
                LocalDateTime.of(2020, 8, 30, 16, 0 ));

        taskList.addToPlanner(TODO);
        taskList.addToPlanner(DEADLINE);
        taskList.addToPlanner(EVENT);

        assertEquals(taskList.markAsDone(1).toString(), DEADLINE_DONE.toString());
    }

    public static void main(String[] args) {

    }
}
