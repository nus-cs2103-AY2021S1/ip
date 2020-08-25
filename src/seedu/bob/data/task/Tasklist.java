package seedu.bob.data.task;

import seedu.bob.exceptions.BobInvalidDateAndTimeException;
import java.io.FileNotFoundException;
import java.io.IOException;

import seedu.bob.storage.Storage;

import java.util.ArrayList;

/**
 * Represents the list containing tasks in Bob.
 */
public class Tasklist {
    private final ArrayList<Task> list;

    /**
     * Creates a tasklist.
     *
     * @param storage Bob's Storage.
     * @throws FileNotFoundException If File in Storage does not exist.
     * @throws BobInvalidDateAndTimeException If there is an error from converting file data to Task.
     */
    public Tasklist(Storage storage) throws FileNotFoundException, BobInvalidDateAndTimeException {
        this.list = storage.getList();
    }

    public Tasklist() {
        list = new ArrayList<>();
    }

    /**
     * Updates Storage file.
     *
     * @param storage Bob's storage.
     * @throws IOException If there is an error rewriting file.
     */
    public void updateData(Storage storage) throws IOException {
        storage.updateFile(list);
    }

    /**
     * Add task to list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Gets size of list.
     *
     * @return Size of task list.
     */
    public int getListSize() {
        return this.list.size();
    }

    /**
     * Marks a task in list as done.
     *
     * @param taskNo Task number of task to be marked as done.
     * @return Task marked as done.
     */
    public Task markTaskDone(int taskNo) {
        int index = taskNo - 1;
        Task task = this.list.get(index).markDone();
        this.list.set(index, task);
        return task;
    }

    /**
     * Deletes task in list.
     *
     * @param taskNo Task number of task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int taskNo) {
        int index = taskNo - 1;
        Task task = list.get(index);
        this.list.remove(index);
        return task;
    }

    /**
     * Get number of done tasks in list.
     *
     * @return Number of done tasks.
     */
    private int getNumOfDoneTask() {
        int doneTask = 0;
        for (Task task : list) {
            if (task.checkIsDone()) {
                doneTask++;
            }
        }
        return doneTask;
    }

    /**
     * Creates a readable String of tasks in list.
     *
     * @return String representing tasks in list.
     */
    private String convertList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getListSize(); i++) {
            int taskNo = i + 1;
            output.append(taskNo).append(". ").append(this.list.get(i)).append("\n");
        }
        return output.toString();
    }

    public String findTasks(String input) {
        StringBuilder tasksFound = new StringBuilder();
        for (Task task: list) {
            if (task.toString().contains(input)) {
                tasksFound.append(task.toString()).append("\n");
            }
        }
        return tasksFound.toString();
    }

    @Override
    public String toString() {
        return getListSize() == 0
                ? "You currently have no tasks.\n"
                : getNumOfDoneTask() == list.size()
                ? "Wow congrats, you finished all your tasks.\n" + convertList()
                : "You have " + (list.size() - getNumOfDoneTask()) + " unfinished tasks.\n" + convertList();
    }
}
