import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;
    public TaskList(ArrayList<Task> list) {
        this.taskList = list;
    }

    public void add(Task task) {
        taskList.add(task);
    }

}
