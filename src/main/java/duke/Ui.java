package duke;

/**
 * Responsible for customizing the Messages shown to User
 */
public class Ui {

    public Ui() {
    }

    /**
     * Return the input according to default's style.
     * @param input The message to display to user.
     */
    public String show(String input) {
        return input;
    }

    /**
     * Return Welcome Message according to default's style.
     */
    public String showWelcome() {
        return show("Duke at your service. How may I help?");
    }

    /**
     * Return the detail of the new Task and new count of tasks at hand according to default's style.
     */
    public String showTaskAdded(String taskDetail, int taskCount) {
        String first = "Got it. I've added this task:\n";
        String second = "    " + taskDetail + "\n";
        String third = String.format("Now you have %d tasks in the list", taskCount);
        return show(first + second + third);
    }

    /**
     * Return the detail of the task that is just set to done according to default's style.
     */
    public String showTaskDone(String taskDetail) {
        return show("Nice! I have marked this task as done:\n"
                + taskDetail);
    }

    /**
     * Return the tasks that the user has on hand.
     * @param taskDetails String of Task Descriptions that corresponds to the tasks user have on hand.
     */
    public String showTasks(String taskDetails) {
        return show("Here are the tasks in your list\n" + taskDetails);
    }

    /**
     * Return the tasks that the user has just deleted.
     * @param taskDetail String of Task that is just deleted.
     */
    public String showDeletedTasks(String taskDetail) {
        return show("Alright! I have deleted this task:\n" + taskDetail);
    }

    /**
     * String error message with different style.
     * @param err Error Message to print out.
     */
    public String showError(String err) {
        String line = "*******************************************************";
        return String.format("%s\n%s\n%s", line, err, line);
    }

    /**
     * Print out the bye with different style.
     */
    public String showBye() {
        return show("Bye. See you again\n"
                + "Window will close in 3 seconds");
    }
}
