import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    Storage storage;
    ArrayList<Task> taskList;

    public TaskList(Storage storage) {
        this.storage = storage;
        taskList = storage.load();
    }

    public void addTask(Task task) {
        taskList.add(task);
        storage.addTask(task);
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void remove(int index) {
        taskList.remove(index);
        storage.reset();
        storage.addAll(taskList);
    }

    public void setCompleted(int whichTask) {
        taskList.get(whichTask).setCompleted();
        storage.reset();
        storage.addAll(taskList);
    }
}
