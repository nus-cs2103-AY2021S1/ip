package dev.jingyen.duke;

import dev.jingyen.duke.model.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public TaskList() {
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
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

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Searches the TaskList for tasks that match a search term
     * @param term the search term
     * @return a List of Tasks that match the search term
     */
    public List<Task> searchTasks(String term) {
        return tasks.stream()
                .filter(t -> t.contains(term))
                .collect(Collectors.toList());
    }
}
