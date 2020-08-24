package chatterbox.task;

import chatterbox.ChatterboxException;
import chatterbox.Storage;
import chatterbox.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles the modification of and other operations relating to the task list.
 */
public class TaskList {
    private final Storage store;
    private static List<Task> tasks = new ArrayList<>();

    public TaskList(Storage store) {
        this.store = store;
    }

    /**
     * Gets tasks from storage.
     */
    public void loadTasks() {
        try {
            tasks = store.getItems();
        } catch (IOException e) {
            Ui.showErrorMessage("Failed to load the save file, starting afresh.");
        }
    }

    public void findAndShowTasks(String keyword) {
        keyword = keyword.strip();
        if (keyword.equals("")) {
            printAllTasks();
        } else {
            StringBuilder foundTasks = new StringBuilder("\nI've found these matching tasks in your list!\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.inputString.contains(keyword)) {
                    foundTasks.append(i + 1).append(". ").append(t).append("\n");
                }
            }
            Ui.showMessage(foundTasks.toString());
        }
    }

    /**
     * Prints the full task list.
     */
    public void printAllTasks() {
        if (tasks.size() != 0) {
            StringBuilder fullList = new StringBuilder("\n");
            for (int i = 0; i < tasks.size(); i++) {
                fullList.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            Ui.showMessage(fullList.toString());
        } else {
            Ui.showMessage("Your list is currently empty.");
        }
    }

    /**
     * Adds a task to the task list, and updates storage.
     *
     * @throws IOException  If task list fails to save.
     */
    public void addTask(Task t) throws IOException {
        tasks.add(t);
        Ui.showAddTaskMessage(t, tasks.size());
        store.saveItems(tasks);
    }

    /**
     * Sets a task as done, and updates storage.
     *
     * @param taskNo    The index of the task to be set as done.
     * @throws ChatterboxException  If task number if invalid.
     * @throws IOException  If task list fails to save.
     */
    public void setTaskAsDone(int taskNo) throws ChatterboxException, IOException {
        if (taskNo < 0 || taskNo >= tasks.size()) {
            throw new ChatterboxException("Invalid task number.");
        }

        Task t = tasks.get(taskNo);
        t.setDone(true);
        Ui.showMessage("Nice! I've marked this task as done: \n" + t);
        store.saveItems(tasks);
    }

    /**
     * Deletes a task, and updates storage.
     *
     * @param taskNo    The index of the task to be deleted.
     * @throws ChatterboxException  If task number if invalid.
     * @throws IOException  If task list fails to save.
     */
    public void deleteTask(int taskNo) throws ChatterboxException, IOException {
        if (taskNo < 0 || taskNo >= tasks.size()) {
            throw new ChatterboxException("Invalid task number.");
        }

        Task t = tasks.remove(taskNo);
        Ui.showDeleteTaskMessage(t, tasks.size());
        store.saveItems(tasks);
    }
}
