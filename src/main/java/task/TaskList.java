package task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Encapsulates a list of tasks.
 *
 * <p>The 'TaskList' supports operators, supported include: </p>
 *
 * <p> (i) Adding to list </p>
 *
 * <p> (ii) Deleting from list </p>
 *
 * <p> (iii) Getting task from list </p>
 *
 * <p> (iv) Displaying full content of list </p>
 */
public class TaskList {
    private final List<task.Task> taskList;

    /**
     * Constructor to create this object.
     */
    public TaskList() {
        List<Task> taskList = new ArrayList<>();
        this.taskList = taskList;
    }

    /**
     * Constructor to create this object.
     *
     * @param list the list of tasks to be encapsulated.
     */
    public TaskList(List<Task> list) {
        this.taskList = list;
    }

    /**
     * Add a task to the list.
     *
     * @param task the task to be added.
     * @return string containing information on addition and list size.
     */
    public String addToTaskList(Task task) {
        this.taskList.add(task);
        return "    ____________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "     "
                + task
                + "\n"
                + "     Now you have "
                + size()
                + " task(s) in the list.\n"
                + "    ____________________________________________________\n";
    }

    /**
     * Delete a task from the list.
     * IndexOutOfBoundsException will still be thrown if index exceeds size or is negative.
     *
     * @param index the index of task to be delete.
     * @return string containing information on deletion and list size.
     */
    public String deleteFromTaskList(int index) {
        assert index >= 0 && index < size();

        Task task = getTask(index);
        this.taskList.remove(index);
        return "    ____________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "     "
                + task
                + "\n"
                + "     Now you have "
                + size()
                + " task(s) in the list.\n"
                + "    ____________________________________________________\n";
    }

    /**
     * Gets a task from the list.
     * IndexOutOfBoundsException will still be thrown if index exceeds size or is negative.
     *
     * @param index the index of task to be receive.
     * @return Task at this particular index
     */
    public Task getTask(int index) {
        assert index >= 0 && index <= size();
        return this.taskList.get(index);
    }

    /**
     * Gets the size of the list.
     *
     * @return size of list.
     */
    public int size() {
        int size = this.taskList.size();
        return size;
    }

    /**
     * Displays the full content of this object.
     *
     * @return string representing the display of TaskList contents
     */
    public String showList() {
        StringBuilder listDisplay = new StringBuilder();
        listDisplay.append("    ____________________________________________________\n");
        listDisplay.append("     Here are the tasks in your list:\n");
        for (int i = 1; i <= this.taskList.size(); i++) {
            listDisplay.append("     ");
            listDisplay.append(i);
            listDisplay.append(".");
            listDisplay.append(this.taskList.get(i - 1));
            listDisplay.append("\n");
        }
        listDisplay.append("    ____________________________________________________\n");

        return listDisplay.toString();
    }

    /**
     * Sorts the TaskList by given comparator.
     *
     * @param comparator the comparator used for comparing different task
     */
    public void sort(Comparator<Task> comparator) {
        Collections.sort(this.taskList, comparator);
    }
}
