package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void markDone(int index) {
        tasks.set(index, tasks.get(index).completeTask());
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }

    public void removeAllTasks() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Searches tasks containing the search term.
     * @param searchTerm the term used to search task.
     * @return list of tasks containing the search term.
     */
    public ArrayList<Task> find(String searchTerm) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchTerm)) {
                results.add(task);
            }
        }
        return results;
    }
}
