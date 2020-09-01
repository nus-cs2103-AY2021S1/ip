/**
 * Handles interaction with users.
 */
public class Ui {
    /**
     * Greets user when Duke bot is activated.
     */
    public String greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello! I'm Duke" + "\nWhat can I do for you?";
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
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints message when a task is added.
     * @param task Task that is added.
     * @param lst List of tasks.
     */
    public String printAddTask(Task task, TaskList lst) {
        return "Got it. I've added this task:\n" + "  " 
                + task.toString() + "\n" + "Now you have " 
                + lst.getSize() + " tasks in the list.";
    }

    /**
     * Prints message when a task is marked as done.
     * @param task Task that is marked as done.
     */
    public String printDoneTask(Task task) {
        return "Nice! I've marked this task as done: \n" 
                + "  " + task.toString();
    }

    /**
     * Prints message when a task is deleted.
     * @param task Task that needs to be deleted.
     * @param lst List of tasks.
     */
    public String printDeleteTask(Task task, TaskList lst) {
        return "Noted. I've removed this task:\n" + "  " 
                + task.toString() + "\n" + "Now you have " 
                + lst.getSize() + " tasks in the list.";
    }

    /**
     * Prints the full list of tasks.
     * @param lst List of tasks.
     */
    public String printTaskList(TaskList lst) { 
        return "Here are the tasks in your list:\n" + lst.toString();
    }

   /**
     * Prints error messages.
     * @param err Error message.
     */
    public String showError(String err) {
        return err;
    }

    /**
     * Prints matching tasks that contains the keyword entered by user.
     * @param lst List of tasks.
     */
    public String printMatchingTasks(TaskList lst) {
        return "Here are the matching tasks in your list:\n" + lst.toString();

    }
}
