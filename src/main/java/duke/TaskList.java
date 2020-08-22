package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import duke.task.*;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }

    public List<Task> getList() {
        return this.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task completeTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        task.done();
        return task;
    }

    public Task deleteTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        tasks.remove(task);
        return task;
    }

    public String taskSizeMessage() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
