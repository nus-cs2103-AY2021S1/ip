import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    public List<Task> getList() {
        return list;
    }

    public void addTask(Task t) {
        list.add(t);
    }

    public void delete(int i) {
        list.remove(i - 1);
    }
}
