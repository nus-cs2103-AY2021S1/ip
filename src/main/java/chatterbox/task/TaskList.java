package chatterbox.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chatterbox.ChatterboxException;
import chatterbox.Storage;
import chatterbox.ui.Ui;

/**
 * Handles the modification of and other operations relating to the task list.
 */
public class TaskList {
    private static List<Task> tasks = new ArrayList<>();
    private final Storage store;

    public TaskList(Storage store) {
        this.store = store;
    }

    /**
     * Gets tasks from storage.
     */
    public void loadTasks() throws IOException, ChatterboxException {
        tasks = store.getItems();
    }

    /**
     * Find all tasks that match the given keyword and print them.
     *
     * @param keyword   Keyword to match the tasks with.
     */
    public String findTasks(String keyword) {
        keyword = keyword.strip();
        if (keyword.equals("")) {
            return getPrintableTaskList();
        } else {
            StringBuilder foundTasks = new StringBuilder("\nI've found these matching tasks in your list!\n");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                if (t.inputString.contains(keyword)) {
                    foundTasks.append(i + 1).append(". ").append(t).append("\n");
                }
            }
            return foundTasks.toString();
        }
    }

    /**
     * Prints the full task list.
     */
    public String getPrintableTaskList() {
        if (tasks.size() != 0) {
            StringBuilder fullList = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                fullList.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return fullList.toString();
        } else {
            return "Your list is currently empty.";
        }
    }

    /**
     * Adds a task to the task list, and updates storage.
     *
     * @throws IOException  If task list fails to save.
     */
    public String addTask(Task t) throws IOException {
        tasks.add(t);
        store.saveItems(tasks);

        return Ui.getAddTaskMessage(t, tasks.size());
    }

    /**
     * Sets a task as done, and updates storage.
     *
     * @param taskNo    The index of the task to be set as done.
     * @throws ChatterboxException  If task number if invalid.
     * @throws IOException  If task list fails to save.
     */
    public String setTaskAsDone(int taskNo) throws ChatterboxException, IOException {
        if (taskNo < 0 || taskNo >= tasks.size()) {
            throw new ChatterboxException("Invalid task number.");
        }

        Task t = tasks.get(taskNo);
        t.setDone(true);
        store.saveItems(tasks);
        return "Nice! I've marked this task as done: \n" + t;
    }

    /**
     * Deletes a task, and updates storage.
     *
     * @param taskNo    The index of the task to be deleted.
     * @throws ChatterboxException  If task number if invalid.
     * @throws IOException  If task list fails to save.
     */
    public String deleteTask(int taskNo) throws ChatterboxException, IOException {
        if (taskNo < 0 || taskNo >= tasks.size()) {
            throw new ChatterboxException("Invalid task number.");
        }

        Task t = tasks.remove(taskNo);
        store.saveItems(tasks);
        return Ui.getDeleteTaskMessage(t, tasks.size());
    }
}
