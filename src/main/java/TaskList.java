import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    protected void markTaskDone(Task completedTask) {
        completedTask.markAsDone();
        int indexOfTask = this.taskList.indexOf(completedTask);
        this.taskList.get(indexOfTask).markAsDone();

    }

    protected void addTask(Task task) {
        this.taskList.add(task);
    }

    protected void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    protected int checkTasksLeft() {
        int index = 0;
        for (Task task: this.taskList) {
            if (!task.getStatus()) {
                index++;
            }
        }
        return index;
    }

    protected ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task: this.taskList) {
            if (task.description.equals(keyword)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
