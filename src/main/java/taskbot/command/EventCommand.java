package taskbot.command;

import taskbot.exceptions.TaskbotException;
import taskbot.task.TaskList;

/**
 * Encapsulates a command to add an event task.
 */
public class EventCommand extends Command {
    // The task description
    private String task;

    /**
     * Creates a EventCommand.
     *
     * @param task The task description.
     */
    public EventCommand(String task) {
        super(false);
        this.task = task;
    }

    /**
     * Gives instructions on how to use the event command.
     *
     * @return A string of instructions to use the command.
     */
    public static String getInstruction() {
        return "Adds an event task to the tasks list\n"
                + "Format: event [desc] /at [time]\n"
                + "desc: The description of the event task\n"
                + "time: the date and time in the following format: dd-MM-YYYY hhMM\n"
                + "Example: 02-10-2020 1430 will represent 2 Oct 2020 2:30pm";
    }

    /**
     * @return The task description.
     */
    public String getTask() {
        return task;
    }

    @Override
    public String execute(TaskList taskList) throws TaskbotException {
        return taskList.addEventTask(task);
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        // Check if obj is an instance of this class
        if (!(obj instanceof EventCommand)) {
            return false;
        }

        // Compare tasks and return accordingly
        return task.equals(((EventCommand) obj).getTask());
    }
}
