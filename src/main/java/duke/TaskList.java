package duke;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }
    
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int taskNum) {
        Task deletedTask = this.tasks.get(taskNum - 1);
        this.tasks.remove(taskNum - 1);
        return deletedTask;
    }

    public void markAsDone(int taskNum) {
        this.tasks.get(taskNum - 1).markAsDone(); // calling method in Task class
    }

    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getNumOfTasks() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder orderedList = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            orderedList.append("\n\t" + i + "." + this.tasks.get(i - 1));
        }
        return orderedList.toString();
    }
}
