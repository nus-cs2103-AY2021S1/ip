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
        taskList.removeIf(task -> task == taskToRemove);
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
