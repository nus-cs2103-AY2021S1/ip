import java.util.ArrayList;

/**
 * TaskList class.
 * Stores Task objects in an ArrayList.
 * Contains array list
 *
 * @author YanCheng
 */
public class TaskList {
    public ArrayList<Task> list;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds task to the list.
     * Also prints out remaining tasks.
     * @param task Task to be added to task list
     */
    public void add(Task task) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task);
        list.add(task);
        System.out.printf("     Now you have %d tasks in the list.\n", list.size());
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Add method for use during Storage initialisation.
     * No output will be printed out.
     * @param task Task to be added to task list
     */
    // initAdd method for adding task to list during initialisation
    public void initAdd(Task task) {
        list.add(task);
    }

    /**
     * Lists out all task currently in the list.
     */
    public void listOut() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("     %d. %s \n", i + 1, list.get(i));
        }

        System.out.println("    ____________________________________________________________");
        System.out.println();
    }

    /**
     * Deletes the task specified by the number.
     * @param input Number of task
     * @throws DukeException If number is not entered
     */
    public void delete(String input) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException(" ☹ OOPS!!! Please enter delete with a number.");
        } else {
            String[] arr = input.split(" ");
            int index;

            try {
                index = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            if (index > list.size()) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            Task task = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("    ____________________________________________________________");
            System.out.println("     Noted. I've removed this task: ");

            System.out.println("       " + task);
            System.out.printf("     Now you have %d tasks in the list.\n", list.size());
            System.out.println("    ____________________________________________________________");
            System.out.println();
        }
    }

    /**
     * Marks the specified task as completed in the list.
     * @param input Number of task
     * @throws DukeException If number is not entered
     */
    public void done(String input) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException(" ☹ OOPS!!! Please enter done with a number.");
        } else {
            String[] arr = input.split(" ");
            int index;

            try {
                // if string after done cannot be parsed to integer
                index = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            if (index > list.size()) {
                throw new DukeException(" ☹ OOPS!!! Invalid number.");
            }

            Task task = list.get(index - 1);
            task.completed();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done: ");

            System.out.println("       " + task);
            System.out.println("    ____________________________________________________________");
            System.out.println();
        }
    }

    /**
     * Returns the current size of the Task List.
     * @return return size of task list
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the task specified.
     * @param i The number of task
     * @return The task specified
     */
    public Task get(int i) {
        return list.get(i);
    }
}
