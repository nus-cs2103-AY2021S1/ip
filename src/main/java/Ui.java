/**
 * Represents the Ui that interacts with the user.
 */
public class Ui {
    private static final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static final String INDENT = "        ";

    /**
     * Displays a welcome message to the user
     */
    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(INDENT + "What can I do for you delightful human?\n" + DIVIDER);
    }

    /**
     * Provides the caller a String representation of the Goodbye Message.
     * @return String representation of the Goodbye message
     */
    public String showGoodbyeMessage() {
        return INDENT + "Guess its time for us to part ways\n"
                + INDENT + "Thanks for the memories\n" + INDENT + ":`(";
    }

    /**
     * Provides the caller a String representation of the tasks provided
     * @param tasks TaskList containing all the tasks to be shown to the user
     * @return String representation of the tasks provided
     */
    public String showAllTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            return INDENT + "I have no tasks to show you sir";
        }

        String output = INDENT + "Here are your tasks for today:\n";
        for (int i = 0; i < tasks.getSize(); i++) {
            Task curr = tasks.getTask(i);
            output += INDENT + (i + 1) + "." + curr + "\n";
        }

        return output;
    }

    /**
     * Provides the caller a String representation of the task that was marked as done.
     * @param task Task that is to be marked as completed
     * @return String representation of the completed task
     */
    public String showDoneTask(Task task) {
        return INDENT + "Good job! I've marked this task as done:\n"
            + INDENT + "  " + task;
    }

    /**
     * Provides the caller a String representation of the task that has been deleted.
     * @param task Deleted task
     * @param tasks TaskList containing the remaining tasks
     * @return String representation of the task that has been deleted as well as the number of tasks remaining
     */
    public String showDeleteTask(Task task, TaskList tasks) {
        return INDENT + "Of course sir. I have removed this task:\n"
                + INDENT + "  " + task + "\n"
                + INDENT + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Provides the caller a String representation of the task that has been added.
     * @param task Task that has been added
     * @param tasks TaskList containing all the tasks
     * @return String representation of the task that has been added as well as the number of tasks remaining
     */
    public String showAddTask(Task task, TaskList tasks) {
        return INDENT + "Affirmative. I've added this task:\n"
                + INDENT + task + "\n"
                + INDENT + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    /**
     * Provides the caller a String representation of the divider.
     * (Currently not being used)
     * @return String representation of the divider
     */
    public String showDivider() {
        return DIVIDER + "\n";
    }

    public String showError(String message) {
        return INDENT + showDivider() + message + "\n" + showDivider();
    }

    public String showInvalidCommandMessage() {
        return INDENT + "Sorry sir but I don't know what you mean :(";
    }
}
