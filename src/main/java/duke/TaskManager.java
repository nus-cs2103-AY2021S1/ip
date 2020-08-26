package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class TaskManager {

    private List<Task> taskStorage;

    public TaskManager() {
        this.taskStorage = new ArrayList<>();
    }

    public TaskManager(List<Task> tasksFromSaveData) {
        this.taskStorage = new ArrayList<>(tasksFromSaveData);
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(this.taskStorage);
    }

    public void storeTask(Task t) {
        this.taskStorage.add(t);
    }

    public Task getTask(int index) {
        return this.taskStorage.get(index);
    }

    public Task removeTask(int index) {
        return this.taskStorage.remove(index);
    }

    public void forEach(Consumer<? super Task> action) {
        this.taskStorage.forEach(action);
    }

    public int size() {
        return this.taskStorage.size();
    }

    public String toString() {
        String printString = "";

        for (int i = 0; i < this.size(); i++) {
            printString += (i + 1) + ". " + this.getTask(i).toString() + "\n";
        }

        return printString;
    }

}
