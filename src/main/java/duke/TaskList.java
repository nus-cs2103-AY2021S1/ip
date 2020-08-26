package duke;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Marks the task at index as done.
     *
     * @param index the index of the task to be marked done
     * @throws IndexOutOfBoundsException if index is incorrect
     */
    public void markDone(int index) throws IndexOutOfBoundsException {
        try {
            get(index).markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No task with this number");
        }
    }

    /**
     * Deletes the task at index.
     *
     * @param index the index of the task to be deleted
     * @throws IndexOutOfBoundsException if index is incorrect
     */
    public void delete(int index) throws IndexOutOfBoundsException {
        try {
            remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("No task with this number");
        }
    }

    /**
     * Returns a sublist with all the tasks that contains the keyword. Containment of keyword is determined by the task.
     *
     * @param s the keyword
     * @return a taskList
     */
    public TaskList find(String s) {
        TaskList newList = new TaskList();
        for (int i = 0; i < size(); i++) {
            if (get(i).contains(s)) {
                newList.add(get(i));
            }
        }
        return newList;
    }
}
