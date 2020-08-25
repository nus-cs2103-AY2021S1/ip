package duke.task;

import duke.task.Task;

import java.util.List;
import java.util.ArrayList;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int id) {
        return this.tasks.get(id - 1);
    }

    public boolean addTask(Task task) {
        return this.tasks.add(task);
    }

    public Task deleteTask(int id) {
        return this.tasks.remove(id -1);
    }

    public int size() {
        return this.tasks.size();
    }

    public TaskList findTask(String keyword) {
        TaskList filtered = new TaskList();
        this.tasks.forEach((task) -> {
            if (task.containsKeyword(keyword)) {
                filtered.addTask(task);
            }
        });
        return filtered;
    }
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < this.tasks.size(); i++) {
            out.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }

        return out.toString();
    }
}
