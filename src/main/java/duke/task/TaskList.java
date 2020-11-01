package duke.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.util.Pair;

/**
 * Keeps track of the tasks.
 */
public class TaskList {
    private static final String ADD_KEY = "add";
    private static final String REMOVE_KEY = "remove";
    private static final String DONE_KEY = "done";
    private static final String UPDATE_KEY = "update";
    private ArrayList<Task> tasks;
    private ArrayList<Pair<String, Pair<Task, Integer>>> prevCommands; // <command, <task, index>>

    /**
     * Creates a task list with elements.
     * @param tasks Elements of the task list
     */
    public TaskList(Collection<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        prevCommands = new ArrayList<>();
    }

    /**
     * Creates a task list with no elements.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     * @param task The task to be added
     */
    public void add(Task task) {
        this.add(tasks.size(), task);
    }

    /**
     * Adds a task to the specified position in the task list
     * @param index The position where the task will be added
     * @param task The task to be added
     */
    public void add(int index, Task task) {
        assert task != null : "task cannot be null";
        tasks.add(index, task);
        Pair<Task, Integer> infoPair = new Pair<>(null, index);
        prevCommands.add(new Pair<>(ADD_KEY, infoPair));
    }

    public void set(int index, Task task) {
        assert task != null : "task cannot be null";
        Pair<Task, Integer> infoPair = new Pair<>(tasks.get(index), index);
        prevCommands.add(new Pair<>(UPDATE_KEY, infoPair));
        tasks.set(index, task);
    }

    /**
     * Removes the task at the specified position and returns it.
     * @param index The position of the task
     * @return The removed task
     */
    public Task remove(int index) {
        assert index >= 0 && index < tasks.size() : "index out of bound";
        Pair<Task, Integer> infoPair = new Pair<>(tasks.get(index), index);
        prevCommands.add(new Pair<>(REMOVE_KEY, infoPair));
        return tasks.remove(index);
    }

    /**
     * Gets the task at the specified position without removing it.
     * @param index The position of the task
     * @return The task at that position
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "index out of bound";
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified position as done.
     * @param index The position of the task
     */
    public void markAsDone(int index) {
        assert index >= 0 && index < tasks.size() : "index out of bound";
        tasks.set(index, tasks.get(index).markAsDone());
        Pair<Task, Integer> infoPair = new Pair<>(null, index);
        prevCommands.add(new Pair<>(DONE_KEY, infoPair));
    }

    /**
     * Returns the size of the task list.
     * @return The size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Undo the latest change to the Task List
     * @return true if able to undo, false otherwise
     */
    public boolean undo() {
        if (prevCommands.size() == 0) {
            return false;
        }
        Pair<String, Pair<Task, Integer>> prevCommand = prevCommands.remove(prevCommands.size() - 1);
        String key = prevCommand.getKey();
        Pair<Task, Integer> info = prevCommand.getValue();
        if (key.equals(ADD_KEY)) {
            assert info.getValue() != null : "undo error, cannot store index";
            int index = info.getValue();
            tasks.remove(index);
        } else if (key.equals(REMOVE_KEY)) {
            assert info.getKey() != null : "undo error, cannot store task";
            assert info.getValue() != null : "undo error, cannot store index";
            int index = info.getValue();
            Task task = info.getKey();
            tasks.add(index, task);
        } else if (key.equals(DONE_KEY)) {
            assert info.getValue() != null : "undo error, cannot store index";
            int index = info.getValue();
            tasks.set(index, tasks.get(index).markAsUndone());
        } else if (key.equals(UPDATE_KEY)) {
            assert info.getKey() != null : "undo error, cannot store task";
            assert info.getValue() != null : "undo error, cannot store index";
            int index = info.getValue();
            Task task = info.getKey();
            tasks.set(index, task);
        } else {
            System.out.println("error in undoing");
        }
        return true;
    }

    /**
     * Converts the task list to string.
     * @return The string representation of the task list
     */
    @Override
    public String toString() {
        String str = "";
        if (tasks.size() == 0) {
            return str;
        }
        for (int i = 0; i < tasks.size() - 1; i++) {
            str += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        str += tasks.size() + ". " + tasks.get(tasks.size() - 1);
        return str;
    }

    /**
     * Selects tasks that matches the predicate.
     * @param predicate The condition that the selected tasks have to fulfill
     * @return A new <code>TaskList</code> of the selected tasks
     */
    public TaskList filter(Predicate<Task> predicate) {
        assert predicate != null : "predicate cannot be null";
        List<Task> filteredTask = tasks.stream().filter(predicate).collect(Collectors.toList());
        return new TaskList(filteredTask);
    }
}
