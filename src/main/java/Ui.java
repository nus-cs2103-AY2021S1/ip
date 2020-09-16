/**
 * Handles interaction with users.
 */
public class Ui {
    /**
     * Greets user when Duke bot is activated.
     * 
     * @return Duke response message.
     */
    public String printGreet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello! I'm Duke" + "\nWhat can I do for you?";
    }

    /**
     * Prints response message on user inputs.
     * 
     * @param string Response message by Duke bot.
     */
    public void printLine(String string) {
        System.out.println(string);
    }

    /**
     * Draws border on top and bottom of the response message.
     * 
     * @param string Response message by Duke bot.
     * @return Response message with border.
     */
    public String drawBorder(String string) {
        String line = "__________________________________________________";
        return line + "\n" + string + "\n" + line;
    }

    /**
     * Prints bye message to user.
     * 
     * @return Duke response message for bye.
     */
    public String printBye() {
        return drawBorder("Bye. Hope to see you again soon!");
    }

    /**
     * Prints message when a task is added.
     * 
     * @param task Task that is added.
     * @param lst List of tasks.
     * @return Duke response message when user adds task.
     */
    public String printAddTask(Task task, TaskList lst) {
        return drawBorder("Got it. I've added this task:\n" + "  " 
                + task.toString() + "\n" + "Now you have " 
                + lst.getSize() + " tasks in the list.");
    }

    /**
     * Prints message when a task is marked as done.
     * 
     * @param task Task that is marked as done.
     * @return Duke response message when a task is done.
     */
    public String printDoneTask(Task task) {
        return drawBorder("Nice! I've marked this task as done: \n" 
                + "  " + task.toString());
    }

    /**
     * Prints message when a task is deleted.
     * 
     * @param task Task that needs to be deleted.
     * @param lst List of tasks.
     * @return Duke response message when a task is deleted.
     */
    public String printDeleteTask(Task task, TaskList lst) {
        return drawBorder("Noted. I've removed this task:\n" + "  " 
                + task.toString() + "\n" + "Now you have " 
                + lst.getSize() + " tasks in the list.");
    }

    /**
     * Prints the full list of tasks.
     * 
     * @param lst List of tasks.
     * @return Duke response message of tasks in the list.
     */
    public String printTaskList(TaskList lst) { 
        return drawBorder("Here are the tasks in your list:\n" + lst.toString());
    }

   /**
     * Prints error messages.
    * 
     * @param err Error message.
     * @return Duke response message for an error.
     */
    public String showError(String err) {
        return drawBorder(err);
    }

    /**
     * Prints matching tasks that contains the keyword entered by user.
     * 
     * @param lst List of tasks.
     * @return Duke response message of matching tasks.
     */
    public String printMatchingTasks(TaskList lst) {
        return drawBorder("Here are the matching tasks in your list:\n" + lst.toString());

    }

    /**
     * Prints response message of duplicate error detected.
     *
     * @return Duke response message of duplicate errors.
     */
    public String detectDuplicatesError() {
        return drawBorder("Duke has detected duplicates ._.\n" 
                + "If you still want to add the task, please enter 'yes'!\n" 
                + "If not, please type 'no'");
    }

    /**
     * Prints all the commands.
     *
     * @return Duke response message of all commands available.
     */
    public String printHelp() {
        return drawBorder("Here are some example commands you can try:\n" 
                + "1. list\n" + "2. todo <task>\n" + "3. deadline <task> /by <yyyy-mm-dd>\n" 
                + "4. event <task> /at <yyyy-mm-dd>\n" + "5. done <index>\n"
                + "6. delete <index>\n" + "7. find <index>\n" + "8. bye\n"
                + "9. yes\n" + "10. no\n" + "11. help");

    }

    /**
     * Prints response to not adding duplicate.
     *
     * @return Duke response message of not adding duplicate task.
     */
    public String printHandleDuplicate() {
        return drawBorder("Ok, noted! Task is not added to the list");
    }
    
}
