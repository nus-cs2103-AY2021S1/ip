import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    protected void add(Task task) {
        this.list.add(task);
    }

    protected int length() {
        return this.list.size();
    }

    protected Task get(int index) {
        return this.list.get(index);
    }

    protected int getIndex(Task task) {
        return this.list.indexOf(task);
    }

    protected boolean isEmpty() {
        return this.list.isEmpty();
    }

    protected void remove(Task task) {
        this.list.remove(task);
    }

    protected ArrayList<Task> get() {
        return this.list;
    }




}
