import java.util.ArrayList;
import java.util.List;

/**
 * Represents the List of all the tasks that Duke
 * is handling this iteration
 */
public class TaskList {

    private List<Task> taskList;

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Returns this Arraylist
     * @return the Arraylist that the class has
     */
    public List<Task> getTasks() {
        return this.taskList;
    }
}
