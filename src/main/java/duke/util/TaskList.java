package duke.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import duke.task.Task;

/**
 * An aggregation of tasks.
 * Functions largely similar to java.util.List.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Returns the task at the provided index.
     * Note that index starts at 1.
     *
     * @param index index of the task to be returned from the list.
     * @return task at the index provided.
     */
    @Override
    public Task get(int index) {
        return super.get(index - 1);
    }

    /**
     * Removes and returns the task at the provided index.
     * Note that index starts at 1.
     *
     * @param index index of the task to be removed from the list.
     * @return task removed.
     */
    @Override
    public Task remove(int index) {
        return super.remove(index - 1);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (Task task : this) {
            if (count != 1) {
                builder.append('\n');
            }
            builder.append(count++ + ". " + task.toString());
        }
        return builder.toString();
    }

    /**
     * Returns a customised string message for the number of tasks in the list.
     *
     * @return string informing the user about the number of tasks in the list.
     */
    public String createTaskNumberCountMessage() {
        return "Now you have " + size() + " tasks in the list.";
    }

}
