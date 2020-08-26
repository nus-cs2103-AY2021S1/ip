package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    /**
     * Filters <code>storage</code> and returns a new <code>TaskManager</code> with the filtered <code>Task</code>s.
     * The returned <code>TaskManager</code> can be mutated with no effect on this object.
     * However, all <code>Task</code>s are still linked and any effects such as <code>Task.doTask()</code> will
     * be lasting.
     *
     * @param predicate Filtering criteria.
     * @return <code>TaskManager</code> containing all filtered out <code>Task</code>s.
     */
    public TaskManager filter(Predicate<? super Task> predicate) {

        return new TaskManager(this.storage
                .stream()
                .filter(predicate)
                .collect(Collectors.toCollection(() -> new ArrayList<Task>())));

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
