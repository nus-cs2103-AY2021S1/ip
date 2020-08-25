package duke.tasklist;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public List<Task> getList() {
        return this.list;
    }

    public Task setDone(int i) {
        this.list.get(i - 1).markAsDone();
        return this.list.get(i - 1);
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public Task delete(int i) {
        return this.list.remove(i - 1);
    }

    public int size() {
        return this.list.size();
    }
 }
