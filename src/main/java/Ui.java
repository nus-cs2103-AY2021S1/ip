/**
 * Represents the Ui control object.
 */
public class Ui {
    private static final String BORDER = "____________________________________________________________\n";

    /**
     * Prints the exit line of the bot.
     */
    public void exitLine() {
        System.out.println(BORDER + "Bye. Hope to see you again soon!\n" + BORDER);
    }

    /**
     * Prints the Welcome message depending on the save state.
     * @param taskList The handler for task list calls
     * @param storage The handler for storage calls
     */
    public void startUp(TaskList taskList, Storage storage) {
        storage.fileCheck();
        if (!storage.getFile().exists() || storage.getFile().length() == 0) {
            System.out.println(
                BORDER + "Hello! I'm Duke\n"
                    + "What can I do for you?\n" + BORDER
            );
        } else {
            System.out.println(
                BORDER + "Well come back!\n" + "You still have "
                    + taskList.getList().size() + " tasks left to clear.\n" + BORDER
            );
        }
    }

    /**
     * Prints the line for adding tasks.
     * @param toAdd The new task to add to the list
     * @param size  The size of current list
     */
    public void addTaskLine(Task toAdd, int size) {
        System.out.println(
            BORDER + "Got it. I've added this task:\n" + "  " + toAdd + "\n"
                + "Now you have " + size + " tasks in the list.\n" + BORDER
        );
    }

    /**
     * Prints the line for removing tasks.
     * @param toRemove The task to remove
     * @param size The size of current list
     */
    public void removeTaskLine(Task toRemove, int size) {
        System.out.println(
                BORDER + "Noted. I've removed this task:\n" + "  " + toRemove + "\n"
                    + "Now you have " + size + " tasks in the list.\n" + BORDER
        );
    }

    /**
     * Returns the bots Border string.
     * @return The Border string
     */
    public String getBorder() {
        return BORDER;
    }
}