/**
 * Represents the Ui that interacts with the user.
 */
public class Ui {
    private final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final String INDENT = "        ";

    public void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(INDENT + "What can I do for you delightful human?\n" + DIVIDER);
    }

    public String showGoodbyeMessage() {
        return INDENT + "Guess its time for us to part ways\n"
                + INDENT + "Thanks for the memories\n" + INDENT + ":`(";
    }

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

    public String showDoneTask(Task task) {
        return INDENT + "Good job! I've marked this task as done:\n" +
            INDENT + "  " + task;
    }

    public String showDeleteTask(Task task, TaskList tasks) {
        return INDENT + "Of course sir. I have removed this task:\n" +
                INDENT + "  " + task + "\n" +
                INDENT + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    public String showAddTask(Task task, TaskList tasks) {
        return INDENT + "Affirmative. I've added this task:\n" +
                INDENT + task + "\n" +
                INDENT + "Now you have " + tasks.getSize() + " tasks in the list.";
    }

    public String showDivider() {
        return "";
        // return DIVIDER + "\n";
    }

    public String showError(String message) {
        return INDENT + message + "\n" + showDivider();
    }

    public String showInvalidCommandMessage() {
        return INDENT + "Sorry sir but I don't know what you mean :(";
    }
}
