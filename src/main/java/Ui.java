/**
 * Represents the Ui control object.
 */
public class Ui {
    /**
     * Prints the exit line of the bot.
     * @return String for exit line
     */
    public String exitLine() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Returns String for Welcome message depending on the save state.
     * @param taskList The handler for task list calls
     * @param storage The handler for storage calls
     * @return String for welcome message
     */
    public String startUp(TaskList taskList, Storage storage) {
        storage.createOrLoadFile();
        if (!storage.getFile().exists() || storage.getFile().length() == 0) {
            return "Hello! I'm Duke\n"
                    + "What can I do for you?\n";
        } else {
            return "Well come back!\n" + "You still have "
                    + taskList.getList().size() + " tasks left to clear.\n";
        }
    }

    /**
     * Returns String for adding tasks.
     * @param toAdd The new task to add to the list
     * @param size  The size of current list
     * @return String for adding tasks
     */
    public String addTaskLine(Task toAdd, int size) {
        return "Got it. I've added this task:\n" + "  " + toAdd + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns string for removing a task.
     * @param toRemove The task to remove
     * @param size The size of current list
     * @return String for removing tasks
     */
    public String removeTaskLine(Task toRemove, int size) {
        return "Noted. I've removed this task:\n" + "  " + toRemove + "\n"
                    + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns String for help command.
     * @return String for help command
     */
    public String helpLine() {
        return "Below are the list of commands:\n"
                + "list\n"
                + "find [keyword]\n"
                + "todo [taskname]\n"
                + "deadline [taskname] /by [yyyy-MM-dd HHmm]\n"
                + "event [taskname] /at [yyy-MM-dd HHmm]\n"
                + "delete [index or 'all']\n"
                + "update [index] ['name' or 'time'] [input]\n"
                + "bye\n";
    }

    /**
     * Returns String for unknown input.
     * @return String for unknown input
     */
    public String unknownInputLine() {
        return "OOPS!!! I'm sorry, but I don't know what that means :(\n"
                + "Type 'help' for list of commands.";
    }

    /**
     * Returns String for updated Task.
     * @param oldTask Previous task
     * @param updatedTask Updated task
     * @return String showing task has been updated
     */
    public String updateTaskLine(Task oldTask, Task updatedTask) {
        return "The selected Task has been updated:\n"
                + "Prev: " + oldTask
                + "\nUpdated: " + updatedTask;
    }
}
