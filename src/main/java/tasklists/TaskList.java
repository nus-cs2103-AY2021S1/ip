package tasklists;

import constants.DukeConstants;
import tasks.Task;

import java.util.List;

public class TaskList {

    private final List<Task> list;

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public Task delete(int idx) {
        return this.list.remove(idx);
    }

    public void add(Task task) {
        this.list.add(task);
    }

    public void print() {
        int num = 0;
        for (Task output : this.list) {
            num++;
            System.out.printf("%s%d.%s\n", DukeConstants.IDENT, num, output);
        }
    }

    public int getSize() {
        return this.list.size();
    }

    public Task markDone(int idx) {
        Task task = this.list.get(idx);
        task.markDone();
        return task;
    }

    public List<Task> getList() {
        return list;
    }
}
