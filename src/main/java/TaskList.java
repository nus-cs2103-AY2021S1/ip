import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getList() {
        return taskList;
    }

    public boolean add(Task task) {
        return this.taskList.add(task);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public String toString() {
        return this.taskList.toString();
    }
}