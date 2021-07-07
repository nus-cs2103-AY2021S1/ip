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
    private static final String ERROR_INVALID_TASK_NUMBER = "Invalid task number.";
    private static final String ERROR_ARCHIVE_FAILED = "Unable to archive your tasks, please try again later.";
    private static final String INFO_TASKS_ARCHIVED = "I have archived all your tasks into the archive file %s,"
            + " you can find it in the data folder";
    private static final String INFO_MATCHING_TASKS = "I've found these matching tasks in your list!";
    private static final String INFO_LIST_EMPTY = "Your list is currently empty, no archive file has been created.";

    private static List<Task> tasks = new ArrayList<>();
    private final Storage storage;

    public TaskList(Storage store) {
        this.storage = store;
    }

    /**
     * Gets tasks from storage.
     */
    public void loadTasks() throws IOException, ChatterboxException {
        tasks = storage.getItems();
    }

    /**
     * Archives the task list into a file and starts it from a clean slate.
     *
     * @return Archive task message if successful else archive error message.
     */
    public String archive() {
        // No point archiving if the list is currently empty
        if (tasks.size() == 0) {
            return INFO_LIST_EMPTY;
        }

        try {
            String archiveFilename = storage.archiveItems();
            tasks = new ArrayList<>();
            return String.format(INFO_TASKS_ARCHIVED, archiveFilename);
        } catch (IOException e) {
            return ERROR_ARCHIVE_FAILED;
        }
    }

    /**
     * Finds all tasks that match the given keyword and returns a string containing a list of them.
     *
     * @param keyword Keyword to match the tasks with.
     * @return String containing the list of found tasks.
     */
    public String findTasks(String keyword) {
        keyword = keyword.strip();
        if (keyword.equals("")) {
            return getPrintableTaskList();
        } else {
            StringBuilder foundTasks = new StringBuilder(INFO_MATCHING_TASKS).append("\n");
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
     *
     * @return String containing list of all tasks.
     */
    public String getPrintableTaskList() {
        if (tasks.size() != 0) {
            StringBuilder fullList = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                fullList.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            return fullList.toString();
        } else {
            return INFO_LIST_EMPTY;
        }
    }

    /**
     * Adds a task to the task list, and updates storage.
     *
     * @param t The task to add.
     * @return Add task message.
     * @throws IOException  If task list fails to save.
     */
    public String addTask(Task t) throws IOException {
        tasks.add(t);
        storage.saveItems(tasks);

        return Ui.getAddTaskMessage(t, tasks.size());
    }

    /**
     * Ensures task number is valid and throws a ChatterboxException if it isn't.
     *
     * @param taskNo The task number to check.
     * @throws ChatterboxException If task number is not valid.
     */
    private void checkTaskNumber(int taskNo) throws ChatterboxException {
        if (taskNo < 0 || taskNo >= tasks.size()) {
            throw new ChatterboxException(ERROR_INVALID_TASK_NUMBER);
        }
    }

    /**
     * Sets a task as done, and updates storage.
     *
     * @param taskNo    The index of the task to be set as done.
     * @return Done task message.
     * @throws ChatterboxException  If task number if invalid.
     * @throws IOException  If task list fails to save.
     */
    public String setTaskAsDone(int taskNo) throws ChatterboxException, IOException {
        checkTaskNumber(taskNo);
        Task t = tasks.get(taskNo);
        t.setDone(true);
        storage.saveItems(tasks);
        return Ui.getDoneTaskMessage(t);
    }

    /**
     * Deletes a task, and updates storage.
     *
     * @param taskNo    The index of the task to be deleted.
     * @return Delete task message.
     * @throws ChatterboxException  If task number if invalid.
     * @throws IOException  If task list fails to save.
     */
    public String deleteTask(int taskNo) throws ChatterboxException, IOException {
        checkTaskNumber(taskNo);
        Task t = tasks.remove(taskNo);
        storage.saveItems(tasks);
        return Ui.getDeleteTaskMessage(t, tasks.size());
    }
}
