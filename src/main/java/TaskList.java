import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasksList;
    private int totalTasks;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasksList = tasksList;
        this.totalTasks = tasksList.size();
    }

    public TaskList() {
        this.tasksList = new ArrayList<>();
        this.totalTasks = 0;
    }

    public void add(Task task) {
        tasksList.add(task);
        totalTasks++;
    }

    public void delete(int taskNumber) {
        tasksList.remove(taskNumber - 1);
        totalTasks--;
    }

    public void markTaskAsDone(int taskNumber) {
        tasksList.get(taskNumber - 1).markAsDone();
    }

    public Task getTask(int taskNumber) {
        return tasksList.get(taskNumber - 1);
    }

    public int getTotalTasks() {
        return this.totalTasks;
    }

    public ArrayList<Task> getTasksList() {
        return this.tasksList;
    }

    public void listTasks() {
        for (int i = 1; i <= totalTasks; i++) {
            System.out.println(i + "." + tasksList.get(i - 1));
        }
    }

    public void listFilteredTasks(String filter) {
        for (int i = 1; i <= totalTasks; i++) {
            if (tasksList.get(i - 1).description.contains(filter)) {
                System.out.println(i + "." + tasksList.get(i - 1));
            }
        }
    }
}
