import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    // Should i make it immutable?
    public void AddTask(Task newTask) {
        taskList.add(newTask);
    }

    public void RemoveTask(Task taskToRemove) {
        
    }
}
