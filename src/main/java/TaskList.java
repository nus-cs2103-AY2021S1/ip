import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public void addNewTask(Task newTask) {
        this.listOfTasks.add(newTask);
    }

    public int totalNumberOfTasks() {
        return this.listOfTasks.size();
    }

    public Task getTask(int index) {
        return this.listOfTasks.get(index);
    }

    public void deleteTask(int index) {
        this.listOfTasks.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder allTasks = new StringBuilder();

        for (Task task: this.listOfTasks) {
            int listIndex = this.listOfTasks.indexOf(task) + 1;
            allTasks.append(listIndex + "." + task + "\n");
        }

        return allTasks.substring(0, allTasks.length() - 1);
    }
}
