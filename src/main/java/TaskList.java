import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        getTaskList().add(task);
    }

    public void deleteTask(int index) {
        getTaskList().remove(index);
    }

    public Task getTask(int index) {
        return getTaskList().get(index);
    }

    public boolean isTaskPresent(int index) {
        return index <= (totalTask() - 1);
    }

    public int totalTask() {
        return getTaskList().size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}

