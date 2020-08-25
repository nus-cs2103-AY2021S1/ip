import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;
    private int totalTasks;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() {
        return list;
    }

    public int getTotalTasks() {
        return totalTasks;
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Task get(int x) {
        return list.get(x);
    }

    public void add(Task t) {
        list.add(t);
    }

    public void remove(int x) {
        list.remove(x);
    }

    @Override
    public String toString() {
        return "Now you have " + totalTasks + " tasks in your list.";
    }
}
