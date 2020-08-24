import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task markDone(int index) {
        Task doneTask = list.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    public Task delete(int index) {
        Task deletedTask = list.get(index);
        list.remove(index);
        return deletedTask;
    }
}
