import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    private TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public static TaskList loadTasks(Storage storage) {
        return new TaskList(storage.loadTask());
    }

    public Task updateTaskStatus(int index, boolean status) {
        Task taskToUpdate = tasks.get(index);
        Task updatedTask = taskToUpdate.updateStatus(true);
        tasks.set(index, updatedTask);
        return updatedTask;
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public Task removeTask(int task) {
        return tasks.remove(task);
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }

    public int length() {
        return tasks.size();
    }
}
