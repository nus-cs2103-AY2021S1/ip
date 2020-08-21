import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public List<Task> getList() {
        return this.taskList;
    }


}
