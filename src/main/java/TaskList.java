import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void add(Task task, Storage storage) throws DukeException {
        this.list.add(task);
        storage.update(task);
    }

    public void delete(int taskNum, Storage storage) throws DukeException {
        this.list.remove(taskNum - 1);
        storage.update(this.list);
    }

    public void markDone(int taskNum, Storage storage) throws DukeException {
        this.list.get(taskNum - 1).markDone();
        storage.update(this.list);
    }

    public Task getTask(int taskNum) {
        return this.list.get(taskNum - 1);
    }

    public int getListLength() {
        return this.list.size();
    }

    @Override
    public String toString() {
        String finalString = "";
        int counter = 0;
        for (Task task : this.list) {
            counter++;
            finalString += "\n" + counter + "." + task.toString();
        }

        return finalString;
    }
}
