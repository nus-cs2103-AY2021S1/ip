import java.util.ArrayList;

public class TaskManager {

    private final ArrayList<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public Task getTask(int taskNum) {
        return taskList.get(taskNum);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void setTaskDone(int taskNum) {
        taskList.get(taskNum - 1).setDone();
    }

    public int getNumTasks() {
        return taskList.size();
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            result = result + (i+1) + ". " + taskList.get(i) + "\n";
        }
        return result;
    }
}
