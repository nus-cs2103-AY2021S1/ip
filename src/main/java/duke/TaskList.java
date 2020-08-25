package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int numTasks() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public Task removeTask(int index) {
        return taskList.remove(index);
    }
}
