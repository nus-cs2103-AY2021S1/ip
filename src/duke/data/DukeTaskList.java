package duke.data;

import java.util.ArrayList;
import duke.task.Task;

public class DukeTaskList {

    private ArrayList<Task> tasks;

    public DukeTaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task taskDelete = tasks.get(index);
        tasks.remove(index);

        return taskDelete;
    }

    public int getSize() {
        return tasks.size();
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
