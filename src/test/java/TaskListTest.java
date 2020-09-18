import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

public class TaskListTest {
    @Test
    public void taskListAddTask_addThreeTasks_success() {
        try {
            Event event = new Event("team meeting", "2020-09-01");
            Deadline deadline = new Deadline("training", "2020-09-03");
            ToDo toDo = new ToDo("buy gifts");

            TaskList taskList = new TaskList();
            taskList.addTask(event);
            taskList.addTask(deadline);
            taskList.addTask(toDo);
            ArrayList<Task> tasks = taskList.getTasks();

            ArrayList<Task> expectedTasks = new ArrayList<>();
            expectedTasks.add(new Event("team meeting", "2020-09-01"));
            expectedTasks.add(new Deadline("training", "2020-09-03"));
            expectedTasks.add(new ToDo("buy gifts"));

            for(int i = 0; i < expectedTasks.size(); i++) {
                assertEquals(tasks.get(i).toString(), expectedTasks.get(i).toString());
            }
        } catch (DukeException e) {
            System.out.println("Error occurred while testing");
        }
    }

    @Test
    public void taskListMakeDone_makeToDoDone_success() {
        try {
            Event event = new Event("team meeting", "2020-09-01");
            Deadline deadline = new Deadline("training", "2020-09-03");
            ToDo toDo = new ToDo("buy gifts");

            TaskList taskList = new TaskList();
            taskList.addTask(event);
            taskList.addTask(deadline);
            taskList.addTask(toDo);
            taskList.markDone(2);
            ArrayList<Task> tasks = taskList.getTasks();

            assertEquals(tasks.get(0).toString(), new Event("team meeting", "2020-09-01").toString());
            assertEquals(tasks.get(2).toString(), "[T][✓] buy gifts");
        } catch (DukeException e) {
            System.out.println("Error occurred while testing");
        }
    }
}
