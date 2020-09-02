package duke.utils;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;


/**
 * Maintains the tasks list for the user.
 */
public class TaskList {

    private final List<Task> list;
    private final Ui ui;


    public TaskList(List<Task> list) {
        this.list = list;
        ui = new Ui();
    }


    /**
     * Adds a task to the list.
     *
     * @param task to add to the list.
     */
    public void addTask(Task task) {
        list.add(task);
        ui.displayThis("Got it. I've added this task: \n         " + task
                + "\n    Now you have " + list.size() + " tasks in the list");
    }


    /**
     * Marks the task as done.
     *
     * @param entryDone index of the task to be marked.
     * @return task that is done.
     */
    public Task done(int entryDone) {
        return list.get(entryDone).markAsDone();
    }


    /**
     * Clears the current list by removing every entry inside.
     */
    public void clear() {
        list.clear();
    }


    /**
     * Deletes a task.
     *
     * @param entryDelete index of the task to delete.
     * @return task that is deleted.
     */
    public Task delete(int entryDelete) {
        return list.remove(entryDelete);
    }


    /**
     * Returns the number of elements inside the tasks list.
     *
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return list.size();
    }


    /**
     * Returns true if there is nothing inside the list.
     *
     * @return true if list is null.
     */
    public boolean isNull() {
        return list.size() <= 0;
    }


    /**
     * Returns the tasks lists.
     *
     * @return the Lists which contains the tasks.
     */
    public List<Task> getList() {
        return list;
    }


    /**
     * Retuns a list of tasks which contains the keyword.
     *
     * @param keyword string the user wants to find.
     * @return List of tasks with the keyword.
     */
    public List<Task> tasksContainingKeywords(String keyword) throws DukeException {
        List<Task> containsKeywords = new ArrayList<>();
        for (Task task : list) {
            if (task.getDescription().contains(keyword)) {
                containsKeywords.add(task);
            }
        }

        return containsKeywords;
    }
}
