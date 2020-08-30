/**
 * Represents the Ui control object.
 */
public class Ui {
    private static final String BORDER = "____________________________________________________________\n";

    /**
     * Prints the exit line of the bot.
     */
    public String exitLine() {
        return BORDER + "Bye. Hope to see you again soon!\n" + BORDER;
    }

    /**
     * Prints the Welcome message depending on the save state.
     * @param taskList The handler for task list calls
     * @param storage The handler for storage calls
     */
    public String startUp(TaskList taskList, Storage storage) {
        storage.fileCheck();
        if (!storage.getFile().exists() || storage.getFile().length() == 0) {
           return BORDER + "Hello! I'm Duke\n"
                    + "What can I do for you?\n" + BORDER;
        } else {
            return BORDER + "Well come back!\n" + "You still have "
                    + taskList.getList().size() + " tasks left to clear.\n" + BORDER;
        }
    }

    /**
     * Prints the line for adding tasks.
     * @param toAdd The new task to add to the list
     * @param size  The size of current list
     */
    public String addTaskLine(Task toAdd, int size) {
        return "Got it. I've added this task:\n" + "  " + toAdd + "\n"
                + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Prints the line for removing tasks.
     * @param toRemove The task to remove
     * @param size The size of current list
     */
    public String removeTaskLine(Task toRemove, int size) {
        return "Noted. I've removed this task:\n" + "  " + toRemove + "\n"
                    + "Now you have " + size + " tasks in the list.\n";
    }

    /**
     * Returns the bots Border string.
     * @return The Border string
     */
    public String getBorder() {
        return BORDER;
    }
}