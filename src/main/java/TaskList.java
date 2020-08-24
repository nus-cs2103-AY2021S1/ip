import java.util.ArrayList;
import java.util.List;

public class TaskList {

    protected List<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public Task deleteTask(int index) {
        return listOfTasks.remove(index - 1);
    }



}
