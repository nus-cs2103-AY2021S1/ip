import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TaskManager {

    private List<Task> storage;

    public TaskManager() {
        this.storage = new ArrayList<>();
    }

    public TaskManager(List<Task> fromSaveData) {
        this.storage = new ArrayList<>(fromSaveData);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(this.storage);
    }

    public void storeTask(Task t) {
        this.storage.add(t);
    }

    public Task getTask(int index) {
        return this.storage.get(index);
    }

    public Task removeTask(int index) {
        return this.storage.remove(index);
    }

    public void forEach(Consumer<? super Task> action) {
        this.storage.forEach(action);
    }

    public int size() {
        return this.storage.size();
    }

    public String toString() {
        String printString = "";
        for (int i = 0; i < this.size(); i++) {
            printString += (i + 1) + ". " + this.getTask(i).toString() + "\n";
        }
        return printString;
    }

}
