package dev.jingyen.duke;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import dev.jingyen.duke.model.Task;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the Task at the given index from the TaskList.
     *
     * @param index the index to remove at
     * @throws IndexOutOfBoundsException if the index is less than zero, or greater than the length of the list - 1.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Give me a valid index >:(");
        }
        tasks.remove(index);
    }

    public void addAllTasks(Collection<Task> newTasks) {
        tasks.addAll(newTasks);
    }

    public int tasksCount() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Give me a valid index >:(");
        }
        return tasks.get(index);
    }

    /**
     * Searches the TaskList for tasks that match a search term
     *
     * @param term the search term
     * @return a List of Tasks that match the search term
     */
    public List<Task> searchTasks(String term) {
        return tasks.stream()
                .filter(t -> t.contains(term))
                .collect(Collectors.toList());
    }
}
