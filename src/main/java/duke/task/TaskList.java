package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
         tasks = new ArrayList<>(100);
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public Task retrieve(int i) {
        return tasks.get(i - 1);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public void remove(int i) {
        tasks.remove(i - 1);
    }
}
