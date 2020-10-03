package duke;

import java.util.ArrayList;

/**
 * TaskList class.
 * Stores Task objects in an ArrayList.
 * Contains an array list.
 *
 * @author YanCheng
 */
public class TaskList {
    private static final String INVALID_NUMBER = "OOPS!!! Invalid number.";
    private ArrayList<Task> list;

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
     * @return String that is to be output
     */
    public String add(Task task) {
        list.add(task);
        StringBuilder output = new StringBuilder();
        // output.append("    ____________________________________________________________\n");
        output.append("Got it. I've added this task:\n");
        output.append(String.format("%s\n", task));
        output.append(String.format("Now you have %d tasks in the list.\n", list.size()));
        // output.append("    ____________________________________________________________\n");

        return output.toString();
    }

    /**
     * Adds method for use during Storage initialisation.
     * No output will be printed out.
     * @param task Task to be added to task list
     */
    // initAdd method for adding task to list during initialisation
    public void initAdd(Task task) {
        list.add(task);
    }

    /**
     * Lists out all task currently in the list.
     * @return String that is to be output
     */
    public String listOut() {
        StringBuilder output = new StringBuilder();
        // output.append("    ____________________________________________________________\n");
        output.append("Here are the tasks in your list:\n");


        for (int i = 0; i < list.size(); i++) {
            output.append(String.format("%d. %s \n", i + 1, list.get(i)));

        }

        // output.append("    ____________________________________________________________\n");
        return output.toString();
    }

    /**
     * Deletes the task specified by the number.
     * @param input Number of task
     * @return String that is to be output
     * @throws DukeException If number is not entered
     */
    public String delete(String input) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException("OOPS!!! Please enter delete with a number.");
        } else {
            String[] arr = input.split(" ");
            int index;

            try {
                index = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(INVALID_NUMBER);
            }

            if (index > list.size() || index < 0) {
                throw new DukeException(INVALID_NUMBER);
            }

            Task task = list.get(index - 1);
            list.remove(index - 1);

            StringBuilder output = new StringBuilder();
            // output.append("    ____________________________________________________________\n");
            output.append("Noted. I've removed this task:\n");
            output.append(String.format("%s\n", task));
            output.append(String.format("Now you have %d tasks in the list.\n", list.size()));
            // output.append("    ____________________________________________________________\n");
            return output.toString();
        }
    }

    /**
     * Marks the specified task as completed in the list.
     * @param input Number of task
     * @return String that is to be output
     * @throws DukeException If number is not entered
     */
    public String done(String input) throws DukeException {
        if (!input.contains(" ")) {
            throw new DukeException("OOPS!!! Please enter done with a number.");
        } else {
            String[] arr = input.split(" ");
            int index;

            try {
                // if string after done cannot be parsed to integer
                index = Integer.parseInt(arr[1]);
            } catch (NumberFormatException e) {
                throw new DukeException(INVALID_NUMBER);
            }

            if (index > list.size() || index < 0) {
                throw new DukeException(INVALID_NUMBER);
            }

            Task task = list.get(index - 1);
            task.completed();
            StringBuilder output = new StringBuilder();
            // output.append("    ____________________________________________________________\n");
            output.append("Nice! I've marked this task as done:\n");
            output.append(String.format("%s\n", task));
            // output.append("    ____________________________________________________________\n");
            return output.toString();
        }
    }

    /**
     * Returns task that contains the keyword
     * @param input Keyword
     * @return String to be output
     */
    public String find(String input) {
        String[] arr = input.split(" ");
        String keyWord = arr[1];

        ArrayList<Task> hits = new ArrayList<>();

        for (Task task: list) {
            if (task.getInfo().contains(keyWord)) {
                hits.add(task);
            }
        }

        StringBuilder output = new StringBuilder();
        // output.append("    ____________________________________________________________\n");
        output.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < hits.size(); i++) {
            output.append(String.format("%d. %s \n", i + 1, hits.get(i)));
        }
        // output.append("    ____________________________________________________________\n");
        return output.toString();
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
