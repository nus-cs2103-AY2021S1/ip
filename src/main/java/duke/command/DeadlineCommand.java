package duke.command;

import duke.main.TaskList;
import duke.task.Deadline;

/**
 * DeadlineCommand is Command to add a Deadline to the related TaskList.
 */
public class DeadlineCommand extends Command {

    /** The Deadline that wants to be added to the TaskList. */
    protected Deadline deadline;

    /**
     * Constructs a DeadlineCommand.
     *
     * @param description The description of the Deadline to be added to the TaskList.
     * @param date The date of the Deadline to be added to the TaskList.
     */
    public DeadlineCommand(String description, String date) {
        this.deadline = new Deadline(description, date);
    }

    /**
     * Adds the Deadline to the related TaskList.
     *
     * @param tasks The related TaskList.
     */
    @Override
    public void perform(TaskList tasks) {
        tasks.add(deadline);
        System.out.println(" Okay! I have added this task:" + "\n" + "   "
                + deadline.toString() + "\n" + " Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks."
                : " task."));
    }

    /**
     * Checks if this is a termination Command.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
