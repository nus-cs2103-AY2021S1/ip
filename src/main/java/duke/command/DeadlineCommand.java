package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;

/**
 * Represents a command which creates a task with a deadline.
 */
public class DeadlineCommand extends Command {

    private String[] commandDetails;

    /**
     * Creates a new instance of a DeadlineCommand.
     *
     * @param commandDetails String array with task details.
     */
    public DeadlineCommand(String[] commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * Executes the operation for an task with a deadline to be added.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed DeadlineCommand.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String day = commandDetails[1].split(" ", 2)[1];
        Deadline deadline = new Deadline(commandDetails[0], day.trim());
        tasks.getTasks().add(deadline);
        return ui.showTask(deadline, tasks.getTasks().size());
    }

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
