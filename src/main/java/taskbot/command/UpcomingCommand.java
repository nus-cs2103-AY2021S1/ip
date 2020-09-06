package taskbot.command;

import taskbot.task.TaskList;

/**
 * Encapsulates a command to get upcoming tasks.
 */
public class UpcomingCommand extends Command {
    // The number of days ahead to look
    private int days;

    /**
     * Creates an upcoming command.
     *
     * @param days Number of days ahead to look.
     */
    public UpcomingCommand(int days) {
        super(false);
        this.days = days;
    }

    /**
     * Gives instructions on how to use the upcoming command.
     * @return A string of instructions to use the command.
     */
    public static String getInstruction() {
        return "Gets upcoming tasks that occur within the given time frame.\n"
                + "Time frame is given in days ahead."
                + "Format: upcoming [days]\n"
                + "days: The number of days ahead to search for a task, up to just before midnight.\n"
                + "Example: upcoming 2 will search for tasks falling 2 days from now, up to 2359.";
    }

    /**
     * @return The days ahead to look.
     */
    public int getDays() {
        return days;
    }

    @Override
    public String execute(TaskList taskList) {
        return taskList.getUpcoming(days);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        // Check if obj is an instance of this class
        if (!(obj instanceof UpcomingCommand)) {
            return false;
        }

        // Compare taskIndex and return accordingly
        return days == ((UpcomingCommand) obj).getDays();
    }
}
