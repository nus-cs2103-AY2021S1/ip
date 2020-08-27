package duke;

import duke.tasks.Task;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    public TaskList setup() {
        TaskList taskList = new TaskList();
        taskList.addTask("Task 1");
        taskList.addTask("Task 2");
        taskList.addTask("Task 3");
        return taskList;
    }

    @Test
    public void addTask_addTodo_todoAdded() {
        TaskList taskList = setup();

        Task added = taskList.addTask("New Todo");
        assert taskList.getList().contains(added);
    }

    @Test
    public void deleteTask_deleteTaskInList_taskDeleted() {
        TaskList taskList = setup(); // 3 Tasks in list

        Task temp = taskList.addTask("Temp Task");
        boolean added = taskList.getList().contains(temp);
        try {
            taskList.deleteTask(4);
        } catch (Exception ex) {}
        assert added && !taskList.getList().contains(temp);
    }
}
