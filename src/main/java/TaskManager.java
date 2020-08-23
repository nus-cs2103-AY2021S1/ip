import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // Should i make it immutable?
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    public void markTaskAsDone(int index) {
        taskList.get(index).setTaskAsDone();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public int getTotalNoOfTasks() {
        return this.taskList.size();
    }

    public void removeTask(Task taskToRemove) {
        taskList.removeIf(task -> task == taskToRemove);
    }

    public void removeTask(int taskIndex) {
        taskList.remove(taskIndex);
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < taskList.size(); i++) {
            String index = (i + 1) + ". ";

            // remove the empty line created in the last task
            if (i == taskList.size() - 1) {
                result = result + index + taskList.get(i).toString();
                break;
            }
            result = result + index + taskList.get(i).toString() + "\n" + "      ";
        }
        return result;
    }
}
