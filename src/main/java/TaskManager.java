import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
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

    public ArrayList<Task> findTaskThatHasKeyword(String keyWord) {
        ArrayList<Task> res = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.getTaskDescription().contains(keyWord)) {
                res.add(task);
            }
        }
        return res;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public String convertTaskListToString(ArrayList<Task> givenTaskList) {
        String result = "";
        for (int i = 0; i < givenTaskList.size(); i++) {
            String index = (i + 1) + ". ";

            // remove the empty line created in the last task
            if (i == givenTaskList.size() - 1) {
                result = result + index + givenTaskList.get(i).toString();
                break;
            }
            result = result + index + givenTaskList.get(i).toString() + "\n" + "      ";
        }
        return result;
    }
}
