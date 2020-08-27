package duke.logic;

import duke.CommonString;
import duke.exception.InvalidInstructionException;
import duke.task.DukeTask;

import java.util.ArrayList;

/**
 * Represents the list containing all of user's <code>DukeTask</code>.
 * It contains an <code>ArrayList</code> to track the user's tasks
 * and methods to operate on it.
 */
public class TaskList {
    private final ArrayList<DukeTask> taskList;

    public TaskList(ArrayList<DukeTask> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds <code>DukeTask</code> into the <code>ArrayList</code>.
     *
     * @param dukeTask DukeTask object.
     */
    public void addToList(DukeTask dukeTask) {
        taskList.add(dukeTask);
    }

    /**
     * Deletes <code>DukeTask</code> from the <code>ArrayList</code>, using given index.
     *
     * @param index Integer denoting location of DukeTask object.
     * @return DukeTask object
     */
    public DukeTask deleteFromList(int index) {
        return taskList.remove(index);
    }

    /**
     * Marks <code>DukeTask</code> from the <code>ArrayList</code> as done, using given index.
     *
     * @param index Integer denoting location of DukeTask object.
     */
    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Returns size of the <code>ArrayList</code>.
     *
     * @return size denoted by Integer
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Returns the <code>ArrayList</code>.
     *
     * @return ArrayList of DukeTask
     */
    public ArrayList<DukeTask> getTaskList() {
        return taskList;
    }
}
