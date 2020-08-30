package task;

import java.util.ArrayList;
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
        List<task.Task> taskList = new ArrayList<>();
        this.taskList = taskList;
    }

    /**
     * Constructor to create this object.
     * @param list the list of tasks to be encapsulated.
     */
    public TaskList(List<task.Task> list) {
        this.taskList = list;
    }

    /**
     * Add a task to the list.
     * @param task the task to be added.
     */
    public void addToTaskList(task.Task task) {
        this.taskList.add(task);
        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "     "
                + task
                + "\n"
                + "     Now you have "
                + size()
                + " task(s) in the list.\n"
                + "    ____________________________________________________________\n"
        );
    }

    /**
     * Delete a task from the list.
     * IndexOutOfBoundsException will still be thrown if index exceeds size or is negative.
     * @param index the index of task to be delete.
     */
    public void deleteFromTaskList(int index) {
        task.Task task = getTask(index);
        this.taskList.remove(index);
        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task:\n"
                + "     "
                + task
                + "\n"
                + "     Now you have "
                + size()
                + " task(s) in the list.\n"
                + "    ____________________________________________________________\n"
        );
    }

    /**
     * Gets a task from the list.
     * IndexOutOfBoundsException will still be thrown if index exceeds size or is negative.
     * @param index the index of task to be receive.
     * @return Task at this particular index
     */
    public task.Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Gets the size of the list.
     * @return size of list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Displays the full content of this object.
     */
    public void showList() {
        System.out.print("    ____________________________________________________________\n");
        System.out.print("     Here are the tasks in your list:\n");
        for (int i = 1; i <= this.taskList.size(); i++) {
            System.out.println("     " + i + "." + this.taskList.get(i - 1));
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
