package duke;

import duke.tasks.Task;
import duke.util.TaskList;
import org.junit.jupiter.api.Test;

public class TaskListTest {

    public TaskList setup() {
        TaskList taskList = new TaskList();
        taskList.addTodo("Task 1");
        taskList.addTodo("Task 2");
        taskList.addTodo("Task 3");
        return taskList;
    }

    @Test
    public void addTask_addTodo_todoAdded() {
        TaskList taskList = setup();

        Task added = taskList.addTodo("New Todo");
        assert taskList.getList().contains(added);
    }

    @Test
    public void deleteTask_deleteTaskInList_taskDeleted() {
        TaskList taskList = setup(); // 3 Tasks in list

        Task temp = taskList.addTodo("Temp Task");
        boolean added = taskList.getList().contains(temp);
        try {
            taskList.deleteTask(4);
        } catch (Exception ex) {}
        assert added && !taskList.getList().contains(temp);
    }
}
