package duke.component;

import java.util.LinkedList;

import duke.task.Task;

/**
 * List that stores the task list.
 * Operations related to a task list has been abstracted in this class.
 */
public class TaskList {
    private final LinkedList<Task> list;

    /**
     * Initializes a task list using the given Task LinkedList.
     */
    public TaskList() {
        this.list = new LinkedList<>();
    }

    /**
     * Initializes an empty task list.
     */
    public TaskList(LinkedList<Task> list) {
        this.list = list;
    }

    /**
     * Get the index of the task whose description matches the given description.
     * @param description   the given description.
     * @return  the index of the corresponding task if it is found,
     *          or -1 if it is not found.
     */
    public int indexOf(String description) {
        return list.stream()
            .filter((task) -> task.getDescription() == description)
            .findFirst()
            .map((task) -> list.indexOf(task))
            .orElse(-1);
    }

    /**
     * Adds the given task to the task list.
     *
     * @param newTask the task to be added.
     */
    public void add(Task newTask) {
        list.add(newTask);
    }

    /**
     * Adds the task to the given index.
     * @param index     where the task will be added.
     * @param newTask   the task to be added.
     */
    public void add(int index, Task newTask) {
        list.add(index, newTask);
    }
    /**
     * Removes the task at the given index.
     *
     * @param index the index of the task to be removed.
     */
    public void remove(int index) {
        assert(index >= 0 && index < list.size());
        list.remove(index);
    }

    /**
     * Removes the task with the given description.
     *
     * @param description the description of the task to be removed.
     * @return true if a task is deleted and false otherwise.
     */
    public boolean remove(String description) {
        int index = indexOf(description);
        if (index < 0) {
            return false;
        }
        assert(index >= 0 && index < list.size());
        list.remove(index);
        return true;
    }

    /**
     * Removes the last added task
     * @return  the (deleted) last added task
     */
    public Task removeLast() {
        return list.removeLast();
    }
    /**
     * Gets the task at the given index.
     *
     * @param index the index given indicating the position of the task.
     * @return the task at the given index.
     */
    public Task get(int index) {
        assert(index >= 0 && index < list.size());
        return list.get(index);
    }

    /**
     * Gets the task list in LinkedList format.
     *
     * @return the task LinkedList.
     */
    public LinkedList<Task> getList() {
        return list;
    }

    /**
     * Marks the task at the given index as done.
     *
     * @param index the index given indicating the position of the task.
     */
    public void markAsDone(int index) {
        assert(index >= 0 && index < list.size());
        list.get(index).markAsDone();
    }
    /**
     * Marks the task at the given index as undone.
     *
     * @param index the index given indicating the position of the task.
     */
    public void markAsUndone(int index) {
        assert(index >= 0 && index < list.size());
        list.get(index).markAsUndone();
    }
    /**
     * Returns the size of the task list.
     *
     * @return the size of the task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns a description of the size of the task list.
     * @return  a description of the size of the task list.
     */
    public String sizeDescription() {
        return "\n    Now you have " + list.size()
            + " task" + (list.size() > 1 ? "s" : "") + " in the list.";
    }

}
