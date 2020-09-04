import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public void markTaskDone(int index) {
        Task completedTask = taskList.get(index);
        completedTask.markAsDone();
    }

    public void deleteTask(int index) {
        Task toDeleteTask = taskList.get(index);
        taskList.remove(toDeleteTask);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
