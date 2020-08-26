import java.util.ArrayList;

public class TaskManager {

    public final ArrayList<Task> taskList;

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

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void removeTask(int taskNum) {
        taskList.remove(taskNum - 1);
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            result = result + (i+1) + ". " + taskList.get(i) + "\n";
        }
        return result;
    }
}
