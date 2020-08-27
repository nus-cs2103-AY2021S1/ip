package duke.core;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ". " + task.toString());
        }
    }

    public void listSearch(String searchTerm) {
        int count = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(searchTerm)) {
                System.out.println(i + 1 + ". " + task.toString());
            }
        }
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public ArrayList<Task> exportTaskList() {
        return tasks;
    }

    public void loadTasks(ArrayList<Task> savedTasks) {
        if (!savedTasks.isEmpty()) tasks.addAll(savedTasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }
}
