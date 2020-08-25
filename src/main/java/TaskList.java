import java.util.ArrayList;
import java.util.List;

public class TaskList {
    public List<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList(){
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = tasks;
    }

    public List<Task> getList() {
        return list;
    }

    /**
     * add a new task.
     * @param t
     */
    public void addTask(Task t) {
        list.add(t);
    }

    /**
     * delete the specified task.
     * @param i
     */
    public void delete(int i) {
        list.remove(i - 1);
    }
}
