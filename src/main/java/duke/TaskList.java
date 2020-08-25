package duke;

import java.util.Iterator;
import java.util.List;
import duke.task.Task;


/**
 * The TaskList class contains the task list and operations on the list.
 *
 * @author  Yen Pin Hsuan
 * @version 1.0
 */
public class TaskList {
    private List<Task> list;

    /**
     * Create and initialise a task list with the given list.
     * @param list List of tasks to be added.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /** Print out all the tasks in the task list. */
    public void list() {
        Iterator<Task> iterator = list.iterator();
        int count = 0;
        System.out.println(Ui.LINE + "    Here are the tasks in your list:");
        while (iterator.hasNext()) {
            count++;
            System.out.println("    " + count + ". " + iterator.next().toString());
        }
        System.out.println(Ui.LINE);
    }

    /**
     * Return the size of the list.
     * @return Size of list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Return the task at the specified position.
     * @param taskNumber The position of the task in the list.
     * @return The task at that position.
     */
    public Task getTask(int taskNumber) {
        return list.get(taskNumber - 1);
    }

    /**
     * Add the task to the list.
     * @param task Task to be added to list.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Remove the task with the specified position in the list and return
     * the task that is removed.
     * @param taskNumber The position of the task to be deleted.
     * @return The task that is deleted.
     */
    public Task removeTask(int taskNumber) {
        return list.remove(taskNumber - 1);
    }
}
