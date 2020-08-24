package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class TaskList implements Serializable {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.setDone(true);
        return task;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList taskList = (TaskList) o;
        return Objects.equals(tasks, taskList.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks);
    }
}
