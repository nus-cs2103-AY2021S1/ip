package duke.task;

import duke.exceptions.NoSuchTaskException;

import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public List<Task> getListOfTasks() {
        return List.copyOf(taskList);
    }

    public Task completeTask(int taskNumber) throws NoSuchTaskException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new NoSuchTaskException();
        }
        Task completedTask = taskList.get(taskNumber - 1).markCompleted();
        taskList.set(taskNumber - 1, completedTask);
        return completedTask;
    }

    public Task deleteTask(int taskNumber) throws NoSuchTaskException {
        if (!isValidTaskNumber(taskNumber)) {
            throw new NoSuchTaskException();
        }
        Task toRemove = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        return toRemove;
    }

    private boolean isValidTaskNumber(int taskNumber) {
        return !(taskNumber < 1 || taskNumber > taskList.size());
    }

    public String tasksToString() {
        StringBuilder tasks = new StringBuilder("Here are the tasks in your list: \n");
        for (int i = 0; i < taskList.size(); i++) {
            tasks.append(String.format("%d. %s", i + 1, taskList.get(i)));
            if (i != taskList.size() - 1) {
                tasks.append('\n');
            }
        }
        return tasks.toString();
    }
}
