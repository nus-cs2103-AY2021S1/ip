import java.util.ArrayList;

/**
 * <p>The TaskManagerData class is a data object that stores info about the task list.</p>
 */
public class TaskManagerData {
    private ArrayList<TaskData> taskList;

    public TaskManagerData() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<TaskData> getTaskList() {
        return taskList;
    }
}
