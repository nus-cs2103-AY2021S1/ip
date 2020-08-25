package duke.tasklist;

import duke.task.Task;

import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void delete(int index) {
        tasks.remove(index);
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    public boolean checkIfValid(int digit) {
        return digit <= tasks.size() && digit > 0;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}
