package chatterbox.task;

import chatterbox.ChatterboxException;
import chatterbox.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final Storage store;
    private static List<Task> tasks = new ArrayList<>();

    public TaskList(Storage store) {
        this.store = store;
    }

    public void loadTasks() {
        try {
            tasks = store.getItems();
        } catch (IOException e) {
            System.out.println("Failed to load the save file, starting afresh.");
        }
    }

    public String getAllTasks() {
        if (tasks.size() != 0) {
            StringBuilder fullList = new StringBuilder("\n");
            for (int i = 0; i < tasks.size(); i++) {
                fullList.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return fullList.toString();
        } else {
            return "Your list is currently empty.";
        }
    }

    public void addTask(Task t) throws IOException {
        System.out.println("Got it. I've added this task: \n"
                + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list");
        store.saveItems(tasks);
    }

    public void setTaskAsDone(int taskNo) throws ChatterboxException, IOException {
        if (taskNo < 0 || taskNo >= tasks.size()) throw new ChatterboxException("Invalid task number.");

        Task t = tasks.get(taskNo);
        t.setDone(true);
        System.out.println("Nice! I've marked this task as done: \n" + t);
        store.saveItems(tasks);
    }

    public void deleteTask(int taskNo) throws IOException, ChatterboxException {
        if (taskNo < 0 || taskNo >= tasks.size()) throw new ChatterboxException("Invalid task number.");

        Task t = tasks.remove(taskNo);
        System.out.println("Noted! I've removed this task from your list: \n"
                + t + "\n"
                + " Now you have " + tasks.size() + " tasks in the list.");
        store.saveItems(tasks);
    }
}
