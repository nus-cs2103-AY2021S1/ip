package duke.tasks;

import duke.DukeException;

import java.util.ArrayList;

/**
 * List class to store tasks
 */
public class TaskList {
    private final ArrayList<Task> list;
    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public int size() {
        return list.size();
    }

    public Task get(int index) {
        return list.get(index);
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Marks task as done, then returns it
     *
     * @param index Index of task to be completed
     * @return Completed task
     */
    public Task markDone(int index) {
        Task doneTask = list.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    /**
     * Deletes task, then returns it
     *
     * @param index Index of task to be deleted
     * @return Deleted task
     */
    public Task delete(int index) {
        Task deletedTask = list.get(index);
        list.remove(index);
        return deletedTask;
    }

    /**
     * Edits task, then returns it
     *
     * @param index Index of task to be edited
     * @return Edited task
     */
    public Task edit(int index, String editInput) throws DukeException {
        Task editedTask = list.get(index);
        editedTask.edit(editInput);
        return editedTask;
    }
}
