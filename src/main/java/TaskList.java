import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index - 1);
    }

    public void markTaskAsDone(int index) throws TaskCompletedException {
        taskList.get(index).markAsDone();
    }

    public Task getSpecificTask(int index) {
        return taskList.get(index);
    }

    public int getTaskListLength() {
        return taskList.size();
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Here are the tasks in your list:\n");
        for (int i =0; i < taskList.size(); i++){
            if (taskList.size() == i + 1) {
                string.append(i + 1).append(". ").append(taskList.get(i).toString());
            } else {
                string.append(i + 1).append(". ").append(taskList.get(i).toString()).append("\n");
            }
        }
        return string.toString();
    }
}
