package bob.data.task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import bob.exceptions.BobInvalidDateAndTimeException;
import bob.exceptions.BobInvalidUndoException;
import bob.storage.Storage;


/**
 * Represents the tasks containing tasks in Bob.
 */
public class Tasklist {
    private static final String LINE_BREAK = "\n";

    private final ArrayList<Task> tasks;
    private Tasklist previousTasklist;

    /**
     * Loads a tasklist.
     *
     * @param storage Bob's Storage.
     * @throws FileNotFoundException If File in Storage does not exist.
     * @throws BobInvalidDateAndTimeException If there is an error from converting file data to Task.
     */
    public Tasklist(Storage storage) throws FileNotFoundException, BobInvalidDateAndTimeException {
        this.tasks = storage.getList();
        this.previousTasklist = null;
    }

    /**
     * Initializes a tasklist.
     */
    public Tasklist() {
        tasks = new ArrayList<>();
        this.previousTasklist = null;
    }

    /**
     * Creates a new tasklist with the previous tasklist saved.
     *
     * @param tasks New list of tasks.
     * @param previousTasklist Previous tasklist.
     */
    private Tasklist(ArrayList<Task> tasks, Tasklist previousTasklist) {
        this.tasks = tasks;
        this.previousTasklist = previousTasklist;
    }

    /**
     * Updates Storage file.
     *
     * @param storage Bob's storage.
     * @throws IOException If there is an error rewriting file.
     */
    public void updateData(Storage storage) throws IOException {
        storage.updateFile(tasks);
    }

    /**
     * Add task to tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Gets size of tasks.
     *
     * @return Size of task tasks.
     */
    public int getListSize() {
        return this.tasks.size();
    }

    /**
     * Marks a task in tasks as done.
     *
     * @param taskNo Task number of task to be marked as done.
     * @return Task marked as done.
     */
    public Task markTaskDone(int taskNo) {
        int index = taskNo - 1;
        Task task = this.tasks.get(index).markDone();
        this.tasks.set(index, task);
        return task;
    }

    /**
     * Deletes task in tasks.
     *
     * @param taskNo Task number of task to be deleted.
     * @return Deleted task.
     */
    public Task deleteTask(int taskNo) {
        int index = taskNo - 1;
        Task task = tasks.get(index);
        this.tasks.remove(index);
        return task;
    }

    /**
     * Get number of done tasks in tasks.
     *
     * @return Number of done tasks.
     */
    private int getNumOfDoneTask() {
        int doneTask = 0;
        for (Task task : tasks) {
            if (task.checkIsDone()) {
                doneTask++;
            }
        }
        return doneTask;
    }

    /**
     * Creates a readable String of tasks in tasks.
     *
     * @return String representing tasks in tasks.
     */
    private String convertList() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < getListSize(); i++) {
            int taskNo = i + 1;
            output.append(taskNo).append(". ").append(this.tasks.get(i)).append(LINE_BREAK);
        }
        return output.toString();
    }

    /**
     * Finds tasks containing input.
     *
     * @param input User input.
     * @return String of tasks containing the input.
     */
    public String findTasks(String input) {
        StringBuilder tasksFound = new StringBuilder();
        for (Task task: tasks) {
            boolean containsInput = task.toString().contains(input);
            if (containsInput) {
                tasksFound.append(task.toString()).append(LINE_BREAK);
            }
        }
        return tasksFound.toString();
    }

    public String getUnfinishedTasks() {
        StringBuilder unFinishedTasks = new StringBuilder();
        for (Task task : tasks) {
            boolean isNotDone = !task.checkIsDone();
            if (isNotDone) {
                unFinishedTasks.append(task.toString()).append(LINE_BREAK);
            }
        }
        return unFinishedTasks.toString();
    }

    /**
     * Creates a new tasklist with the previous tasklist saved.
     *
     * @return Tasklist with the previous tasklist saved.
     */
    public Tasklist savePreviousTasklist() {
        ArrayList<Task> newTasks = new ArrayList<>();
        for (Task task: tasks) {
            newTasks.add(task);
        }
        return new Tasklist(newTasks, this);
    }

    public Tasklist getPreviousTasklist() throws BobInvalidUndoException {
        boolean noPreviousTaskist = previousTasklist == null;
        boolean hasPreviousTaskist = !noPreviousTaskist;

        if (noPreviousTaskist) {
            throw new BobInvalidUndoException();
        }

        assert hasPreviousTaskist;
        return this.previousTasklist;
    }

    @Override
    public String toString() {
        return getListSize() == 0
                ? "You currently have no tasks.\n"
                : getNumOfDoneTask() == tasks.size()
                ? "Wow congrats, you finished all your tasks.\n" + convertList()
                : "You have " + (tasks.size() - getNumOfDoneTask()) + " unfinished tasks.\n" + convertList();
    }
}
