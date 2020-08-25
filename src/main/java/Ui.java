/**
 * Handles interaction with users.
 */
public class Ui {
    /**
     * Greets user when Duke bot is activated.
     */
    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printLine(logo + "\nHello! I'm Duke" + "\nWhat can I do for you?");
    }

    /**
     * Prints response message on user inputs.
     * @param string Response message by Duke bot.
     */
    public void printLine(String string) {
        System.out.println(string);
    }

    /**
     * Draws border on top and bottom of the response message.
     * @param string Response message by Duke bot.
     * @return Response message with border.
     */
    public String drawBorder(String string) {
        String line = "_________________________________________________________________";
        return line + "\n" + string + "\n" + line;
    }

    /**
     * Prints bye message to user.
     */
    public void bye() {
        printLine(drawBorder("Bye. Hope to see you again soon!"));
    }

    /**
     * Prints message when a task is added.
     * @param task Task that is added.
     * @param lst List of tasks.
     */
    public void printAddTask(Task task, TaskList lst) {
        printLine(drawBorder("Got it. I've added this task:\n" + "  " + task.toString() + "\n" +
                "Now you have " + lst.getSize() + " tasks in the list."));
    }

    /**
     * Prints message when a task is marked as done.
     * @param task Task that is marked as done.
     */
    public void printDoneTask(Task task) {
        printLine(drawBorder("Nice! I've marked this task as done: \n" + "  " +
                task.toString()));
    }

    /**
     * Prints message when a task is deleted.
     * @param task Task that needs to be deleted.
     * @param lst List of tasks.
     */
    public void printDeleteTask(Task task, TaskList lst) {
        printLine(drawBorder("Noted. I've removed this task:\n" + "  " +
                task.toString() + "\n" +
                "Now you have " + lst.getSize() + " tasks in the list."));
    }

    /**
     * Prints the full list of tasks.
     * @param lst List of tasks.
     */
    public void printTaskList(TaskList lst) {
        printLine(drawBorder( "Here are the tasks in your list:\n" + lst.toString()));
    }

    /**
     * Prints error messages.
     * @param err Error message.
     */
    public void showError(String err) {
        printLine(drawBorder(err));
    }
}
