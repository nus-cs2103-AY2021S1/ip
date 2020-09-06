package taskbot.command;

import taskbot.exceptions.TaskbotException;
import taskbot.task.TaskList;

/**
 * Encapsulates a command to add a deadline task.
 */
public class DeadlineCommand extends Command {
    private String task;

    /**
     * Creates a command to make a deadline task.
     * @param task The description of the task.
     */
    public DeadlineCommand(String task) {
        super(false);
        this.task = task;
    }

    /**
     * Gives instructions on how to use the deadline command.
     * @return A string of instructions to use the command.
     */
    public static String getInstruction() {
        return "Adds a deadline task to the tasks list\n"
                + "Format: deadline [desc] /by [time]\n"
                + "desc: The description of the deadline task\n"
                + "time: the date and time in the following format: dd-MM-YYYY hhMM\n"
                + "Example: 02-10-2020 1430 will represent 2 Oct 2020 2:30pm";
    }


    public String getTask() {
        return task;
    }


    @Override
    public String execute(TaskList taskList) throws TaskbotException {
        taskList.addDeadlineTask(task);
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        // Check if obj is compared with itself
        if (obj == this) {
            return true;
        }

        // Check if obj is an instance of this class
        if (!(obj instanceof DeadlineCommand)) {
            return false;
        }

        // Compare tasks and return accordingly
        return task.equals(((DeadlineCommand) obj).getTask());
    }
}
