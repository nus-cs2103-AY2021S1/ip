import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> mainList;

    public TaskList(ArrayList<Task> mainList) {
        this.mainList = mainList;
    }

    public TaskList() {
        this.mainList = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.mainList.add(task);
    }

    public void remove(int index) {
        this.mainList.remove(index);
    }

    public Task get(int index) {
        return this.mainList.get(index);
    }

    public int size() {
        return this.mainList.size();
    }
}
